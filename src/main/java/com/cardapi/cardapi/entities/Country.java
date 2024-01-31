package com.cardapi.cardapi.entities;

import jakarta.persistence.Embeddable;
import lombok.Value;

import java.util.Locale;
import java.util.Set;

@Embeddable
@Value
public class Country {
    private static final Set<String> ISO_COUNTRIES = Set.of(Locale.getISOCountries());

    private final String isoCode;

    public Country(String isoCode) {
        if (!ISO_COUNTRIES.contains(isoCode) || isoCode.equalsIgnoreCase("Others")) {
            throw new IllegalArgumentException("isoCode" + isoCode + " is invalid");
        }
        this.isoCode = isoCode;

    }


}
