package computerdatabase.StudentsFrontend.HomeWork

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class MainPage extends Simulation {

  val baseDomain = "https://dev-api.genialskillsweb.com:";
  val httpProtocol = http.baseUrl("https://https://dev-api.genialskillsweb.com");
  val baseUrlTeachersAPI = baseDomain + 8851;
  val headers = Map("Content-Type" -> "application/json",
                    "Accept" -> "application/json",
                    "Token"  -> "38ae1e8d-d353-ed6b-edbf-91fe9b7c7715")

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

      setUp(scn.inject(rampUsers(25000).during(120)).protocols(httpProtocol))
}
