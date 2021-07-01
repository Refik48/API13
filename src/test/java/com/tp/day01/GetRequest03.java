package com.tp.day01;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;


public class GetRequest03 {
     /*
    https://restful-booker.herokuapp.com/booking/7 url'ine
    accept type'i "application/json" olan GET request'i yolladigimda
    gelen response'un
    status kodunun 200
    ve content type'inin "application/json"
    ve firstname'in "Jim"
    ve lastname'in "Ericsson"
    ve checkin date'in 2020-05-08"
    ve checkout date'in 2020-09-30 oldugunu test edin
     */

    @Test
    public void test01() {
        String url = "https://restful-booker.herokuapp.com/booking/7";

        Response response = given().accept("application/json").when().get(url);

        response.prettyPrint();

        response.then().assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                body("firstname", Matchers.equalTo("Jim")).
                body("lastname",Matchers.equalTo("Ericcson")).
                body("totalprice",Matchers.equalTo(275)).
                body("depositpaid",Matchers.equalTo(false)).
                body("bookingdates.checkin", Matchers.equalTo("2020-05-08")).
                body("bookingdates.checkout",Matchers.equalTo("2021-04-27"));
                // Son ikisi neden String icinde degil ?
                // Cunku response.prettyPrint(); kismiyla gelen Headers kisminda bu degerler String degil.

                // bookingdates.checkin neden boyle ? Cunku ic ice yapi var.
                // checkin'e girebilmek icin oncelikle bookingdates'e girmem gerekiyor.


    }

}
