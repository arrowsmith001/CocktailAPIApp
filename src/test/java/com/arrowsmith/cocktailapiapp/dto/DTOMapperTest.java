package com.arrowsmith.cocktailapiapp.dto;

import com.arrowsmith.cocktailapiapp.api.*;
import com.arrowsmith.cocktailapiapp.model.Cocktail;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.springframework.test.util.AssertionErrors.assertTrue;

class DTOMapperTest {

    private String getApiKey(){
        return System.getenv().get("THE_COCKTAIL_DB_API_KEY");
    }

    final CocktailApiRequester requester = new TheCocktailDBRequester(getApiKey());


    @Test
    @DisplayName("All cocktail ingredients referred to in the DTO should be represented in the model")
    void testCocktailIngredientsDTOMapping() throws JsonProcessingException {

        final String responseBody = requester.getRandomCocktail();
        final CocktailApiResponse response = CocktailApiResponse.deserialize(responseBody);

        final CocktailDTO dto = response.getDrinks()[0];
        final Cocktail cocktail = DTOMapper.cocktailDTOtoFullModel(dto);

        final Set<String> ingredientsInDTO = new HashSet<>(dto.getIngredientNames().values());

        for(String name : ingredientsInDTO)
        {
            final boolean doesExistInModel = cocktail.getMeasuredIngredients()
                    .stream()
                    .anyMatch(ing -> ing.getName().equalsIgnoreCase(name));

            assertTrue("Ingredient '" + name + "' not found in model for '" + cocktail.getName() + "'", doesExistInModel);
        }
    }

    @Test
    @DisplayName("All cocktail preparation instructions referred to in the DTO should be represented in the model")
    void testCocktailInstructionsDTOMapping() throws JsonProcessingException {

        final String responseBody = requester.getRandomCocktail();
        final CocktailApiResponse response = CocktailApiResponse.deserialize(responseBody);

        final CocktailDTO dto = response.getDrinks()[0];
        final Cocktail cocktail = DTOMapper.cocktailDTOtoFullModel(dto);

        final Set<String> instructionsInDTO = new HashSet<>(dto.getInstructions().keySet());

        for(String instrKey : instructionsInDTO)
        {
            final String instrString = dto.getInstructions().get(instrKey);

            final boolean doesExistInModel = cocktail.getInstructions()
                    .stream()
                    .anyMatch(ing -> ing.getInstructionString().equalsIgnoreCase(instrString));

            assertTrue("Instruction string with key '" + instrKey + "' not found in model", doesExistInModel);
        }
    }
}