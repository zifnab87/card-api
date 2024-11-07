package com.cardapi.cardapi.puzzlysis.lettergrid;

import com.cardapi.cardapi.puzzlysis.common.ConverterTo;
import com.cardapi.cardapi.puzzlysis.common.nuggets.CharacterGrid;
import com.cardapi.cardapi.puzzlysis.common.nuggets.CoordinatePairList;
import com.cardapi.cardapi.puzzlysis.common.nuggets.Nugget;
import com.cardapi.cardapi.puzzlysis.common.nuggets.WordList;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LetterGridSolver implements ConverterTo<WordList> {

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

    @Override
    public List<WordList> apply(List<Nugget> nuggets) {
        // Extract CharacterGrid and CoordinatePairList from nuggets
        CharacterGrid grid = nuggets.stream()
                .filter(n -> n instanceof CharacterGrid)
                .map(n -> (CharacterGrid) n)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No CharacterGrid found"));

        List<CoordinatePairList> coordinatePairList = nuggets.stream()
                .filter(n -> n instanceof CoordinatePairList)
                .map(n -> (CoordinatePairList) n)
                .collect(Collectors.toList());

        return apply(grid, coordinatePairList);
    }

    public List<WordList> apply(CharacterGrid grid, List<CoordinatePairList> coordinatePairList) {
        List<String> words = new ArrayList<>();

        for (CoordinatePairList coordinatesClue : coordinatePairList) {
            StringBuilder word = new StringBuilder();

            for (int[] coord : coordinatesClue.getCoordinates()) {
                int row = coord[0] - 1; // Convert to 0-indexed
                int col = coord[1] - 1; // Convert to 0-indexed
                word.append(grid.getGrid()[row][col]);
            }

            words.add(word.toString());
        }

        return List.of(new WordList(words)); // Return the reconstructed WordList
    }
}