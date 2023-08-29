package com.arrowsmith.cocktailapiapp.api;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.logging.*;

public class TheCocktailDBRequester implements CocktailApiRequester {
    static Logger logger = Logger.getLogger(TheCocktailDBRequester.class.getName());

    public TheCocktailDBRequester(String apiKey) {

        urls = new TheCocktailDBUrls(apiKey);
    }

    private final TheCocktailDBUrls urls;


    @Override
    public String getRandomCocktail() {
        final String url = urls.getRandomCocktailUrl();
        return makeGetRequest(url);
    }

    @Override
    public String searchCocktailsByLetter(char startingLetter) {
        final String url = urls.searchCocktailsByLetterUrl(startingLetter);
        return makeGetRequest(url);
    }

    @Override
    public String getCocktailById(Object id) {
        final String url = urls.getCocktailByIdUrl(id);
        return makeGetRequest(url);
    }

    @Override
    public String getIngredientById(Object id) {
        final String url = urls.getIngredientByIdUrl(id);
        return makeGetRequest(url);
    }

    @Override
    public String searchCocktailByName(String term) {
        final String url = urls.searchCocktailByNameUrl(term);
        return makeGetRequest(url);
    }

    @Override
    public String getIngredientByName(String term) {
        final String url = urls.getIngredientByNameUrl(term);
        return makeGetRequest(url);
    }


    @Override
    public String searchCocktailsByIngredientName(String ingredientName) {
        final String url = urls.searchCocktailsByIngredientNameUrl(ingredientName);
        return makeGetRequest(url);
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
