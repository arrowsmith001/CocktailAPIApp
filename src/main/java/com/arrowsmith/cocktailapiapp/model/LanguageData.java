package com.arrowsmith.cocktailapiapp.model;

public class LanguageData {

    public static String ENGLISH_ICON = "https://img.icons8.com/?size=512&id=n5cRfdV5J7nI&format=png";
    public static String FRENCH_ICON = "https://img.icons8.com/?size=512&id=1PjSlP5VQF71&format=png";
    public static String SPANISH_ICON = "https://img.icons8.com/?size=512&id=PqPbL71WdPiX&format=png";
    public static String GERMAN_ICON = "https://img.icons8.com/?size=512&id=PEPe9YoyNSe6&format=png";
    public static String ITALIAN_ICON = "https://img.icons8.com/?size=512&id=6QSlwHlcIbQk&format=png";

    public LanguageData() {}

    public LanguageData(String language, String languageAbbreviated, String iconUrl) {
        this.language = language;
        this.languageAbbreviated = languageAbbreviated;
        this.iconUrl = iconUrl;
    }

    public static LanguageData englishData = new LanguageData("English", "EN", ENGLISH_ICON);
    public static LanguageData frenchData = new LanguageData("Français", "FR", FRENCH_ICON);
    public static LanguageData germanData = new LanguageData("Deutsch", "DE", GERMAN_ICON);
    public static LanguageData spanishData = new LanguageData("Español", "ES", SPANISH_ICON);
    public static LanguageData italianData = new LanguageData("Italiano", "IT", ITALIAN_ICON);


    private String language;
    private String languageAbbreviated;
    private String iconUrl;


    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLanguageAbbreviated() {
        return languageAbbreviated;
    }

    public void setLanguageAbbreviated(String languageAbbreviated) {
        this.languageAbbreviated = languageAbbreviated;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof LanguageData data)
        {
            return language.equals(data.language)
                    && languageAbbreviated.equals(data.languageAbbreviated)
                    && iconUrl.equals(data.iconUrl);
        }
        return false;
    }

}
