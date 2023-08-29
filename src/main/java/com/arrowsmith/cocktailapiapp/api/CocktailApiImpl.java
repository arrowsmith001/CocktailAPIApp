package com.arrowsmith.cocktailapiapp.api;

import com.arrowsmith.cocktailapiapp.dto.CocktailDTO;
import com.arrowsmith.cocktailapiapp.dto.DTOMapper;
import com.arrowsmith.cocktailapiapp.dto.IngredientDTO;
import com.arrowsmith.cocktailapiapp.model.Cocktail;
import com.arrowsmith.cocktailapiapp.model.CocktailBase;
import com.arrowsmith.cocktailapiapp.model.Ingredient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CocktailApiImpl implements CocktailApi {
    static Logger logger = Logger.getLogger(CocktailApiImpl.class.getName());


    public CocktailApiImpl(CocktailApiRequester requester)
    {
        this.requester = requester;
    }
    private final ObjectMapper mapper = new ObjectMapper();


    private final CocktailApiRequester requester;


    @Override
    public Cocktail getRandomCocktail() {
        try {
            final String response = requester.getRandomCocktail();

            final CocktailApiResponse cocktailApiResponse = deserializeResponse(response);

            final CocktailDTO dto = cocktailApiResponse.getDrinks()[0];

            return DTOMapper.cocktailDTOtoFullModel(dto);

        } catch (Exception e) {
            logger.log(Level.SEVERE, e::getMessage);
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

            if(cocktailApiResponse.getDrinks() == null) return new ArrayList<>();

            List<Cocktail> cocktails = new ArrayList<>(Arrays.stream(cocktailApiResponse.getDrinks())
                    .map(DTOMapper::cocktailDTOtoFullModel)
                    .toList());

            sortAlphabetically(cocktails);

            return cocktails;

        } catch (Exception e) {
            logger.log(Level.SEVERE, e::getMessage);
            return new ArrayList<>();
        }
    }


    @Override
    public Cocktail getCocktailById(Object id) {
        try {
            final String response = requester.getCocktailById(id);

            final CocktailApiResponse cocktailApiResponse = deserializeResponse(response);

            final CocktailDTO dto = cocktailApiResponse.getDrinks()[0];

            return DTOMapper.cocktailDTOtoFullModel(dto);

        } catch (Exception e) {
            logger.log(Level.SEVERE, e::getMessage);
            return null;
        }
    }

    @Override
    public Ingredient getIngredientById(Object id) {
        try {
            final String response = requester.getIngredientById(id);

            final CocktailApiResponse cocktailApiResponse = deserializeResponse(response);

            final IngredientDTO dto = cocktailApiResponse.getIngredients()[0];

            return DTOMapper.ingredientDTOtoFullModel(dto);

        } catch (Exception e) {
            logger.log(Level.SEVERE, e::getMessage);
            return null;
        }
    }

    @Override
    public List<Cocktail> searchForCocktailByName(String term) {
        try {

            final String response = requester.searchCocktailByName(term);

            final CocktailApiResponse cocktailApiResponse = deserializeResponse(response);

            return Arrays.stream(cocktailApiResponse.getDrinks())
                    .map(DTOMapper::cocktailDTOtoFullModel)
                    .toList();

        } catch (Exception e) {
            logger.log(Level.SEVERE, e::getMessage);
            return new ArrayList<>();
        }
    }

    @Override
    public List<Ingredient> searchForIngredientByName(String term) {
        try {

            final String response = requester.getIngredientByName(term);

            final CocktailApiResponse cocktailApiResponse = deserializeResponse(response);

            return Arrays.stream(cocktailApiResponse.getIngredients())
                    .map(DTOMapper::ingredientDTOtoFullModel)
                    .toList();

        } catch (Exception e) {
            logger.log(Level.SEVERE, e::getMessage);
            return new ArrayList<>();
        }
    }

    @Override
    public List<CocktailBase> listCocktailsByIngredient(Object o) {
        try {
            String ingredientName;

            if(o instanceof Ingredient ingredient) ingredientName = ingredient.getName();
            else if(o instanceof String name) ingredientName = name;
            else ingredientName = o.toString();

            final String response = requester.searchCocktailsByIngredientName(ingredientName);

            final CocktailApiResponse cocktailApiResponse = deserializeResponse(response);

            return Arrays.stream(cocktailApiResponse.getDrinks())
                    .map(DTOMapper::cocktailDTOtoBasicModel)
                    .toList();

        } catch (Exception e) {
            logger.log(Level.SEVERE, e::getMessage);
            return new ArrayList<>();
        }
    }



    private static void sortAlphabetically(List<Cocktail> cocktails) {
        cocktails.sort(Comparator.comparing(CocktailBase::getName));
    }

}
