package com.arrowsmith.cocktailapiapp.api;

import com.arrowsmith.cocktailapiapp.CocktailApiAppApplication;
import com.arrowsmith.cocktailapiapp.dto.CocktailDTO;
import com.arrowsmith.cocktailapiapp.dto.DTOMapper;
import com.arrowsmith.cocktailapiapp.dto.IngredientDTO;
import com.arrowsmith.cocktailapiapp.model.Cocktail;
import com.arrowsmith.cocktailapiapp.model.Ingredient;
import com.fasterxml.jackson.core.JsonProcessingException;
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
    public CocktailApiImpl(String apiKey)
    {
        requests = new CocktailApiImplGetRequests(apiKey);
    }

    final CocktailApiImplGetRequests requests;
    final ObjectMapper mapper = new ObjectMapper();


    @Override
    public Cocktail getRandomCocktail() {
        try {
            final String response = makeGetRequest(requests.getRandom());

            final CocktailApiResponse cocktailApiResponse = deserializeResponse(response);

            final CocktailDTO dto = cocktailApiResponse.drinks[0];

            return DTOMapper.cocktailDTOtoModel(dto);

        } catch (Exception e) {
            System.out.println(e);
            return null;
        }

    }

    private CocktailApiResponse deserializeResponse(String response) throws JsonProcessingException {
        return mapper.readValue(response, CocktailApiResponse.class);
    }

    @Override
    public List<Cocktail> getCocktailsStartingWithLetter(char startingLetter) {

        try {
            final String response = makeGetRequest(requests.getSearchByLetterRequest(startingLetter));

            final CocktailApiResponse cocktailApiResponse = deserializeResponse(response);

            return Arrays.stream(cocktailApiResponse.drinks).map(dto -> {
                try {
                    return DTOMapper.cocktailDTOtoModel(dto);
                } catch (NoSuchFieldException | IllegalAccessException e) {
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
            final String response = makeGetRequest(requests.getSearchById(id));

            final CocktailApiResponse cocktailApiResponse = deserializeResponse(response);

            final CocktailDTO dto = cocktailApiResponse.drinks[0];

            return DTOMapper.cocktailDTOtoModel(dto);

        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @Override
    public Ingredient getIngredientById(Object id) {
        try {
            final String response = makeGetRequest(requests.getIngredientById(id));

            final CocktailApiResponse cocktailApiResponse = deserializeResponse(response);

            final IngredientDTO dto = cocktailApiResponse.ingredients[0];

            return DTOMapper.ingredientDTOtoModel(dto);

        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @Override
    public List<Cocktail> searchForCocktailByName(String term) {
        try {
            final String response = makeGetRequest(requests.getSearchByName(term));

            final CocktailApiResponse cocktailApiResponse = deserializeResponse(response);

            return Arrays.stream(cocktailApiResponse.drinks).map(dto -> {
                try {
                    return DTOMapper.cocktailDTOtoModel(dto);
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }).toList();

        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @Override
    public List<Ingredient> searchForIngredientByName(String term) {
        try {
            final String formattedTerm = String.join("+", term.split(" "));

            final String response = makeGetRequest(requests.getIngredientByName(formattedTerm));

            final CocktailApiResponse cocktailApiResponse = deserializeResponse(response);

            return Arrays.stream(cocktailApiResponse.ingredients).map(dto -> {
                try {
                    return DTOMapper.ingredientDTOtoModel(dto);
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }).toList();

        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @Override
    public List<Cocktail> listCocktailsByIngredient(Object ingredient) {
        try {
            String ingredientName;
            if(ingredient instanceof Ingredient) ingredientName = ((Ingredient) ingredient).getName();
            else ingredientName = ingredient.toString();

            ingredientName = String.join("+", ingredientName.split(" "));

            final String response = makeGetRequest(requests.getSearchCocktailByIngredientName((String) ingredientName));

            final CocktailApiResponse cocktailApiResponse = deserializeResponse(response);

            return Arrays.stream(cocktailApiResponse.drinks).map(dto -> {
                try {
                    return DTOMapper.cocktailDTOtoModel(dto);
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }).toList();

        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }


    private String makeGetRequest(String url) throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

}
