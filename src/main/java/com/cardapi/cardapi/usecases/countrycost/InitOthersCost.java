package com.cardapi.cardapi.usecases.countrycost;

import com.cardapi.cardapi.adapters.persistence.cardcost.ports.FindCostByCountryPort;
import com.cardapi.cardapi.adapters.persistence.cardcost.ports.SaveCountryCostPort;
import com.cardapi.cardapi.entities.Country;
import com.cardapi.cardapi.entities.CountryCost;
import com.cardapi.cardapi.helpers.UseCase;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@UseCase
@RequiredArgsConstructor
public class InitOthersCost {

    private final FindCostByCountryPort findCostByCountryPort;
    private final SaveCountryCostPort saveCountryCostPort;

    public void command() {
        boolean exists = findCostByCountryPort.findByCountry(new Country("Others")) != null;
        if (exists) {
            return;
        }
        saveCountryCostPort.save(new CountryCost("Others", BigDecimal.valueOf(10)));
    }
}
