package com.arrowsmith.cocktailapiapp.api;

import com.arrowsmith.cocktailapiapp.dto.CocktailDTO;
import com.arrowsmith.cocktailapiapp.dto.IngredientDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
@JsonIgnoreProperties(ignoreUnknown = true)
public class CocktailApiResponse {

    public CocktailDTO[] drinks;

    public IngredientDTO[] ingredients;

}
