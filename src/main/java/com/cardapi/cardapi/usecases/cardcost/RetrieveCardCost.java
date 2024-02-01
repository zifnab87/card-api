package com.cardapi.cardapi.usecases.cardcost;

import com.cardapi.cardapi.adapters.web.bintable.ports.BinLookUp;
import com.cardapi.cardapi.entities.Country;
import com.cardapi.cardapi.entities.CountryCost;
import com.cardapi.cardapi.helpers.UseCase;
import com.cardapi.cardapi.repositories.CountryCostRepo;
import lombok.*;

import java.math.BigDecimal;
import java.util.Locale;

@UseCase
@RequiredArgsConstructor
public class RetrieveCardCost {

    private final CountryCostRepo countryCostRepo;

    private final BinLookUp binLookUpPort;

    public Response query(Query query) {
        String pan = query.cardNumber;
        String country = binLookUpPort.binLookup("403244").getData().getCountry().getCode().toUpperCase();
        CountryCost res = countryCostRepo.findByCountry(new Country(country));
        if (res == null) {
            res = countryCostRepo.findByCountry(new Country("Others"));
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
