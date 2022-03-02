package computerdatabase.StudentsFrontend.HomeWork

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import computerdatabase.Authentication

class MainPage extends Simulation {

  val baseDomain = "https://dev-api.genialskillsweb.com:";
  val httpProtocol = http.baseUrl("https://dev-api.genialskillsweb.com/");
  val baseUrlTeachersAPI = baseDomain + 8851;
  val Token = new Authentication().getToken();
  val headers = Map("Content-Type" -> "application/json",
                    "Accept" -> "application/json",
                    "Token"  -> Token)

  val scnPendingHomework = scenario("PendingHomeWork") // A scenario is a chain of requests and pause
    .exec(http("/api/homework/pending/student")
      .post(baseUrlTeachersAPI + "/api/homework/pending/student")
      .body(RawFileBody("./src/test/resources/bodies/GsSubscriptionsWebAPI/ApiSubscriptionsAssignedHomeWork.json")).asJson
      .headers(headers));

  val scnCompletedHomework = scenario("CompletedHomeWork")
    .exec(http("/api/homework/completed/student")
      .post(baseUrlTeachersAPI + "/api/homework/completed/student")
      .body(RawFileBody("./src/test/resources/bodies/GsSubscriptionsWebAPI/ApiSubscriptionsCompletedHomeWork.json")).asJson
      .headers(headers))

      setUp(scnPendingHomework.inject(rampUsers(10000).during(60)).protocols(httpProtocol),
            scnCompletedHomework.inject(rampUsers(10000).during(60)).protocols(httpProtocol))
}
