package com.cardapi.cardapi.adapters.persistence.cardcost.ports;

import com.cardapi.cardapi.entities.Country;
import com.cardapi.cardapi.entities.CountryCost;

public interface FindCostByCountryPort {
    CountryCost findByCountry(Country country);
}
