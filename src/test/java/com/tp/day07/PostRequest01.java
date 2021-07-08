package com.tp.day07;

import com.tp.TestData.TestDataJsonPlaceHolder;
import com.tp.testBase.TestBaseJsonPlaceHolder;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

import static org.hamcrest.Matchers.equalTo;

public class PostRequest01 extends TestBaseJsonPlaceHolder {
    /*https://jsonplaceholder.typicode.com/todos URL ine aşağıdaki body gönderildiğinde,
}
     "userId": 55,
             "title": "Tidy your room",
             "completed": false
             }
             Dönen response un Status kodunun 201 ve response body nin aşağıdaki gibi olduğunu test edin
             {
             "userId": 55,
             "title": "Tidy your room",
             "completed": false,
             "id": …
             }*/
    @Test
    public void test(){

        // url-endpoint oluşturuldu
        spec01.pathParam("parametre1","todos");

        //request body oluşturmalıyım.
        TestDataJsonPlaceHolder testData=new TestDataJsonPlaceHolder();
        JSONObject requestBody=testData.day07PostRequest01Method();
        System.out.println(requestBody);

        //request gönder
        Response response= RestAssured.given().
                contentType(ContentType.JSON).
                spec(spec01).
                auth().basic("admin","password123").
                body(requestBody.toString()).
                when().post("/{parametre1}");
        response.prettyPrint();

        // De-Serialization => gson
        HashMap<String,Object> actualDataMap = response.as(HashMap.class);
        System.out.println(actualDataMap);
        Assert.assertEquals(201,response.getStatusCode());
        Assert.assertEquals(requestBody.getInt("userId"),actualDataMap.get("userId"));
        Assert.assertEquals(requestBody.getString("title"),actualDataMap.get("title"));
        Assert.assertEquals(requestBody.getBoolean("completed"),actualDataMap.get("completed"));

        // JsonPath
        JsonPath json = response.jsonPath();
        Assert.assertEquals(requestBody.getInt("userId"),json.getInt("userId"));
        Assert.assertEquals(requestBody.getString("title"),json.getString("title"));
        Assert.assertEquals(requestBody.getBoolean("completed"),json.getBoolean("completed"));

        // Matchers class
        response.then().assertThat().
                statusCode(201).
                body("userId", equalTo(requestBody.getInt("userId"))).
                body("title",equalTo(requestBody.getString("title"))).
                body("completed",equalTo(requestBody.getBoolean("completed")));
    }
}
