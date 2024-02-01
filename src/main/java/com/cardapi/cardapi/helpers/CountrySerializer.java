package com.cardapi.cardapi.helpers;

import com.cardapi.cardapi.entities.Country;
import com.fasterxml.jackson.databind.util.StdConverter;

public class CountrySerializer extends StdConverter<Country, String> {
    @Override
    public String convert(Country country) {
        return country.getIsoCode();
    }
}
