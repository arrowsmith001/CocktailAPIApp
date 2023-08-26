package com.arrowsmith.cocktailapiapp.api;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.logging.*;

class TheCocktailDBRequester implements CocktailApiRequester {
    static Logger logger = Logger.getLogger(TheCocktailDBRequester.class.getName());

    public TheCocktailDBRequester(String apiKey) {
        this.apiKey = apiKey;
    }

    private final String apiKey;
    private static final String BASE_URL = "https://www.thecocktaildb.com/api/json/v1/";
    private static final String RANDOM = "/random.php";
    private static final String BASE_SEARCH_BY_FIRST_LETTER = "/search.php?f=";
    private static final String BASE_SEARCH_INGREDIENT_BY_NAME = "/search.php?i=";
    private static final String BASE_SEARCH_COCKTAIL_BY_NAME = "/search.php?s=";
    private static final String BASE_COCKTAIL_BY_ID_LOOKUP = "/lookup.php?i=";
    private static final String BASE_COCKTAIL_SEARCH_BY_INGREDIENT_NAME = "/filter.php?i=";
    private static final String BASE_LOOKUP_INGREDIENT_BY_ID = "/lookup.php?iid=";

    private String replaceWhitespace(String term)
    {
        return String.join("+", term.split(" "));
    }

    @Override
    public String getRandomCocktail() {
        return makeGetRequest(BASE_URL + apiKey + RANDOM);
    }

    @Override
    public String searchCocktailsByLetter(char startingLetter) {
        return makeGetRequest(BASE_URL + apiKey + BASE_SEARCH_BY_FIRST_LETTER + startingLetter);
    }

    @Override
    public String getCocktailById(Object id) {
        return makeGetRequest(BASE_URL + apiKey + BASE_COCKTAIL_BY_ID_LOOKUP + id);
    }

    @Override
    public String getIngredientById(Object id) {
        return makeGetRequest(BASE_URL + apiKey + BASE_LOOKUP_INGREDIENT_BY_ID + id);
    }

    @Override
    public String searchCocktailByName(String term) {
        return makeGetRequest(BASE_URL + apiKey + BASE_SEARCH_COCKTAIL_BY_NAME + replaceWhitespace(term));
    }

    @Override
    public String getIngredientByName(String term) {
        return makeGetRequest(BASE_URL + apiKey + BASE_SEARCH_INGREDIENT_BY_NAME + replaceWhitespace(term));
    }


    @Override
    public String searchCocktailsByIngredientName(String ingredientName) {
        return makeGetRequest(BASE_URL + apiKey + BASE_COCKTAIL_SEARCH_BY_INGREDIENT_NAME + replaceWhitespace(ingredientName));
    }

    private String makeGetRequest(String url) {

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();

            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        }
        catch (IOException e) {
            logger.log(Level.SEVERE, e::getMessage);
        } catch (InterruptedException e) {
            logger.log(Level.SEVERE, e::getMessage);
            Thread.currentThread().interrupt();
        }

        return null;
    }
}
