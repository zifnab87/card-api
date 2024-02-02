package com.cardapi.cardapi.usecases.countrycost;


import com.cardapi.cardapi.AbstractIntegrationTest;
import com.cardapi.cardapi.entities.Country;
import com.cardapi.cardapi.entities.CountryCost;
import com.cardapi.cardapi.exceptions.InvalidIsoCodeException;
import com.cardapi.cardapi.exceptions.OthersCostCantBeDeletedException;
import com.cardapi.cardapi.repositories.CountryCostRepo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DeleteCostByCountryIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private CountryCostRepo countryCostRepo;

    @Autowired
    private DeleteCostByCountry deleteCostByCountry;


    @BeforeEach
    public void setUp() {
        countryCostRepo.deleteAll();
        countryCostRepo.save(new CountryCost("EG", BigDecimal.valueOf(12.5)));
        countryCostRepo.save(new CountryCost("Others", BigDecimal.valueOf(15)));
    }

    @AfterEach
    public void tearDown() {
        countryCostRepo.deleteAll();
    }

    @Test
    @Transactional
    public void given_existing_country_cost_it_should_delete_country_cost() {
        deleteCostByCountry.command("EG");

        CountryCost res = countryCostRepo.findByCountry(new Country("EG"));
        assertNull(res);
    }

    @Test
    @Transactional
    public void given_not_existing_country_cost_it_should_throw() {
        assertThrows(IllegalArgumentException.class, () -> {
            deleteCostByCountry.command("US");
        });
    }

    @Test
    @Transactional
    public void given_Others_as_country_it_should_throw() {
        assertThrows(OthersCostCantBeDeletedException.class, () -> {
            deleteCostByCountry.command("Others");
        });
    }


    @Test
    @Transactional
    public void given_invalid_country_it_should_throw() {
        assertThrows(InvalidIsoCodeException.class, () -> {
            deleteCostByCountry.command("XX");
        });
    }
}