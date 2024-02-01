package com.cardapi.cardapi.usecases.countrycost;

import com.cardapi.cardapi.entities.Country;
import com.cardapi.cardapi.exceptions.OthersCostCantBeDeletedException;
import com.cardapi.cardapi.helpers.UseCase;
import com.cardapi.cardapi.repositories.CardCostRepo;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class DeleteCostByCountry {

    private final CardCostRepo cardCostRepo;

    public void command(String isoCode) {

        if (isoCode.equalsIgnoreCase("Others")) {
            throw new OthersCostCantBeDeletedException("card cost for country Others can't be deleted");
        }

        boolean exists = cardCostRepo.findByCountry(new Country(isoCode)) != null;

        if (!exists) {
            throw new IllegalArgumentException("card cost for country with iso code "+ isoCode + " doesn't exist");
        }

        cardCostRepo.deleteByCountry(new Country(isoCode));
    }

}
