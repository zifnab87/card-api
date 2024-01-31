package com.cardapi.cardapi.usecases.cardcost;

import com.cardapi.cardapi.helpers.UseCase;
import com.cardapi.cardapi.repositories.CardCostRepo;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class DeleteCardCostByCountry {

    private final CardCostRepo cardCostRepo;

    public void command(String country) {
        cardCostRepo.deleteByCountry(country);
    }

}
