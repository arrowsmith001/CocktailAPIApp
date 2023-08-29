package com.arrowsmith.cocktailapiapp;

import com.arrowsmith.cocktailapiapp.api.CocktailApi;
import com.arrowsmith.cocktailapiapp.api.CocktailApiImpl;
import com.arrowsmith.cocktailapiapp.model.Cocktail;
import com.arrowsmith.cocktailapiapp.model.CocktailBase;
import com.arrowsmith.cocktailapiapp.model.Ingredient;
import com.arrowsmith.cocktailapiapp.model.MeasuredIngredient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

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
		final List<CocktailBase> vodkaCocktails = api.listCocktailsByIngredient("vodka");
		final int index = (int) (Math.random() * vodkaCocktails.size());

		final CocktailBase randomCocktail = vodkaCocktails.get(index);
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
		final List<Cocktail> margaritas = api.searchForCocktailByName("margarita");

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

	@Test
	@DisplayName("Ingredient getters should work")
	void testIngredientGetters()
	{
		final Ingredient ingredient = new Ingredient();

		ingredient.setId(0);
		ingredient.setName("Some Random Ingredient");
		ingredient.setMeasure("Some amount");
		ingredient.setDescription("Some description");

		assertEquals("Id doesn't match", ingredient.getId(), 0);
		assertEquals("Name doesn't match", ingredient.getName(), "Some Random Ingredient");
		assertEquals("Measure doesn't match", ingredient.getMeasure(), "Some amount");
		assertEquals("Search term doesn't match", ingredient.getSearchTerm(), "Some+Random+Ingredient");

	}
	@Test
	@DisplayName("Ingredient getters should work")
	void testCocktailGetters()
	{
		final Cocktail cocktail = new Cocktail();

		cocktail.setId(0);
		cocktail.setName("Some Random Cocktail");
		//cocktail.setInstructions(new ArrayList<>());

		assertEquals("Id doesn't match", cocktail.getId(), 0);
		assertEquals("Name doesn't match", cocktail.getName(), "Some Random Cocktail");
		//assertEquals("Instructions don't match", cocktail.getInstructions()[0], "Some instructions");

	}


	@Test
	@DisplayName("Cocktail template page should contain the name of the cocktail")
	void testCocktailPageRenderedTemplate()
	{
		final Cocktail cocktail = api.getRandomCocktail();

		final CocktailApiAppApplication app = new CocktailApiAppApplication();
		final String renderedTemplate = app.getCocktailById(cocktail.getId());

		assertTrue("The following template does not contain '" + cocktail.getName() + "': \n\n" + renderedTemplate,
				renderedTemplate.contains(cocktail.getName()));
	}

}
