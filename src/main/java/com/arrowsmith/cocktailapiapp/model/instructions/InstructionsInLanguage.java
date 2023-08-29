package com.arrowsmith.cocktailapiapp.model.instructions;

public abstract class InstructionsInLanguage {
    public InstructionsInLanguage(String instructions)
    {
        setInstructions(instructions);
    }

    public abstract String getLanguage();
    public abstract String getLanguageAbbreviated();
    public abstract String getThumbnailUrl();

    private String instructions;

    public String getInstructions() {
        return instructions;
    }
    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

}

