package com.arrowsmith.cocktailapiapp.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.springframework.test.util.AssertionErrors.assertEquals;

@SpringBootTest
class InstructionsTest {


    @Test
    @DisplayName("Setting the language for an instruction should update all language-related return values")
    void testSetLanguage()
    {
        final Instructions instruction = new Instructions("", Language.ENGLISH);

        assertEquals("Language data not equal to English", LanguageData.englishData(), instruction.getLanguageData());

        instruction.setLanguage(Language.FRENCH);

        assertEquals("Language data not equal to French", LanguageData.frenchData(), instruction.getLanguageData());
    }
}