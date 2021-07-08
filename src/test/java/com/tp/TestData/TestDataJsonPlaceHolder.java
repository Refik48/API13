package com.tp.TestData;

import org.json.JSONObject;

import java.util.HashMap;

public class TestDataJsonPlaceHolder {

    public int statusCode = 201;

    public HashMap<String,Object> setUpTestData() {
        HashMap<String, Object> expectedDataMap = new HashMap<String, Object>();
        expectedDataMap.put("statusCode", 200);
        expectedDataMap.put("completed", false);
        expectedDataMap.put("userId", 1);
        expectedDataMap.put("title", "quis ut nam facilis et officia qui");
        expectedDataMap.put("Via", "1.1 vegur");
        expectedDataMap.put("Server", "cloudflare");

        return expectedDataMap;
    }
    public JSONObject day07PostRequest01Method() {
        JSONObject requestBody=new JSONObject();
        requestBody.put("userId",55);
        requestBody.put("title","Tidy your room");
        requestBody.put("completed",false);
        return requestBody;
    }
    public JSONObject day07SetUpPutRequest01Method(){
    /*
        {
      "userId": 21,
      "title": "Wash the dishes",
      "completed": false
     }
     */
        JSONObject requestBody=new JSONObject();
        requestBody.put("userId",21);
        requestBody.put("title","Wash the dishes");
        requestBody.put("completed",false);
        return requestBody;
    }

    public JSONObject day07PatchRequest01Method() {
        JSONObject requestBody = new JSONObject();
        requestBody.put("title","API calismaliyim");
        return requestBody;
    }

    public void day07PatchRequest01ExpectedBodyMethod() {
        JSONObject expedtedBody = new JSONObject();
        expedtedBody.put("userId",10);
        expedtedBody.put("title","API calismaliyim");
        expedtedBody.put("completed",true);
    }

}
