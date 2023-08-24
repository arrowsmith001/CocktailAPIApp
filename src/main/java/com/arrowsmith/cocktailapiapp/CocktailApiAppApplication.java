package com.arrowsmith.cocktailapiapp;

import com.arrowsmith.cocktailapiapp.api.CocktailApi;
import com.arrowsmith.cocktailapiapp.api.CocktailApiImpl;
import com.arrowsmith.cocktailapiapp.model.Cocktail;
import com.arrowsmith.cocktailapiapp.model.Ingredient;
import com.google.common.base.Charsets;
import com.google.common.collect.Maps;
import com.google.common.io.Resources;
import com.hubspot.jinjava.Jinjava;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@RestController
public class CocktailApiAppApplication {

	// TODO: Place apikey in config file
	final CocktailApi api = new CocktailApiImpl("1");

	static Logger logger = Logger.getLogger(CocktailApiAppApplication.class.getName());

	public static void main(String[] args) {
		SpringApplication.run(CocktailApiAppApplication.class, args);
	}

	final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	@GetMapping("/")
	public String home() {

		final String[] index = getIndex();

		Map<String, Object> context = Maps.newHashMap();
		context.put("index", index);

		return renderTemplate("home", context);
	}


	@GetMapping("/random")
	public String getRandomCocktail() {

		final Cocktail randomCocktail = api.getRandomCocktail();

		Map<String, Object> context = Maps.newHashMap();
		context.put("cocktail", randomCocktail);

		return renderTemplate("cocktail", context);
	}

	@GetMapping("/search")
	public String searchForCocktailByName(@RequestParam String term) {

		final List<Cocktail> cocktails = api.searchForCocktailByName(term);

		Map<String, Object> context = Maps.newHashMap();
		context.put("term", term);
		context.put("cocktails", cocktails);

		return renderTemplate("listing", context);
	}

	@GetMapping("/cocktail")
	public String getCocktailById(@RequestParam Integer id) {

		final Cocktail cocktail = api.getCocktailById(id);

		Map<String, Object> context = Maps.newHashMap();
		context.put("cocktail", cocktail);

		return renderTemplate("cocktail", context);
	}

	@GetMapping("/index")
	public String listCocktailsByLetter(@RequestParam Character letter) {

		final List<Cocktail> cocktails = api.getCocktailsStartingWithLetter(letter);

		Map<String, Object> context = Maps.newHashMap();
		context.put("letter", letter);
		context.put("cocktails", cocktails);

		return renderTemplate("index", context);
	}

	@GetMapping("/ingredient")
	public String goToIngredientAndCocktailList(@RequestParam String term) {

		final Ingredient ingredient = api.searchForIngredientByName(term).get(0);
		final List<Cocktail> cocktails = api.listCocktailsByIngredient(ingredient);

		Map<String, Object> context = Maps.newHashMap();
		context.put("term", term);
		context.put("ingredient", ingredient);
		context.put("cocktails", cocktails);

		return renderTemplate("ingredient", context);
	}


	@GetMapping("/cocktailsByIngredient")
	public String listCocktailsByIngredient(@RequestParam String ingredientName) {

		final List<Cocktail> cocktails = api.listCocktailsByIngredient(ingredientName);

		Map<String, Object> context = Maps.newHashMap();
		context.put("ingredientName", ingredientName);
		context.put("cocktails", cocktails);

		return renderTemplate("listing", context);
	}

	private String[] getIndex() {
		final String[] index = new String[alphabet.length()];
		for (int i = 0; i < alphabet.length(); i++) {
			index[i] = Character.toString(alphabet.charAt(i));
		}
		return index;
	}


	private String renderTemplate(String templateName, Map<String, Object> context) {

		Jinjava jinjava = new Jinjava();

		try
		{
			String template =  Resources.toString(Resources.getResource(templateName + ".html"), Charsets.UTF_8);
			return jinjava.render(template, context);
		}
		catch (Exception e)
		{
			logger.log(Level.SEVERE, e.toString());
			return e.toString();
		}
	}


}
