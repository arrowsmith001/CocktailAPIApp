package com.arrowsmith.cocktailapiapp;

import com.arrowsmith.cocktailapiapp.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import io.restassured.RestAssured;
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

        RestAssured.given()
                .get(urls.getRandomCocktailUrl())
                .then()
                .statusCode(200);
    }

    @Test
    public void testGetIngredientByName() {

        RestAssured.given()
                .get(urls.getIngredientByNameUrl(""))
                .then()
                .statusCode(200);

    }
    @Test
    public void testGetIngredientById() {

        RestAssured.given()
                .get(urls.getIngredientByIdUrl(0))
                .then()
                .statusCode(200);

    }
    @Test
    public void testGetCocktailById() {

        RestAssured.given()
                .get(urls.getCocktailByIdUrl(0))
                .then()
                .statusCode(200);

    }
    @Test
    public void testListCocktailsByIngredient() {

        RestAssured.given()
                .get(urls.listCocktailsByIngredientUrl(""))
                .then()
                .statusCode(200);

    }
    @Test
    public void testListCocktailsByStartingLetter() {

        RestAssured.given()
                .get(urls.listCocktailsByStartingLetterUrl(' '))
                .then()
                .statusCode(200);

    }
    @Test
    public void testListCocktailsByName() {

        RestAssured.given()
                .get(urls.listCocktailsByNameUrl(""))
                .then()
                .statusCode(200);

    }
}
