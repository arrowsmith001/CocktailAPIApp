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
    private static final String baseUrl = "https://www.thecocktaildb.com/api/json/v1/";
    private static final String random = "/random.php";
    private static final String baseSearchByFirstLetter = "/search.php?f=";
    private static final String baseSearchIngredientByName = "/search.php?i=";
    private static final String baseSearchCocktailByName = "/search.php?s=";
    private static final String baseCocktailByIdLookup = "/lookup.php?i=";
    private static final String baseCocktailSearchByIngredientName = "/filter.php?i=";
    private static final String baseLookupIngredientById = "/lookup.php?iid=";

    private String replaceWhitespace(String term)
    {
        return String.join("+", term.split(" "));
    }

    @Override
    public String getRandomCocktail() {
        return makeGetRequest(baseUrl + apiKey + random);
    }

    @Override
    public String searchCocktailsByLetter(char startingLetter) {
        return makeGetRequest(baseUrl + apiKey + baseSearchByFirstLetter + startingLetter);
    }

    @Override
    public String getCocktailById(Object id) {
        return makeGetRequest(baseUrl + apiKey + baseCocktailByIdLookup + id);
    }

    @Override
    public String getIngredientById(Object id) {
        return makeGetRequest(baseUrl + apiKey + baseLookupIngredientById + id);
    }

    @Override
    public String searchCocktailByName(String term) {
        return makeGetRequest(baseUrl + apiKey + baseSearchCocktailByName + replaceWhitespace(term));
    }

    @Override
    public String getIngredientByName(String term) {
        return makeGetRequest(baseUrl + apiKey + baseSearchIngredientByName + replaceWhitespace(term));
    }


    @Override
    public String searchCocktailsByIngredientName(String ingredientName) {
        return makeGetRequest(baseUrl + apiKey + baseCocktailSearchByIngredientName + replaceWhitespace(ingredientName));
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
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            logger.log(Level.WARNING, e::getMessage);
            Thread.currentThread().interrupt();
        }

        return null;
    }
}