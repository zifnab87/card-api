package com.cardapi.cardapi.puzzlysis.lettergrid;

import com.cardapi.cardapi.puzzlysis.common.ConverterTo;
import com.cardapi.cardapi.puzzlysis.common.nuggets.CharacterGrid;
import com.cardapi.cardapi.puzzlysis.common.nuggets.CoordinatePairList;
import com.cardapi.cardapi.puzzlysis.common.nuggets.Nugget;
import com.cardapi.cardapi.puzzlysis.common.nuggets.WordList;

import java.util.ArrayList;
import java.util.List;

public class WordListAndCharacterGridToCoordinatePairList implements ConverterTo<CoordinatePairList> {

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

    @Override
    public List<CoordinatePairList> apply(List<Nugget> nuggets) {
        // Extract CharacterGrid and WordList from nuggets
        CharacterGrid grid = nuggets.stream()
                .filter(n -> n instanceof CharacterGrid)
                .map(n -> (CharacterGrid) n)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No CharacterGrid found"));

        WordList wordList = nuggets.stream()
                .filter(n -> n instanceof WordList)
                .map(n -> (WordList) n)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No WordList found"));

        // Get the coordinates for each word's letters
        return apply(grid, wordList);
    }

    public List<CoordinatePairList> apply(CharacterGrid grid, WordList wordList) {
        return getWordCoordinatePairs(grid.getGrid(), wordList);
    }

    private List<CoordinatePairList> getWordCoordinatePairs(char[][] charGrid, WordList wordList) {
        List<CoordinatePairList> coordinatePairLists = new ArrayList<>();

        // Loop through each word in the word list
        for (String word : wordList.getItems()) {
            List<int[]> coordinates = new ArrayList<>();

            for (char letter : word.toCharArray()) {
                boolean found = false;

                // Iterate through the grid from top to bottom, left to right
                for (int i = 0; i < charGrid.length && !found; i++) {
                    for (int j = 0; j < charGrid[0].length && !found; j++) {
                        if (charGrid[i][j] == letter) {
                            // Add the 1-indexed coordinate
                            coordinates.add(new int[]{i + 1, j + 1});
                            found = true;  // Move to the next letter after finding the match
                        }
                    }
                }

                // If the letter wasn't found in the grid, throw an error
                if (!found) {
                    throw new IllegalArgumentException("Word '" + word + "' cannot be fully found in the grid.");
                }
            }

            // Add this word's coordinates to the list
            coordinatePairLists.add(new CoordinatePairList(coordinates));
        }

        return coordinatePairLists;
    }
}