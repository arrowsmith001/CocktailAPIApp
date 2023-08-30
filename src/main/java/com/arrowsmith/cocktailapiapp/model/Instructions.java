package com.arrowsmith.cocktailapiapp.model;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Instructions that = (Instructions) o;
        return Objects.equals(instructionString, that.instructionString) && Objects.equals(languageData, that.languageData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(instructionString, languageData);
    }
}

