package com.arrowsmith.cocktailapiapp;

import com.arrowsmith.cocktailapiapp.api.CocktailApi;
import com.arrowsmith.cocktailapiapp.api.CocktailApiImpl;
import com.arrowsmith.cocktailapiapp.model.Cocktail;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertNotNull;

@SpringBootTest
class CocktailApiAppApplicationTests {

	//@Value("${THE_COCKTAILDB_API_KEY}")
	// TODO: Externalize
	final String apiKey = "1";

	final CocktailApiAppApplication app = new CocktailApiAppApplication();

	@Test
	@DisplayName("Random cocktail returns a cocktail with a valid name")
	void testRandomCocktail()
	{
		final CocktailApi api = new CocktailApiImpl(apiKey);
		final Cocktail randomCocktail = api.getRandomCocktail();

		assertNotNull("Random cocktail is null", randomCocktail);
		assertNotNull("Random cocktail name is null", randomCocktail.getName());
	}

	@Test
	@DisplayName("Search for the id of a random cocktail returns the same cocktail")
	void testCocktailById()
	{
		final CocktailApi api = new CocktailApiImpl(apiKey);
		final Cocktail randomCocktail = api.getRandomCocktail();
		final Cocktail sameCocktail = api.getCocktailById(randomCocktail.getId());

		assertEquals("Cocktail names not equal",randomCocktail.getName(), sameCocktail.getName());
	}

}
