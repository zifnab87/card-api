package com.cardapi.cardapi.puzzlysis.common;

import com.cardapi.cardapi.puzzlysis.common.nuggets.WordList;


public interface Solver {
    // Method to get the reconstructed solution (words list)
    WordList<String> getSolution();
}
