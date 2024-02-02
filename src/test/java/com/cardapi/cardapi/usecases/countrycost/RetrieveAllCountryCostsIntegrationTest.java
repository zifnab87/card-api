package com.cardapi.cardapi.usecases.countrycost;

import com.cardapi.cardapi.AbstractIntegrationTest;
import com.cardapi.cardapi.entities.Country;
import com.cardapi.cardapi.entities.CountryCost;
import com.cardapi.cardapi.exceptions.InvalidIsoCodeException;
import com.cardapi.cardapi.repositories.CountryCostRepo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RetrieveAllCountryCostsIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private CountryCostRepo countryCostRepo;

    @Autowired
    private RetrieveAllCountryCosts retrieveAllCountryCosts;


    @BeforeEach
    public void setUp() {
        countryCostRepo.deleteAll();
        countryCostRepo.save(new CountryCost("EG", BigDecimal.TEN));
        countryCostRepo.save(new CountryCost("US", BigDecimal.TEN));
        countryCostRepo.save(new CountryCost("GR", BigDecimal.TEN));
        countryCostRepo.save(new CountryCost("IT", BigDecimal.TEN));
        countryCostRepo.save(new CountryCost("ES", BigDecimal.TEN));
        countryCostRepo.save(new CountryCost("DE", BigDecimal.TEN));
        countryCostRepo.save(new CountryCost("CN", BigDecimal.TEN));
        countryCostRepo.save(new CountryCost("FR", BigDecimal.TEN));
        countryCostRepo.save(new CountryCost("NO", BigDecimal.TEN));
        countryCostRepo.save(new CountryCost("SE", BigDecimal.TEN));
        countryCostRepo.save(new CountryCost("Others", BigDecimal.valueOf(15)));
    }

    @AfterEach
    public void tearDown() {
        countryCostRepo.deleteAll();
    }

    @Test
    @Transactional
    public void given_page_size_10_it_creates_two_pages() {

        Page<CountryCost> page1 =  retrieveAllCountryCosts.query(PageRequest.of(0,10));
        Page<CountryCost> page2 =  retrieveAllCountryCosts.query(PageRequest.of(1,10));

        assertEquals(10, page1.getNumberOfElements());
        assertEquals(1, page2.getNumberOfElements());
        assertEquals(11,page1.getTotalElements());
        assertEquals(2,page1.getTotalPages());
    }
}