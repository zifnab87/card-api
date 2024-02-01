package com.cardapi.cardapi.controllers;

import com.cardapi.cardapi.entities.CardCost;
import com.cardapi.cardapi.usecases.cardcost.*;
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
    private final DeleteCardCostByCountry deleteCardCostByCountry;

    @PostMapping
    public void create(@RequestBody CreateCardCost.Command command) {
        createCardCost.command(command);
    }

    @PutMapping
    public void update(@RequestBody UpdateCardCost.Command command) {
        updateCardCost.command(command);
    }

    @GetMapping
    public Page<CardCost> queryAll() {
        return retrieveAllCardCosts.query();
    }

    @GetMapping("{isoCode}")
    public CardCost queryByCountry(@PathVariable String isoCode) {
        return retrieveCardCostByCountry.query(isoCode);
    }

    @DeleteMapping("{isoCode}")
    public void deleteByCountry(@PathVariable String isoCode) {
        deleteCardCostByCountry.command(isoCode);
    }
}
