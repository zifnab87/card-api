package com.cardapi.cardapi.usecases.countrycost;

import com.cardapi.cardapi.adapters.persistence.cardcost.ports.FindCostByCountryPort;
import com.cardapi.cardapi.adapters.persistence.cardcost.ports.SaveCountryCostPort;
import com.cardapi.cardapi.entities.CountryCost;
import com.cardapi.cardapi.entities.Country;
import com.cardapi.cardapi.helpers.UseCase;
import com.cardapi.cardapi.repositories.CountryCostRepo;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.math.BigDecimal;

@UseCase
@RequiredArgsConstructor
public class UpdateCountryCost {

    private final FindCostByCountryPort findCostByCountryPort;
    private final SaveCountryCostPort saveCountryCostPort;

    public void command(Command command) {
        CountryCost countryCost = findCostByCountryPort.findByCountry(new Country(command.country));

        if (countryCost == null) {
            throw new IllegalArgumentException("card cost for country with iso code "+ command.country + " doesn't exist");
        }
        countryCost.setCost(command.cost);
        saveCountryCostPort.save(countryCost);
    }

    @Data
    public static class Command {
        String country;
        BigDecimal cost;

    }
}
