package hellocucumber;

import com.arrowsmith.cocktailapiapp.api.CocktailApi;
import com.arrowsmith.cocktailapiapp.api.CocktailApiImpl;
import com.arrowsmith.cocktailapiapp.model.Cocktail;
import com.arrowsmith.cocktailapiapp.model.Ingredient;
import io.cucumber.java.en.*;

import org.junit.jupiter.api.Assertions.*;

import java.util.List;

import static org.springframework.test.util.AssertionErrors.assertTrue;

class IsItVodka {
    public static final Ingredient vodka = new Ingredient("vodka");
}

public class StepDefinitions {

    private CocktailApi api = new CocktailApiImpl(System.getenv("THE_COCKTAIL_DB_API_KEY"));
    private Ingredient ingredient;
    private List<Cocktail> cocktails;
    private Cocktail selectedCocktail;


    @Given("the ingredient of vodka")
    public void theIngredientOfVodka() {
        this.ingredient = IsItVodka.vodka;
    }

    @When("I search for cocktails containing vodka")
    public void iSearchForCocktailsContainingVodka() {
        cocktails = api.listCocktailsByIngredient(ingredient);

    }

    @When("I select a cocktail from that list")
    public void iSelectACocktailFromThatList() {
        final int randomIndex = (int) (Math.random() * cocktails.size());
        final Cocktail cocktailStub = cocktails.get(randomIndex);
        selectedCocktail = api.getCocktailById(cocktailStub.getId());
    }

    @Then("vodka should be included in the ingredients")
    public void vodkaShouldBeIncludedInTheIngredients() {
        final boolean hasVodka = selectedCocktail.getIngredients().stream().anyMatch((ing) -> ing.getName().equalsIgnoreCase("vodka"));
        assertTrue(selectedCocktail.getName() + " does not have vodka", hasVodka);
    }
}
