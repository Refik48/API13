package com.tp.practise;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

public class Get04_Authorization {
    /*
         Positive Scenario:
         When Asagidaki Endpoint'e bir GET request yolladim
         https://www.gmibank.com/api/tp-customers/42697
     And Accept type “application/json” dir
     Then
     HTTP Status Code 200
     And Response format "application/json"
     And firstname "Ali"
     And lastname "Deckow"
     And middleInitial Leroy Hoeger Sipes
     And email com.github.javafaker.Name@7c011174@gmail.com
     And zelleEnrolled false
     And country null
        */

    @Test
    public void test01() {
        // 1. Adim Url'i olustur
        String endPoint = "https://www.gmibank.com/api/tp-customers/42697";

        // Token almamiz gerekiyor.
        String bearerToken = "";

        Response response = RestAssured.given().accept(ContentType.JSON).when().get(endPoint);
        response.prettyPrint();
    }
}
