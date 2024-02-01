package com.cardapi.cardapi.usecases.countrycost;

import com.cardapi.cardapi.AbstractUnitTest;
import com.cardapi.cardapi.adapters.persistence.cardcost.ports.FindCostByCountryPort;
import com.cardapi.cardapi.adapters.persistence.cardcost.ports.SaveCountryCostPort;
import com.cardapi.cardapi.adapters.web.bintable.ports.BinLookUpPort;
import com.cardapi.cardapi.entities.CountryCost;
import com.cardapi.cardapi.usecases.cardcost.RetrieveCardCost;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CreateCountryCostUnitTest extends AbstractUnitTest {

    @Mock
    private FindCostByCountryPort findCostByCountryPort;

    @Mock
    private SaveCountryCostPort saveCountryCostPort;

    private CreateCountryCost createCountryCost;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        createCountryCost = new CreateCountryCost(findCostByCountryPort, saveCountryCostPort);
    }

    @Test
    public void given_not_existing_country_it_should_not_throw() {
        //arrange
        when(findCostByCountryPort.findByCountry(any())).thenReturn(null);

        //act
        CreateCountryCost.Command command = new CreateCountryCost.Command("US", BigDecimal.TEN);
        assertDoesNotThrow(() -> createCountryCost.command(command));
    }

    @Test
    public void given_existing_country_it_should_throw() {
        //arrange
        when(findCostByCountryPort.findByCountry(any())).thenReturn(new CountryCost("US", BigDecimal.TEN));

        //act
        CreateCountryCost.Command command = new CreateCountryCost.Command("US", BigDecimal.TEN);

        //assert
         assertThrows(IllegalArgumentException.class, () -> createCountryCost.command(command));
    }
}