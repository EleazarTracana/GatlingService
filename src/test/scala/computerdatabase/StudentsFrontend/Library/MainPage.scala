package computerdatabase.StudentsFrontend.Library

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class MainPage extends Simulation {

  val baseDomain = "https://dev-api.genialskillsweb.com:";
  val httpProtocol = http.baseUrl("https://dev-api.genialskillsweb.com");
  val baseUrlSubscriptionsAPI = baseDomain + 8852;
  val baseUrlAthenasAPI = baseDomain + 8739;
  val headers = Map("Content-Type" -> "application/json",
                    "Accept" -> "application/json",
                    "Token"  -> "38ae1e8d-d353-ed6b-edbf-91fe9b7c7715")

  val scn = scenario("Libray/MainPage") // A scenario is a chain of requests and pauses
    .exec(http("/api/subscriptions/student/courses")
       .get(baseUrlSubscriptionsAPI + "/api/subscriptions/student/courses")
       .headers(headers))
    .exec(http("/api/additional-material/read/")
      .post(baseUrlAthenasAPI + "/api/additional-material/read/")
      .body(RawFileBody("./src/test/resources/bodies/AthenasWebAPI/ApiAthenasAdditionalMaterial.json")).asJson
      .headers(headers));

      setUp(scn.inject(rampUsers(25000).during(120)).protocols(httpProtocol))
}
