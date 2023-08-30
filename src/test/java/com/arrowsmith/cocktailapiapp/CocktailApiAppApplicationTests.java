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

		assertTrue("The following template does not contain '" + cocktail.getName() + "': \n\n" + renderedTemplate,
				renderedTemplate.contains(cocktail.getName()));
	}

}
