package com.arrowsmith.cocktailapiapp.api;

public class TheCocktailDBUrls {
    public TheCocktailDBUrls(String apiKey)
    {
        this.apiKey = apiKey;
    }

    private final String apiKey;

    private static final String BASE_URL = "https://www.thecocktaildb.com/api/json/v1/";
    private static final String RANDOM = "/random.php";
    private static final String BASE_SEARCH_BY_FIRST_LETTER = "/search.php?f=";
    private static final String BASE_SEARCH_INGREDIENT_BY_NAME = "/search.php?i=";
    private static final String BASE_SEARCH_COCKTAIL_BY_NAME = "/search.php?s=";
    private static final String BASE_COCKTAIL_BY_ID_LOOKUP = "/lookup.php?i=";
    private static final String BASE_COCKTAIL_SEARCH_BY_INGREDIENT_NAME = "/filter.php?i=";
    private static final String BASE_LOOKUP_INGREDIENT_BY_ID = "/lookup.php?iid=";


    public String getRandomCocktailUrl() {
        return BASE_URL + apiKey + RANDOM;
    }

    public String searchCocktailsByLetterUrl(char startingLetter) {
        return BASE_URL + apiKey + BASE_SEARCH_BY_FIRST_LETTER + startingLetter;
    }

    public String getCocktailByIdUrl(Object id) {
        return BASE_URL + apiKey + BASE_COCKTAIL_BY_ID_LOOKUP + id;
    }

    public String getIngredientByIdUrl(Object id) {
        return BASE_URL + apiKey + BASE_LOOKUP_INGREDIENT_BY_ID + id;
    }

    public String searchCocktailByNameUrl(String term) {
        return BASE_URL + apiKey + BASE_SEARCH_COCKTAIL_BY_NAME + replaceWhitespace(term);
    }

    public String getIngredientByNameUrl(String term) {
        return BASE_URL + apiKey + BASE_SEARCH_INGREDIENT_BY_NAME + replaceWhitespace(term);
    }

    public String searchCocktailsByIngredientNameUrl(String ingredientName) {
        return BASE_URL + apiKey + BASE_COCKTAIL_SEARCH_BY_INGREDIENT_NAME + replaceWhitespace(ingredientName);
    }


    private String replaceWhitespace(String term)
    {
        return String.join("+", term.split(" "));
    }
}
