package com.cardapi.cardapi.puzzlysis.common;


import com.cardapi.cardapi.puzzlysis.common.nuggets.Nugget;

import java.util.List;

public interface Solver<T extends Nugget> {
    // Method to get the reconstructed solution (words list)
    T getSolution();

    List<Nugget> getInputNuggets();

    default Solver addInput(Nugget nugget) {
        this.getInputNuggets().add(nugget);
        return this;
    }

    default Solver addInput(List<Nugget> nuggets) {
        this.getInputNuggets().addAll(nuggets);
        return this;
    }
}
