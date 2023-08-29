package com.arrowsmith.cocktailapiapp.dto;

import com.arrowsmith.cocktailapiapp.model.*;

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

        setBasicCocktailInfo(out, dto);

        return out;
    }

    public static Cocktail cocktailDTOtoFullModel(CocktailDTO dto) {

        final Cocktail out = new Cocktail();

        // Info
        setBasicCocktailInfo(out, dto);
        setAdditionalCocktailInfo(out, dto);

        // Instructions
        setCocktailInstructions(out, dto);

        // Ingredients
        setCocktailIngredients(out, dto);

        return out;
    }

    public static Ingredient ingredientDTOtoFullModel(IngredientDTO dto) {

        final Ingredient out = new Ingredient();

        out.setId(dto.id);
        out.setName(dto.ingredientName);
        out.setDescription(dto.description);
        out.setAlcoholic(dto.alcoholicYesOrNo.equalsIgnoreCase("Yes"));
        out.setType(dto.type);
        out.setAbv(dto.abv);

        return out;
    }

    private static void setBasicCocktailInfo(CocktailBase cocktailBase, CocktailDTO dto) {
        cocktailBase.setId(dto.getId());
        cocktailBase.setName(dto.getCocktailName());
        cocktailBase.setImageUrl(dto.getImageUrl());
    }

    private static void setAdditionalCocktailInfo(Cocktail cocktail, CocktailDTO dto) {
        cocktail.setGlass(dto.getGlass());
        cocktail.setCategory(dto.getCategory());
        cocktail.setAlcoholic(dto.getAlcoholic());
    }

    private static void setCocktailInstructions(Cocktail cocktail, CocktailDTO dto) {

        List<Instructions> instructions = new ArrayList<>();

        final String englishInstructions = dto.getInstructionsInEnglish();

        if(englishInstructions != null)
        {
            instructions.add(new Instructions(englishInstructions, Language.ENGLISH));
        }

        final String frenchInstructions = dto.getInstructionsInFrench();
        if(frenchInstructions != null)
        {
            instructions.add(new Instructions(frenchInstructions, Language.FRENCH));
        }

        final String spanishInstructions = dto.getInstructionsInSpanish();
        if(spanishInstructions != null)
        {
            instructions.add(new Instructions(spanishInstructions, Language.SPANISH));
        }

        final String germanInstructions = dto.getInstructionsInGerman();
        if(germanInstructions != null)
        {
            instructions.add(new Instructions(germanInstructions, Language.GERMAN));
        }

        final String italianInstructions = dto.getInstructionsInItalian();
        if(italianInstructions != null)
        {
            instructions.add(new Instructions(italianInstructions, Language.ITALIAN));
        }

        cocktail.setInstructions(instructions);

    }

    private static void setCocktailIngredients(Cocktail cocktail, CocktailDTO dto) {

        List<MeasuredIngredient> ingredients = new ArrayList<>();

        for (int i = 1; i <= 15; i++) {

            try
            {

                Method ingredientGetter = CocktailDTO.class.getMethod("getStrIngredient" + i);
                final Object ingredient = ingredientGetter.invoke(dto);

                Method measureGetter = CocktailDTO.class.getMethod("getStrMeasure" + i);
                final Object measure = measureGetter.invoke(dto);

                if(ingredient != null)
                {
                    final String ingredientName = ((String) ingredient).trim();
                    final MeasuredIngredient measuredIngredient = new MeasuredIngredient(ingredientName);

                    if(measure != null)
                    {
                        final String measureString = ((String) measure).trim();
                        measuredIngredient.setMeasure(measureString);
                    }

                    ingredients.add(measuredIngredient);
                }

            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                logger.log(Level.SEVERE, e::getMessage);
            }

        }

        cocktail.setMeasuredIngredients(ingredients);
    }



}
