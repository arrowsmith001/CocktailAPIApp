package com.arrowsmith.cocktailapiapp.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@SpringBootTest
class IngredientTest {


    @Test
    @DisplayName("Ingredient getters should work")
    void testIngredientGetters()
    {
        final Ingredient ingredient = new Ingredient();

        ingredient.setId(0);
        ingredient.setName("Some Random Ingredient");
        ingredient.setMeasure("Some amount");
        ingredient.setDescription("Some description");

        assertEquals("Id doesn't match", ingredient.getId(), 0);
        assertEquals("Name doesn't match", ingredient.getName(), "Some Random Ingredient");
        assertEquals("Measure doesn't match", ingredient.getMeasure(), "Some amount");
        assertEquals("Search term doesn't match", ingredient.getSearchTerm(), "Some+Random+Ingredient");

    }
}