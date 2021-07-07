package com.tp.TestData;

import org.json.JSONObject;

import java.util.HashMap;

public class TestDataHerokuApp {

    public HashMap<String, Object> setUpTestData() {
        // Bookingdates icerisinde ayri bir map yapisi vardir. Bu sebeple bu kisim ayri map olusturulur.
        // Map'in type'i String, String olur cunku checkin ve checkout degerleri String'tir.
        HashMap<String, String> bookingdatesMap = new HashMap<String, String>();
        bookingdatesMap.put("checkin", "2016-11-18");
        bookingdatesMap.put("checkout", "2020-09-16");

        // checkin ve checkout'u her datamin oldugu Map'imin icine aliyorum.
        HashMap<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("statusCode", 200);
        dataMap.put("firstname", "Mary");
        dataMap.put("lastname", "Wilson");
        dataMap.put("totalprice", 345);
        dataMap.put("depositpaid", true);
        dataMap.put("bookingdates", bookingdatesMap);
        return dataMap;
    }
        public JSONObject day06PostRequest02Method (){

            JSONObject bookingDates= new JSONObject();
            bookingDates.put("checkin","2020-09-09");
            bookingDates.put("checkout","2020-09-21");

            JSONObject booking=new JSONObject();

            booking.put("firstname","Selim");
            booking.put("lastname","Ak");
            booking.put("totalprice",11111);
            booking.put("depositpaid",true);
            booking.put("bookingdates",bookingDates);
            return booking;
        }
    }

