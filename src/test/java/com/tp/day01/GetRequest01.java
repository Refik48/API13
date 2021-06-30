package com.tp.day01;

import io.restassured.http.ContentType;
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
        // Soruda istenilenler body icerisinde olmayan seyler o yuzden expected result beklentisi yoktur.

        // 3) Request gonder
        Response response = given().accept("application/json").when().get(url);
        response.prettyPrint();
        // json formatinda sectim accept icinde.
        /*
            Given : Istenen endpoint sorgusundan once yapilacak gereklilikleri ifade eder.
            When : Kullanicinin aksiyonunu belirtir.
            Then : Ciktilari ifade eder.(Assert islemleri genelde the ile yapilir)
            And : Coklu islemler yapilacaksa kullanilir.
         */
        // new objesi neden olusturmadik ? Cunku Response bir interface'dir.
        // Interface'lerden obje uretilmez.

        // 4) Respose al(actual result)
        // body testi yapmadigim icin actual result'a gerek yoktur.

        // 5) Assertion islemini yapalim.
        response.then().assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                statusLine("HTTP/1.1 200 OK");

        System.out.println("status code : " + response.getStatusCode());
        System.out.println(response.getStatusLine());
        System.out.println(response.getHeaders());
        System.out.println(response.getContentType());


    }
}
