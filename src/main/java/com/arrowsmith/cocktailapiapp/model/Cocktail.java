package com.arrowsmith.cocktailapiapp.model;

import com.arrowsmith.cocktailapiapp.model.instructions.InstructionsInLanguage;

import java.util.List;

public class Cocktail extends CocktailBase
{
    private List<InstructionsInLanguage> instructions;
    private List<MeasuredIngredient> measuredIngredients;

    private String glass;

    private String category;

    private String alcoholic;



    public List<MeasuredIngredient> getMeasuredIngredients() {
        return measuredIngredients;
    }

    public void setMeasuredIngredients(List<MeasuredIngredient> measuredIngredients) {
        this.measuredIngredients = measuredIngredients;
    }



    public String getGlass() {
        return glass;
    }

    public String getCategory() {
        return category;
    }

    public String getAlcoholic() {
        return alcoholic;
    }

    public List<InstructionsInLanguage> getInstructions() {
        return instructions;
    }

    public void setInstructions(List<InstructionsInLanguage> instructions) {
        this.instructions = instructions;
    }

    public void setGlass(String glass) {
        this.glass = glass;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setAlcoholic(String alcoholic) {
        this.alcoholic = alcoholic;
    }
}
