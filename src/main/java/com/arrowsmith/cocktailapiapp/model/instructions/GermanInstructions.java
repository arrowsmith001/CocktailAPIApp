package com.arrowsmith.cocktailapiapp.model.instructions;

public class GermanInstructions extends InstructionsInLanguage {

    public GermanInstructions(String instructions) {
        super(instructions);
    }

    @Override
    public String getLanguageAbbreviated() {
        return "DE";
    }

    @Override
    public String getLanguage() {
        return "Deutsch";
    }

    @Override
    public String getThumbnailUrl() {
        return "https://img.icons8.com/?size=512&id=PEPe9YoyNSe6&format=png";
    }
}
