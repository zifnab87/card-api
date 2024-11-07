package com.cardapi.cardapi.puzzlysis.common;

import com.cardapi.cardapi.puzzlysis.common.solutions.SolutionList;

public interface Solver {
    // Method to get the reconstructed solution (words list)
    SolutionList<String> getSolution();
}
