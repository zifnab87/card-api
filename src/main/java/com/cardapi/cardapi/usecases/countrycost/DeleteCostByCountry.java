package com.cardapi.cardapi.usecases.countrycost;

import com.cardapi.cardapi.entities.Country;
import com.cardapi.cardapi.exceptions.OthersCostCantBeDeletedException;
import com.cardapi.cardapi.helpers.UseCase;
import com.cardapi.cardapi.repositories.CountryCostRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
public class DeleteCostByCountry {

    private final CountryCostRepo countryCostRepo;

    @Transactional // https://stackoverflow.com/a/77681667/986160
    public void command(String isoCode) {

        if (isoCode.equalsIgnoreCase("Others")) {
            throw new OthersCostCantBeDeletedException("card cost for country Others can't be deleted");
        }

        boolean exists = countryCostRepo.findByCountry(new Country(isoCode)) != null;

        if (!exists) {
            throw new IllegalArgumentException("card cost for country with iso code "+ isoCode + " doesn't exist");
        }

        countryCostRepo.deleteByCountry(new Country(isoCode));
    }

}
