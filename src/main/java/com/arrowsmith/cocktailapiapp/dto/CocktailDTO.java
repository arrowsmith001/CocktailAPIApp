package com.arrowsmith.cocktailapiapp.dto;

import com.arrowsmith.cocktailapiapp.model.Cocktail;
import com.arrowsmith.cocktailapiapp.model.Ingredient;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@JsonSerialize
@JsonIgnoreProperties(ignoreUnknown = true)
public class CocktailDTO {



    @JsonProperty("idDrink")
    public int id;

    @JsonProperty("strDrink")
    // Drink name
    public String cocktailName;

    @JsonProperty("strInstructions")
    public String instructions;

    @JsonProperty("strDrinkThumb")
    public String imageUrl;

    // The ingredients for this drink (up to 15)
    public String strIngredient1;
    public String strIngredient2;
    public String strIngredient3;
    public String strIngredient4;
    public String strIngredient5;
    public String strIngredient6;
    public String strIngredient7;
    public String strIngredient8;
    public String strIngredient9;
    public String strIngredient10;
    public String strIngredient11;
    public String strIngredient12;
    public String strIngredient13;
    public String strIngredient14;
    public String strIngredient15;

    // Corresponding measures for the ingredients for this drink (up to 15)
    public String strMeasure1;
    public String strMeasure2;
    public String strMeasure3;
    public String strMeasure4;
    public String strMeasure5;
    public String strMeasure6;
    public String strMeasure7;
    public String strMeasure8;
    public String strMeasure9;
    public String strMeasure10;
    public String strMeasure11;
    public String strMeasure12;
    public String strMeasure13;
    public String strMeasure14;
    public String strMeasure15;


}
