package computerdatabase.StudentsFrontend.HomeWork

import computerdatabase.Authentication
import io.gatling.core.Predef._
import io.gatling.http.Predef._

class SeeAssignmentPage extends Simulation {

  val baseDomain = "https://dev-api.genialskillsweb.com:";
  val httpProtocol = http.baseUrl("https://dev-api.genialskillsweb.com");
  val baseUrlTeachersAPI = baseDomain + 8851;
  val Token = new Authentication().getToken();
  val injectUsersCount = Integer.getInteger("users", 1)
  val injectUsersSeconds = java.lang.Long.getLong("ramp", 0)
  val authorizationCode = new Authentication().getAuthCode(Token);
  print(authorizationCode)
  val headers = Map("Content-Type" -> "application/json",
                    "Accept" -> "application/json",
                    "Token"  -> Token)

  val scn = scenario("See Assignment") // A scenario is a chain of requests and pauses
    //Create Authorization code
    .exec(http("/api/homework/authorization-code/create/{teacherPlanQuizId}")
      .post(baseUrlTeachersAPI + "/api/homework/authorization-code/create/9")
      .headers(headers))
    .pause(1)
    //HomeworkDetails details
    .exec(http("/api/homework/quiz/student-quiz/{authorizationCode}")
      .get(baseUrlTeachersAPI + "/api/homework/quiz/student-quiz/"+authorizationCode)
      .headers(headers))

      setUp(scn.inject(rampUsers(injectUsersCount).during(injectUsersSeconds)).protocols(httpProtocol))
}
