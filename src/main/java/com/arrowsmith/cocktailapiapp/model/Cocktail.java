package com.arrowsmith.cocktailapiapp.model;

import java.util.List;
import java.util.Objects;

public class Cocktail extends BasicCocktail
{
    public Cocktail(){}
    public Cocktail(String name){
        setName(name);
    }

    private List<Instructions> instructions;
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

    public List<Instructions> getInstructions() {
        return instructions;
    }

    public void setInstructions(List<Instructions> instructions) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Cocktail cocktail = (Cocktail) o;
        return Objects.equals(instructions, cocktail.instructions) && Objects.equals(measuredIngredients, cocktail.measuredIngredients) && Objects.equals(glass, cocktail.glass) && Objects.equals(category, cocktail.category) && Objects.equals(alcoholic, cocktail.alcoholic);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), instructions, measuredIngredients, glass, category, alcoholic);
    }
}
