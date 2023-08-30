Feature: Exploring detailed cocktail information

  Scenario: Vodka cocktails contain vodka
    Given the ingredient of vodka
    When I search for cocktails containing vodka
    When I select a cocktail from that list
    Then vodka should be included in the ingredients

  Scenario: Language should be changeable if available
    Given I select a cocktail with the default language
    And the cocktail has instructions in multiple languages
    When I select a different language
    Then the selected language should not be the default language

