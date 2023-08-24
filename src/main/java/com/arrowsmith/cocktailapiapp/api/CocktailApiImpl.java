package com.arrowsmith.cocktailapiapp.api;

import com.arrowsmith.cocktailapiapp.CocktailApiAppApplication;
import com.arrowsmith.cocktailapiapp.CocktailDTO;
import com.arrowsmith.cocktailapiapp.model.Cocktail;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class CocktailApiImpl implements CocktailApi {

    static Logger logger = Logger.getLogger(CocktailApiAppApplication.class.getName());

    private final String baseUrl = "https://www.thecocktaildb.com/api/json/v1/";
    private final String apiKey = "1";

    private String getBaseUrlWithApiKey(String append) {
        return baseUrl + apiKey + append;
    }

    private String random = "/random.php";
    private String baseSearch = "/search.php?f=";
    private String baseIdSearch = "/lookup.php?i=";
    private String getSearchByLetter(char c) {
        return  baseSearch + Character.toString(c).toLowerCase();
    }
    private String getSearchById(Object id) {
        return  baseIdSearch + id.toString();
    }

    private String makeGetRequest(String url) throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    @Override
    public Cocktail getRandomCocktail() {
        try {
            final String response = makeGetRequest(getBaseUrlWithApiKey(random));
            System.out.println(response.toString());

            final ObjectMapper mapper = new ObjectMapper();
            final CocktailApiResponse cocktailApiResponse = mapper.readValue(response, CocktailApiResponse.class);

            return cocktailApiResponse.drinks[0].toModel();

        } catch (Exception e) {
            System.out.println(e);
            return null;
        }

    }

    @Override
    public List<Cocktail> getCocktailsStartingWithLetter(char startingLetter) {

        try {
            final String response = makeGetRequest(getBaseUrlWithApiKey(getSearchByLetter(startingLetter)));
            System.out.println(response.toString());

            final ObjectMapper mapper = new ObjectMapper();
            final CocktailApiResponse cocktailApiResponse = mapper.readValue(response, CocktailApiResponse.class);

            return Arrays.stream(cocktailApiResponse.drinks).map(dto -> {
                try {
                    return dto.toModel();
                } catch (NoSuchFieldException e) {
                    throw new RuntimeException(e);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }).toList();

        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @Override
    public Cocktail getCocktailById(Object id) {
        try {
            final String response = makeGetRequest(getBaseUrlWithApiKey(getSearchById(id)));
            System.out.println(response.toString());

            final ObjectMapper mapper = new ObjectMapper();
            final CocktailApiResponse cocktailApiResponse = mapper.readValue(response, CocktailApiResponse.class);

            return cocktailApiResponse.drinks[0].toModel();

        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
