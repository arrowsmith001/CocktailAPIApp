package com.arrowsmith.cocktailapiapp;

import com.arrowsmith.cocktailapiapp.api.CocktailApi;
import com.arrowsmith.cocktailapiapp.api.CocktailApiImpl;
import com.arrowsmith.cocktailapiapp.api.TheCocktailDBRequester;
import com.arrowsmith.cocktailapiapp.model.Cocktail;
import com.arrowsmith.cocktailapiapp.model.BasicCocktail;
import com.arrowsmith.cocktailapiapp.model.Ingredient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.collect.Maps;
import com.google.common.io.Resources;
import com.hubspot.jinjava.Jinjava;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@RestController
public class CocktailApiAppApplication {
	static Logger logger = Logger.getLogger(CocktailApiAppApplication.class.getName());

	private static final String COCKTAIL = "cocktail";
	private static final String COCKTAILS = "cocktails";
	private static final String INDEX = "index";

	private static final CocktailApi api = new CocktailApiImpl(new TheCocktailDBRequester(System.getenv("THE_COCKTAIL_DB_API_KEY")));

	public static void main(String[] args) {

		SpringApplication.run(CocktailApiAppApplication.class, args);
	}

	@GetMapping("/")
	public String home() {

		Map<String, Object> context = Maps.newHashMap();

		return renderTemplate("home", context);
	}


	@GetMapping("/random")
	public String getRandomCocktail() {

		Map<String, Object> context = Maps.newHashMap();

		try
		{
			final Cocktail randomCocktail = api.getRandomCocktail();
			context.put(COCKTAIL, randomCocktail);

		}
		catch(Exception e)
		{
			logger.log(Level.SEVERE, "getRandomCocktail Api call failed");
		}

		return renderTemplate(COCKTAIL, context);
	}

	@GetMapping("/search")
	public String listCocktailsByName(@RequestParam String term)  {

		Map<String, Object> context = Maps.newHashMap();
		context.put("term", term);

		try
		{
			final List<Cocktail> cocktails = api.listCocktailsByName(term);

			context.put(COCKTAILS, cocktails);
		}
		catch(Exception e)
		{
			logger.log(Level.SEVERE, "listCocktailsByName Api call failed");
		}

		return renderTemplate("listing", context);
	}

	@GetMapping("/cocktail")
	public String getCocktailById(@RequestParam Integer id)  {

		Map<String, Object> context = Maps.newHashMap();

		try
		{
			final Cocktail cocktail = api.getCocktailById(id);
			context.put(COCKTAIL, cocktail);
		}
		catch(Exception e)
		{
			logger.log(Level.SEVERE, "getCocktailById Api call failed");
		}

		return renderTemplate(COCKTAIL, context);
	}

	@GetMapping("/index")
	public String listCocktailsByLetter(@RequestParam Character letter) throws JsonProcessingException {
		Map<String, Object> context = Maps.newHashMap();
		context.put("letter", letter);

		try
		{

			final List<Cocktail> cocktails = api.listCocktailsStartingWithLetter(letter);
			context.put(COCKTAILS, cocktails);

		}
		catch(Exception e)
		{
			logger.log(Level.SEVERE, "listCocktailsByLetter Api call failed");
		}

		return renderTemplate(INDEX, context);
	}

	@GetMapping("/ingredient")
	public String listIngredientWithCocktails(@RequestParam String term) {

		Map<String, Object> context = Maps.newHashMap();
		context.put("term", term);

		try
		{
			final Ingredient ingredient = api.listIngredientsByName(term).get(0);
			final List<BasicCocktail> cocktails = api.listCocktailsByIngredient(ingredient);

			context.put("ingredient", ingredient);
			context.put(COCKTAILS, cocktails);
		}
		catch(Exception e)
		{
			logger.log(Level.SEVERE, "listIngredientWithCocktails Api call failed");
		}

		return renderTemplate("ingredient", context);
	}


	@GetMapping("/cocktailsByIngredient")
	public String listCocktailsByIngredient(@RequestParam String ingredientName)  {

		Map<String, Object> context = Maps.newHashMap();
		context.put("ingredientName", ingredientName);

		try
		{

			final List<BasicCocktail> cocktails = api.listCocktailsByIngredient(ingredientName);
			context.put(COCKTAILS, cocktails);
		}
		catch(Exception e)
		{
			logger.log(Level.SEVERE, "listCocktailsByIngredient Api call failed");
		}

		return renderTemplate("listing", context);
	}


	private String renderTemplate(String templateName, Map<String, Object> context) {

		Jinjava jinjava = new Jinjava();

		try
		{
			String template =  Resources.toString(Resources.getResource(templateName + ".html"), StandardCharsets.UTF_8);
			return jinjava.render(template, context);
		}
		catch (Exception e)
		{
			logger.log(Level.SEVERE, e.toString());
			return e.toString();
		}
	}


}
