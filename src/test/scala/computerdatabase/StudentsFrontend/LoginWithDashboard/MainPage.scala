package computerdatabase.StudentsFrontend.LoginWithDashboard

import computerdatabase.Authentication
import io.gatling.core.Predef._
import io.gatling.http.Predef._

class MainPage extends Simulation {

  val baseDomain = "https://dev-api.genialskillsweb.com:";
  val httpProtocol = http.baseUrl("https://dev-api.genialskillsweb.com");
  val baseUrlUsersAPI = baseDomain + 8853;
  val baseUrlTeachersAPI = baseDomain + 8851;
  val baseUrlSubscriptionsAPI = baseDomain + 8852;
  val injectUsersCount = Integer.getInteger("users", 1)
  val injectUsersSeconds = java.lang.Long.getLong("ramp", 0)
  val Token = new Authentication().getToken();
  val headers = Map("Content-Type" -> "application/json",
                    "Accept" -> "application/json",
                    "Token"  -> "3a7ce15a-29e5-26a9-7106-85ce6217a245")

  //USE ALL LOCAL ADDRESSES.
  http.useAllLocalAddresses;

  val scn = {
    //POST
    scenario("/api/login/client") // A scenario is a chain of requests and pauses
      .exec(http("/api/login/client")
      .post(baseUrlUsersAPI + "/api/login/client")
      .body(RawFileBody("./src/test/resources/bodies/GsUsersWebAPI/ApiLoginClient.json")).asJson)
    //POST
    .exec(http("/api/homework/pending/student POST")
      .post(baseUrlTeachersAPI +"/api/homework/pending/student")
      .body(RawFileBody("./src/test/resources/bodies/GsTeachersAPI/ApiTeacherPendingHomework.json")).asJson
      .headers(headers))
    //OPTIONS
    .exec(http("/api/homework/pending/student OPTIONS")
      .options(baseUrlTeachersAPI +"/api/homework/pending/student")
      .headers(headers))
    //GET
    .exec(http("/api/subscriptions/student/courses")
      .get(baseUrlSubscriptionsAPI + "/api/subscriptions/student/courses")
      .headers(headers))
  };

  /* .exec(http("/api/activity/create")
      .post(baseUrlUsersAPI +"/api/activity/create")
      .headers(headers))*/

      setUp(scn.inject(rampUsers(injectUsersCount).during(injectUsersSeconds)).protocols(httpProtocol))
}
