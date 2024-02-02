package com.cardapi.cardapi.entities;

import com.cardapi.cardapi.exceptions.InvalidIsoCodeException;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;

import java.util.Locale;
import java.util.Set;

@Value
@Schema(implementation = String.class)
public class Country {
    private static final Set<String> ISO_COUNTRIES = Set.of(Locale.getISOCountries());

    private final String isoCode;

    public Country(String isoCode) {
        if (isoCode == null || (!ISO_COUNTRIES.contains(isoCode) && !isoCode.equalsIgnoreCase("Others"))) {
            throw new InvalidIsoCodeException("isoCode " + isoCode + " is invalid");
        }
        this.isoCode = isoCode;
    }


}
