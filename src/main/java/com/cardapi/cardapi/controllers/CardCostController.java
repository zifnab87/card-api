package com.cardapi.cardapi.controllers;

import com.cardapi.cardapi.entities.CardCost;
import com.cardapi.cardapi.usecases.cardcost.CreateCardCost;
import com.cardapi.cardapi.usecases.cardcost.RetrieveAllCardCosts;
import com.cardapi.cardapi.usecases.cardcost.RetrieveCardCostByCountry;
import com.cardapi.cardapi.usecases.cardcost.UpdateCardCost;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("card-costs")
@RequiredArgsConstructor
public class CardCostController {

    private final CreateCardCost createCardCost;
    private final UpdateCardCost updateCardCost;
    private final RetrieveCardCostByCountry retrieveCardCostByCountry;
    private final RetrieveAllCardCosts retrieveAllCardCosts;

    @PostMapping
    public void create(@RequestBody CardCost cardCost) {
        createCardCost.command(cardCost);
    }

    @PutMapping
    public void update(@RequestBody CardCost cardCost) {
        updateCardCost.command(cardCost);
    }

    @GetMapping
    public Page<CardCost> queryAll() {
        return retrieveAllCardCosts.query();
    }

    @GetMapping("{country}")
    public CardCost queryAll(@PathVariable String country) {
        return retrieveCardCostByCountry.query(country);
    }
}
