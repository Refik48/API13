package com.tp.day06;

import com.tp.TestData.TestDataHerokuApp;
import com.tp.testBase.TestBaseHerokuApp;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class PostRequest02 extends TestBaseHerokuApp {
    /*
    https://restful-booker.herokuapp.com/booking url ine, Request Body olarak
{ "firstname": "Selim",
               "lastname": "Ak",
               "totalprice": 11111,
               "depositpaid": true,
               "bookingdates": {
                   "checkin": "2020-09-09",
                   "checkout": "2020-09-21"
                }
 }gönderildiğinde, Status kodun 200 olduğunu ve dönen response body nin ,
 "booking": {
         "firstname": " Selim ",
         "lastname": " Ak ",
         "totalprice":  11111,
         "depositpaid": true,
         "bookingdates": {
             "checkin": "2020-09-01",
              "checkout": " 2020-09-21”
         },
        }
olduğunu test edin
     */
    @Test
    public void test() {

        // url olustur
        spec02.pathParam("p1","booking");

        //requestBody'i olusturun
        TestDataHerokuApp testData = new TestDataHerokuApp();
        JSONObject requestBodyJSONObject = testData.day06PostRequest02Method();
        System.out.println(requestBodyJSONObject);

        Response response = RestAssured.given().
                accept("application/json").spec(spec02).
                auth().basic("admin","password123").
                body(requestBodyJSONObject.toString()). // requestbody JSONObject ile olusturulmussa
                when().post("/{p1}");                // toString yazilmasi gerekmektedir.

        response.prettyPrint();

        Map<String,Object> actualDataMap = response.as(HashMap.class);

        Assert.assertEquals(requestBodyJSONObject.getString("firstname"),
                ((Map)actualDataMap.get("booking")).get("firstname"));

        Assert.assertEquals(requestBodyJSONObject.getString("lastname"),
                ((Map)actualDataMap.get("booking")).get("lastname"));

        Assert.assertEquals(requestBodyJSONObject.getInt("totalprice"),
                ((Map)actualDataMap.get("booking")).get("totalprice"));

        Assert.assertEquals(requestBodyJSONObject.getInt("depositpaid"),
                ((Map)actualDataMap.get("booking")).get("depositpaid"));

        Assert.assertEquals(requestBodyJSONObject.getJSONObject("bookindates").getString("checkin"),
                ((Map)((Map)actualDataMap.get("booking")).get("bookindates")).get("checkin"));

        Assert.assertEquals(requestBodyJSONObject.getJSONObject("bookindates").getString("checkout"),
                ((Map)((Map)actualDataMap.get("booking")).get("bookindates")).get("checkout"));
    }
}
