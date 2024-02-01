package com.cardapi.cardapi.adapters.persistence.cardcost.ports;

import com.cardapi.cardapi.entities.CountryCost;

public interface SaveCountryCostPort {
    void save(CountryCost countryCost);
}
