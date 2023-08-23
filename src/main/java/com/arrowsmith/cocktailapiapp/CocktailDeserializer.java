package com.arrowsmith.cocktailapiapp;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CocktailDeserializer extends StdDeserializer<Cocktail> {

    public CocktailDeserializer() {
        this(null);
    }

    @Override
    public Cocktail deserialize(JsonParser jp, DeserializationContext deserializationContext) throws IOException
    {
        // Root node
        JsonNode node = jp.getCodec().readTree(jp);

        // Drink
        return Cocktail.fromJsonNode(node);

    }

    public CocktailDeserializer(Class<?> vc) {
        super(vc);
    }

}
