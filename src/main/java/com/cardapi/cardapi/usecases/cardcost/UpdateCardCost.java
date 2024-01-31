package com.cardapi.cardapi.usecases.cardcost;

import com.cardapi.cardapi.entities.CardCost;
import com.cardapi.cardapi.helpers.UseCase;
import com.cardapi.cardapi.repositories.CardCostRepo;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class UpdateCardCost {

    private final CardCostRepo cardCostRepo;

    public void command(CardCost cardCost) {
        cardCostRepo.save(cardCost);
    }
}
