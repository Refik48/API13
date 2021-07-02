package com.tp.testBase;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class TestBaseDummy {

    protected RequestSpecification spec03;

    @Before
    public void setup() {
        spec03 = new RequestSpecBuilder().setBaseUri("http://dummy.restapiexample.com/api/v1").build();
        // Siteye gittik butun route'ler v1 kismina kadar sonrasi degisiyor o yuzden base'i v1'e kadar aldik.
    }
}
