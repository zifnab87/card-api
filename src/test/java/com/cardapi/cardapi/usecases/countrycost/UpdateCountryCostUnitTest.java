package com.cardapi.cardapi.usecases.countrycost;

import com.cardapi.cardapi.AbstractUnitTest;
import com.cardapi.cardapi.adapters.persistence.cardcost.ports.FindCostByCountryPort;
import com.cardapi.cardapi.adapters.persistence.cardcost.ports.SaveCountryCostPort;
import com.cardapi.cardapi.entities.CountryCost;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class UpdateCountryCostUnitTest extends AbstractUnitTest {

    @Mock
    private FindCostByCountryPort findCostByCountryPort;

    @Mock
    private SaveCountryCostPort saveCountryCostPort;

    private UpdateCountryCost updateCountryCost;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        updateCountryCost = new UpdateCountryCost(findCostByCountryPort, saveCountryCostPort);
    }

    @Test
    public void given_existing_country_it_should_not_throw() {
        //arrange
        when(findCostByCountryPort.findByCountry(any())).thenReturn(new CountryCost("US", BigDecimal.TEN));

        //act
        UpdateCountryCost.Command command = new UpdateCountryCost.Command("US", BigDecimal.TEN);
        assertDoesNotThrow(() -> updateCountryCost.command(command));
    }

    @Test
    public void given_not_existing_country_it_should_throw() {
        //arrange
        when(findCostByCountryPort.findByCountry(any())).thenReturn(null);

        //act
        UpdateCountryCost.Command command = new UpdateCountryCost.Command("US", BigDecimal.TEN);

        //assert
        assertThrows(IllegalArgumentException.class, () -> updateCountryCost.command(command));
    }
}
