package com.cardapi.cardapi.puzzlysis.lettergrid;

import com.cardapi.cardapi.puzzlysis.common.Solver;
import com.cardapi.cardapi.puzzlysis.common.nuggets.CoordinatePairList;
import com.cardapi.cardapi.puzzlysis.common.nuggets.CharacterGrid;
import com.cardapi.cardapi.puzzlysis.common.nuggets.Nugget;
import com.cardapi.cardapi.puzzlysis.common.nuggets.WordList;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LetterGridSolver implements Solver<WordList> {
    private CharacterGrid grid;
    private List<CoordinatePairList> coordinatePairList;
    private WordList wordList;

    private final List<Nugget> inputNuggets = new ArrayList<>();

    public LetterGridSolver(List<Nugget> nuggets) {
        this(
                nuggets.stream().filter(n -> n instanceof CharacterGrid).map(n -> (CharacterGrid) n).findFirst()
                        .orElseThrow(()-> { throw new IllegalArgumentException("no grid found");})
                ,
                nuggets.stream().filter(n -> n instanceof CoordinatePairList).map(n -> (CoordinatePairList) n).collect(Collectors.toList())
        );
        this.addInput(nuggets);
    }


    // Constructor that takes GridClue and CoordinatesClue list
    public LetterGridSolver(CharacterGrid grid, List<CoordinatePairList> coordinatePairList) {

        if (grid == null) {
            throw new IllegalArgumentException("nuggets didn't a single cotnain GridClue");
        }
        var coordinatesClues = coordinatePairList;

        this.grid = grid;  // Retrieve grid from GridClue
        this.coordinatePairList = coordinatesClues;
        this.wordList = new WordList(new ArrayList<>());
        this.inputNuggets.add(grid);
        this.addInput(grid).addInput(coordinatePairList);
        solve();
    }




    // Method to solve and reconstruct the solution
    private void solve() {
        for (CoordinatePairList coordinatesClue : coordinatePairList) {
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
    public WordList getSolution() {
        return wordList;
    }

    @Override
    public List<Nugget> getInputNuggets() {
        return inputNuggets;
    }

    public void printSolution() {
        wordList.print();
    }
}