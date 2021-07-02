package com.tp.testBase;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class TestBaseJsonPlaceHolder {

    protected RequestSpecification spec01;
    // Neden protected ? Cunku child'larda kullanabiliyorum.

    @Before
    public void setup() {
        spec01 = new RequestSpecBuilder().setBaseUri("https://jsonplaceholder.typicode.com").build();
        // Interface'lerden nesne olusturamam, farkli bir sey yaparak;
        // RequestSpecBuilder Class'ini cagiriyorum. Bu class'in icinde method'lar var.
        // Bu method'lardan setBaseUrl method ile base url'imi buraya atiyorum.

    }
}
