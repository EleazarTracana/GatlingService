package computerdatabase.StudentsFrontend.HomeWork

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class SeeAssignmentPage extends Simulation {

  val baseDomain = "https://genial-skills-dev-auto-scaling-api.citriom.io:";
  val httpProtocol = http.baseUrl("https://genial-skills-dev-auto-scaling-api.citriom.io");
  val baseUrlSubscriptionsAPI = baseDomain + 8852;
  val baseUrlTeachersAPI = baseDomain + 8851;
  val headers = Map("Content-Type" -> "application/json",
                    "Accept" -> "application/json",
                    "Token"  -> "abd072c4-071a-0e50-4023-bfe6b7ec9171")

  val scn = scenario("See Assignment") // A scenario is a chain of requests and pauses
    //Create Authorization code
    .exec(http("/api/homework/authorization-code/create/{teacherPlanQuizId}")
      .post(baseUrlTeachersAPI + "/api/homework/authorization-code/create/9")
      .headers(headers))

    //HomeworkDetails details
    .exec(http("/api/homework/quiz/student-quiz/{authorizationCode}")
      .post(baseUrlTeachersAPI + "/api/homework/quiz/student-quiz/12315")
      .headers(headers))

      setUp(scn.inject(rampUsers(1000).during(60)).protocols(httpProtocol))
}
