package com.arrowsmith.cocktailapiapp.model;

public class Instructions {

    public Instructions(String instructions, Language language)
    {
        setInstructions(instructions);
        setLanguage(language);
    }


    private String instructions;

    private LanguageData languageData;

    public void setLanguage(Language language) {
        switch (language)
        {
            case ENGLISH -> {
                    languageData = LanguageData.englishData;
            }
            case FRENCH -> {
                languageData = LanguageData.frenchData;
            }
            case SPANISH -> {
                languageData = LanguageData.spanishData;
            }
            case GERMAN -> {
                languageData = LanguageData.germanData;
            }
            case ITALIAN -> {
                languageData = LanguageData.italianData;
            }
        }
    }

    public LanguageData getLanguageData() {
        return languageData;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

}

