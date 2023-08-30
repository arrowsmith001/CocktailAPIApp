package com.arrowsmith.cocktailapiapp.api;

import com.arrowsmith.cocktailapiapp.dto.CocktailDTO;
import com.arrowsmith.cocktailapiapp.dto.IngredientDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.logging.Level;
import java.util.logging.Logger;


public class CocktailApiResponse {


    private CocktailDTO[] drinks;
    private IngredientDTO[] ingredients;


    public static CocktailApiResponse deserialize(String response) throws JsonProcessingException {

        final ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(response, CocktailApiResponse.class);

    }


    public CocktailDTO[] getDrinks() {
        return drinks;
    }


    public IngredientDTO[] getIngredients() {
        return ingredients;
    }

    public void setIngredients(IngredientDTO[] ingredients) {
        this.ingredients = ingredients;
    }


    public void setDrinks(CocktailDTO[] drinks) {
        this.drinks = drinks;
    }
}

