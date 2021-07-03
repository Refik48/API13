package com.tp.practise;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;

public class Get01 {
    /*
         Positive Scenario:
         When Asagidaki Endpoint'e bir GET request yolladim
         https://restful-booker.herokuapp.com/booking/10
         And Accept type “application/json” dir
         Then
         HTTP Status Code 200
         And Response format "application/json"
         And firstname "Mark"
         And lastname "Wilson"
         And depositpaid true
         And checkin date "2016-06-19"
         And checkout date "2019-08-26"
         1.Adim Url'i olustur
         2.Adim Data'yi oluştur
         3.Adim Request gönder
         4.Adim Validation yap
        */

    @Test
    public void test01() {
        // 1. Adim Url'i olustur
        String endPoint = "https://restful-booker.herokuapp.com/booking/10";

        // 3. Adim Request gonder
        Response response = RestAssured.given().accept("application/json").when().get(endPoint);

        response.prettyPrint();

        response.then().assertThat().contentType("application/json");

        // Body'nin validation'i
        response.then().assertThat().body("firstname",equalTo("Eric"),
                "lastname",equalTo("Brown"),
                "depositpaid",equalTo(false)
                ,"bookingdates.checking",equalTo("2015-09-12"),
                "bookingdates.checkout",equalTo("2019-04-26"));

        JsonPath jsonPath = response.jsonPath();
        Assert.assertEquals("Susan",jsonPath.getString("firstname"));
        Assert.assertEquals("Wilson",jsonPath.getString("lastname"));
        // jsonPath.getString yerine jsonPath.get kullanirsakta calisir ama daha yavas olur.
        // Cunku data type'n ne oldugunu bulmaya calisir.
        Assert.assertEquals(970,jsonPath.getInt("totalprice"));
        Assert.assertEquals(false,jsonPath.getBoolean("depositpaid"));
        Assert.assertEquals("2015-09-12",jsonPath.getString("bookingdates.checking"));
        Assert.assertEquals("2019-04-26",jsonPath.getString("bookingdates.checkout"));
    }

}
