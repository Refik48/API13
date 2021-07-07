package com.tp.day06;

import com.tp.TestData.TestDataDummy;
import com.tp.testBase.TestBaseDummy;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

public class PostRequest01 extends TestBaseDummy {
    /*
    http://dummy.restapiexample.com/api/v1/create url ine, Request Body olarak
        {
            "name":"Ahmet Aksoy",
                   "salary":"1000",
                   "age":"18",
                   "profile_image": ""
        }
        gönderildiğinde, Status kodun 200 olduğunu ve dönen response body nin ,
        {
           "status": "success",
                   "data": {
                “id”:…
           },
           "message": "Successfully! Record has been added."
        }
        olduğunu test edin
     */
    @Test
    public void test() {

        // url olustur
        spec03.pathParam("p1","create");

        // expected data'dan once post yaparken request body gonderdigim icin onu olusturmaliyim
        TestDataDummy testData = new TestDataDummy();
        HashMap<String,Object> requestBodyMap = testData.day06PostTestDataRequestBodyMapMethod();
        System.out.println("Request Body : " + requestBodyMap);

        //expected datayı oluşturalım
        HashMap<String,Object> expectedDataMap=testData.day06PostTestDataExpectedBodyMapMethod();
        System.out.println("Expected Data Map : " + expectedDataMap);

        // request olustur
        Response response = RestAssured.given().accept("application/json").
                spec(spec03).body(requestBodyMap).auth().
                basic("admin","password123").
                when().post("/{p1}");

        response.prettyPrint();

        HashMap<String,Object> actualDataMap = response.as(HashMap.class);
        // Assertion
        Assert.assertEquals(expectedDataMap.get("statusCode"),response.getStatusCode());
        Assert.assertEquals(expectedDataMap.get("status"),actualDataMap.get("status"));
        Assert.assertEquals(expectedDataMap.get("message"),actualDataMap.get("message"));

        // Autohorzaion : Belli bir database ya da server'a ulasabilme yetkisi
        // ya da orada yapabildigim islemleri belirler.
        // postta yeni bir kayit olusturdugumuz icin authorization yaptik
    }
}
