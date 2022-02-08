package computerdatabase.StudentsFrontend.LoginWithDashboard

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class MainPage extends Simulation {

  val baseDomain = "https://genial-skills-dev-auto-scaling-api.citriom.io:";
  val httpProtocol = http.baseUrl("https://genial-skills-dev-auto-scaling-api.citriom.io");
  val baseUrlUsersAPI = baseDomain + 8853;
  val baseUrlTeachersAPI = baseDomain + 8851;
  val baseUrlSubscriptionsAPI = baseDomain + 8852;
  val headers = Map("Content-Type" -> "application/json",
                    "Accept" -> "application/json",
                    "Token"  -> "41275ccf-a66b-43b2-f7c3-cae12224d1d7")

  val scn = scenario("gs-users-web-api") // A scenario is a chain of requests and pauses
    .exec(http("/api/login/client")
      .post(baseUrlUsersAPI + "/api/login/client")
      .body(RawFileBody("./src/test/resources/bodies/GsUsersWebAPI/ApiLoginClient.json")).asJson)
    .exec(http("/api/homework/pending/student")
      .post(baseUrlTeachersAPI +"/api/homework/pending/student")
      .body(RawFileBody("./src/test/resources/bodies/GsTeachersAPI/ApiTeacherPendingHomework.json")).asJson
      .headers(headers))
    .exec(http("/api/subscriptions/student/courses")
      .get(baseUrlSubscriptionsAPI + "/api/subscriptions/student/courses")
       .headers(headers));

      setUp(scn.inject(rampUsers(1000).during(60)).protocols(httpProtocol))
}