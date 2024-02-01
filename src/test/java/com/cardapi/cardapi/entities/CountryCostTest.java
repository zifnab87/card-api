package com.cardapi.cardapi.entities;

import com.cardapi.cardapi.AbstractUnitTest;
import com.cardapi.cardapi.exceptions.InvalidCountryCostException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CountryCostTest extends AbstractUnitTest {

    @Test
    public void given_positive_cost_in_setter_it_should_set_cost() {
        CountryCost countryCost = new CountryCost();
        BigDecimal positiveCost = BigDecimal.valueOf(50);

        countryCost.setCost(positiveCost);

        assertEquals(positiveCost, countryCost.getCost());
    }

    @Test
    public void given_zero_cost_it_in_setter_should_throw() {
        CountryCost countryCost = new CountryCost();
        BigDecimal costZero = BigDecimal.ZERO;

        assertThrows(InvalidCountryCostException.class, () -> {
            countryCost.setCost(costZero);
        });
    }

    @Test
    public void given_negative_cost_in_setter_it_should_throw() {
        CountryCost countryCost = new CountryCost();
        BigDecimal negativeCost = BigDecimal.valueOf(-10);

        assertThrows(InvalidCountryCostException.class, () -> {
            countryCost.setCost(negativeCost);
        });
    }



    @Test
    public void given_positive_cost_in_constructor_it_should_set_cost() {
        String isoCode = "US";
        BigDecimal cost = BigDecimal.valueOf(100);

        CountryCost countryCost = new CountryCost(isoCode, cost);

        assertEquals(isoCode, countryCost.getCountry().getIsoCode());
        assertEquals(cost, countryCost.getCost());
    }

    @Test
    public void given_zero_cost_in_constructor_it_should_throw() {
        String isoCode = "US";
        BigDecimal zeroCost = BigDecimal.ZERO;

        assertThrows(InvalidCountryCostException.class, () -> {
            new CountryCost(isoCode, zeroCost);
        });
    }

    @Test
    public void given_negative_cost_in_constructor_it_should_throw() {
        String isoCode = "US";
        BigDecimal negativeCost = BigDecimal.valueOf(-50);

        assertThrows(InvalidCountryCostException.class, () -> {
            new CountryCost(isoCode, negativeCost);
        });
    }

}