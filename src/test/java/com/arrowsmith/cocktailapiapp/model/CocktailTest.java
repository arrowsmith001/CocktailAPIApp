package com.arrowsmith.cocktailapiapp.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.springframework.test.util.AssertionErrors.*;

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

    @Test
    @DisplayName("Unequal cocktails are unequal")
    void testInequality()
    {
        final Cocktail cocktail = new Cocktail();
        cocktail.setName("Mojito");

        final Cocktail cocktail2 = new Cocktail();
        cocktail.setName("Margarita");


        assertFalse("Cocktails match", cocktail.equals(cocktail2));
        assertFalse("Objects match", cocktail.equals("Frog"));

    }
    @Test
    @DisplayName("Unequal cocktails are unequal")
    void testConstructorWithName()
    {
        final Cocktail cocktail = new Cocktail("Mojito");

        assertEquals("Cocktails does not have expected name", cocktail.getName(), "Mojito");

    }

}