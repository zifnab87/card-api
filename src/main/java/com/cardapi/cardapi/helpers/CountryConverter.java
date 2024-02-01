package com.cardapi.cardapi.helpers;

import com.cardapi.cardapi.entities.Country;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class CountryConverter implements AttributeConverter<Country, String> {

    @Override
    public String convertToDatabaseColumn(Country country) {
        return country.getIsoCode();
    }

    @Override
    public Country convertToEntityAttribute(String isoCode) {
        return new Country(isoCode);
    }
}