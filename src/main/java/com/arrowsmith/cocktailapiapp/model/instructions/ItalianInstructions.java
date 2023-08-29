package com.arrowsmith.cocktailapiapp.model.instructions;

public class ItalianInstructions extends InstructionsInLanguage {

    public ItalianInstructions(String instructions) {
        super(instructions);
    }

    @Override
    public String getLanguageAbbreviated() {
        return "IT";
    }

    @Override
    public String getLanguage() {
        return "Italiano";
    }

    @Override
    public String getThumbnailUrl() {
        return "https://img.icons8.com/?size=512&id=6QSlwHlcIbQk&format=png";
    }
}
