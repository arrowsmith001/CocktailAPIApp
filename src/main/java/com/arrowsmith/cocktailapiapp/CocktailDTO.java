package com.arrowsmith.cocktailapiapp;

import com.arrowsmith.cocktailapiapp.model.Cocktail;
import com.arrowsmith.cocktailapiapp.model.Ingredient;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.checkerframework.checker.units.qual.C;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@JsonSerialize
@JsonIgnoreProperties(ignoreUnknown = true)
public class CocktailDTO {

    public Cocktail toModel() throws NoSuchFieldException, IllegalAccessException {

        final Cocktail out = new Cocktail();

        out.setName(strDrink);
        out.setInstructions(strInstructions);

        List<Ingredient> ingredients = new ArrayList<Ingredient>();

        for (int i = 1; i <= 15; i++) {

            Field ingredientField = getClass().getDeclaredField("strIngredient" + i);
            final Object ingredient = ingredientField.get(this);

            Field measureField = getClass().getDeclaredField("strMeasure" + i);
            final Object measure = measureField.get(this);

            if(ingredient != null && measure != null)
            {
                final Ingredient newIngredient = new Ingredient();

                final String nameString = ((String) ingredient).trim();
                final String measureString = ((String) measure).trim();

                newIngredient.setName(nameString);
                newIngredient.setMeasure(measureString);

                ingredients.add(newIngredient);
            }
        }

        out.setIngredients(ingredients);

        return out;
    }

    /**
     * Drink ID
     */
    public int idDrink;

    /**
     * Drink name
     */
    // Drink name
    public String strDrink;

    /**
       Drink preparation instructions in English
     */
    public String strInstructions;

    /**
     Thumbnail image link
     */
    public String strDrinkThumb;

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
