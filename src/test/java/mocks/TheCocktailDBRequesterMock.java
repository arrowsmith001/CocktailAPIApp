package mocks;

import com.arrowsmith.cocktailapiapp.api.CocktailApiRequester;
import org.apache.maven.wagon.resource.Resource;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class TheCocktailDBRequesterMock implements CocktailApiRequester {

    private static final String BASE_URL = "src/test/resources/api_mock_data/";

    @Override
    public String getRandomCocktail() {
        return readFromFile( "mojito.json");
    }

    @Override
    public String searchCocktailsByLetter(char startingLetter) {
       return readFromFile( "letter_a.json");
    }

    @Override
    public String getCocktailById(Object id) {
        return readFromFile( "mojito.json");
    }

    @Override
    public String getIngredientById(Object id) {
        return readFromFile( "baileys.json");
    }

    @Override
    public String getIngredientByName(String term) {
        return readFromFile( "baileys.json");
    }

    @Override
    public String searchCocktailByName(String term) {
        return readFromFile( "search_mojito.json");
    }

    @Override
    public String searchCocktailsByIngredientName(String ingredientName) {
        return readFromFile( "search_milk.json");
    }


    private String readFromFile(String fileName)
    {
        File file = new File(BASE_URL + fileName);

        try
        {
            BufferedReader br = new BufferedReader(new FileReader(file));
            return br.readLine();
        }catch(Exception e)
        {
            return null;
        }
    }
}
