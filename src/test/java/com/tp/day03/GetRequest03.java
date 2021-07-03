package com.tp.day03;

import com.tp.testBase.TestBaseDummy;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class GetRequest03 extends TestBaseDummy {
      /*
    http://dummy.restapiexample.com/api/v1/employees
    url ine bir istek gönderildiğinde
    Dönen response un
     Status kodunun 200,
     1)10’dan büyük tüm id’leri ekrana yazdırın ve
    10’dan büyük 14 id olduğunu,
     2)30’dan küçük tüm yaşları ekrana yazdırın ve
      bu yaşların içerisinde en büyük yaşın 23 olduğunu
     3)Maası 350000 den büyük olan tüm employee name’leri ekrana yazdırın ve
      bunların içerisinde “Charde Marshall” olduğunu test edin
     */

    @Test
    public void test() {
        spec03.pathParam("parametre1","employees");

        Response response = given().accept("application/json").spec(spec03).when().get("/{parametre1}");

        response.prettyPrint();

        // Status kodunun 200
        Assert.assertEquals(200,response.getStatusCode());

        JsonPath json = response.jsonPath();

        // 10'dan buyuk tum id'leri ekrana yazdirin ve 10'dan butuk 14 id oldugunu test edin.
        List<Integer> idList1 = json.getList("data.id");
        idList1.stream().filter(x-> x>10).forEach(x-> System.out.print(x + " ")); // Lambda yontemi

        // Grovy Dili : Javanin alt dillerinden biridir. it ifadesi cons'lardaki this ifadesi gibidir.
        List<Integer> idList2 = json.getList("data.findAll{it.id>10}.id");
        // findAll : Belirtilen data'nin icerisinde istenen sarta bagli olan degerleri getirir.
        System.out.println(idList2);
        Assert.assertEquals(14,idList2.size());

        // 30’dan küçük tüm yaşları ekrana yazdırın
        List<Integer> yasList1 = json.getList("data.employee_age");
        List<Integer> age30 = yasList1.stream().filter(x -> x < 30).collect(Collectors.toList());
        Collections.sort(age30);
        Collections.reverse(age30);
        System.out.println(age30);
        Assert.assertEquals(23,(int) age30.get(0));

        System.out.println("============================================================");
        List<Integer> yasList2 = json.getList("data.findAll{it.employee_age<30}.employee_age");
        System.out.println(yasList2);
        Collections.sort(yasList2);
        Assert.assertEquals(Integer.valueOf("23"),yasList2.get(yasList2.size()-1));

    }

}
