package com.cardapi.cardapi.usecases.cardcost;

import com.cardapi.cardapi.entities.CardCost;
import com.cardapi.cardapi.helpers.UseCase;
import com.cardapi.cardapi.repositories.CardCostRepo;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class RetrieveCardCostByCountry {

    private final CardCostRepo cardCostRepo;

    public CardCost query(String country) {
        return cardCostRepo.findByCountry(country);
    }
}
