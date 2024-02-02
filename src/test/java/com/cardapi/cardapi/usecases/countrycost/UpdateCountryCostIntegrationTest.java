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

import static org.junit.jupiter.api.Assertions.*;

public class UpdateCountryCostIntegrationTest  extends AbstractIntegrationTest {

    @Autowired
    private CountryCostRepo countryCostRepo;

    @Autowired
    private UpdateCountryCost updateCountryCost;


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
    public void given_existing_country_cost_it_should_update_country_cost() {
        updateCountryCost.command(new UpdateCountryCost.Command("EG", BigDecimal.ONE));

        CountryCost res = countryCostRepo.findByCountry(new Country("EG"));

        assertEquals("EG",res.getCountry().getIsoCode());
        assertEquals(BigDecimal.ONE,res.getCost());
    }

    @Test
    @Transactional
    public void given_not_existing_country_cost_it_should_throw() {
        assertThrows(IllegalArgumentException.class, () -> {
            updateCountryCost.command(new UpdateCountryCost.Command("US", BigDecimal.ONE));
        });
    }


    @Test
    @Transactional
    public void given_invalid_country_it_should_throw() {
        assertThrows(InvalidIsoCodeException.class, () -> {
            updateCountryCost.command(new UpdateCountryCost.Command("XX", BigDecimal.valueOf(3)));
        });
    }



    @Test
    @Transactional
    public void given_negative_cost_it_should_throw() {

        assertThrows(InvalidCountryCostException.class, () -> {
            updateCountryCost.command(new UpdateCountryCost.Command("EG", BigDecimal.valueOf(-4)));
        });
    }

    @Test
    @Transactional
    public void given_zero_cost_it_should_throw() {
        assertThrows(InvalidCountryCostException.class, () -> {
            updateCountryCost.command(new UpdateCountryCost.Command("EG", BigDecimal.valueOf(0)));
        });
    }
}