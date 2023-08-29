package com.arrowsmith.cocktailapiapp.model.instructions;

public class FrenchInstructions extends InstructionsInLanguage {

    public FrenchInstructions(String instructions) {
        super(instructions);
    }

    @Override
    public String getLanguageAbbreviated() {
        return "FR";
    }

    @Override
    public String getLanguage() {
        return "Français";
    }

    @Override
    public String getThumbnailUrl() {
        return "https://img.icons8.com/?size=512&id=1PjSlP5VQF71&format=png";
    }
}
