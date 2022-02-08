package computerdatabase.GSSubscriptionsWebAPI

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class BasicSimulation extends Simulation {

  val httpProtocol = http.baseUrl("https://genial-skills-dev-api.citriom.io:8852")
    .header("Accept","application/json")
    .header("content-type","application/json")
    .header("Token","2c28b239-481d-330a-fcf0-b62f55905129")

  val scn = scenario("gs-subscriptions-web-api") // A scenario is a chain of requests and pauses
    .exec(http("/api/subscriptions/clients")
      .get("/api/subscriptions/clients"))
    .pause(5)

    .exec(http("/api/subscriptions/tries/quiz/{subcriptionId}")
     .get("/api/subscriptions/tries/quiz/1"))
    .pause(5)

    .exec(http("/api/subscriptions/tries/quiz")
    .post("/api/subscriptions/tries/quiz")
      .body(RawFileBody("./src/test/resources/bodies/GsSubscriptionsWebAPI/ApiSubscriptionsTriesQuiz.json")).asJson)
    .pause(5)

    .exec(http("/api/plans/guests")
    .post("/api/plans/guests")
    .body(RawFileBody("./src/test/resources/bodies/GsSubscriptionsWebAPI/ApiSubscriptionsPlanGuest.json")).asJson)
    .pause(5)

    .exec(http("/api/billing-interval/read/")
    .get("/api/billing-interval/read/"))
    .pause(5)

  
      setUp(scn.inject(atOnceUsers(1000)).protocols(httpProtocol))
}
