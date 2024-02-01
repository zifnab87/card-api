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
public class UpdateCardCost {

    private final CardCostRepo cardCostRepo;

    public void command(Command command) {
        CardCost cardCost = cardCostRepo.findByCountry(new Country(command.country));

        if (cardCost == null) {
            throw new IllegalArgumentException("card cost for country with iso code "+ command.country + " doesn't exist");
        }
        cardCost.setCost(command.cost);
        cardCostRepo.save(cardCost);
    }

    @Value
    public static class Command {
        String country;
        BigDecimal cost;

    }
}
