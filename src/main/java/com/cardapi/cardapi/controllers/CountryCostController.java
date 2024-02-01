package com.cardapi.cardapi.controllers;

import com.cardapi.cardapi.entities.CountryCost;
import com.cardapi.cardapi.usecases.countrycost.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("country-costs")
@RequiredArgsConstructor
public class CountryCostController {

    private final CreateCountryCost createCountryCost;
    private final UpdateCountryCost updateCountryCost;
    private final RetrieveCountryCostByCountry retrieveCountryCostByCountry;
    private final RetrieveAllCountryCosts retrieveAllCountryCosts;
    private final DeleteCostByCountry deleteCostByCountry;

    @PostMapping
    public void create(@RequestBody CreateCountryCost.Command command) {
        createCountryCost.command(command);
    }

    @PutMapping
    public void update(@RequestBody UpdateCountryCost.Command command) {
        updateCountryCost.command(command);
    }

    @GetMapping
    public Page<CountryCost> queryAll() {
        return retrieveAllCountryCosts.query();
    }

    @GetMapping("{isoCode}")
    public CountryCost queryByCountry(@PathVariable String isoCode) {
        return retrieveCountryCostByCountry.query(isoCode);
    }

    @DeleteMapping("{isoCode}")
    public void deleteByCountry(@PathVariable String isoCode) {
        deleteCostByCountry.command(isoCode);
    }
}
