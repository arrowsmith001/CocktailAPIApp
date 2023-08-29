package com.arrowsmith.cocktailapiapp;

import com.arrowsmith.cocktailapiapp.api.*;
import org.apache.http.HttpStatus;
import org.springframework.boot.test.context.SpringBootTest;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.junit.Assert.assertEquals;


@SpringBootTest
public class RestAssuredTests {

    private String getApiKey(){
        return System.getenv().get("THE_COCKTAIL_DB_API_KEY");
    }

    final TheCocktailDBUrls urls = new TheCocktailDBUrls(getApiKey());

    @Test
    public void testGetRandom() {

        Response response = RestAssured.get(urls.getRandomCocktailUrl());

        System.out.println("Response : "+response.asString());
        System.out.println("Status Code : "+response.getStatusCode ());
        System.out.println("Body: "+response.getBody().asString());
        System.out.println("Time taken: "+response.getTime());
        System.out.println("Header: "+response.getHeader("content-type"));

        assertEquals("Http status is not OK", HttpStatus.SC_OK, response.getStatusCode());
    }
}
