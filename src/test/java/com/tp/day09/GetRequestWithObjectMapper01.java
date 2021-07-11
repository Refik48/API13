package com.tp.day09;

import com.tp.testBase.TestBaseJsonPlaceHolder;
import com.tp.utilities.JsonUtil;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

public class GetRequestWithObjectMapper01 extends TestBaseJsonPlaceHolder {

    /*
    https://jsonplaceholder.typicode.com/todos/198 url’ine bir get request gönderildiğinde,
    Dönen response ‘un status kodunun 200 ve body kısmının
    {
       "userId": 10,
       "id": 198,
       "title": "quis eius est sint explicabo",
       "completed": true
    }
    Olduğunu Object Mapper kullanarak test edin
     */

    @Test
    public void test() {

        // url olustur
        spec01.pathParams("parametre1","todos",
                "parametre2",198);

        // expectedData'yi olusturuyoruz.
        // 1) ilk olarak json ifademi bir String'e atamaliyim
        String jsonData = "{\n" +
                "       \"userId\": 10,\n" +
                "       \"id\": 198,\n" +
                "       \"title\": \"quis eius est sint explicabo\",\n" +
                "       \"completed\": true\n" +
                "    }";
        // 2) Neye cevirmek istiyorsak o type'i belirliyorum.
        Map<String,Object> expectedDataMap = JsonUtil.convertJsonToJava(jsonData,Map.class);
        System.out.println(expectedDataMap);
        // jsonData,Map.class burada yazdigim Map esitligin sol tarafindan dolayi Map oldu
        // Eger sol taraf HashMap olsaydi sag tarafta HashMap olacakti.

        Response response = RestAssured.given().contentType(ContentType.JSON).
                spec(spec01).when().get("/{parametre1}/{parametre2}");
        response.prettyPrint();

        // actualDataMap'i olusturuyoruz.
        Map<String,Object> actualDataMap = JsonUtil.convertJsonToJava(response.asString(),Map.class);

        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertEquals(expectedDataMap.get("userId"), actualDataMap.get("userId"));
        Assert.assertEquals(expectedDataMap.get("id"), actualDataMap.get("id"));
        Assert.assertEquals(expectedDataMap.get("title"), actualDataMap.get("title"));
        Assert.assertEquals(expectedDataMap.get("completed"), actualDataMap.get("completed"));

    }
}
