package com.cardapi.cardapi.usecases.countrycost;

import com.cardapi.cardapi.entities.CountryCost;
import com.cardapi.cardapi.helpers.UseCase;
import com.cardapi.cardapi.repositories.CardCostRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@UseCase
@RequiredArgsConstructor
public class RetrieveAllCountryCosts {
    private final CardCostRepo cardCostRepo;

    public Page<CountryCost> query() {
        return cardCostRepo.findAll(PageRequest.of(0, Integer.MAX_VALUE));
    }
}
