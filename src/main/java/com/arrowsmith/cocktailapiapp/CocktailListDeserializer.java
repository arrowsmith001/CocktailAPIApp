package com.arrowsmith.cocktailapiapp;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.io.IOException;
import java.util.List;

public class CocktailListDeserializer extends StdDeserializer<CocktailList> {

    public CocktailListDeserializer() {
        this(null);
    }

    @Override
    public CocktailList deserialize(JsonParser jp, DeserializationContext deserializationContext) throws IOException
    {

        final CocktailList out = new CocktailList();

        // Root node
        JsonNode node = jp.getCodec().readTree(jp);

        // Drinks list
        JsonNode drinksList = node.get("drinks");

        int i = 0;
        while(drinksList.has(i))
        {

           final JsonNode element = drinksList.get(i++);

           // Deserialize
           final Cocktail cocktail = Cocktail.fromJsonNode(element);

           out.addCocktail(cocktail);
        }

        System.out.println(out);

        return out;

    }

    public CocktailListDeserializer(Class<?> vc) {
        super(vc);
    }

}