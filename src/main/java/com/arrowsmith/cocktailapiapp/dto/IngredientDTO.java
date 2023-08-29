package com.arrowsmith.cocktailapiapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
public class IngredientDTO {

    @JsonProperty("idIngredient")
    public int id;
    @JsonProperty("strIngredient")
    public String ingredientName;
    @JsonProperty("strDescription")
    public String description;
    @JsonProperty("strType")
    public String type;
    @JsonProperty("strAlcohol")
    public String alcoholicYesOrNo;
    @JsonProperty("strABV")
    public double abv;

}
