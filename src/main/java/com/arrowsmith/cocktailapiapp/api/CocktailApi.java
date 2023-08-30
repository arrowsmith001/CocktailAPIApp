package com.arrowsmith.cocktailapiapp.api;

import com.arrowsmith.cocktailapiapp.model.Cocktail;
import com.arrowsmith.cocktailapiapp.model.BasicCocktail;
import com.arrowsmith.cocktailapiapp.model.Ingredient;

import java.util.List;


public interface CocktailApi {

    Cocktail getCocktailById(Object id);
    Ingredient getIngredientById(Object id);
    List<Cocktail> listCocktailsByName(String term);
    List<Ingredient> listIngredientsByName(String term);
    List<Cocktail> listCocktailsStartingWithLetter(char startingLetter);
    List<BasicCocktail> listCocktailsByIngredient(Object ingredient);
    Cocktail getRandomCocktail();
}

