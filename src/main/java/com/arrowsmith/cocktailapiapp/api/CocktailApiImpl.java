package com.arrowsmith.cocktailapiapp.api;

import com.arrowsmith.cocktailapiapp.dto.CocktailDTO;
import com.arrowsmith.cocktailapiapp.dto.DTOMapper;
import com.arrowsmith.cocktailapiapp.dto.IngredientDTO;
import com.arrowsmith.cocktailapiapp.model.Cocktail;
import com.arrowsmith.cocktailapiapp.model.BasicCocktail;
import com.arrowsmith.cocktailapiapp.model.Ingredient;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.*;
import java.util.logging.Logger;


public class CocktailApiImpl implements CocktailApi {
    static Logger logger = Logger.getLogger(CocktailApiImpl.class.getName());


    public CocktailApiImpl(CocktailApiRequester requester)
    {
        this.requester = requester;
    }

    private final CocktailApiRequester requester;


    @Override
    public Cocktail getRandomCocktail() throws JsonProcessingException {

            final String response = requester.getRandomCocktail();

            final CocktailApiResponse cocktailApiResponse = CocktailApiResponse.deserialize(response);

            final CocktailDTO dto = cocktailApiResponse.getDrinks()[0];

            return DTOMapper.cocktailDTOtoFullModel(dto);


    }



    @Override
    public List<Cocktail> listCocktailsStartingWithLetter(char startingLetter) throws JsonProcessingException {

            final String response =  requester.listCocktailsStartingWithLetter(startingLetter);

            final CocktailApiResponse cocktailApiResponse = CocktailApiResponse.deserialize(response);

            if(cocktailApiResponse.getDrinks() == null) return new ArrayList<>();

            List<Cocktail> cocktails = new ArrayList<>(Arrays.stream(cocktailApiResponse.getDrinks())
                    .map(DTOMapper::cocktailDTOtoFullModel)
                    .toList());

            sortAlphabetically(cocktails);

            return cocktails;

    }


    @Override
    public Cocktail getCocktailById(Object id) throws JsonProcessingException {

            final String response = requester.getCocktailById(id);

            final CocktailApiResponse cocktailApiResponse = CocktailApiResponse.deserialize(response);

            final CocktailDTO dto = cocktailApiResponse.getDrinks()[0];

            return DTOMapper.cocktailDTOtoFullModel(dto);

    }

    @Override
    public Ingredient getIngredientById(Object id) throws JsonProcessingException {

            final String response = requester.getIngredientById(id);

            final CocktailApiResponse cocktailApiResponse = CocktailApiResponse.deserialize(response);

            final IngredientDTO dto = cocktailApiResponse.getIngredients()[0];

            return DTOMapper.ingredientDTOtoFullModel(dto);

    }

    @Override
    public List<Cocktail> listCocktailsByName(String term) throws JsonProcessingException {

            final String response = requester.listCocktailsByName(term);

            final CocktailApiResponse cocktailApiResponse = CocktailApiResponse.deserialize(response);

            return Arrays.stream(cocktailApiResponse.getDrinks())
                    .map(DTOMapper::cocktailDTOtoFullModel)
                    .toList();

    }

    @Override
    public List<Ingredient> listIngredientsByName(String term) throws JsonProcessingException {

            final String response = requester.getIngredientByName(term);

            final CocktailApiResponse cocktailApiResponse = CocktailApiResponse.deserialize(response);

            return Arrays.stream(cocktailApiResponse.getIngredients())
                    .map(DTOMapper::ingredientDTOtoFullModel)
                    .toList();


    }

    @Override
    public List<BasicCocktail> listCocktailsByIngredient(Object o) throws JsonProcessingException {

            String ingredientName;

            if(o instanceof Ingredient ingredient) ingredientName = ingredient.getName();
            else if(o instanceof String name) ingredientName = name;
            else ingredientName = o.toString();

            final String response = requester.listCocktailsByIngredient(ingredientName);

            final CocktailApiResponse cocktailApiResponse = CocktailApiResponse.deserialize(response);

            return Arrays.stream(cocktailApiResponse.getDrinks())
                    .map(DTOMapper::cocktailDTOtoBasicModel)
                    .toList();


    }



    private static void sortAlphabetically(List<Cocktail> cocktails) {
        cocktails.sort(Comparator.comparing(BasicCocktail::getName));
    }

}
