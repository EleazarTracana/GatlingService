package computerdatabase.StudentsFrontend.HomeWork

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class SeeAssignmentPage extends Simulation {

  val baseDomain = "https://genial-skills-dev-auto-scaling-api.citriom.io:";
  val httpProtocol = http.baseUrl("https://genial-skills-dev-auto-scaling-api.citriom.io");
  val baseUrlTeachersAPI = baseDomain + 8851;
  val headers = Map("Content-Type" -> "application/json",
                    "Accept" -> "application/json",
                    "Token"  -> "5bb648ca-10df-1f55-7f6b-a2f5f74e5efb")

  val scn = scenario("See Assignment") // A scenario is a chain of requests and pauses
    //Create Authorization code
    .exec(http("/api/homework/authorization-code/create/{teacherPlanQuizId}")
      .post(baseUrlTeachersAPI + "/api/homework/authorization-code/create/9")
      .headers(headers))
    .pause(1)
    //HomeworkDetails details
    .exec(http("/api/homework/quiz/student-quiz/{authorizationCode}")
      .get(baseUrlTeachersAPI + "/api/homework/quiz/student-quiz/sMduVmotZKgo2Jn8teMGwRuDceN1uJEkBeZPQvgfBcNZNUqjh0")
      .headers(headers))

      setUp(scn.inject(rampUsers(1000).during(60)).protocols(httpProtocol))
}
