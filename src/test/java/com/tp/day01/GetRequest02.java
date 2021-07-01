package com.tp.day01;

import io.restassured.response.Response;
import org.junit.Test;
import static io.restassured.RestAssured.given;

public class GetRequest02 {
     /*
    https://restful-booker.herokuapp.com/booking url'ine
    accept type'i "application/json" olan GET request'i yolladigimda
    gelen response'un
    status kodunun 200
    content type'inin "application/json" oldugunu test edin
     */
    @Test
    public void test01() {
        String url = "https://restful-booker.herokuapp.com/booking";


    }



    /*
    https://restful-booker.herokuapp.com/booking url'ine
    accept type'i "application/json" olan GET request'i yolladigimda
    gelen response'un
    status kodunun 200
    content type'inin "application/json" oldugunu test edin

    https://restful-booker.herokuapp.com/booking/1001 url'ine
    accept type'i "application/json" olan GET request'i yolladigimda
    gelen response'un
    status kodunun 404 oldugunu
    */

}
