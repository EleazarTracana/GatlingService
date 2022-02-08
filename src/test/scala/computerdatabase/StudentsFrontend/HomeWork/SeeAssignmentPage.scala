package computerdatabase.StudentsFrontend.HomeWork

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class SeeAssignmentPage extends Simulation {

  val baseDomain = "https://genial-skills-dev-api.citriom.io:";
  val httpProtocol = http.baseUrl("https://genial-skills-dev-api.citriom.io");
  val baseUrlSubscriptionsAPI = baseDomain + 8852;
  val baseUrlTeachersAPI = baseDomain + 8851;
  val headers = Map("Content-Type" -> "application/json",
                    "Accept" -> "application/json",
                    "Token"  -> "86ae469d-9639-c3a8-7e5a-19d8fcc31327")

  val scn = scenario("See Assignment") // A scenario is a chain of requests and pauses
    //Create Authorization code
    .exec(http("/api/homework/authorization-code/create/{teacherPlanQuizId}")
      .get(baseUrlTeachersAPI + "/api/homework/authorization-code/create/9"))

    //HomeworkDetails details
    .exec(http("/api/homework/quiz/student-quiz/{authorizationCode}")
      .post(baseUrlSubscriptionsAPI + "/api/homework/quiz/student-quiz/12315"))

      setUp(scn.inject(rampUsers(9000).during(60)).protocols(httpProtocol))
}
