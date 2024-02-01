package com.cardapi.cardapi.usecases.cardcost;

import com.cardapi.cardapi.AbstractUnitTest;
import com.cardapi.cardapi.adapters.persistence.cardcost.ports.FindCostByCountryPort;
import com.cardapi.cardapi.adapters.web.bintable.BinLookUpResponse;
import com.cardapi.cardapi.adapters.web.bintable.ports.BinLookUpPort;
import com.cardapi.cardapi.entities.Country;
import com.cardapi.cardapi.entities.CountryCost;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;

public class RetrieveCardCostUnitTest extends AbstractUnitTest {

    @Mock
    private FindCostByCountryPort findCostByCountryPort;

    @Mock
    private BinLookUpPort binLookUpPort;

    private RetrieveCardCost retrieveCardCost;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        retrieveCardCost = new RetrieveCardCost(findCostByCountryPort, binLookUpPort);
    }

    @Test
    public void happy_path() {
        //arrange
        String bin = "123456";
        String countryIsoCode = "US";
        BinLookUpResponse binLookupResponse = new BinLookUpResponse(200, "message", new BinLookUpResponse.Data(new BinLookUpResponse.Country(countryIsoCode)));
        when(binLookUpPort.binLookup(bin)).thenReturn(binLookupResponse);

        CountryCost countryCost = new CountryCost(countryIsoCode, BigDecimal.TEN);
        when(findCostByCountryPort.findByCountry(new Country(countryIsoCode))).thenReturn(countryCost);


        //act
        RetrieveCardCost.Response response = retrieveCardCost.query(new RetrieveCardCost.Query("1234567890123456"));

        // assert
        assertEquals(countryIsoCode, response.getCountry());
        assertEquals(BigDecimal.TEN, response.getCost());

    }

    @Test
    public void given_PAN_less_than_8_length_it_should_throw() {
        //arrange
        String invalidPan = "1234567";

        //assert-act
        assertThrows(IllegalArgumentException.class, () -> {
            retrieveCardCost.query(new RetrieveCardCost.Query(invalidPan));
        });
    }

    @Test
    public void given_PAN_more_than_19_length_it_should_throw() {
        //arrange
        String invalidPan = "12345678901234567890";

        //assert-act
        assertThrows(IllegalArgumentException.class, () -> {
            retrieveCardCost.query(new RetrieveCardCost.Query(invalidPan));
        });
    }

    @Test
    public void given_no_cost_associated_with_a_country_Others_cost_should_be_returned() {
        //arrange
        BigDecimal costForOthers = BigDecimal.valueOf(10);
        when(findCostByCountryPort.findByCountry(new Country("US"))).thenReturn(null);
        when(findCostByCountryPort.findByCountry(new Country("Others"))).thenReturn(new CountryCost("Others", BigDecimal.valueOf(10)));

        BinLookUpResponse mockResponse = new BinLookUpResponse(200, "message", new BinLookUpResponse.Data(new BinLookUpResponse.Country("US")));
        when(binLookUpPort.binLookup(anyString())).thenReturn(mockResponse);

        RetrieveCardCost retrieveCardCost = new RetrieveCardCost(findCostByCountryPort, binLookUpPort);


        // act
        RetrieveCardCost.Query query = new RetrieveCardCost.Query("1234567890123456");
        RetrieveCardCost.Response response = retrieveCardCost.query(query);

        assertEquals("US", response.getCountry());
        assertEquals(costForOthers, response.getCost());
    }
}