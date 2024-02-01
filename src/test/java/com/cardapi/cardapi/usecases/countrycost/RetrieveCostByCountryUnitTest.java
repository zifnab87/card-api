package com.cardapi.cardapi.usecases.countrycost;

import com.cardapi.cardapi.AbstractUnitTest;
import com.cardapi.cardapi.adapters.persistence.cardcost.ports.FindCostByCountryPort;
import com.cardapi.cardapi.adapters.persistence.cardcost.ports.SaveCountryCostPort;
import com.cardapi.cardapi.entities.CountryCost;
import com.cardapi.cardapi.exceptions.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class RetrieveCostByCountryUnitTest extends AbstractUnitTest {

    @Mock
    private FindCostByCountryPort findCostByCountryPort;

    private RetrieveCostByCountry retrieveCostByCountry;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        retrieveCostByCountry = new RetrieveCostByCountry(findCostByCountryPort);
    }

    @Test
    public void given_existing_country_it_should_not_throw() {
        //arrange
        when(findCostByCountryPort.findByCountry(any())).thenReturn(new CountryCost("US", BigDecimal.TEN));

        //act
        assertDoesNotThrow(() -> retrieveCostByCountry.query("US"));
    }

    @Test
    public void given_not_existing_country_it_should_throw() {
        //arrange
        when(findCostByCountryPort.findByCountry(any())).thenReturn(null);

        //assert
        assertThrows(NotFoundException.class, () -> retrieveCostByCountry.query("US"));
    }
}
