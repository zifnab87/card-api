package com.cardapi.cardapi.usecases.countrycost;

import com.cardapi.cardapi.entities.CountryCost;
import com.cardapi.cardapi.entities.Country;
import com.cardapi.cardapi.helpers.UseCase;
import com.cardapi.cardapi.repositories.CardCostRepo;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.math.BigDecimal;

@UseCase
@RequiredArgsConstructor
public class UpdateCountryCost {

    private final CardCostRepo cardCostRepo;

    public void command(Command command) {
        CountryCost countryCost = cardCostRepo.findByCountry(new Country(command.country));

        if (countryCost == null) {
            throw new IllegalArgumentException("card cost for country with iso code "+ command.country + " doesn't exist");
        }
        countryCost.setCost(command.cost);
        cardCostRepo.save(countryCost);
    }

    @Value
    public static class Command {
        String country;
        BigDecimal cost;

    }
}
