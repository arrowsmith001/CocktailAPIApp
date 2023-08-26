Feature: Is it vodka?

  Vodka cocktails should contain vodka

  Scenario: Vodka cocktails contain vodka
    Given the ingredient of vodka
    When I search for cocktails containing vodka
    When I select a cocktail from that list
    Then vodka should be included in the ingredients
