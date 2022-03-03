package computerdatabase


import com.google.gson.{Gson, JsonObject, JsonParser}
import org.apache.http.client.methods.HttpPost
import org.apache.http.entity.StringEntity
import org.apache.http.impl.client.DefaultHttpClient

import scala.util.control.Exception

class Authentication {
  def getToken(): String = {

    val login = new Login("estu4", "abc123", "es")
    val body = new Gson().toJson(login)

    val post = new HttpPost("https://dev-api.genialskillsweb.com:8853/api/login/client")
    post.setHeader("Content-type", "application/json")
    post.setEntity(new StringEntity(body))
    val response = (new DefaultHttpClient).execute(post)
    val entity = response.getEntity()
    var content = ""
    if (entity != null) {
      val inputStream = entity.getContent
      content = scala.io.Source.fromInputStream(inputStream).mkString
    }
    val json = JsonParser.parseString(content).getAsJsonObject()
    val element = json.get("Token")
    val token  = element.getAsString();
    token
  }
  def getAuthCode(Token: String): String ={
    val post = new HttpPost("https://dev-api.genialskillsweb.com:8851/api/homework/authorization-code/create/9")
    post.setHeader("Content-type", "application/json")
    post.setHeader("Token", Token)
    val response = (new DefaultHttpClient).execute(post)
    val entity = response.getEntity()
    var content = ""
    if (entity != null) {
      val inputStream = entity.getContent
      content = scala.io.Source.fromInputStream(inputStream).mkString
    }
    val json = JsonParser.parseString(content).getAsJsonObject()
    val element = json.get("AuthorizationCode")
    val token  = element.getAsString();
    token
  }
}
