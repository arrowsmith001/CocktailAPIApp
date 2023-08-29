package com.arrowsmith.cocktailapiapp.model;

public class MeasuredIngredient extends IngredientBase {

    public MeasuredIngredient(String ingredientName) {
        setName(ingredientName);
    }

    private String measure;

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

}
