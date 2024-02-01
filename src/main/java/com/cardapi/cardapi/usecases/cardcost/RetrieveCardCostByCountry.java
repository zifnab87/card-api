package com.cardapi.cardapi.usecases.cardcost;

import com.cardapi.cardapi.entities.CardCost;
import com.cardapi.cardapi.entities.Country;
import com.cardapi.cardapi.exceptions.NotFoundException;
import com.cardapi.cardapi.helpers.UseCase;
import com.cardapi.cardapi.repositories.CardCostRepo;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class RetrieveCardCostByCountry {

    private final CardCostRepo cardCostRepo;

    public CardCost query(String isoCode) {
        CardCost cardCost = cardCostRepo.findByCountry(new Country(isoCode));
        if (cardCost == null) {
            throw new NotFoundException("Card Cost for country with isoCode: " + isoCode +" wasn't found");
        }
        return  cardCost;
    }
}
