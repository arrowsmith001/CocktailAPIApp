package com.arrowsmith.cocktailapiapp.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.util.AssertionErrors.assertEquals;

class LanguageDataTest {

    @Test
    @DisplayName("Getters and setters work")
    void testGettersAndSetters()
    {
        final LanguageData data = new LanguageData();

        data.setLanguage("Chinese");
        data.setIconUrl("icon.jpg");
        data.setLanguageAbbreviated("CH");

        final String language = data.getLanguage();
        final String iconUrl = data.getIconUrl();
        final String languageAbbreviated = data.getLanguageAbbreviated();

        assertEquals("language not equal", "Chinese", language);
        assertEquals("iconUrl not equal", "icon.jpg", iconUrl);
        assertEquals("languageAbbreviated not equal", "CH", languageAbbreviated);
    }
}