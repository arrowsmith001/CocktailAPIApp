package com.arrowsmith.cocktailapiapp.api;

public interface CocktailApiRequester {

    String getCocktailById(Object id);
    String getIngredientById(Object id);
    String getIngredientByName(String term);
    String listCocktailsByName(String term);
    String getRandomCocktail();
    String listCocktailsStartingWithLetter(char startingLetter);
    String listCocktailsByIngredient(String ingredientName);
}
