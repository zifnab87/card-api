package com.cardapi.cardapi.puzzlysis.converters.lettergrid;

import com.cardapi.cardapi.puzzlysis.common.Solver;
import com.cardapi.cardapi.puzzlysis.common.clues.Clue;
import com.cardapi.cardapi.puzzlysis.common.clues.CoordinatesClue;
import com.cardapi.cardapi.puzzlysis.common.clues.GridClue;
import com.cardapi.cardapi.puzzlysis.common.solutions.SolutionList;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class WordListToGridAndCoordinatesSolver implements Solver {
    private GridClue gridClue;
    private List<CoordinatesClue> coordinatesClues; // List of CoordinatesClue
    private SolutionList<String> solution;  // Reconstructed solution (words list)

    // Constructor that takes GridClue and CoordinatesClue list
    public WordListToGridAndCoordinatesSolver(List<Clue> clues) {
        var gridClue = filterOnlyGridClue(clues);

        if (gridClue == null) {
            throw new IllegalArgumentException("clues didn't a single cotnain GridClue");
        }
        var coordinatesClues = filterCoordinatesClues(clues);

        this.gridClue = gridClue;  // Retrieve grid from GridClue
        this.coordinatesClues = coordinatesClues;
        this.solution = new SolutionList<>(new ArrayList<>());
        solve();
    }

    private List<CoordinatesClue> filterCoordinatesClues(List<Clue> clues) {
        return clues.stream().filter(c -> c instanceof CoordinatesClue)
                .map(c -> (CoordinatesClue) c)
                .collect(Collectors.toList());
    }

    private GridClue filterOnlyGridClue(List<Clue> clues) {
        return clues.stream().filter(c -> c instanceof GridClue)
                .map(c -> (GridClue) c)
                .findFirst().orElseThrow(() -> { throw new IllegalArgumentException("clues didn't a single cotnain GridClue"); });
    }

    // Method to solve and reconstruct the solution
    private void solve() {
        for (CoordinatesClue coordinatesClue : coordinatesClues) {
            List<int[]> coordinates = coordinatesClue.getCoordinates();
            StringBuilder word = new StringBuilder();

            // For each coordinate, get the corresponding letter from the grid
            for (int[] coord : coordinates) {
                int row = coord[0] - 1; // Convert to 0-indexed
                int col = coord[1] - 1; // Convert to 0-indexed
                word.append(gridClue.getGrid()[row][col]);
            }

            // Add the reconstructed word to the solution
            solution.getItems().add(word.toString());
        }
    }

    @Override
    // Method to get the reconstructed solution (words list)
    public SolutionList<String> getSolution() {
        return solution;
    }

    public void printSolution() {
        solution.print();
    }
}