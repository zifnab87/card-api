package com.cardapi.cardapi.usecases.cardcost;

import com.cardapi.cardapi.AbstractIntegrationTest;
import com.cardapi.cardapi.entities.Country;
import com.cardapi.cardapi.entities.CountryCost;
import com.cardapi.cardapi.repositories.CountryCostRepo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class RetrieveCardCostIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private RetrieveCardCost retrieveCardCost;

    @Autowired
    private CountryCostRepo countryCostRepo;


    @BeforeEach
    public void setUp() {
        countryCostRepo.save(new CountryCost("EG", BigDecimal.valueOf(12.5)));
        countryCostRepo.save(new CountryCost("Others", BigDecimal.valueOf(10)));
    }

    @AfterEach
    public void tearDown() {
        countryCostRepo.deleteAll();
    }

    @Test
    @Transactional
    public void given_valid_PAN_number_with_associated_country_cost_it_should_return_country_cost() { //calls stubbed lookup
        var res = retrieveCardCost.query(new RetrieveCardCost.Query("53231312"));

        assertTrue(BigDecimal.valueOf(12.5).compareTo(res.cost) == 0);
        assertEquals("EG", res.country);
    }

    @Test
    @Transactional
    public void given_valid_PAN_number_with_not_associated_country_cost_it_should_return_others_cost() { //calls stubbed lookup
        var res = retrieveCardCost.query(new RetrieveCardCost.Query("123456789"));

        assertTrue(BigDecimal.valueOf(10).compareTo(res.cost) == 0);
        assertEquals("US", res.country);
    }

    @Test
    @Transactional
    public void given_invalid_PAN_with_length_less_than_8_it_should_throw() {
        assertThrows(IllegalArgumentException.class, () -> {
            retrieveCardCost.query(new RetrieveCardCost.Query("1234567"));
        });
    }

    @Test
    @Transactional
    public void given_invalid_PAN_with_length_more_than_19_it_should_throw() {
        assertThrows(IllegalArgumentException.class, () -> {
            retrieveCardCost.query(new RetrieveCardCost.Query("12345678901234567890"));
        });
    }



}
