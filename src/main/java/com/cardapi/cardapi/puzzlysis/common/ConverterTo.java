package com.cardapi.cardapi.puzzlysis.common;

import com.cardapi.cardapi.puzzlysis.common.nuggets.Nugget;

import java.util.List;

public interface ConverterTo<T extends Nugget> {
    List<T> apply(List<Nugget> nuggets);

    default void print() {
        System.out.println(this);
    }
}
