package computerdatabase.StudentsFrontend.HomeWork

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class MainPage extends Simulation {

  val baseDomain = "https://genial-skills-dev-auto-scaling-api.citriom.io:";
  val httpProtocol = http.baseUrl("https://genial-skills-dev-auto-scaling-api.citriom.io");
  val baseUrlSubscriptionsAPI = baseDomain + 8852;
  val headers = Map("Content-Type" -> "application/json",
                    "Accept" -> "application/json",
                    "Token"  -> "41275ccf-a66b-43b2-f7c3-cae12224d1d7")

  val scn = scenario("PendingHomeWork") // A scenario is a chain of requests and pauses
    //left page pending assigned homework
    .exec(http("/api/homework/pending/student")
      .post(baseUrlSubscriptionsAPI + "/api/homework/pending/student")
      .body(RawFileBody("./src/test/resources/bodies/GsSubscriptionsWebAPI/ApiSubscriptionsAssignedHomeWork.json")).asJson)
    //right page completed homework
    .exec(http("/api/homework/completed/student")
      .post(baseUrlSubscriptionsAPI + "/api/homework/completed/student")
      .body(RawFileBody("./src/test/resources/bodies/GsSubscriptionsWebAPI/ApiSubscriptionsCompletedHomeWork.json")).asJson)

      setUp(scn.inject(rampUsers(1).during(1)).protocols(httpProtocol))
}
