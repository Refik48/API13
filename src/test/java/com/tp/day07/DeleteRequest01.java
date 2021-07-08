package com.tp.day07;

import com.tp.TestData.TestDataDummy;
import com.tp.testBase.TestBaseJsonPlaceHolder;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;

public class DeleteRequest01 extends TestBaseJsonPlaceHolder {
    /*
    http://dummy.restapiexample.com/api/v1/delete/2 bir DELETE request gönderdiğimde

    Dönen response un status kodunun 200 ve body kısmının aşağıdaki gibi olduğunu test edin
    {
     "status": "success",
     "data": "2",
     "message": "Successfully! Record has been deleted"
    } day07DeleteRequest01SetUpMethod
     */

    @Test
    public void test(){

        //url oluştur
        spec01.pathParams("parametre1","delete",
                "parametre2",2);

        //expected Data oluştur
        TestDataDummy testData=new TestDataDummy();
        JSONObject expectedData=testData.day07DeleteRequest01SetUpMethod();
        System.out.println(expectedData);

        //request gönder
        Response response=RestAssured.given().
                spec(spec01).
                auth().
                basic("admin","password123").
                when().
                delete("/{parametre1}/{parametre2}");

        response.prettyPrint();

        response.then().
                assertThat().
                statusCode(expectedData.getInt("statusCode")).
                body("status", equalTo(expectedData.getString("status")),
                        "data",equalTo(expectedData.getString("data")),
                        "message",equalTo(expectedData.getString("message")));
    }
}
