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

	@Value("${env.THE_COCKTAIL_DB_API_KEY}")
	String apiKey;

	final CocktailApi api = new CocktailApiImpl(apiKey);

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

//	// TODO: Make more tests
//	@Test
//	@DisplayName("")
//	void newTest()
//	{
//
//	}

}
