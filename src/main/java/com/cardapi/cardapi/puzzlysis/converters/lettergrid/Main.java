package com.cardapi.cardapi.puzzlysis.converters.lettergrid;

import com.cardapi.cardapi.puzzlysis.common.solutions.SolutionList;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        var expectedSolution = new SolutionList<>(Arrays.asList("apple", "banana", "cherry"));

        var clueProvider = new WordListToCoordinatesClueProvider(expectedSolution);
        clueProvider.printClues();

        expectedSolution.print();

        System.out.println("<-- solution ---->");


        var solver = new WordListToGridAndCoordinatesSolver(clueProvider.getClues());

        assert expectedSolution.equals(solver.getSolution());

    }
}