package com.cardapi.cardapi.usecases.countrycost;

import com.cardapi.cardapi.adapters.persistence.cardcost.ports.FindCostByCountryPort;
import com.cardapi.cardapi.adapters.persistence.cardcost.ports.DeleteCostByCountryPort;
import com.cardapi.cardapi.entities.Country;
import com.cardapi.cardapi.exceptions.OthersCostCantBeDeletedException;
import com.cardapi.cardapi.helpers.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
public class DeleteCostByCountry {

    private final FindCostByCountryPort findCostByCountryPort;
    private final DeleteCostByCountryPort deleteCostByCountryPort;

    @Transactional // https://stackoverflow.com/a/77681667/986160
    public void command(String isoCode) {

        if (isoCode.equalsIgnoreCase("Others")) {
            throw new OthersCostCantBeDeletedException("card cost for country Others can't be deleted");
        }

        boolean exists = findCostByCountryPort.findByCountry(new Country(isoCode)) != null;

        if (!exists) {
            throw new IllegalArgumentException("card cost for country with iso code "+ isoCode + " doesn't exist");
        }

        deleteCostByCountryPort.deleteByCountry(new Country(isoCode));
    }

}
