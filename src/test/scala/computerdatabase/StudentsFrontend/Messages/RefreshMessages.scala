package computerdatabase.StudentsFrontend.Messages

import computerdatabase.Authentication
import io.gatling.core.Predef._
import io.gatling.http.Predef._

class RefreshMessages extends Simulation {

  val baseDomain = "https://dev-api.genialskillsweb.com:";
  val httpProtocol = http.baseUrl("https://dev-api.genialskillsweb.com");
  val baseUrlMessagesAPI = baseDomain + 8854;
  val injectUsersCount = Integer.getInteger("users", 1)
  val injectUsersSeconds = java.lang.Long.getLong("ramp", 0)
  val Token = new Authentication().getToken();
  val headers = Map("Content-Type" -> "application/json",
                    "Accept" -> "application/json",
                    "Token"  -> Token)

  val scnReceived = scenario("ReceivedMessages") // A scenario is a chain of requests and pauses
    .exec(http("/api/messages/received/1/10/en")
      .get(baseUrlMessagesAPI + "/api/messages/received/1/10/en")
      .headers(headers))
   .exec(http("/api/messages/unreads")
      .get(baseUrlMessagesAPI + "/api/messages/unreads")
      .headers(headers));

  val scnSent = scenario("SentMessages") // A scenario is a chain of requests and pauses
    .exec(http("/api/messages/sent/1/10/en")
      .get(baseUrlMessagesAPI + "/api/messages/sent/1/10/en")
      .headers(headers));

      setUp(scnReceived.inject(rampUsers(injectUsersCount).during(injectUsersSeconds)).protocols(httpProtocol),
            scnSent.inject(rampUsers(injectUsersCount).during(injectUsersSeconds)).protocols(httpProtocol))
}
