package computerdatabase.AthenasWebAPI

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class BasicSimulation extends Simulation {

  val httpProtocol = http.baseUrl("https://genial-skills-dev-api.citriom.io:8739")
    .header("Accept","application/json")
    .header("content-type","application/json")
    .header("Token","2c28b239-481d-330a-fcf0-b62f55905129")

  val scn = scenario("athenas-web-api") // A scenario is a chain of requests and pauses
    .exec(http("")
      .post("/api/lessons/by-lesson-models/true")
      .body(RawFileBody("./src/test/resources/bodies/GsSubscriptionsWebAPI/ApiSubscriptionsTriesQuiz.json")).asJson)
    .pause(5)

  
      setUp(scn.inject(atOnceUsers(1000)).protocols(httpProtocol))
}
