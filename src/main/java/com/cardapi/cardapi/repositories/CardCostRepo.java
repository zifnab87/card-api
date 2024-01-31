package com.cardapi.cardapi.repositories;

import com.cardapi.cardapi.entities.CardCost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface CardCostRepo extends JpaRepository<CardCost, Integer> {

    CardCost findByCountry(String isoCode);
    void deleteByCountry(String isoCode);

}
