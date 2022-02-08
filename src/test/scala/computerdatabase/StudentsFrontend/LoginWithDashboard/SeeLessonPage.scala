package computerdatabase.StudentsFrontend.LoginWithDashboard

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class SeeLessonPage extends Simulation {

  val baseDomain = "https://genial-skills-dev-api.citriom.io:";
  val httpProtocol = http.baseUrl("https://genial-skills-dev-api.citriom.io");
  val baseUrlAthenasAPI = baseDomain + 8739;
  val headers = Map("Content-Type" -> "application/json",
                    "Accept" -> "application/json",
                    "Token"  -> "86ae469d-9639-c3a8-7e5a-19d8fcc31327")

  val scn = scenario("SeeLessonPage") // A scenario is a chain of requests and pauses
    //lesson
    .exec(http("/api/lessons/{LessonId}")
      .get(baseUrlAthenasAPI + "/api/lessons/843"))
    //additional material for lesson
    .exec(http("/api/additional-material/lesson/read/{lessonId}/{page}/{pageSize}")
      .get(baseUrlAthenasAPI + "/api/additional-material/lesson/read/843/1/1"))

      setUp(scn.inject(rampUsers(10000).during(60)).protocols(httpProtocol))
}
