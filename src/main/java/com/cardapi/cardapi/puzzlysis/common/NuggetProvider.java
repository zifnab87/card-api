package com.cardapi.cardapi.puzzlysis.common;

import com.cardapi.cardapi.puzzlysis.common.nuggets.CoordinatePairList;
import com.cardapi.cardapi.puzzlysis.common.nuggets.Nugget;

import java.util.List;

public interface NuggetProvider<T extends Nugget> {
    List<Nugget> getInputNuggets();

    default NuggetProvider addInput(Nugget nugget) {
        this.getInputNuggets().add(nugget);
        return this;
    }

    default NuggetProvider addInput(List<Nugget> nuggets) {
        this.getInputNuggets().addAll(nuggets);
        return this;
    }

    List<T> getOutputNuggets();
}
