package com.cardapi.cardapi.controllers;

import com.cardapi.cardapi.usecases.cardcost.RetrieveCardCost;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Gets a PAN card number between 8 and 19 digits long and provides its country isocode by accessing external endpoint and its cost from local cost matrix. If cost for specific country doesn't exist 'Others' cost will be returned", description = "Returns one result")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "400", description = "The PAN number provided doesn't have the correct length (between 8 and 19 included) so it is invalid"),
            @ApiResponse(responseCode = "404", description = "The cost doesn't exist for this country"),
            @ApiResponse(responseCode = "5xx", description = "Unexpected Network Issue"),

    })
    @PostMapping
    public RetrieveCardCost.Response retrieveCardCost(@RequestBody RetrieveCardCost.Query query) {
        return retrieveCardCost.query(query);
    }




}
