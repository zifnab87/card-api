package com.cardapi.cardapi.usecases.countrycost;

import com.cardapi.cardapi.adapters.persistence.cardcost.ports.FindAllCountryCostsPort;
import com.cardapi.cardapi.entities.CountryCost;
import com.cardapi.cardapi.helpers.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@UseCase
@RequiredArgsConstructor
public class RetrieveAllCountryCosts {
    private final FindAllCountryCostsPort findAllCountryCostsPort;

    public Page<CountryCost> query(PageRequest pageRequest) {
        return findAllCountryCostsPort.findAll(pageRequest);
    }
}
