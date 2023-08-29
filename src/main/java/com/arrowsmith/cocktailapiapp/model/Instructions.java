package com.arrowsmith.cocktailapiapp.model;

public class Instructions {

    public Instructions() {}

    public Instructions(String instructionString, Language language)
    {
        setInstructionString(instructionString);
        setLanguage(language);
    }


    private String instructionString;

    private LanguageData languageData;


    public void setLanguage(Language language) {
        switch (language)
        {
            case ENGLISH -> setLanguageData(LanguageData.englishData());
            case FRENCH -> setLanguageData(LanguageData.frenchData());
            case SPANISH -> setLanguageData(LanguageData.spanishData());
            case GERMAN  -> setLanguageData(LanguageData.germanData());
            case ITALIAN -> setLanguageData(LanguageData.italianData());
        }
    }

    public LanguageData getLanguageData() {
        return languageData;
    }

    public void setLanguageData(LanguageData languageData) {
        this.languageData = languageData;
    }

    public String getInstructionString() {
        return instructionString;
    }

    public void setInstructionString(String instructionString) {
        this.instructionString = instructionString;
    }

}

