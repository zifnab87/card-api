package com.cardapi.cardapi.puzzlysis.common.nuggets;

import java.util.ArrayList;
import java.util.List;

public interface Nugget {
    default void print() {
        System.out.println(this);
    }

    default List<Nugget> wrapToList() {
        var array = new ArrayList<Nugget>();
        array.add(this);
        return array;
    }
}