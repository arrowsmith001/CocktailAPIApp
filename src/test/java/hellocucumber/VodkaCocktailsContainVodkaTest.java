package hellocucumber;

import com.arrowsmith.cocktailapiapp.api.CocktailApi;
import com.arrowsmith.cocktailapiapp.api.CocktailApiImpl;
import com.arrowsmith.cocktailapiapp.api.TheCocktailDBRequester;
import com.arrowsmith.cocktailapiapp.model.*;
import io.cucumber.java.en.*;

import java.util.List;

import static org.springframework.test.util.AssertionErrors.assertTrue;


public class VodkaCocktailsContainVodkaTest {

    public static final Ingredient vodka = new Ingredient("vodka");

    private String getApiKey(){
        return System.getenv().get("THE_COCKTAIL_DB_API_KEY");
    }

    final CocktailApi api = new CocktailApiImpl(new TheCocktailDBRequester(getApiKey()));

    private Ingredient ingredient;
    private List<BasicCocktail> basicCocktails;
    private Cocktail selectedCocktail;


    @Given("the ingredient of vodka")
    public void theIngredientOfVodka() {
        this.ingredient = vodka;
    }

    @When("I search for cocktails containing vodka")
    public void iSearchForCocktailsContainingVodka()
    {
        basicCocktails = api.listCocktailsByIngredient(ingredient);
    }

    @When("I select a cocktail from that list")
    public void iSelectACocktailFromThatList() {
        final int randomIndex = (int) (Math.random() * basicCocktails.size());
        final BasicCocktail basicCocktail = basicCocktails.get(randomIndex);
        selectedCocktail = api.getCocktailById(basicCocktail.getId());
    }

    @Then("vodka should be included in the ingredients")
    public void vodkaShouldBeIncludedInTheIngredients() {
        final boolean hasVodka = selectedCocktail.getMeasuredIngredients().stream().anyMatch((ing) -> ing.getName().equalsIgnoreCase("vodka"));
        assertTrue(selectedCocktail.getName() + " does not have vodka", hasVodka);
    }



}


