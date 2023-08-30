package com.arrowsmith.cocktailapiapp.model;


public class Ingredient extends BasicIngredient {

    public Ingredient(){}

    public Ingredient(String name)
    {
        setName(name);
    }

    private int id;
    private String measure;
    private String description;
    private String type;

    private boolean isAlcoholic;
    private double abv;


    public String getSearchTerm(){
        return String.join("+", getName().split(" "));
    }

    public boolean isAlcoholic() {
        return isAlcoholic;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public void setAlcoholic(boolean alcoholic) {
        isAlcoholic = alcoholic;
    }

    public double getAbv() {
        return abv;
    }

    public void setAbv(double abv) {
        this.abv = abv;
    }
}
