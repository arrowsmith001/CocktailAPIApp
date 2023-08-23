package com.arrowsmith.cocktailapiapp;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.List;

@JsonSerialize
public class Cocktail {

    public Cocktail(String name)
    {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
    private List<Ingredient> ingredients;
    private String instructions;
    private String imageUrl;

    static Cocktail fromJsonNode(JsonNode node){

        JsonNode drinkName = node.get("strDrink");

        return new Cocktail(drinkName.textValue());
    }

    @Override
    public String toString() {
        return "Cocktail{" +
                "name='" + name + '\'' +
                ", ingredients=" + ingredients +
                ", instructions='" + instructions + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
