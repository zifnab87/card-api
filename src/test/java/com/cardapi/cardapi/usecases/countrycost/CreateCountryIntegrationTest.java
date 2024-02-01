package com.cardapi.cardapi.usecases.countrycost;

import com.cardapi.cardapi.AbstractIntegrationTest;
import com.cardapi.cardapi.entities.Country;
import com.cardapi.cardapi.entities.CountryCost;
import com.cardapi.cardapi.exceptions.InvalidCountryCostException;
import com.cardapi.cardapi.exceptions.InvalidIsoCodeException;
import com.cardapi.cardapi.repositories.CountryCostRepo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CreateCountryIntegrationTest  extends AbstractIntegrationTest {

    @Autowired
    private CountryCostRepo countryCostRepo;

    @Autowired
    private CreateCountryCost createCountryCost;


    @BeforeEach
    public void setUp() {
        countryCostRepo.save(new CountryCost("EG", BigDecimal.valueOf(12.5)));
        countryCostRepo.save(new CountryCost("Others", BigDecimal.valueOf(15)));
    }

    @AfterEach
    public void tearDown() {
        countryCostRepo.deleteAll();
    }

    @Test
    @Transactional
    public void given_non_existing_country_cost_it_should_store_country_cost() {
        createCountryCost.command(new CreateCountryCost.Command("US", BigDecimal.TEN));

        CountryCost res = countryCostRepo.findByCountry(new Country("US"));

        assertEquals(BigDecimal.TEN,res.getCost());
        assertEquals("US",res.getCountry().getIsoCode());
    }

    @Test
    @Transactional
    public void given_preexisting_country_it_should_throw() {

        assertThrows(IllegalArgumentException.class, () -> {
             createCountryCost.command(new CreateCountryCost.Command("EG", BigDecimal.TEN));
        });
    }

    @Test
    @Transactional
    public void given_invalid_country_it_should_throw() {
        assertThrows(InvalidIsoCodeException.class, () -> {
            createCountryCost.command(new CreateCountryCost.Command("XX", BigDecimal.valueOf(3)));
        });
    }

    @Test
    @Transactional
    public void given_negative_cost_it_should_throw() {

        assertThrows(InvalidCountryCostException.class, () -> {
            createCountryCost.command(new CreateCountryCost.Command("US", BigDecimal.valueOf(-4)));
        });
    }

    @Test
    @Transactional
    public void given_zero_cost_it_should_throw() {
        assertThrows(InvalidCountryCostException.class, () -> {
            createCountryCost.command(new CreateCountryCost.Command("US", BigDecimal.valueOf(0)));
        });
    }
}
