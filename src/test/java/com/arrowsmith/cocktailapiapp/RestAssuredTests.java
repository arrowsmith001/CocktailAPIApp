package com.arrowsmith.cocktailapiapp;

import com.arrowsmith.cocktailapiapp.api.CocktailApi;
import com.arrowsmith.cocktailapiapp.api.CocktailApiImpl;
import com.arrowsmith.cocktailapiapp.api.CocktailApiRequester;
import com.arrowsmith.cocktailapiapp.api.TheCocktailDBRequester;
import org.apache.http.HttpStatus;
import org.springframework.boot.test.context.SpringBootTest;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.junit.Assert.assertEquals;


@SpringBootTest
public class RestAssuredTests {

    final CocktailApiRequester api = new TheCocktailDBRequester(System.getenv().get("THE_COCKTAIL_DB_API_KEY"));
    private static final String BASE_URL = "https://www.thecocktaildb.com/api/json/v1/";
    private static final String RANDOM = "/random.php";

    @Test
    public void testGet() {

        Response response = RestAssured.get("https://www.thecocktaildb.com/api/json/v1/1/random.php");

        System.out.println("Response : "+response.asString());
        System.out.println("Status Code : "+response.getStatusCode ());
        System.out.println("Body: "+response.getBody().asString());
        System.out.println("Time taken: "+response.getTime());
        System.out.println("Header: "+response.getHeader("content-type"));

        assertEquals("Http status is not OK", response.getStatusCode(), HttpStatus.SC_OK);
    }
}
