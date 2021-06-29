package com.tp.day01;

import io.restassured.response.Response;
import org.junit.Test;
import static io.restassured.RestAssured.given;

public class GetRequest01 {
    /*
    GetRequest01:
    https://restful-booker.herokuapp.com/booking/3 adresine bir request gonderildiginde
        donecek cevap(response) icin
    ØHTTP status kodunun 200
    ØContent Type’in Json
    ØVe Status Line’in HTTP/1.1 200 OK
    Oldugunu test edin.
     */

    @Test
    public void test01() {

        // API testinde driver'a ihtiyacimiz yok cunku UI testi yapmayacagiz, backend testi yapacagiz.

        // 1) Url belirlenmeli
        String url = "https://restful-booker.herokuapp.com/booking/3";

        // 2) Expected result olustur.
        // Burda bizden body'de gelen reponse assert etmemiz beklenmemistir. Bu sebeple bu adima gerek yoktur.

        // 3) Request gonder
        Response response = given().accept("application/json").when().get(url);

        // 4) Respose al(actual result)

        // 5) Assertion islemini yapalim.

    }
}
