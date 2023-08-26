package com.arrowsmith.cocktailapiapp.dto;

import com.arrowsmith.cocktailapiapp.model.Cocktail;
import com.arrowsmith.cocktailapiapp.model.CocktailBase;
import com.arrowsmith.cocktailapiapp.model.Ingredient;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DTOMapper {
    private DTOMapper(){}
    static Logger logger = Logger.getLogger(DTOMapper.class.getName());

    public static CocktailBase cocktailDTOtoBasicModel(CocktailDTO dto) {

        final CocktailBase out = new CocktailBase();

        out.setId(dto.id);
        out.setName(dto.cocktailName);
        out.setImageUrl(dto.imageUrl);

        return out;
    }

    public static Cocktail cocktailDTOtoFullModel(CocktailDTO dto) {

        final Cocktail out = new Cocktail();

        out.setId(dto.id);
        out.setName(dto.cocktailName);
        out.setImageUrl(dto.imageUrl);
        out.setInstructions(dto.instructions);

        List<Ingredient> ingredients = new ArrayList<>();

        for (int i = 1; i <= 15; i++) {

            try
            {
                final Ingredient newIngredient = new Ingredient();

                Method ingredientGetter = CocktailDTO.class.getMethod("getStrIngredient" + i);
                final Object ingredient = ingredientGetter.invoke(dto);

                Method measureGetter = CocktailDTO.class.getMethod("getStrMeasure" + i);
                final Object measure = measureGetter.invoke(dto);

                if(ingredient != null)
                {
                    final String nameString = ((String) ingredient).trim();
                    newIngredient.setName(nameString);

                    if(measure != null)
                    {
                        final String measureString = ((String) measure).trim();
                        newIngredient.setMeasure(measureString);
                    }

                    ingredients.add(newIngredient);
                }
            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                logger.log(Level.SEVERE, e::getMessage);
            }

        }

        out.setIngredients(ingredients);

        return out;
    }

    public static Ingredient ingredientDTOtoModel(IngredientDTO dto) {

        final Ingredient out = new Ingredient();

        out.setId(dto.id);
        out.setName(dto.ingredientName);
        out.setDescription(dto.description);

        return out;
    }
}
