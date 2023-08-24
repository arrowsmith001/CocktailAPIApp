package com.arrowsmith.cocktailapiapp.api;

import com.arrowsmith.cocktailapiapp.CocktailDTO;
import com.arrowsmith.cocktailapiapp.model.Cocktail;

import java.util.List;


public interface CocktailApi {

    Cocktail getRandomCocktail();
    List<Cocktail> getCocktailsStartingWithLetter(char startingLetter);

    Cocktail getCocktailById(Object id);
}

