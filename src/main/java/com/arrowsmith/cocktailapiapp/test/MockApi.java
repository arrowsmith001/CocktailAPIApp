package com.arrowsmith.cocktailapiapp.test;

import com.arrowsmith.cocktailapiapp.api.CocktailApi;
import com.arrowsmith.cocktailapiapp.model.Cocktail;
import com.arrowsmith.cocktailapiapp.model.Ingredient;

import java.util.List;

// TODO: Implement Mock API
public class MockApi implements CocktailApi {


    @Override
    public Cocktail getRandomCocktail() {
        return null;
    }

    @Override
    public List<Cocktail> getCocktailsStartingWithLetter(char startingLetter) {
        return null;
    }

    @Override
    public Cocktail getCocktailById(Object id) {
        return null;
    }

    @Override
    public List<Cocktail> searchForCocktailByName(String term) {
        return null;
    }

    @Override
    public List<Ingredient> searchForIngredientByName(String term) {
        return null;
    }

    @Override
    public List<Cocktail> listCocktailsByIngredient(Object ingredient) {
        return null;
    }

    @Override
    public Ingredient getIngredientById(Object id) {
        return null;
    }
}
