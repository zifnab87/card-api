package com.cardapi.cardapi.entities;

import com.cardapi.cardapi.exceptions.InvalidCardCostException;
import com.cardapi.cardapi.helpers.CountryConverter;
import com.cardapi.cardapi.helpers.CountryDeserializer;
import com.cardapi.cardapi.helpers.CountrySerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(uniqueConstraints = @UniqueConstraint(name = "UQ_country", columnNames = {"country"}))
public class CountryCost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Convert(converter = CountryConverter.class)
    @JsonSerialize(converter = CountrySerializer.class)
    @JsonDeserialize(converter = CountryDeserializer.class)
    private Country country;

    private BigDecimal cost;

    public CountryCost(String isoCode, BigDecimal cost) {
        this.country = new Country(isoCode);
        setCost(cost);
    }

    public void setCost(BigDecimal cost) {
        if (!greaterThan(cost, BigDecimal.ZERO)) {
            throw new InvalidCardCostException("cost can't be zero or negative");
        }
        this.cost = cost;
    }

    private static boolean greaterThan(BigDecimal left, BigDecimal right) {
        return left.compareTo(right) == 1;
    }



}
