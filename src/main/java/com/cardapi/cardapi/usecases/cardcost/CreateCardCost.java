package com.cardapi.cardapi.usecases.cardcost;

import com.cardapi.cardapi.entities.CardCost;
import com.cardapi.cardapi.entities.Country;
import com.cardapi.cardapi.helpers.UseCase;
import com.cardapi.cardapi.repositories.CardCostRepo;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.math.BigDecimal;

@UseCase
@RequiredArgsConstructor
public class CreateCardCost {

    private final CardCostRepo cardCostRepo;

    public void command(CreateCardCost.Command command) {

        boolean exists = cardCostRepo.findByCountry(new Country(command.country)) != null;

        if (exists) {
            throw new IllegalArgumentException("Country with isoCode "+ command.country + " already exists");
        }
        cardCostRepo.save(new CardCost(command.country, command.cost));
    }

    @Value
    public static class Command {
        String country;
        BigDecimal cost;

    }
}
