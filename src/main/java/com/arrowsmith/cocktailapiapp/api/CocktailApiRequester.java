package com.arrowsmith.cocktailapiapp.api;

public interface CocktailApiRequester {


    String getRandomCocktail();

    String searchCocktailsByLetter(char startingLetter);

    String getCocktailById(Object id);

    String getIngredientById(Object id);

    String searchCocktailByName(String term);

    String getIngredientByName(String term);

    String searchCocktailsByIngredientName(String ingredientName);
}
