package com.cardapi.cardapi.helpers;

import com.cardapi.cardapi.entities.Country;
import com.fasterxml.jackson.databind.util.StdConverter;

public class CountryDeserializer extends StdConverter<String, Country> {
    @Override
    public Country convert(String s) {
        return new Country(s);
    }
}
