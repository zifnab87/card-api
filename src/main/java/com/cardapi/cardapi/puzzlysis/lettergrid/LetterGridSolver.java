package com.cardapi.cardapi.puzzlysis.lettergrid;

import com.cardapi.cardapi.puzzlysis.common.Solver;
import com.cardapi.cardapi.puzzlysis.common.nuggets.CoordinateList;
import com.cardapi.cardapi.puzzlysis.common.nuggets.CharacterGrid;
import com.cardapi.cardapi.puzzlysis.common.nuggets.Nugget;
import com.cardapi.cardapi.puzzlysis.common.nuggets.WordList;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LetterGridSolver implements Solver {
    private CharacterGrid grid;
    private List<CoordinateList> coordinateList; // List of CoordinatesClue
    private WordList<String> wordList;  // Reconstructed solution (words list)

    // Constructor that takes GridClue and CoordinatesClue list
    public LetterGridSolver(CharacterGrid grid, List<CoordinateList> coordinateList) {

        if (grid == null) {
            throw new IllegalArgumentException("nuggets didn't a single cotnain GridClue");
        }
        var coordinatesClues = coordinateList;

        this.grid = grid;  // Retrieve grid from GridClue
        this.coordinateList = coordinatesClues;
        this.wordList = new WordList<>(new ArrayList<>());
        solve();
    }




    // Method to solve and reconstruct the solution
    private void solve() {
        for (CoordinateList coordinatesClue : coordinateList) {
            List<int[]> coordinates = coordinatesClue.getCoordinates();
            StringBuilder word = new StringBuilder();

            // For each coordinate, get the corresponding letter from the grid
            for (int[] coord : coordinates) {
                int row = coord[0] - 1; // Convert to 0-indexed
                int col = coord[1] - 1; // Convert to 0-indexed
                word.append(grid.getGrid()[row][col]);
            }

            // Add the reconstructed word to the solution
            wordList.getItems().add(word.toString());
        }
    }

    @Override
    // Method to get the reconstructed solution (words list)
    public WordList<String> getSolution() {
        return wordList;
    }

    public void printSolution() {
        wordList.print();
    }
}