package com.cardapi.cardapi.usecases.countrycost;

import com.cardapi.cardapi.entities.CountryCost;
import com.cardapi.cardapi.helpers.UseCase;
import com.cardapi.cardapi.repositories.CountryCostRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@UseCase
@RequiredArgsConstructor
public class RetrieveAllCountryCosts {
    private final CountryCostRepo countryCostRepo;

    public Page<CountryCost> query(PageRequest pageRequest) {
        return countryCostRepo.findAll(pageRequest);
    }
}
