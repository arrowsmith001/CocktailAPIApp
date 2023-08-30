package com.arrowsmith.cocktailapiapp.dto;

import com.arrowsmith.cocktailapiapp.api.CocktailApiImpl;
import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


@JsonIgnoreProperties(ignoreUnknown = true)
public class CocktailDTO {

    @JsonProperty("idDrink")
    private int id;

    @JsonProperty("strDrink")

    // Drink name
    private String cocktailName;

    @JsonProperty("strDrinkThumb")
    private String imageUrl;

    @JsonProperty("strGlass")
    private String glass;
    @JsonProperty("strAlcoholic")
    private String alcoholic;
    @JsonProperty("strCategory")
    private String category;

    private final Map<String, String> ingredientNames = new HashMap<>();
    private final Map<String, String> ingredientMeasures = new HashMap<>();
    private final Map<String, String> instructions = new HashMap<>();

    @JsonAnySetter
    public void setOtherField(String name, Object value) {

        if(value == null) return;

        if(name.startsWith("strIngredient"))
        {
            ingredientNames.put(name, (String) value);
        }
        else if(name.startsWith("strMeasure"))
        {
            ingredientMeasures.put(name, (String) value);
        }
        else if(name.startsWith("strInstructions"))
        {
            instructions.put(name, (String) value);
        }
    }



    public Map<String, String> getIngredientNames() {
        return ingredientNames;
    }

    public Map<String, String> getIngredientMeasures() {
        return ingredientMeasures;
    }

    public Map<String, String> getInstructions() {
        return instructions;
    }



    public String getGlass() {
        return glass;
    }

    public void setGlass(String glass) {
        this.glass = glass;
    }

    public String getAlcoholic() {
        return alcoholic;
    }

    public void setAlcoholic(String alcoholic) {
        this.alcoholic = alcoholic;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCocktailName() {
        return cocktailName;
    }

    public void setCocktailName(String cocktailName) {
        this.cocktailName = cocktailName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

}
