package com.arrowsmith.cocktailapiapp.api;

import com.arrowsmith.cocktailapiapp.CocktailApiAppApplication;
import com.arrowsmith.cocktailapiapp.dto.CocktailDTO;
import com.arrowsmith.cocktailapiapp.dto.DTOMapper;
import com.arrowsmith.cocktailapiapp.dto.IngredientDTO;
import com.arrowsmith.cocktailapiapp.model.Cocktail;
import com.arrowsmith.cocktailapiapp.model.Ingredient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class CocktailApiImpl implements CocktailApi {
    static Logger logger = Logger.getLogger(CocktailApiAppApplication.class.getName());

    public CocktailApiImpl(String apiKey)
    {
        requester = new TheCocktailDBRequester(apiKey);
    }

    final CocktailApiRequester requester;
    final ObjectMapper mapper = new ObjectMapper();



    @Override
    public Cocktail getRandomCocktail() {
        try {
            final String response = requester.getRandomCocktail();

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
            final String response =  requester.searchCocktailsByLetter(startingLetter);

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
            final String response = requester.getCocktailById(id);

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
            final String response = requester.getIngredientById(id);

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

            final String response = requester.searchCocktailByName(term);

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

            final String response =requester.getIngredientByName(term);

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

            final String response = requester.searchCocktailsByIngredientName(ingredientName);

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




}
