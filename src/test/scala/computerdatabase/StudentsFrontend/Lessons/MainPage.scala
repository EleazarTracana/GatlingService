package computerdatabase.StudentsFrontend.Lessons

import computerdatabase.Authentication
import io.gatling.core.Predef._
import io.gatling.core.body.Body
import io.gatling.http.Predef._

class MainPage extends Simulation {

  val baseDomain = "https://dev-api.genialskillsweb.com:";
  val httpProtocol = http.baseUrl("https://dev-api.genialskillsweb.com");
  val baseUrlSubscriptionsAPI = baseDomain + 8852;
  val baseUrlAthenasAPI = baseDomain + 8739;
  val Token = new Authentication().getToken();
  val byLessonsModel = "{\"Token\": "+Token+", \"LangCode\": \"en\",\"LessonModelList\": [ {\"LevelCode\": \"K\", \"SubjectCode\": \"SCI-SP\"}]}"
  val injectUsersCount = Integer.getInteger("users", 1)
  val injectUsersSeconds = java.lang.Long.getLong("ramp", 0)
  val headers = Map("Content-Type" -> "application/json",
                    "Accept" -> "application/json",
                    "Token"  -> Token)

  val scnAllCourses = scenario("AllCourses") // A scenario is a chain of requests and pauses
    .exec(http("/api/subscriptions/student/courses")
      .get(baseUrlSubscriptionsAPI + "/api/subscriptions/student/courses")
      .headers(headers));
  val scnLessonsBySubject = scenario("LessonsBySubject") // A scenario is a chain of requests and pauses
    .exec(http("/api/lessons/by-lesson-models/true")
      .post(baseUrlAthenasAPI + "/api/lessons/by-lesson-models/true")
      .body(StringBody(byLessonsModel))
      .headers(headers));

      setUp(scnAllCourses.inject(rampUsers(injectUsersCount).during(injectUsersSeconds)).protocols(httpProtocol),
            scnLessonsBySubject.inject(rampUsers(injectUsersCount).during(injectUsersSeconds)).protocols(httpProtocol))
}
