package com.tp.day05;

import com.tp.TestData.TestDataDummy;
import com.tp.testBase.TestBaseDummy;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetRequest01 extends TestBaseDummy {
    /*
        http://dummy.restapiexample.com/api/v1/employees url ine bir istek gönderildiğinde
        Status kodun 200 olduğunu,
        5. Çalışan isminin "Airi Satou" olduğunu ,  çalışan sayısının 24 olduğunu,
        Sondan 2. çalışanın maaşının 106450 olduğunu
        40,21 ve 19 yaslarında çalışanlar olup olmadığını
        11. Çalışan bilgilerinin
          {
         "id":"11"
         "employee_name": "Jena Gaines",
        "employee_salary": 90560,
        "employee_age": 30,
        "profile_image": "" }
        } gibi olduğunu test edin.
 */
    @Test
    public void test01() {

        spec03.pathParam("parametre1", "employees");
        Response response = RestAssured.given().accept("application/json").
                spec(spec03).when().get("/{parametre1}");

        // response.prettyPrint();

        // De-serizaliton islemi
        TestDataDummy testDataDummy = new TestDataDummy();
        HashMap<String, Object> expectedDataMap = testDataDummy.setupTestData();
        HashMap<String, Object> actualDataMap = response.as(HashMap.class);
        Assert.assertEquals(expectedDataMap.get("statusCode"), response.getStatusCode());
        System.out.println("Expected Data Map : " + expectedDataMap);
        System.out.println("Actual Data Map : " + actualDataMap);

        // 5. Çalışan isminin "Airi Satou" olduğunu
        Assert.assertEquals(expectedDataMap.get("besinciCalisan"),
                ((Map)((List)actualDataMap.get("data")).get(4)).get("employee_name"));
                        // Once Data'nin icine girmeliyiz cunku geri kalanlar onun icerisinde.

        // calisanlarin sayisinin 24 oldugunu test edin
        Assert.assertEquals(expectedDataMap.get("calisanSayisi"),
                ((List)actualDataMap.get("data")).size());

        int dataSize = actualDataMap.size(); // 3 Dondurur, cunku
        // 1.si data'nin 2.message 3. status (konsolda actual data map icerisinde)
        int dataSize2 = ((List<?>) actualDataMap.get("data")).size();
        // data'nin size'i ise 24'tur (konsolda actual data map icerisinde)

        // Sondan 2. çalışanın maaşının 106450 olduğunu test edin
        Assert.assertEquals(expectedDataMap.get("istenenMaas"),
                ((Map)((List<?>) actualDataMap.get("data")).get(dataSize2-2)).get("employee_salary"));


        List<Integer> actualYasList=new ArrayList<Integer>();
        for(int i=0; i<dataSize; i++){

            actualYasList.add((Integer)((Map)((List<?>) actualDataMap.
                    get("data")).get(i)).get("employee_age"));
        }

        Assert.assertTrue(actualYasList.containsAll((List)expectedDataMap.get("yasListesi")));

        Assert.assertEquals(
                ((Map)expectedDataMap.get("employe11")).get("id"),
                ((Map) ((List<?>) actualDataMap.get("data")).get(10)).get("id")
        );

        Assert.assertEquals(
                ((Map)expectedDataMap.get("employe11")).get("profile_image"),
                ((Map) ((List<?>) actualDataMap.get("data")).get(10)).get("profile_image")
        );

        Assert.assertEquals(
                ((Map)expectedDataMap.get("employe11")).get("employee_name"),
                ((Map) ((List<?>) actualDataMap.get("data")).get(10)).get("employee_name")
        );

        Assert.assertEquals(
                ((Map)expectedDataMap.get("employe11")).get("employee_salary"),
                ((Map) ((List<?>) actualDataMap.get("data")).get(10)).get("employee_salary")
        );

        Assert.assertEquals(
                ((Map)expectedDataMap.get("employe11")).get("employee_age"),
                ((Map) ((List<?>) actualDataMap.get("data")).get(10)).get("employee_age")
        );


    }
}
