package com.cardapi.cardapi.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class CardCost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "isoCode", column = @Column(name = "country"))
    })
    private final Country country;

    private final BigDecimal cost;

    public CardCost(String isoCode, BigDecimal cost) {
        this.country = new Country(isoCode);
        if (greaterThan(cost, BigDecimal.ZERO)) {
            throw new IllegalArgumentException("cost can't be zero or negative");
        }
        this.cost = cost;
    }

    private static boolean greaterThan(BigDecimal left, BigDecimal right) {
        return left.compareTo(right) == -1;
    }



}
