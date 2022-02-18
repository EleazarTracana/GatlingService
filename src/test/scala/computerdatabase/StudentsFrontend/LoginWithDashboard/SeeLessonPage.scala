package computerdatabase.StudentsFrontend.LoginWithDashboard

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class SeeLessonPage extends Simulation {

  val baseDomain = "https://dev-api.genialskillsweb.com:";
  val httpProtocol = http.baseUrl("https://dev-api.genialskillsweb.com");
  val baseUrlAthenasAPI = baseDomain + 8739;
  val headers = Map("Content-Type" -> "application/json",
                    "Accept" -> "application/json",
                    "Token"  -> "38ae1e8d-d353-ed6b-edbf-91fe9b7c7715")

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

      setUp(scn.inject(rampUsers(20000).during(120)).protocols(httpProtocol))
}
