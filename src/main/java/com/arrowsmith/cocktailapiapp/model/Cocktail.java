package com.arrowsmith.cocktailapiapp.model;

import java.util.List;

public class Cocktail extends CocktailBase
{
    private String instructions;
    private List<Ingredient> ingredients;


    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }


    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }


}
