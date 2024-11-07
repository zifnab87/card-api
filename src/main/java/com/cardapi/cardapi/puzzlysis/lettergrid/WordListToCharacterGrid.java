package com.cardapi.cardapi.puzzlysis.lettergrid;

import com.cardapi.cardapi.puzzlysis.common.ConverterTo;
import com.cardapi.cardapi.puzzlysis.common.nuggets.CharacterGrid;
import com.cardapi.cardapi.puzzlysis.common.nuggets.CoordinatePairList;
import com.cardapi.cardapi.puzzlysis.common.nuggets.Nugget;
import com.cardapi.cardapi.puzzlysis.common.nuggets.WordList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class WordListToCharacterGrid implements ConverterTo<CharacterGrid> {

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

    @Override
    public List<CharacterGrid> apply(List<Nugget> nuggets) {
        var wordList = nuggets.stream().filter(n -> n instanceof WordList).map(n -> (WordList) n).findFirst()
                .orElseThrow(()-> { throw new IllegalArgumentException("no wordlist found");});
        return apply(wordList);
    }

    public List<CharacterGrid> apply(WordList wordList) {
        List<Character> uniqueLetters = collectUniqueLetters(wordList);
        int uniqueLetterCount = uniqueLetters.size();

        // Calculate n and m based on the number of unique letters
        int sideLength = (int) Math.ceil(Math.sqrt(uniqueLetterCount));
        int n = sideLength;
        int m = sideLength;

        // Adjust m if necessary to ensure enough space
        while (n * m < uniqueLetterCount) {
            if (n <= m) {
                n++;
            } else {
                m++;
            }
        }

        // Create grid using functional approach (no instance variables)
        char[][] grid = createGrid(n, m, wordList);
        return List.of(new CharacterGrid(grid));
    }

    // Helper method to collect unique letters from a WordList
    private List<Character> collectUniqueLetters(WordList wordList) {
        return wordList.getItems().stream()
                .flatMapToInt(String::chars)
                .mapToObj(c -> (char) c)
                .collect(Collectors.toSet()) // Collect to a Set to ensure uniqueness
                .stream()
                .collect(Collectors.toList()); // Convert Set to List for further use
    }

    // Helper method to create the character grid
    private char[][] createGrid(int n, int m, WordList wordList) {
        List<Character> letters = collectUniqueLetters(wordList);
        if (letters.size() > m * n) {
            throw new IllegalArgumentException("Unique letters exceed grid capacity.");
        }

        List<Character> allLetters = fillWithRandomLetters(letters, n * m);
        return populateGrid(n, m, allLetters);
    }

    // Fill up the list with random letters until it matches the grid size
    private List<Character> fillWithRandomLetters(List<Character> letters, int totalCells) {
        int remainingCells = totalCells - letters.size();
        List<Character> randomLetters = new ArrayList<>(letters);

        for (int i = 0; i < remainingCells; i++) {
            randomLetters.add((char) ('a' + new Random().nextInt(26))); // Random letter from a-z
        }
        Collections.shuffle(randomLetters); // Shuffle to randomly distribute unique and padding letters
        return randomLetters;
    }

    // Helper method to populate the grid with letters
    private char[][] populateGrid(int n, int m, List<Character> letters) {
        char[][] grid = new char[n][m];
        int index = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                grid[i][j] = letters.get(index++);
            }
        }
        return grid;
    }
}