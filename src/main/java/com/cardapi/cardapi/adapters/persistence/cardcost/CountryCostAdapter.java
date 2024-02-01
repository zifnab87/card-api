package com.cardapi.cardapi.adapters.persistence.cardcost;

import com.cardapi.cardapi.adapters.persistence.cardcost.ports.FindAllCountryCostsPort;
import com.cardapi.cardapi.adapters.persistence.cardcost.ports.FindCostByCountryPort;
import com.cardapi.cardapi.adapters.persistence.cardcost.ports.SaveCountryCostPort;
import com.cardapi.cardapi.adapters.persistence.cardcost.ports.DeleteCostByCountryPort;
import com.cardapi.cardapi.entities.Country;
import com.cardapi.cardapi.entities.CountryCost;
import com.cardapi.cardapi.repositories.CountryCostRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CountryCostAdapter implements
        FindCostByCountryPort,
        FindAllCountryCostsPort,
        SaveCountryCostPort, DeleteCostByCountryPort {

    private final CountryCostRepo repo;

    @Override
    public Page<CountryCost> findAll(PageRequest pageRequest) {
        return repo.findAll(pageRequest);
    }

    @Override
    public CountryCost findByCountry(Country country) {
        return repo.findByCountry(country);
    }

    @Override
    public void save(CountryCost countryCost) {
        repo.save(countryCost);
    }

    @Override
    public void deleteByCountry(Country country) {
        repo.deleteByCountry(country);
    }
}
