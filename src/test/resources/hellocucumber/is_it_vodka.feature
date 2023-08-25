Feature: Is it vodka?
  Vodka cocktails should contain vodka

  Scenario: The user has searched for cocktails containing vodka
    Given a user selects a cocktail
    When the user looks at the cocktail ingredients
    Then vodka should be included in the ingredients
