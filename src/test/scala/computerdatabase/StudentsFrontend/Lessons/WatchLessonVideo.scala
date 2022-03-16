package computerdatabase.StudentsFrontend.Lessons

import computerdatabase.Authentication
import io.gatling.core.Predef._
import io.gatling.http.Predef._

class WatchLessonVideo extends Simulation {

  val baseDomain = "https://dev-api.genialskillsweb.com:";
  val httpProtocol = http.baseUrl("https://dev-api.genialskillsweb.com");
  val baseUrlAthenasAPI = baseDomain + 8739;
  val injectUsersCount = Integer.getInteger("users", 1)
  val injectUsersSeconds = java.lang.Long.getLong("ramp", 0)


  val scnExams = scenario("LessonExams") // A scenario is a chain of requests and pauses
    .exec(http("/vid-resources/{subject-grade}/video.mp4")
      .get(baseUrlAthenasAPI + "/assets/vid-resources/MAT-SP/2/BodyPart_30d0558c-c858-4a8c-af98-f750f1bad286.mp4"))

      setUp(scnExams.inject(rampUsers(injectUsersCount).during(injectUsersSeconds)).protocols(httpProtocol))
}
