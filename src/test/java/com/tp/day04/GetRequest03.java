package com.tp.day04;

import com.tp.TestData.TestDataHerokuApp;
import com.tp.testBase.TestBaseHerokuApp;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;


public class GetRequest03 extends TestBaseHerokuApp {
    /*
    https://restful-booker.herokuapp.com/booking/1 url ine bir istek gönderildiğinde
    dönen response body nin
    {
   "firstname": "Eric",
   "lastname": "Smith",
   "totalprice": 555,
   "depositpaid": false,
   "bookingdates": {
       "checkin": "2016-09-09",
       "checkout": "2017-09-21"
    }
} gibi olduğunu test edin.
     */
    @Test
    public void test() {
        spec02.pathParams("p1","booking",
                "p2",1);

        Response response = RestAssured.given().
                accept("application/json").
                spec(spec02).
                when().
                get("/{p1}/{p2}");

        response.prettyPrint();

        TestDataHerokuApp testData = new TestDataHerokuApp();
        HashMap<String, Object> expectedDataMap = testData.setUpTestData();

        // De-serialization
        HashMap<String,Object> actualDataMap = response.as(HashMap.class);
        System.out.println(actualDataMap);

        // Assert islemi
        Assert.assertEquals(expectedDataMap.get("firstname"),actualDataMap.get("firstname"));
        Assert.assertEquals(expectedDataMap.get("lastname"),actualDataMap.get("lastname"));
        Assert.assertEquals(expectedDataMap.get("totalprice"),actualDataMap.get("totalprice"));
        Assert.assertEquals(expectedDataMap.get("depositpaid"),actualDataMap.get("depositpaid"));
        Assert.assertEquals(((Map)expectedDataMap.get("bookingdates")).get("checkin"),
                ((Map)actualDataMap.get("bookingdates")).get("checkin"));


    }
}
