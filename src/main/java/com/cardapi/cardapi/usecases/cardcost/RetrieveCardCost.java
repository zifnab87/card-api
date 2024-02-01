package com.cardapi.cardapi.usecases.cardcost;

import com.cardapi.cardapi.adapters.persistence.cardcost.ports.FindCostByCountryPort;
import com.cardapi.cardapi.adapters.web.bintable.ports.BinLookUp;
import com.cardapi.cardapi.entities.Country;
import com.cardapi.cardapi.entities.CountryCost;
import com.cardapi.cardapi.helpers.UseCase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@UseCase
@RequiredArgsConstructor
public class RetrieveCardCost {

    private final FindCostByCountryPort findByCountryPort;
    private final BinLookUp binLookUpPort;

    public Response query(Query query) {
        String pan = query.cardNumber;
        String country = binLookUpPort.binLookup("403244").getData().getCountry().getCode().toUpperCase();
        CountryCost res = findByCountryPort.findByCountry(new Country(country));
        if (res == null) {
            res = findByCountryPort.findByCountry(new Country("Others"));
        }

        return new Response(country, res.getCost());
    }


    @Data
    public static class Query {
        String cardNumber;
    }

    @Data
    @AllArgsConstructor
    public static class Response {
        String country;
        BigDecimal cost;
    }
}
