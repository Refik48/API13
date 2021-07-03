package com.tp.day04;

import com.tp.testBase.TestBaseJsonPlaceHolder;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

public class GetRequest01_DeSerialization extends TestBaseJsonPlaceHolder {

    /*
        De-Serialization :
        Java'da olusturdugumuz bir nesneyi veya sinifi, saklama ya da transfer etmek istedigimiz formata
        donusturme islemine Serailization denir. Bunun tam yersi duruma ise De-serialization denir.
        Yani API'de donen response'u Map, List of Map, Set gibi java objelerine cevirme islemidir.
        Bu islemi yapabilmek icin gson ve jackson-mapper-asl kutuphaneleri gerekmektedir.
     */
    /*
    https://jsonplaceholder.typicode.com/todos/2 url ‘ine istek gönderildiğinde,
     Dönen response un
     Status kodunun 200, dönen body de,
       "completed": değerinin false
       "title”: değerinin “quis ut nam facilis et officia qui”
       "userId"  sinin 1 ve header değerlerinden
    "Via" değerinin “1.1 vegur” ve
       "Server" değerinin “cloudflare” olduğunu test edin…
     */

    @Test
    public void test() {
        // 1) Url olustur
        spec01.pathParams("p1","todos","p2","2");

        //2- expected data oluştur
        HashMap<String,Object> expectedDataMap=new HashMap<String, Object>();
        expectedDataMap.put("statusCode",200);
        expectedDataMap.put("completed",false);
        expectedDataMap.put("userId",1);
        expectedDataMap.put("title","quis ut nam facilis et officia qui");
        expectedDataMap.put("Via","1.1 vegur");
        expectedDataMap.put("Server","cloudflare");
        System.out.println("ExpectedData");
        System.out.println(expectedDataMap);
        System.out.println("--------------------------------------");

        // 3) Request'i gonder
        Response response = RestAssured.given().
                accept("application/json").
                spec(spec01).when().
                get("/{p1}/{p2}");

        response.prettyPrint();

        // 4) Actual Datayi olustur.
        HashMap<String,Object> actualDataMap = response.as(HashMap.class);
        // API'dan gelen response body'i Hashmap gibi yapiyoruz.
        Assert.assertEquals(expectedDataMap.get("statusCode"),response.getStatusCode());
        Assert.assertEquals(expectedDataMap.get("completed"),actualDataMap.get("completed"));
        Assert.assertEquals(expectedDataMap.get("title"),actualDataMap.get("title"));
        Assert.assertEquals(expectedDataMap.get("userId"),actualDataMap.get("userId"));
        Assert.assertEquals(expectedDataMap.get("Via"),response.getHeader("Via"));
        Assert.assertEquals(expectedDataMap.get("Server"),response.getHeader("Server"));
        Assert.assertEquals("application/json; charset=urf-8",response.contentType());

        System.out.println("=====================");
        System.out.println("Actual Data");
        System.out.println(actualDataMap);


    }
}
