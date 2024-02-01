package com.cardapi.cardapi.usecases.cardcost;

import com.cardapi.cardapi.adapters.persistence.cardcost.ports.FindCostByCountryPort;
import com.cardapi.cardapi.adapters.web.bintable.ports.BinLookUpPort;
import com.cardapi.cardapi.entities.Country;
import com.cardapi.cardapi.entities.CountryCost;
import com.cardapi.cardapi.helpers.UseCase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@UseCase
@RequiredArgsConstructor
public class RetrieveCardCost {

    private final FindCostByCountryPort findByCountryPort;
    private final BinLookUpPort binLookUpPort;

    public Response query(Query query) {
        String pan = query.cardNumber;
        if (pan.length() < 8 || pan.length() > 19) {
            throw new IllegalArgumentException("PAN number should have length 8 to 19");
        }
        String bin = pan.substring(0, 6);
        String country = binLookUpPort.binLookup(bin).getData().getCountry().getCode().toUpperCase();
        CountryCost res = findByCountryPort.findByCountry(new Country(country));
        if (res == null) {
            res = findByCountryPort.findByCountry(new Country("Others"));
        }

        return new Response(country, res.getCost());
    }


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
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
