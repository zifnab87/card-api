package com.cardapi.cardapi.usecases.cardcost;

import com.cardapi.cardapi.entities.CardCost;
import com.cardapi.cardapi.helpers.UseCase;
import com.cardapi.cardapi.repositories.CardCostRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@UseCase
@RequiredArgsConstructor
public class RetrieveAllCardCosts {
    private final CardCostRepo cardCostRepo;

    public Page<CardCost> query() {
        return cardCostRepo.findAll(PageRequest.of(0, Integer.MAX_VALUE));
    }
}
