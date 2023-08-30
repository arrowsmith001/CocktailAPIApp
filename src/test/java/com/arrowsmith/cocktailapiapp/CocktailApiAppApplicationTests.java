package com.arrowsmith.cocktailapiapp;

import com.arrowsmith.cocktailapiapp.api.CocktailApi;
import com.arrowsmith.cocktailapiapp.api.CocktailApiImpl;
import com.arrowsmith.cocktailapiapp.api.TheCocktailDBRequester;
import com.arrowsmith.cocktailapiapp.model.Cocktail;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


import static org.springframework.test.util.AssertionErrors.*;

@SpringBootTest
class CocktailApiAppApplicationTests {

	private String getApiKey(){
		return System.getenv().get("THE_COCKTAIL_DB_API_KEY");
	}

	final CocktailApiAppApplication app = new CocktailApiAppApplication();

	final CocktailApi api = new CocktailApiImpl(new TheCocktailDBRequester(getApiKey()));

	@Test
	@DisplayName("Cocktail template page should contain the name of the cocktail")
	void testCocktailPageRenderedTemplate()
	{
		final Cocktail cocktail = api.getRandomCocktail();

		final String renderedTemplate = app.getCocktailById(cocktail.getId());

		final boolean containsCocktailName = renderedTemplate.contains(cocktail.getName());

		assertTrue("The following template does not contain '" + cocktail.getName() + "': \n\n" + renderedTemplate, containsCocktailName);
	}


	@Test
	@DisplayName("Cocktail by ingredient page should contain the name of the ingredient")
	void testCocktailByLetterPageRenderedTemplate()
	{
		final String ingredientName = "Gin";

		final String renderedTemplate = app.listCocktailsByIngredient(ingredientName);

		final boolean containsIngredientName = renderedTemplate.contains(ingredientName);

		assertTrue("The following template does not contain '" + ingredientName + "': \n\n" + renderedTemplate,
				containsIngredientName);
	}

	@Test
	@DisplayName("Searching cocktails by name should include the search term")
	void testSearchCocktailsByNameRenderedTemplate()
	{
		final String searchTerm = "Marg";

		final String renderedTemplate = app.listCocktailsByName(searchTerm);

		final boolean containsSearchTerm = renderedTemplate.contains(searchTerm);

		assertTrue("The following template does not contain '" + searchTerm + "': \n\n" + renderedTemplate,
				containsSearchTerm);
	}

	@Test
	@DisplayName("Searching cocktails by an ingredient should include the search term")
	void testSearchCocktailsByIngredientRenderedTemplate()
	{
		final String searchTerm = "Milk";

		final String renderedTemplate = app.listIngredientWithCocktails(searchTerm);

		final boolean containsSearchTerm = renderedTemplate.contains(searchTerm);

		assertTrue("The following template does not contain '" + searchTerm + "': \n\n" + renderedTemplate,
				containsSearchTerm);
	}

	@Test
	@DisplayName("Searching cocktails by an ingredient should include the search term")
	void testSearchCocktailsByIngredientRenderedTemplate()
	{
		final String searchTerm = "Milk";

		final String renderedTemplate = app.listCocktailsByLetter('a');

		final boolean containsSearchTerm = renderedTemplate.contains(searchTerm);

		assertTrue("The following template does not contain '" + searchTerm + "': \n\n" + renderedTemplate,
				containsSearchTerm);
	}

}
