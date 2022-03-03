package computerdatabase.StudentsFrontend.Library

import computerdatabase.Authentication
import io.gatling.core.Predef._
import io.gatling.http.Predef._

class MainPage extends Simulation {

  val baseDomain = "https://dev-api.genialskillsweb.com:";
  val httpProtocol = http.baseUrl("https://dev-api.genialskillsweb.com");
  val baseUrlSubscriptionsAPI = baseDomain + 8852;
  val baseUrlAthenasAPI = baseDomain + 8739;
  val injectUsersCount = Integer.getInteger("users", 1)
  val injectUsersSeconds = java.lang.Long.getLong("ramp", 0)
  val Token = new Authentication().getToken();
  val headers = Map("Content-Type" -> "application/json",
                    "Accept" -> "application/json",
                    "Token"  -> Token)

  val scn = scenario("Libray/MainPage") // A scenario is a chain of requests and pauses
    .exec(http("/api/subscriptions/student/courses")
       .get(baseUrlSubscriptionsAPI + "/api/subscriptions/student/courses")
       .headers(headers))
    .exec(http("/api/additional-material/read/")
      .post(baseUrlAthenasAPI + "/api/additional-material/read/")
      .body(RawFileBody("./src/test/resources/bodies/AthenasWebAPI/ApiAthenasAdditionalMaterial.json")).asJson
      .headers(headers));

      setUp(scn.inject(rampUsers(injectUsersCount).during(injectUsersSeconds)).protocols(httpProtocol))
}
