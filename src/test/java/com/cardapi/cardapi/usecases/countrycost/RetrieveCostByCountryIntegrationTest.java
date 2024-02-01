package com.cardapi.cardapi.usecases.countrycost;

import com.cardapi.cardapi.AbstractIntegrationTest;
import com.cardapi.cardapi.entities.Country;
import com.cardapi.cardapi.entities.CountryCost;
import com.cardapi.cardapi.exceptions.InvalidIsoCodeException;
import com.cardapi.cardapi.exceptions.NotFoundException;
import com.cardapi.cardapi.exceptions.OthersCostCantBeDeletedException;
import com.cardapi.cardapi.repositories.CountryCostRepo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RetrieveCostByCountryIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private CountryCostRepo countryCostRepo;

    @Autowired
    private RetrieveCostByCountry retrieveCostByCountry;


    @BeforeEach
    public void setUp() {
        countryCostRepo.save(new CountryCost("EG", BigDecimal.TEN));
        countryCostRepo.save(new CountryCost("Others", BigDecimal.valueOf(15)));
    }

    @AfterEach
    public void tearDown() {
        countryCostRepo.deleteAll();
    }

    @Test
    @Transactional
    public void given_existing_country_cost_it_should_retrieve_country_cost() {

        CountryCost res =  retrieveCostByCountry.query("EG");
        assertEquals(BigDecimal.TEN,res.getCost());
        assertEquals("EG", res.getCountry().getIsoCode());
    }

    @Test
    @Transactional
    public void given_Others_as_country_it_should_retrieve_country_cost() {
        CountryCost res =  retrieveCostByCountry.query("Others");

        assertEquals(BigDecimal.valueOf(15), res.getCost());
        assertEquals("Others", res.getCountry().getIsoCode());
    }

    @Test
    @Transactional
    public void given_invalid_country_it_should_throw() {
        assertThrows(InvalidIsoCodeException.class, () -> {
            retrieveCostByCountry.query("XX");
        });
    }
}