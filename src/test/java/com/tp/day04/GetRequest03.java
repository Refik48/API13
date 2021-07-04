package com.tp.day04;

import com.tp.testBase.TestBaseHerokuApp;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;

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


    }
}
