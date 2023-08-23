package com.arrowsmith.cocktailapiapp;

import java.util.ArrayList;
import java.util.List;

public class CocktailList {
    private List<Cocktail> cocktails = new ArrayList<Cocktail>();

    public void addCocktail(Cocktail cocktail) {
        cocktails.add(cocktail);
    }

    public Cocktail first() {
        return cocktails.get(0);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for(Cocktail c : cocktails)
        {
            sb.append(c.toString());

        }

        return sb.toString();
    }
}
