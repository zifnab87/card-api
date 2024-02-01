package com.cardapi.cardapi.usecases.cardcost;

import com.cardapi.cardapi.entities.CardCost;
import com.cardapi.cardapi.entities.Country;
import com.cardapi.cardapi.helpers.UseCase;
import com.cardapi.cardapi.repositories.CardCostRepo;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@UseCase
@RequiredArgsConstructor
public class InitOthersCost {

    private final CardCostRepo repo;

    public void command() {
        boolean exists = repo.findByCountry(new Country("Others")) != null;
        if (exists) {
            return;
        }
        repo.save(new CardCost("Others", BigDecimal.valueOf(12)));
    }
}
