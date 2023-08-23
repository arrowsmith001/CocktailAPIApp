package com.arrowsmith.cocktailapiapp;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.google.common.base.Charsets;
import com.google.common.collect.Maps;
import com.google.common.io.Resources;
import com.hubspot.jinjava.Jinjava;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.logging.LogLevel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@RestController
public class CocktailApiAppApplication {

	static Logger logger = Logger.getLogger(CocktailApiAppApplication.class.getName());

	public static void main(String[] args) {

		SpringApplication.run(CocktailApiAppApplication.class, args);

		try
		{
			CocktailApi api = new CocktailApi();
		}
		catch (Exception e)
		{
			logger.log(Level.SEVERE, () -> e.toString());
		}

	}

	private String baseUrl = "https://www.thecocktaildb.com/api/json/v1/";
	private String apiKey = "1";
	private String random = "/random.php";
	private String searchByLetter =  "/search.php?f=z";

	@GetMapping("/random")
	public String getRandomCocktail() throws IOException, InterruptedException {

		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(baseUrl + apiKey + random))
				.method("GET", HttpRequest.BodyPublishers.noBody())
				.build();


		HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

		ObjectMapper mapper = new ObjectMapper();
		SimpleModule module = new SimpleModule();
		module.addDeserializer(CocktailList.class, new CocktailListDeserializer());
		mapper.registerModule(module);

		// Deserialize
		CocktailList cocktailList = mapper.readValue(response.body(), CocktailList.class);
		Cocktail cocktail = cocktailList.first();

		// Create jin java
		Jinjava jinjava = new Jinjava();

		Map<String, Object> context = Maps.newHashMap();
		context.put("cocktailName", cocktail.getName());

		String template =  Resources.toString(Resources.getResource("template.html"), Charsets.UTF_8);

		logger.log(Level.INFO, template);

		return jinjava.render(template, context);
	}

}
