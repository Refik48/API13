package com.tp.day03;

import com.tp.testBase.TestBaseDummy;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetRequest01 extends TestBaseDummy {
    /*
    http://dummy.restapiexample.com/api/v1/employees url’inde bulunan
    1) Butun calisanlarin isimlerini consola yazdıralim
    2) 3. calisan kisinin ismini konsola yazdıralim
    3) Ilk 5 calisanin adini konsola yazdiralim
    4) En son calisanin adini konsola yazdiralim
     */

    @Test
    public void test01() {
       spec03.pathParam("parametre1","employees");

       Response response = given().accept("application/json").spec(spec03).when().get("/{parametre1}");

       response.prettyPrint();

        JsonPath jsonPath = response.jsonPath();

        // 1) Butun calisanlarin isimlerini consola yazdıralim
        System.out.println(jsonPath.getList("data.employee_name"));
        // response'daki tum employee'leri bulur ve getirir.

        // 2) 3. calisan kisinin ismini konsola yazdıralim
        System.out.println(jsonPath.getString("data.employee_name[2]"));

        // 3) Ilk 5 calisanin adini konsola yazdiralim
        System.out.println(jsonPath.getString("data.employee_name[0,1,2,3,4]"));

        // 4) En son calisanin adini konsola yazdiralim
        System.out.println(jsonPath.getString("data.employee_name[-1]"));
    }
}
