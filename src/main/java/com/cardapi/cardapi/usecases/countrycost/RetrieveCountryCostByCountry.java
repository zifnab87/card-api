package com.cardapi.cardapi.usecases.countrycost;

import com.cardapi.cardapi.entities.CountryCost;
import com.cardapi.cardapi.entities.Country;
import com.cardapi.cardapi.exceptions.NotFoundException;
import com.cardapi.cardapi.helpers.UseCase;
import com.cardapi.cardapi.repositories.CardCostRepo;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class RetrieveCountryCostByCountry {

    private final CardCostRepo cardCostRepo;

    public CountryCost query(String isoCode) {
        CountryCost countryCost = cardCostRepo.findByCountry(new Country(isoCode));
        if (countryCost == null) {
            throw new NotFoundException("Card Cost for country with isoCode: " + isoCode +" wasn't found");
        }
        return countryCost;
    }
}
