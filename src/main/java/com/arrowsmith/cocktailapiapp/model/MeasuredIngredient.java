package com.arrowsmith.cocktailapiapp.model;

public class MeasuredIngredient extends BasicIngredient {

    public MeasuredIngredient(String ingredientName, String ingredientMeasure) {
        setName(ingredientName);
        setMeasure(ingredientMeasure);
    }

    private String measure;

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

}
