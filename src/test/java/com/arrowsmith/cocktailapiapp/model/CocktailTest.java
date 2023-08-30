package com.arrowsmith.cocktailapiapp.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.springframework.test.util.AssertionErrors.assertEquals;

class CocktailTest {


    @Test
    @DisplayName("Ingredient getters should work")
    void testCocktailGetters()
    {
        final Cocktail cocktail = new Cocktail();

        cocktail.setId(0);
        cocktail.setName("Some Random Cocktail");
        //cocktail.setInstructions(new ArrayList<>());

        assertEquals("Id doesn't match", cocktail.getId(), 0);
        assertEquals("Name doesn't match", cocktail.getName(), "Some Random Cocktail");
        //assertEquals("Instructions don't match", cocktail.getInstructions()[0], "Some instructions");

    }

}