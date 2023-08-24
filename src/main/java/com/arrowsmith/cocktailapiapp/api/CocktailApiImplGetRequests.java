package com.arrowsmith.cocktailapiapp.api;

public class CocktailApiImplGetRequests {

    CocktailApiImplGetRequests(String apiKey) {
        this.apiKey = apiKey;
    }

    private final String apiKey;
    private final String baseUrl = "https://www.thecocktaildb.com/api/json/v1/";

    private String getRequestUrl(String append) {
        return baseUrl + apiKey + append;
    }

    private String random = "/random.php";
    private String baseSearchByFirstLetter = "/search.php?f=";
    private String baseSearchIngredientByName = "/search.php?i=";
    private String baseSearchCocktailByName = "/search.php?s=";
    private String baseCocktailByIdLookup = "/lookup.php?i=";
    private String baseCocktailSearchByIngredientName = "/filter.php?i=";

    public String getSearchByLetterRequest(char c) {
        return getRequestUrl(baseSearchByFirstLetter) + Character.toString(c).toLowerCase();
    }

    public String getSearchById(Object id) {
        return getRequestUrl(baseCocktailByIdLookup) + id.toString();
    }

    public String getSearchByName(String term) {
        return getRequestUrl(baseSearchCocktailByName) + term;
    }

    public String getSearchCocktailByIngredientName(String term) {
        return getRequestUrl(baseCocktailSearchByIngredientName) + term;
    }

    public String getRandom() {
        return getRequestUrl(random);
    }

    public String getIngredientById(Object id) {
        return getRequestUrl(baseCocktailByIdLookup) + id;
    }
    public String getIngredientByName(String term) {
        return getRequestUrl(baseSearchIngredientByName) + term;
    }
}
