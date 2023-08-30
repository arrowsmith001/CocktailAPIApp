package com.arrowsmith.cocktailapiapp.dto;

import com.arrowsmith.cocktailapiapp.model.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DTOMapper {
    private DTOMapper(){}
    static Logger logger = Logger.getLogger(DTOMapper.class.getName());

    public static BasicCocktail cocktailDTOtoBasicModel(CocktailDTO dto) {

        final BasicCocktail out = new BasicCocktail();

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

    private static void setBasicCocktailInfo(BasicCocktail cocktailBase, CocktailDTO dto) {
        cocktailBase.setId(dto.getId());
        cocktailBase.setName(dto.getCocktailName());
        cocktailBase.setImageUrl(dto.getImageUrl());
    }

    private static void setAdditionalCocktailInfo(Cocktail cocktail, CocktailDTO dto) {
        cocktail.setGlass(dto.getGlass());
        cocktail.setCategory(dto.getCategory());
        cocktail.setAlcoholic(dto.getAlcoholic());
    }

    private static final String STR_INSTRUCTIONS = "strInstructions";

    private static void setCocktailInstructions(Cocktail cocktail, CocktailDTO dto) {

        List<Instructions> instructions = new ArrayList<>();

        final Map<String, String> fields = dto.getInstructions();

        // Add English
        if(fields.containsKey(STR_INSTRUCTIONS))
        {
            final String englishInstructions = fields.get(STR_INSTRUCTIONS);
            instructions.add(new Instructions(englishInstructions, Language.ENGLISH));
        }

        final List<String> instructionsKeys = fields.keySet().stream()
                .filter(s -> s.startsWith(STR_INSTRUCTIONS) && !s.equals(STR_INSTRUCTIONS))
                .toList();

        for(String instructionKey : instructionsKeys)
        {
            final String instructionString = fields.get(instructionKey);
            if(instructionString == null) continue;

            final String languageAbbreviated = instructionKey.replaceFirst(STR_INSTRUCTIONS, "");

            switch (languageAbbreviated) {
                case "FR" -> instructions.add(new Instructions(instructionString, Language.FRENCH));
                case "ES" -> instructions.add(new Instructions(instructionString, Language.SPANISH));
                case "DE" -> instructions.add(new Instructions(instructionString, Language.GERMAN));
                case "IT" -> instructions.add(new Instructions(instructionString, Language.ITALIAN));
                default -> logger.log(Level.INFO, () -> "Unused language: " + languageAbbreviated);

            }
        }

        cocktail.setInstructions(instructions);
    }

    private static void setCocktailIngredients(Cocktail cocktail, CocktailDTO dto) {

        List<MeasuredIngredient> ingredients = new ArrayList<>();

        final Map<String, String> names = dto.getIngredientNames();
        final Map<String, String> measures = dto.getIngredientMeasures();

        int i = 1;
        String nameKey = "strIngredient" + i;
        String measureKey = "strMeasure" + i;

        while(names.containsKey(nameKey) && measures.containsKey(measureKey))
        {
            final String ingredientName = names.get(nameKey);
            final String ingredientMeasure = measures.get(measureKey);

            if(ingredientName == null) break;

            MeasuredIngredient measured = new MeasuredIngredient(ingredientName, ingredientMeasure);
            ingredients.add(measured);

            i++;
            nameKey = "strIngredient" + i;
            measureKey = "strMeasure" + i;
        }

        cocktail.setMeasuredIngredients(ingredients);
    }



}
