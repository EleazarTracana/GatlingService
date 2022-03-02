package computerdatabase.StudentsFrontend.HomeWork

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class SeeAssignmentPage extends Simulation {

  System.getenv("JAVA_OPTS")
  val baseDomain = "https://dev-api.genialskillsweb.com:";
  val httpProtocol = http.baseUrl("https://dev-api.genialskillsweb.com");
  val baseUrlTeachersAPI = baseDomain + 8851;
  val headers = Map("Content-Type" -> "application/json",
                    "Accept" -> "application/json",
                    "Token"  -> "ae4a6134-636c-d81b-f8b1-b5d305251060")

  val scn = scenario("See Assignment") // A scenario is a chain of requests and pauses
    //Create Authorization code
    .exec(http("/api/homework/authorization-code/create/{teacherPlanQuizId}")
      .post(baseUrlTeachersAPI + "/api/homework/authorization-code/create/9")
      .headers(headers))
    .pause(1)
    //HomeworkDetails details
    .exec(http("/api/homework/quiz/student-quiz/{authorizationCode}")
      .get(baseUrlTeachersAPI + "/api/homework/quiz/student-quiz/rRsyiC7NSGzfAsI7vYN4yGelnDZX1F6IFjDVvsDAifMmpbZ2Jh")
      .headers(headers))

      setUp(scn.inject(rampUsers(15000).during(60)).protocols(httpProtocol))
}
