package com.cardapi.cardapi.puzzlysis.lettergrid;

import com.cardapi.cardapi.puzzlysis.common.ClueProvider;
import com.cardapi.cardapi.puzzlysis.common.nuggets.Nugget;

import com.cardapi.cardapi.puzzlysis.common.nuggets.CoordinateList;
import com.cardapi.cardapi.puzzlysis.common.nuggets.CharacterGrid;
import com.cardapi.cardapi.puzzlysis.common.nuggets.WordList;


import java.util.*;


public class WordListAndGridToCoordinatesClueProvider implements ClueProvider {
    CharacterGrid grid;
    private List<Nugget> nuggets;  // List of Clue objects (grid and coordinates separately)
    private int n; // Number of rows
    private int m; // Number of columns
    private WordList<String> wordList;  // Generalized solution interface
    private Random random;

    // Constructor that calculates dimensions automatically based on unique letters
    public WordListAndGridToCoordinatesClueProvider(CharacterGrid grid, WordList<String> wordList) {
        this.wordList = wordList;
        this.nuggets = new ArrayList<>();
        this.random = new Random();
        this.grid = grid;

        // Calculate n and m based on the number of unique letters
        this.n = grid.getGrid().length;
        this.m = grid.getGrid()[0].length;

        // Adjust m if necessary to ensure enough space
        createGrid();
    }



    // Method to initialize and fill the grid
    private void createGrid() {
        List<Character> letters = collectUniqueLetters();
        if (letters.size() > m * n) {
            throw new IllegalArgumentException("Unique letters exceed grid capacity.");
        }
        fillWithRandomLetters(letters);
        populateGrid(letters);
        recordWordCoordinates();
    }

    public void printClues() {
        for (var clue : nuggets) {
            clue.print();
        }
    }

    // Method to get the list of nuggets
    @Override
    public List<Nugget> getClues() {
        return nuggets;
    }

    // Collect unique letters from words
    private List<Character> collectUniqueLetters() {
        Set<Character> uniqueLettersSet = new HashSet<>();
        for (String word : wordList.getItems()) {
            for (char letter : word.toCharArray()) {
                uniqueLettersSet.add(letter);
            }
        }
        return new ArrayList<>(uniqueLettersSet);
    }

    // Fill up the list with random letters until it matches the grid size
    private void fillWithRandomLetters(List<Character> letters) {
        int totalCells = n * m;
        int remainingCells = totalCells - letters.size();
        for (int i = 0; i < remainingCells; i++) {
            letters.add((char) ('a' + random.nextInt(26))); // Random letter from a-z
        }
        Collections.shuffle(letters); // Shuffle to randomly distribute unique and padding letters
    }

    // Populate the grid with letters
    private void populateGrid(List<Character> letters) {
        var charGrid = grid.getGrid();
        int index = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                charGrid[i][j] = letters.get(index++);
            }
        }
    }

    // Record the 1-indexed coordinates for each word's letters
    private void recordWordCoordinates() {
        var charGrid = grid.getGrid();

        for (String word : wordList.getItems()) {
            List<int[]> coordinates = new ArrayList<>();
            for (char letter : word.toCharArray()) {
                boolean found = false;
                for (int i = 0; i < n && !found; i++) {
                    for (int j = 0; j < m && !found; j++) {
                        if (charGrid[i][j] == letter) {
                            coordinates.add(new int[] { i + 1, j + 1 });
                            found = true;
                        }
                    }
                }
            }
            nuggets.add(new CoordinateList(coordinates));  // Add coordinates clue
        }
    }

}
