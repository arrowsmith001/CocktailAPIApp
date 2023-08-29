package com.arrowsmith.cocktailapiapp.model.instructions;

public class SpanishInstructions extends InstructionsInLanguage {

    public SpanishInstructions(String instructions) {
        super(instructions);
    }

    @Override
    public String getLanguageAbbreviated() {
        return "ES";
    }

    @Override
    public String getLanguage() {
        return "Español";
    }

    @Override
    public String getThumbnailUrl() {
        return "https://img.icons8.com/?size=512&id=PqPbL71WdPiX&format=png";
    }
}
