package computerdatabase.StudentsFrontend.LoginWithDashboard

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class SeeLessonPage extends Simulation {

  val baseDomain = "https://genial-skills-dev-auto-scaling-api.citriom.io:";
  val httpProtocol = http.baseUrl("https://genial-skills-dev-auto-scaling-api.citriom.io");
  val baseUrlAthenasAPI = baseDomain + 8739;
  val headers = Map("Content-Type" -> "application/json",
                    "Accept" -> "application/json",
                    "Token"  -> "abbfec55-25c9-7412-8fc7-e0d2410cc3a4")

  val scn = scenario("SeeLessonPage") // A scenario is a chain of requests and pauses
    //lesson
    .exec(http("/api/lessons/lesson")
      .post(baseUrlAthenasAPI + "/api/lessons/lesson")
      .body(RawFileBody("./src/test/resources/bodies/AthenasWebAPI/ApiAthenasLesson.json")).asJson
      .headers(headers))
    //additional material for lesson
     .exec(http("/api/additional-material/lesson/read/{lessonId}/{page}/{pageSize}")
      .get(baseUrlAthenasAPI + "/api/additional-material/lesson/read/843/1/1")
       .headers(headers))

      setUp(scn.inject(rampUsers(8000).during(60)).protocols(httpProtocol))
}
