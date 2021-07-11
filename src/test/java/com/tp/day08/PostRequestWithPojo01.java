package com.tp.day08;

import com.tp.pojos.TodosPojo01;
import com.tp.testBase.TestBaseJsonPlaceHolder;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;

public class PostRequestWithPojo01 extends TestBaseJsonPlaceHolder {

  /*
     https://jsonplaceholder.typicode.com/todos
    Request body  {
                      "userId": 21,
                      "id": 201,
                      "title": "Tidy your room",
                      "completed": false
                    }
   Status code is 201
    response body {
                      "userId": 21,
                      "id": 201,
                      "title": "Tidy your room",
                      "completed": false
                    }
 */

    @Test
    public void test() {

        // url
        spec01.pathParam("p1","todos");

        // requestBody olusturuyorum
        TodosPojo01 todos = new TodosPojo01(21,201,"Tidy your room",false);
        System.out.println(todos);

        // request gonder
        Response response = RestAssured.given().contentType(ContentType.JSON).
                spec(spec01).auth().basic("admin","password123").
                body(todos).when().post("/{p1}");

        response.prettyPrint();

        // De-serialization(Gson) -> Pojo
        TodosPojo01 actualData = response.as(TodosPojo01.class);
                                // response'dan gelen cevabi Todospojo kalibindaki gibi actualData'ya yaz.

        // Asertions
        Assert.assertEquals(201,response.getStatusCode());
        Assert.assertEquals(todos.getId(),actualData.getId());
        Assert.assertEquals(todos.getUserId(),actualData.getUserId());
        Assert.assertEquals(todos.getTitle(),actualData.getTitle());
        Assert.assertEquals(todos.isCompleted(),actualData.isCompleted());

        // JsonPath -> Pojo
        JsonPath json=response.jsonPath();

        Assert.assertEquals(todos.getId(),json.getInt("id"));
        Assert.assertEquals(todos.getUserId(),json.getInt("userId"));
        Assert.assertEquals(todos.getTitle(),json.getString("title"));
        Assert.assertEquals(todos.isCompleted(),json.getBoolean("completed"));

        //Macther class -> Pojo

        response.
                then().
                assertThat().
                body("userId", equalTo(todos.getUserId()),
                        "id",equalTo(todos.getId()),
                        "title",equalTo(todos.getTitle()),
                        "completed",equalTo(todos.isCompleted()));
    }
}
