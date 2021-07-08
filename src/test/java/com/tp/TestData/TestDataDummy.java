package com.tp.TestData;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TestDataDummy {
    /*
   http://dummy.restapiexample.com/api/v1/employees url ine bir istek gönderildiğinde
    Status kodun 200 olduğunu,
    5. Çalışan isminin "Airi Satou" olduğunu ,  çalışan sayısının 24 olduğunu,
    Sondan 2. çalışanın maaşının 106450 olduğunu
    40,21 ve 19 yaslarında çalışanlar olup olmadığını
    11. Çalışan bilgilerinin
     {
    "id":11
    "employee_name": "Jena Gaines",
    "employee_salary": 90560,
    "employee_age": 30,
    "profile_image": "" }
    } gibi olduğunu test edin.
    */

    public HashMap<String,Object> setupTestData(){
        HashMap<String,Object> expectedDataMap = new HashMap<String, Object>();
        List<Integer> ageList = new ArrayList<Integer>();
        ageList.add(40);
        ageList.add(21);
        ageList.add(19);
        HashMap<String,Object> onbirinciDataMap = new HashMap<String,Object>();
        onbirinciDataMap.put("id",11);
        onbirinciDataMap.put("employee_name","Jena Gaines");
        onbirinciDataMap.put("employee_salary",90560);
        onbirinciDataMap.put("employee_age",30);
        onbirinciDataMap.put("profile_image","");

        expectedDataMap.put("ageList",ageList);
        expectedDataMap.put("onbirinciData",onbirinciDataMap);
        expectedDataMap.put("statusCode",200);
        expectedDataMap.put("besinciCalisan","Airi Satou");
        expectedDataMap.put("calisanSayisi",24);
        expectedDataMap.put("istenenMaas", 106450);

        return expectedDataMap;
    }

    public HashMap<String,Object> day06PostTestDataRequestBodyMapMethod() {
        HashMap<String,Object> requestBodyMap = new HashMap<String,Object>();
        requestBodyMap.put("name","Kazim Nihat");
        requestBodyMap.put("salary",8888);
        requestBodyMap.put("age",25);
        requestBodyMap.put("profile_image","");
        return requestBodyMap;
    }

    public  HashMap<String,Object> day06PostTestDataExpectedBodyMapMethod() {
        HashMap<String,Object> expectedDataMap = new HashMap<>();
        expectedDataMap.put("statusCode",200);
        expectedDataMap.put("status","success");
        expectedDataMap.put("message","Successfully! Record has been added.");
        return expectedDataMap;
    }
    public JSONObject day07DeleteRequest01SetUpMethod(){
        /*
        {
 "status": "success",
 "data": "2",
 "message": "Successfully! Record has been deleted"
}
         */
        JSONObject expectedData=new JSONObject();
        expectedData.put("status","success");
        expectedData.put("data","2");
        expectedData.put("message","Successfully! Record has been deleted");
        expectedData.put("statusCode",200);
        return expectedData;
    }
}
