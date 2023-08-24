package com.arrowsmith.cocktailapiapp.api;

import com.arrowsmith.cocktailapiapp.CocktailDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
@JsonIgnoreProperties(ignoreUnknown = true)
public class CocktailApiResponse {

    public CocktailDTO[] drinks;

}
