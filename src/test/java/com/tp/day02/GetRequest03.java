package com.tp.day02;

import com.tp.testBase.TestBaseHerokuApp;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetRequest03 extends TestBaseHerokuApp {
     /*
    https://restful-booker.herokuapp.com/booking/5 url’ine bir request yolladigimda
     	HTTP Status Code’unun 200
     	ve response content type’inin “application/JSON” oldugunu
			ve response body’sinin asagidaki gibi oldugunu test edin
				{"firstname": Sally,
     			"lastname": "Smith",
     			"totalprice": 789,
     			"depositpaid": false,
     			"bookingdates": { 	"checkin": "2017-12-11",
     	                     		"checkout":"2020-02-20" }
      		}
     */

    @Test
    public void test() {
        spec02.pathParams("parametre1","booking",
                "parametre2",5);

        Response response = given().accept("application/json").spec(spec02).when().
                get("/{parametre1}/{parametre2}");

        response.prettyPrint();

//        response.then().
//                assertThat().
//                statusCode(200).
//                contentType(ContentType.JSON).
//                body("firstname", equalTo("Eric"),
//                        "lastname" ,equalTo("Smith"),
//                        "totalprice",equalTo(414),
//                        "depositpaid",equalTo(false),
//                        "bookingdates.checkin",equalTo("2016-04-11"),
//                        "bookingdates.checkout",equalTo("2020-03-26") );

        // ARTIK BIZ JSON PATH ile ASSERTION YAPACAGIZ :

        JsonPath json = response.jsonPath();
        // jsonPath method'u ile response icerisindeki datayi,
        // JsonPath Class'indan tanimladigim json'in icerisine atiyorum.
        // Body icerisindeki dataya ulasmak icin bunu yapiyoruz.

        Assert.assertEquals("Data degisiyor surekli","Susan",json.getString("firstname"));
        // Eger 3 veri girersem ILKINI "message" olarak atar.
        Assert.assertEquals("Jones",json.getString("lastname"));
        Assert.assertEquals(678,json.getInt("totalprice"));
        Assert.assertEquals(false,json.getBoolean("depositpaid"));
        Assert.assertEquals("2015-06-10",json.getString("bookingdates.checkin"));
        Assert.assertEquals("2019-09-07",json.getString("bookingdates.checkout"));
        Assert.assertEquals(200,response.getStatusCode());
        // statuc code ve contect type'da assert etmeliyim.
        response.then().statusCode(200).contentType(ContentType.JSON);
        // Json path kullandim diye bu yontemi kullanamam diye bir kural yok.

    }
}
