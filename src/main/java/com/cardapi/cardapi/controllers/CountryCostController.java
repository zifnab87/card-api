package com.cardapi.cardapi.controllers;

import com.cardapi.cardapi.entities.CountryCost;
import com.cardapi.cardapi.usecases.countrycost.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("country-costs")
@RequiredArgsConstructor
public class CountryCostController {

    private final CreateCountryCost createCountryCost;
    private final UpdateCountryCost updateCountryCost;
    private final RetrieveCostByCountry retrieveCostByCountry;
    private final RetrieveAllCountryCosts retrieveAllCountryCosts;
    private final DeleteCostByCountry deleteCostByCountry;

    @Operation(summary = "Create card cost for a country by providing its isocode or 'Others'", description = "Doesn't return anything")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully stored"),
            @ApiResponse(responseCode = "400", description = "Country with isoCode already exists; The isoCode provided is invalid; The card cost can't be negative or zero"),

    })
    @PostMapping
    public void create(@RequestBody CreateCountryCost.Command command) {
        createCountryCost.command(command);
    }

    @Operation(summary = "Update card cost for a country by providing its isocode or 'Others'", description = "Doesn't return anything")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated"),
            @ApiResponse(responseCode = "400", description = "Country with isoCode doesn't exist; The isoCode provided is invalid; The card cost can't be negative or zero"),

    })
    @PutMapping
    public void update(@RequestBody UpdateCountryCost.Command command) {
        updateCountryCost.command(command);
    }


    @Operation(summary = "Get paginated costs for all countries by providing optional request parameter ?page=0,1 ...", description = "Returns paginated results with page size 10")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),

    })
    @GetMapping
    public Page<CountryCost> queryAll(@RequestParam(required = false, defaultValue = "0") Integer page) {
        return retrieveAllCountryCosts.query(PageRequest.of(page, 10));
    }

    @Operation(summary = "Get card cost for a specific country by providing its isocode or 'Others'", description = "Returns one result")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "400", description = "The isoCode provided is invalid"),
            @ApiResponse(responseCode = "404", description = "The cost doesn't exist for this country"),

    })
    @GetMapping("{isoCode}")
    public CountryCost queryByCountry(@PathVariable String isoCode) {
        return retrieveCostByCountry.query(isoCode);
    }


    @Operation(summary = "Delete card cost for a country by providing its isocode or 'Others'", description = "Doesn't return anything")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted"),
            @ApiResponse(responseCode = "400", description = "Country 'Others' can't be deleted; Country with isoCode doesn't exist; The isoCode provided is invalid;"),
    })
    @DeleteMapping("{isoCode}")
    public void deleteByCountry(@PathVariable String isoCode) {
        deleteCostByCountry.command(isoCode);
    }
}
