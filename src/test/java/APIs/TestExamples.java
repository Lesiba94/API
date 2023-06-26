package APIs;


import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class TestExamples {
    JSONObject request = new JSONObject();
    @Test
    public void testGet()
    {
        given().get("https://reqres.in/api/users?page=2").
                then().statusCode(200).
                body("data[1].last_name",equalTo("Ferguson")).
                body("data.last_name",hasItems("Edwards","Howell","Fields")).log().all();
    }

    @Test
    public void testPost()
    {


        request.put("name","morpheus");
        request.put("job","leader");



        given().
                contentType(ContentType.JSON).
                post("https://reqres.in/api/users").
                then().statusCode(201).log().all();
    }

    @Test
    public void testPost1()
    {
        request.put("email","eve.holt@reqres.in");
        request.put("password","pistol");
        System.out.println(request);
        given().
                contentType(ContentType.JSON).
                body(request.toJSONString()).
               when().
                post("https://reqres.in/api/register").then().
                statusCode(200).
                log().
                all();
    }
}
