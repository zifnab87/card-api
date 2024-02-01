package com.cardapi.cardapi.usecases.countrycost;

import com.cardapi.cardapi.entities.CountryCost;
import com.cardapi.cardapi.entities.Country;
import com.cardapi.cardapi.helpers.UseCase;
import com.cardapi.cardapi.repositories.CountryCostRepo;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.math.BigDecimal;

@UseCase
@RequiredArgsConstructor
public class CreateCountryCost {

    private final CountryCostRepo countryCostRepo;

    public void command(Command command) {

        boolean exists = countryCostRepo.findByCountry(new Country(command.country)) != null;

        if (exists) {
            throw new IllegalArgumentException("Country with isoCode "+ command.country + " already exists");
        }
        countryCostRepo.save(new CountryCost(command.country, command.cost));
    }

    @Data
    public static class Command {
        String country;
        BigDecimal cost;

    }
}
