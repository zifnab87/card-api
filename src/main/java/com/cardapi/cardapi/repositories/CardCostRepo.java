package com.cardapi.cardapi.repositories;

import com.cardapi.cardapi.entities.CountryCost;
import com.cardapi.cardapi.entities.Country;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CardCostRepo extends JpaRepository<CountryCost, Integer> {

    CountryCost findByCountry(Country country);
    void deleteByCountry(Country country);

}
