package com.arrowsmith.cocktailapiapp.api;

import com.arrowsmith.cocktailapiapp.model.Cocktail;
import com.arrowsmith.cocktailapiapp.model.BasicCocktail;
import com.arrowsmith.cocktailapiapp.model.Ingredient;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;


public interface CocktailApi {

    Cocktail getCocktailById(Object id) throws JsonProcessingException;
    Ingredient getIngredientById(Object id) throws JsonProcessingException;
    List<Cocktail> listCocktailsByName(String term) throws JsonProcessingException;
    List<Ingredient> listIngredientsByName(String term) throws JsonProcessingException;
    List<Cocktail> listCocktailsStartingWithLetter(char startingLetter) throws JsonProcessingException;
    List<BasicCocktail> listCocktailsByIngredient(Object ingredient) throws JsonProcessingException;
    Cocktail getRandomCocktail() throws JsonProcessingException;
}

