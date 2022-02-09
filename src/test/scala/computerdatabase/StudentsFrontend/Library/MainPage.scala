package computerdatabase.StudentsFrontend.Library

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class MainPage extends Simulation {

  val baseDomain = "https://genial-skills-dev-auto-scaling-api.citriom.io:";
  val httpProtocol = http.baseUrl("https://genial-skills-dev-auto-scaling-api.citriom.io");
  val baseUrlSubscriptionsAPI = baseDomain + 8852;
  val baseUrlAthenasAPI = baseDomain + 8739;
  val headers = Map("Content-Type" -> "application/json",
                    "Accept" -> "application/json",
                    "Token"  -> "5bb648ca-10df-1f55-7f6b-a2f5f74e5efb")

  val scn = scenario("Libray/MainPage") // A scenario is a chain of requests and pauses
    .exec(http("/api/subscriptions/student/courses")
       .get(baseUrlSubscriptionsAPI + "/api/subscriptions/student/courses")
       .headers(headers))
    .exec(http("/api/additional-material/read/")
      .post(baseUrlAthenasAPI + "/api/additional-material/read/")
      .body(RawFileBody("./src/test/resources/bodies/AthenasWebAPI/ApiAthenasAdditionalMaterial.json")).asJson
      .headers(headers));

      setUp(scn.inject(rampUsers(500).during(60)).protocols(httpProtocol))
}
