package com.cardapi.cardapi.puzzlysis.lettergrid;

import com.cardapi.cardapi.puzzlysis.common.ClueProvider;
import com.cardapi.cardapi.puzzlysis.common.nuggets.CharacterGrid;
import com.cardapi.cardapi.puzzlysis.common.nuggets.Nugget;
import com.cardapi.cardapi.puzzlysis.common.nuggets.WordList;

import java.util.*;

public class WordListToGridClueProvider implements ClueProvider {
    private char[][] grid;
    private List<Nugget> nuggets;  // List of Clue objects (grid only)
    private int n; // Number of rows
    private int m; // Number of columns
    private Random random;

    // Constructor that calculates dimensions automatically based on unique letters
    public WordListToGridClueProvider(WordList<String> expectedSolution) {
        this.nuggets = new ArrayList<>();
        this.random = new Random();

        List<Character> uniqueLetters = collectUniqueLetters(expectedSolution);
        int uniqueLetterCount = uniqueLetters.size();

        // Calculate n and m based on the number of unique letters
        int sideLength = (int) Math.ceil(Math.sqrt(uniqueLetterCount));
        this.n = sideLength;
        this.m = sideLength;

        // Adjust m if necessary to ensure enough space
        while (n * m < uniqueLetterCount) {
            if (n <= m) {
                n++;
            } else {
                m++;
            }
        }

        this.grid = new char[n][m];
        createGrid(expectedSolution);
    }

    // Constructor with specified dimensions
    public WordListToGridClueProvider(WordList<String> expectedSolution, int n, int m) {
        this.n = n;
        this.m = m;
        this.grid = new char[n][m];
        this.nuggets = new ArrayList<>();
        this.random = new Random();
        createGrid(expectedSolution);
    }

    // Method to initialize and fill the grid
    private void createGrid(WordList<String> expectedSolution) {
        List<Character> letters = collectUniqueLetters(expectedSolution);
        if (letters.size() > m * n) {
            throw new IllegalArgumentException("Unique letters exceed grid capacity.");
        }
        fillWithRandomLetters(letters);
        populateGrid(letters);
        recordGridClue();
    }

    // Method to print the grid clue
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
    private List<Character> collectUniqueLetters(WordList<String> expectedSolution) {
        Set<Character> uniqueLettersSet = new HashSet<>();
        for (String word : expectedSolution.getItems()) {
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
        int index = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                grid[i][j] = letters.get(index++);
            }
        }
    }

    // Record the grid clue
    private void recordGridClue() {
        nuggets.add(new CharacterGrid(grid));  // Add grid clue
    }
}