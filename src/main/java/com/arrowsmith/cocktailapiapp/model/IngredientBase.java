package com.arrowsmith.cocktailapiapp.model;

public class IngredientBase {

    public IngredientBase() {}

    public IngredientBase(String name) {
        setName(name);
    }

    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
