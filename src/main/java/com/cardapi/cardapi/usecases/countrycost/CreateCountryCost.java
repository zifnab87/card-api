package com.cardapi.cardapi.usecases.countrycost;

import com.cardapi.cardapi.adapters.persistence.cardcost.ports.FindCostByCountryPort;
import com.cardapi.cardapi.adapters.persistence.cardcost.ports.SaveCountryCostPort;
import com.cardapi.cardapi.entities.CountryCost;
import com.cardapi.cardapi.entities.Country;
import com.cardapi.cardapi.helpers.UseCase;
import lombok.*;

import java.math.BigDecimal;

@UseCase
@RequiredArgsConstructor
public class CreateCountryCost {

    private final FindCostByCountryPort findCostByCountryPort;
    private final SaveCountryCostPort saveCountryCostPort;

    public void command(Command command) {

        boolean exists = findCostByCountryPort.findByCountry(new Country(command.country)) != null;

        if (exists) {
            throw new IllegalArgumentException("Country with isoCode "+ command.country + " already exists");
        }
        saveCountryCostPort.save(new CountryCost(command.country, command.cost));
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class Command {
        String country;
        BigDecimal cost;

    }
}
