package com.arrowsmith.cocktailapiapp.api;

import com.arrowsmith.cocktailapiapp.model.Cocktail;
import com.arrowsmith.cocktailapiapp.model.BasicCocktail;
import com.arrowsmith.cocktailapiapp.model.Ingredient;
import com.arrowsmith.cocktailapiapp.model.MeasuredIngredient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.springframework.test.util.AssertionErrors.*;
import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertFalse;
import static org.springframework.test.util.AssertionErrors.assertTrue;

@SpringBootTest
class CocktailApiImplTest {

    private String getApiKey(){
        return System.getenv().get("THE_COCKTAIL_DB_API_KEY");
    }

    final CocktailApi api = new CocktailApiImpl(new TheCocktailDBRequester(getApiKey()));

    @Test
    @DisplayName("Random cocktail returns a cocktail with a valid name")
    void testRandomCocktail()
    {
        final Cocktail randomCocktail = api.getRandomCocktail();

        assertNotNull("Random cocktail is null", randomCocktail);
        assertNotNull("Random cocktail name is null", randomCocktail.getName());
    }

    @Test
    @DisplayName("Search for the id of a random cocktail returns the same cocktail")
    void testCocktailById()
    {
        final Cocktail randomCocktail = api.getRandomCocktail();
        final Cocktail sameCocktail = api.getCocktailById(randomCocktail.getId());

        assertEquals("Cocktail names not equal", randomCocktail.getName(), sameCocktail.getName());
    }

    @Test
    @DisplayName("Cocktails retrieved with a starting letter of A do actually begin with A")
    void testLetterA()
    {
        final List<Cocktail> cocktailsBeginningWithA = api.listCocktailsStartingWithLetter('a');

        assertFalse("Empty list of cocktails", cocktailsBeginningWithA.isEmpty());

        for (Cocktail c : cocktailsBeginningWithA)
        {
            final char ch = c.getName().charAt(0);
            assertEquals(c.getName() + " does not begin with A", "A", Character.toString(ch).toUpperCase());
        }

    }


    @Test
    @DisplayName("Random cocktail retrieved from a search by ingredient (vodka) actually does contain vodka when searched for")
    void testRandomVodkaCocktail()
    {
        final List<BasicCocktail> vodkaCocktails = api.listCocktailsByIngredient("vodka");
        final int index = (int) (Math.random() * vodkaCocktails.size());

        final BasicCocktail randomCocktail = vodkaCocktails.get(index);
        final Cocktail randomCocktailFull = api.getCocktailById(randomCocktail.getId());

        boolean vodkaFound = false;
        for(MeasuredIngredient ingredient : randomCocktailFull.getMeasuredIngredients())
        {

            if(ingredient.getName().toUpperCase().trim().equals("VODKA"))
            {
                vodkaFound = true;
                break;
            }
        }
        assertTrue("Vodka not found", vodkaFound);
    }


    @Test
    @DisplayName("Searching for 'margarita' should return cocktails with the word margarita in their name")
    void testSearchForMargaritas()
    {
        final List<Cocktail> margaritas = api.listCocktailsByName("margarita");

        String nonMargaritaCocktail = "";
        boolean nonMargaritaCocktailFound = false;

        for(Cocktail cocktail : margaritas)
        {
            final boolean containsMargarita = cocktail.getName().toUpperCase().trim().contains("MARGARITA");
            if (!containsMargarita) {
                nonMargaritaCocktail = cocktail.getName();
                nonMargaritaCocktailFound = true;
                break;
            }
        }

        assertFalse("Non-margarita cocktail found: " + nonMargaritaCocktail, nonMargaritaCocktailFound);
    }

    @Test
    @DisplayName("Searching for ice as an ingredient should return an ingredient called ice")
    void testIngredientByName()
    {
        final List<Ingredient> ingredient = api.listIngredientsByName("ice");

        final String formattedName = ingredient.get(0).getName().toUpperCase().trim();

        assertEquals("Not ice", "ICE", formattedName);

    }

    @Test
    @DisplayName("Search for the id of a particular ingredient returns the same cocktail")
    void testIngredientById()
    {
        final Ingredient ingredient = api.listIngredientsByName("ice").get(0);
        final Ingredient ingredientById = api.getIngredientById(ingredient.getId());

        final boolean idsMatch = ingredient.getId() == ingredientById.getId();

        assertTrue("Ids don't match", idsMatch);

    }

    @Test
    @DisplayName("List cocktails by ingredient accepts either an Ingredient object or name string")
    void testListCocktailsByIngredient()
    {
        final BasicCocktail cocktailByIngredientObject = api.listCocktailsByIngredient("ice").get(0);
        final BasicCocktail cocktailByIngredientName = api.listCocktailsByIngredient(new Ingredient("ice")).get(0);

        assertNotNull("Cocktail by object null", cocktailByIngredientObject);
        assertNotNull("Cocktail by name null", cocktailByIngredientName);

        assertEquals("Cocktails not equal", cocktailByIngredientName, cocktailByIngredientObject);

    }
}
