package com.arrowsmith.cocktailapiapp.model.instructions;

public class EnglishInstructions extends InstructionsInLanguage {

    public EnglishInstructions(String instructions) {
        super(instructions);
    }

    @Override
    public String getLanguageAbbreviated() {
        return "EN";
    }

    @Override
    public String getLanguage() {
        return "English";
    }

    @Override
    public String getThumbnailUrl() {
        return "https://img.icons8.com/?size=512&id=n5cRfdV5J7nI&format=png";
    }
}
