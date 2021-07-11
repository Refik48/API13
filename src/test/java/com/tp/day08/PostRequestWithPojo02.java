package com.tp.day08;

import com.tp.pojos.TodosPojo02ActualBookingPojo;
import com.tp.pojos.TodosPojo02BookingDatesPojo;
import com.tp.pojos.TodosPojo02BookingPojo;
import com.tp.testBase.TestBaseHerokuApp;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

public class PostRequestWithPojo02 extends TestBaseHerokuApp {
     /*
    https://restful-booker.herokuapp.com/booking
    request body {
                   "firstname": "Selim",
                   "lastname": "Ak",
                   "totalprice": 15000,
                   "depositpaid": true,
                   "bookingdates": {
                       "checkin": "2020-09-09",
                       "checkout": "2020-09-21"
                    }
                 }
   Status code is 200
    response body  {
                            "bookingid": 11,
                            "booking": {
                                "firstname": "Selim",
                                "lastname": "Ak",
                                "totalprice": 15000,
                                "depositpaid": true,
                                "bookingdates": {
                                    "checkin": "2020-09-09",
                                    "checkout": "2020-09-21"
                                }
                            }
                         }
     */

    @Test
    public void test() {
        // url
        spec02.pathParam("p1","booking");

        // requestBody olusturuyorum
        TodosPojo02BookingDatesPojo bookingDates = new
                TodosPojo02BookingDatesPojo("2020-09-09","2020-09-21");
        System.out.println("BookingDates : " + bookingDates);

        TodosPojo02BookingPojo booking = new
                TodosPojo02BookingPojo("Selim","Ak",15000,true,bookingDates);
        System.out.println("Booking : " + booking);

        // request gonder
        Response response= RestAssured.given().
                contentType(ContentType.JSON).
                spec(spec02).
                auth().basic("admin","password123").
                body(booking).
                when().
                post("/{p1}");

        response.prettyPrint();

        // De- Serialization

        TodosPojo02ActualBookingPojo actualData = response.as(TodosPojo02ActualBookingPojo.class);
        System.out.println(actualData);

        Assert.assertEquals(200,response.getStatusCode());

        Assert.assertEquals(booking.getFirstname(),actualData.getBooking().getFirstname());

        Assert.assertEquals(booking.getLastname(),actualData.getBooking().getLastname());

        Assert.assertEquals(booking.getTotalprice(),actualData.getBooking().getTotalprice());

        Assert.assertEquals(booking.isDepositpaid(),actualData.getBooking().isDepositpaid());

        Assert.assertEquals(booking.getBookingdates().getCheckin(),
                actualData.getBooking().getBookingdates().getCheckin());

        Assert.assertEquals(booking.getBookingdates().getCheckout(),
                actualData.getBooking().getBookingdates().getCheckout());
    }

}
