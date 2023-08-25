package com.arrowsmith.cocktailapiapp;

import com.arrowsmith.cocktailapiapp.api.CocktailApi;
import com.arrowsmith.cocktailapiapp.api.CocktailApiImpl;
import com.arrowsmith.cocktailapiapp.model.Cocktail;
import com.arrowsmith.cocktailapiapp.model.Ingredient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.springframework.test.util.AssertionErrors.*;

@SpringBootTest
class CocktailApiAppApplicationTests {


	final CocktailApi api = new CocktailApiImpl(System.getenv().get("THE_COCKTAIL_DB_API_KEY"));

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
		final List<Cocktail> cocktailsBeginningWithA = api.getCocktailsStartingWithLetter('a');

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
		final List<Cocktail> vodkaCocktails = api.listCocktailsByIngredient("vodka");
		final int index = (int) (Math.random() * vodkaCocktails.size());

		final Cocktail randomCocktail = vodkaCocktails.get(index);
		final Cocktail randomCocktailFull = api.getCocktailById(randomCocktail.getId());

		boolean vodkaFound = false;
		for(Ingredient ingredient : randomCocktailFull.getIngredients())
		{

			if(ingredient.getName().toUpperCase().trim().equals("VODKA"))
			{
				vodkaFound = true;
			}
		}
		assertTrue("Vodka not found", vodkaFound);
	}


	@Test
	@DisplayName("Searching for margarita should return cocktails with the word margarita in their name")
	void testSearchForMargaritas()
	{
		final List<Cocktail> margaritas = api.searchForCocktailByName("margarita");

		boolean nonMargaritaCocktailFound = false;
		for(Cocktail cocktail : margaritas)
		{
			if(cocktail.getName().toUpperCase().trim().contains("MARGARITA"))
			{
				// Cocktail is a margarita
			}
			else nonMargaritaCocktailFound = true;
		}

		assertFalse("Non-margarita cocktail found", nonMargaritaCocktailFound);
	}

	@Test
	@DisplayName("Searching for ice as an ingredient should return an ingredient called ice")
	void testIngredientByName()
	{
		final List<Ingredient> ingredient = api.searchForIngredientByName("ice");

		final String formattedName = ingredient.get(0).getName().toUpperCase().trim();

		assertEquals("Not ice", "ICE", formattedName);

	}

	@Test
	@DisplayName("Searching for ice as an ingredient should return an ingredient called ice")
	void testIngredientById()
	{
		final Ingredient ingredient = api.searchForIngredientByName("ice").get(0);
		final Ingredient ingredientById = api.getIngredientById(ingredient.getId());

		final boolean idsMatch = ingredient.getId() == ingredientById.getId();

		assertTrue("Ids don't match", idsMatch);

	}


}
