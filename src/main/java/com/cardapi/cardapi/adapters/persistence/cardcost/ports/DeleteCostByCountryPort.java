package com.cardapi.cardapi.adapters.persistence.cardcost.ports;

import com.cardapi.cardapi.entities.Country;

public interface DeleteCostByCountryPort {
    void deleteByCountry(Country country);
}
