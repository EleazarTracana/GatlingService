package computerdatabase.StudentsFrontend.HomeWork

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class MainPage extends Simulation {

  val baseDomain = "https://genial-skills-dev-auto-scaling-api.citriom.io:";
  val httpProtocol = http.baseUrl("https://genial-skills-dev-auto-scaling-api.citriom.io");
  val baseUrlTeachersAPI = baseDomain + 8851;
  val headers = Map("Content-Type" -> "application/json",
                    "Accept" -> "application/json",
                    "Token"  -> "abd072c4-071a-0e50-4023-bfe6b7ec9171")

  val scn = scenario("PendingHomeWork") // A scenario is a chain of requests and pauses
    //left page pending assigned homework
    .exec(http("/api/homework/pending/student")
      .post(baseUrlTeachersAPI + "/api/homework/pending/student")
      .body(RawFileBody("./src/test/resources/bodies/GsSubscriptionsWebAPI/ApiSubscriptionsAssignedHomeWork.json")).asJson
      .headers(headers))
    //right page completed homework
    .exec(http("/api/homework/completed/student")
      .post(baseUrlTeachersAPI + "/api/homework/completed/student")
      .body(RawFileBody("./src/test/resources/bodies/GsSubscriptionsWebAPI/ApiSubscriptionsCompletedHomeWork.json")).asJson
      .headers(headers))

      setUp(scn.inject(rampUsers(13000).during(60)).protocols(httpProtocol))
}
