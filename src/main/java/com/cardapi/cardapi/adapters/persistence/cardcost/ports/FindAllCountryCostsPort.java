package com.cardapi.cardapi.adapters.persistence.cardcost.ports;

import com.cardapi.cardapi.entities.CountryCost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface FindAllCountryCostsPort {
    Page<CountryCost> findAll(PageRequest pageRequest);
}
