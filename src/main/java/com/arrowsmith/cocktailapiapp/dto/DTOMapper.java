package com.arrowsmith.cocktailapiapp.dto;

import com.arrowsmith.cocktailapiapp.model.Cocktail;
import com.arrowsmith.cocktailapiapp.model.Ingredient;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class DTOMapper {

    public static Cocktail cocktailDTOtoModel(CocktailDTO dto) throws NoSuchFieldException, IllegalAccessException {

        final Cocktail out = new Cocktail();

        out.setId(dto.id);
        out.setName(dto.cocktailName);
        out.setInstructions(dto.instructions);
        out.setImageUrl(dto.imageUrl);

        List<Ingredient> ingredients = new ArrayList<Ingredient>();

        for (int i = 1; i <= 15; i++) {

            Field ingredientField = CocktailDTO.class.getDeclaredField("strIngredient" + i);
            final Object ingredient = ingredientField.get(dto);

            Field measureField = CocktailDTO.class.getDeclaredField("strMeasure" + i);
            final Object measure = measureField.get(dto);

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
    public static Ingredient ingredientDTOtoModel(IngredientDTO dto) throws NoSuchFieldException, IllegalAccessException {

        final Ingredient out = new Ingredient();

        out.setId(dto.id);
        out.setName(dto.ingredientName);
        out.setDescription(dto.description);

        return out;
    }
}
