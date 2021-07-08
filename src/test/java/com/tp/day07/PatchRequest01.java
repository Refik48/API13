package com.tp.day07;

import com.tp.TestData.TestDataJsonPlaceHolder;
import com.tp.testBase.TestBaseJsonPlaceHolder;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

public class PatchRequest01 extends TestBaseJsonPlaceHolder {
 /*
     https://jsonplaceholder.typicode.com/todos/198 URL ine aşağıdaki body gönderdiğimde
       {

          "title": "API calismaliyim"

         }
    Dönen response un status kodunun 200 ve body kısmının aşağıdaki gibi olduğunu test edin
    {
     "userId": 10,
     "title": "API calismaliyim"
     "completed": true,
     "id": 198
    }
  */
    @Test
    public void test(){

        // url oluştur
        spec01.pathParams("parametre1","todos",
                "parametre2",198);


        // requestBody -request yaparken kullacagiz
        TestDataJsonPlaceHolder testData = new TestDataJsonPlaceHolder();
        JSONObject requestBody = testData.day07SetUpPutRequest01Method();

        //expected-- request body den farklı olduğu için oluşturduk. Assertion işlemini yaparken kullanacağız
        JSONObject expectedData = testData.day07PatchRequest01Method();
        System.out.println(expectedData);

        // request olustur
        Response response = RestAssured.given().contentType(ContentType.JSON).spec(spec01).
                auth().basic("admin","password123").
                body(requestBody.toString()).when().patch("/{p1}/{p2}");

        response.prettyPrint();

        // De-Serilization
        HashMap<String,Object> actualDataMap=response.as(HashMap.class);
        System.out.println(actualDataMap);
        Assert.assertEquals(200,response.getStatusCode());
        Assert.assertEquals(expectedData.getBoolean("completed"),actualDataMap.get("completed"));
        Assert.assertEquals(expectedData.getInt("userId"),actualDataMap.get("userId"));
        Assert.assertEquals(expectedData.getString("title"),actualDataMap.get("title"));





    }
}
