package hellocucumber;

import com.arrowsmith.cocktailapiapp.api.CocktailApi;
import com.arrowsmith.cocktailapiapp.api.CocktailApiImpl;
import com.arrowsmith.cocktailapiapp.api.TheCocktailDBRequester;
import com.arrowsmith.cocktailapiapp.model.Cocktail;
import com.arrowsmith.cocktailapiapp.model.Instructions;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

import static org.junit.Assert.assertFalse;

public class SelectADifferentLanguageTest {

    private String getApiKey(){
        return System.getenv().get("THE_COCKTAIL_DB_API_KEY");
    }

    final CocktailApi api = new CocktailApiImpl(new TheCocktailDBRequester(getApiKey()));

    private Cocktail selectedCocktail;
    private Instructions selectedInstructions;



    @Given("I select a cocktail with the default language")
    public void iSelectACocktailWithTheDefaultLanguage() {
        selectedCocktail = api.getRandomCocktail();
        selectedInstructions = selectedCocktail.getInstructions().get(0);
    }

    @And("the cocktail has instructions in multiple languages")
    public void theCocktailHasInstructionsInMultipleLanguages() {
        assert(selectedCocktail.getInstructions().size() > 1);
    }

    @When("I select a different language")
    public void iSelectADifferentLanguage() {

        final List<Instructions> instructions = selectedCocktail.getInstructions();

        // Random index in the range [1, instructions.size()]
        final int randomIndex = 1 + (int) (Math.random() * (instructions.size() - 1));

        selectedInstructions = selectedCocktail.getInstructions().get(randomIndex);
    }

    @Then("the selected language should not be the default language")
    public void theSelectedLanguageShouldNotBeTheDefaultLanguage() {

        final Instructions defaultInstructions = selectedCocktail.getInstructions().get(0);

        boolean isDefault = selectedInstructions.equals(defaultInstructions);

        assertFalse(isDefault);
    }
}


