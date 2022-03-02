package computerdatabase.StudentsFrontend.Lessons

import computerdatabase.Authentication
import io.gatling.core.Predef._
import io.gatling.http.Predef._

class LessonDetails extends Simulation {

  val baseDomain = "https://dev-api.genialskillsweb.com:";
  val httpProtocol = http.baseUrl("https://dev-api.genialskillsweb.com");
  val baseUrlGsWebAPI = baseDomain + 8981;
  val injectUsersCount = 10000;
  val injectUsersSeconds = 60;
  val Token = new Authentication().getToken();
  val headers = Map("Content-Type" -> "application/json",
                    "Accept" -> "application/json",
                    "Token"  -> Token)


  val scnConcept = scenario("LessonConcept") // A scenario is a chain of requests and pauses
    .exec(http("/api/lessons/{lessonId}/concept")
      .get(baseUrlGsWebAPI + "/api/lessons/843/concept")
      .headers(headers))

  val scnExamples = scenario("LessonExamples") // A scenario is a chain of requests and pauses
    .exec(http("/api/lessons/{lessonId}/examples")
      .get(baseUrlGsWebAPI + "/api/lessons/843/examples")
      .headers(headers))

  val scnDefinitions = scenario("LessonDefinitions") // A scenario is a chain of requests and pauses
    .exec(http("/api/lessons/{lessonId}/definitions")
      .get(baseUrlGsWebAPI + "/api/lessons/843/definitions")
      .headers(headers))

  val scnPractices = scenario("LessonPractices") // A scenario is a chain of requests and pauses
    .exec(http("/api/lessons/{lessonId}/practices")
      .get(baseUrlGsWebAPI + "/api/lessons/843/practices")
      .headers(headers))

  val scnExams = scenario("LessonExams") // A scenario is a chain of requests and pauses
    .exec(http("/api/lessons/{lessonId}/exams")
      .get(baseUrlGsWebAPI + "/api/lessons/843/exams")
      .headers(headers))



      setUp(scnConcept.inject(rampUsers(injectUsersCount).during(injectUsersSeconds)).protocols(httpProtocol),
            scnExamples.inject(rampUsers(injectUsersCount).during(injectUsersSeconds)).protocols(httpProtocol),
            scnDefinitions.inject(rampUsers(injectUsersCount).during(injectUsersSeconds)).protocols(httpProtocol),
            scnPractices.inject(rampUsers(injectUsersCount).during(injectUsersSeconds)).protocols(httpProtocol),
            scnExams.inject(rampUsers(injectUsersCount).during(injectUsersSeconds)).protocols(httpProtocol))
}
