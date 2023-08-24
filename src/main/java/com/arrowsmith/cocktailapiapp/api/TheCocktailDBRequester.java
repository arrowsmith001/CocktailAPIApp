package com.arrowsmith.cocktailapiapp.api;


import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

class TheCocktailDBRequester implements CocktailApiRequester {
    public TheCocktailDBRequester(String apiKey) {
        this.apiKey = apiKey;
    }

    private final String apiKey;
    private final String baseUrl = "https://www.thecocktaildb.com/api/json/v1/";
    private final String random = "/random.php";
    private final String baseSearchByFirstLetter = "/search.php?f=";
    private final String baseSearchIngredientByName = "/search.php?i=";
    private final String baseSearchCocktailByName = "/search.php?s=";
    private final String baseCocktailByIdLookup = "/lookup.php?i=";
    private final String baseCocktailSearchByIngredientName = "/filter.php?i=";
    private final String baseLookupIngredientById = "/lookup.php?iid=";

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
        catch (Exception e)
        {
            // TODO: Log
            return null;
        }
    }
}