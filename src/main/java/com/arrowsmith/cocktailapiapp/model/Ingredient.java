package com.arrowsmith.cocktailapiapp.model;

import jakarta.persistence.criteria.CriteriaBuilder;

// TODO: Image url https://www.thecocktaildb.com/images/ingredients/blueberries-Small.png
public class Ingredient {

    public Ingredient(){}

    public Ingredient(String name)
    {
        setName(name);
    }

    private int id;
    private String name;
    private String measure;
    private String description;

    public String getSearchTerm(){
        return String.join("+", name.split(" "));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
