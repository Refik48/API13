package com.tp.day02;

import com.tp.testBase.TestBaseJsonPlaceHolder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetRequest02 extends TestBaseJsonPlaceHolder {
       /*
    https://jsonplaceholder.typicode.com/todos/123 url'ine
    accept type'i "application/json" olan GET request'i yolladigimda
    gelen response’un
    status kodunun 200
    ve content type'inin "application/json"
    ve Headers'daki "Server" in "cloudflare"
     ve response body'deki "userId"'nin 7
    ve "title" in "esse et quis iste est earum aut impedit"
    ve "completed" bolumunun false oldugunu test edin
     */
    @Test
    public void test() {
        // url olusturmak.
        spec01.pathParams("name","todos", // Parametreler key value seklindedir. Key'e istedigimiz
                "id",123);              // degeri verebiliriz fakat value' bizden istenendir.
        // 2'den fazla parametrem oldugu icin pathParams'i sectim. 1 tane olsaydi pathParam'i sececektim.

        Response response = given().accept(ContentType.JSON).spec(spec01).when().get("/{name}/{id}");
        // accept icerisine ContentType.JSON bazen calismayip,                           /todos/123
        // hata verebiliyor "application/json" da yazabiliriz.
        // get'e yazilacak degerler { } icersiinde yazilmalidir.

        response.prettyPrint();

        response.then().assertThat().
                statusCode(200).
                contentType(ContentType.JSON). // Her seferinde Matchers yazmaktan kurtulmak icin :
                body("userId", equalTo(7),
                "title",equalTo("esse et quis iste est earum aut impedit"),
                "completed", equalTo(false)).
                header("Server",equalTo("cloudflare"));
                // equalTo'dan onceki Matchers.'yi sil -> Import -> Matcher.equalTo

        System.out.println(response.getHeader("Server"));
        Assert.assertEquals("cloudflare",response.getHeader("Server"));
        // Server ismini boyle test edebilirim veya "completed", equalTo(false))'dan sonra
        // yazdigimla sekliylede test edebilirim.

    }
}
