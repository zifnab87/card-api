package com.cardapi.cardapi.entities;

import com.cardapi.cardapi.AbstractUnitTest;
import com.cardapi.cardapi.exceptions.InvalidIsoCodeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CountryTest extends AbstractUnitTest {

    @Test
    public void given_valid_iso_code_it_should_create_it() {
        String isoCode = "US";
        Country country = new Country(isoCode);
        assertEquals(isoCode, country.getIsoCode());
    }

    @Test
    public void given_valid_iso_wrong_case_it_should_throw() {
        // Valid ISO country code (ignoring case)
        String wrongCaseIso = "uS";
        assertThrows(InvalidIsoCodeException.class, () -> {
            new Country(wrongCaseIso);
        });
    }

    @Test
    public void given_others_as_iso_code_it_should_create_it() {
        String isoCode = "Others";
        Country country = new Country(isoCode);
        assertEquals(isoCode, country.getIsoCode());
    }

    @Test
    public void given_invalid_iso_code_it_should_throw() {
        String invalidIsoCode = "XX";
        assertThrows(InvalidIsoCodeException.class, () -> {
            new Country(invalidIsoCode);
        });
    }

    @Test
    public void given_null_iso_code_it_should_throw() {
        String nullIsoCode = null;
        assertThrows(InvalidIsoCodeException.class, () -> {
            new Country(nullIsoCode);
        });
    }

    @Test
    public void given_empty_iso_code_it_should_throw() {
        String emptyIsoCode = "";
        assertThrows(InvalidIsoCodeException.class, () -> {
            new Country(emptyIsoCode);
        });
    }
}