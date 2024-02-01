package com.cardapi.cardapi.repositories;

import com.cardapi.cardapi.entities.CardCost;
import com.cardapi.cardapi.entities.Country;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CardCostRepo extends JpaRepository<CardCost, Integer> {

    CardCost findByCountry(Country country);
    void deleteByCountry(Country country);

}
