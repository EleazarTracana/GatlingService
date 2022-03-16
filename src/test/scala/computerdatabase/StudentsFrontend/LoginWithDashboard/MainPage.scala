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
                    "Token"  -> Token)

  //USE ALL LOCAL ADDRESSES.
  http.useAllLocalAddresses;

  val scn = {
    //POST
    scenario("/api/login/client") // A scenario is a chain of requests and pauses
      .exec(http("/api/login/client POST")
      .post(baseUrlUsersAPI + "/api/login/client")
      .body(RawFileBody("./src/test/resources/bodies/GsUsersWebAPI/ApiLoginClient.json")).asJson)
    //POST
    .exec(http("/api/homework/pending/student POST")
      .post(baseUrlTeachersAPI +"/api/homework/pending/student")
      .body(RawFileBody("./src/test/resources/bodies/GsTeachersAPI/ApiTeacherPendingHomework.json")).asJson
      .headers(headers))
    //GET
    .exec(http("/api/subscriptions/student/courses GET")
      .get(baseUrlSubscriptionsAPI + "/api/subscriptions/student/courses")
      .headers(headers))
     //POST
    .exec(http("/api/activity/create POST")
    .post(baseUrlUsersAPI +"/api/activity/create")
    .headers(headers))
    //OPTIONS
      .exec(http("/api/subscriptions/student/courses OPTIONS")
        .options(baseUrlSubscriptionsAPI + "/api/subscriptions/student/courses")
        .headers(headers))
    //OPTIONS
      .exec(http("/api/login/client OPTIONS")
        .options(baseUrlUsersAPI + "/api/login/client")
        .body(RawFileBody("./src/test/resources/bodies/GsUsersWebAPI/ApiLoginClient.json")).asJson)
    //OPTIONS
      .exec(http("/api/activity/create OPTIONS")
        .options(baseUrlUsersAPI +"/api/activity/create")
        .headers(headers))
      //OPTIONS
      .exec(http("/api/homework/pending/student OPTIONS")
        .options(baseUrlTeachersAPI +"/api/homework/pending/student")
        .headers(headers))
  };

      setUp(scn.inject(rampUsers(injectUsersCount).during(injectUsersSeconds)).protocols(httpProtocol))
}
