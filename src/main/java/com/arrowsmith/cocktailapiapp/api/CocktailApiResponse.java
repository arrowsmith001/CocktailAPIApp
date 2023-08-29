package com.arrowsmith.cocktailapiapp.api;

import com.arrowsmith.cocktailapiapp.dto.CocktailDTO;
import com.arrowsmith.cocktailapiapp.dto.IngredientDTO;


public class CocktailApiResponse {


    private CocktailDTO[] drinks;
    private IngredientDTO[] ingredients;



    public CocktailDTO[] getDrinks() {
        return drinks;
    }


    public IngredientDTO[] getIngredients() {
        return ingredients;
    }

    public void setIngredients(IngredientDTO[] ingredients) {
        this.ingredients = ingredients;
    }


}

