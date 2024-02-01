package com.cardapi.cardapi.controllers;

import com.cardapi.cardapi.usecases.cardcost.RetrieveCardCost;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("payment-cards-cost")
@RequiredArgsConstructor
public class CardCostController {

    private final RetrieveCardCost retrieveCardCost;

    @PostMapping
    public RetrieveCardCost.Response retrieveCardCost(@RequestBody RetrieveCardCost.Query query) {
        return retrieveCardCost.query(query);
    }




}
