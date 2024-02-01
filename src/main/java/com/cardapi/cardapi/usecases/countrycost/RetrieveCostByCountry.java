package com.cardapi.cardapi.usecases.countrycost;

import com.cardapi.cardapi.adapters.persistence.cardcost.ports.FindCostByCountryPort;
import com.cardapi.cardapi.entities.Country;
import com.cardapi.cardapi.entities.CountryCost;
import com.cardapi.cardapi.exceptions.NotFoundException;
import com.cardapi.cardapi.helpers.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class RetrieveCostByCountry {

    private final FindCostByCountryPort findCostByCountryPort;

    public CountryCost query(String isoCode) {
        CountryCost countryCost = findCostByCountryPort.findByCountry(new Country(isoCode));
        if (countryCost == null) {
            throw new NotFoundException("Card Cost for country with isoCode: " + isoCode +" wasn't found");
        }
        return countryCost;
    }
}
