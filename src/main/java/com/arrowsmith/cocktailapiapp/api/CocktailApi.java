package com.arrowsmith.cocktailapiapp.api;

import com.arrowsmith.cocktailapiapp.model.Cocktail;
import com.arrowsmith.cocktailapiapp.model.Ingredient;

import java.util.List;


public interface CocktailApi {

    Cocktail getRandomCocktail();
    List<Cocktail> getCocktailsStartingWithLetter(char startingLetter);

    Cocktail getCocktailById(Object id);

    List<Cocktail> searchForCocktailByName(String term);
    List<Ingredient> searchForIngredientByName(String term);

    List<Cocktail> listCocktailsByIngredient(Object ingredient);
    Ingredient getIngredientById(Object id);
}

