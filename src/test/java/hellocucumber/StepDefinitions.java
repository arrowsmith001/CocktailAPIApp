package hellocucumber;

import com.arrowsmith.cocktailapiapp.api.CocktailApi;
import com.arrowsmith.cocktailapiapp.api.CocktailApiImpl;
import com.arrowsmith.cocktailapiapp.model.Cocktail;
import com.arrowsmith.cocktailapiapp.model.CocktailBase;
import com.arrowsmith.cocktailapiapp.model.Ingredient;
import io.cucumber.java.en.*;

import java.util.List;

import static org.springframework.test.util.AssertionErrors.assertTrue;

class IsItVodka {
    public static final Ingredient vodka = new Ingredient("vodka");
}

public class StepDefinitions {

    private CocktailApi api = new CocktailApiImpl(System.getenv("THE_COCKTAIL_DB_API_KEY"));
    private Ingredient ingredient;
    private List<CocktailBase> basicCocktails;
    private Cocktail selectedCocktail;


    @Given("the ingredient of vodka")
    public void theIngredientOfVodka() {
        this.ingredient = IsItVodka.vodka;
    }

    @When("I search for cocktails containing vodka")
    public void iSearchForCocktailsContainingVodka() {
        basicCocktails = api.listCocktailsByIngredient(ingredient);

    }

    @When("I select a cocktail from that list")
    public void iSelectACocktailFromThatList() {
        final int randomIndex = (int) (Math.random() * basicCocktails.size());
        final CocktailBase basicCocktail = basicCocktails.get(randomIndex);
        selectedCocktail = api.getCocktailById(basicCocktail.getId());
    }

    @Then("vodka should be included in the ingredients")
    public void vodkaShouldBeIncludedInTheIngredients() {
        final boolean hasVodka = selectedCocktail.getMeasuredIngredients().stream().anyMatch((ing) -> ing.getIngredient().getName().equalsIgnoreCase("vodka"));
        assertTrue(selectedCocktail.getName() + " does not have vodka", hasVodka);
    }
}
