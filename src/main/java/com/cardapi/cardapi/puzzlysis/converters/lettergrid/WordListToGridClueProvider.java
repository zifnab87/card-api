package com.cardapi.cardapi.puzzlysis.converters.lettergrid;

import com.cardapi.cardapi.puzzlysis.common.ClueProvider;
import com.cardapi.cardapi.puzzlysis.common.clues.Clue;
import com.cardapi.cardapi.puzzlysis.common.clues.GridClue;
import com.cardapi.cardapi.puzzlysis.common.solutions.SolutionList;

import java.util.*;

public class WordListToGridClueProvider implements ClueProvider {
    private char[][] grid;
    private List<Clue> clues;  // List of Clue objects (grid only)
    private int n; // Number of rows
    private int m; // Number of columns
    private Random random;

    // Constructor that calculates dimensions automatically based on unique letters
    public WordListToGridClueProvider(SolutionList<String> expectedSolution) {
        this.clues = new ArrayList<>();
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
    public WordListToGridClueProvider(SolutionList<String> expectedSolution, int n, int m) {
        this.n = n;
        this.m = m;
        this.grid = new char[n][m];
        this.clues = new ArrayList<>();
        this.random = new Random();
        createGrid(expectedSolution);
    }

    // Method to initialize and fill the grid
    private void createGrid(SolutionList<String> expectedSolution) {
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
        for (var clue : clues) {
            clue.print();
        }
    }

    // Method to get the list of clues
    @Override
    public List<Clue> getClues() {
        return clues;
    }

    // Collect unique letters from words
    private List<Character> collectUniqueLetters(SolutionList<String> expectedSolution) {
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
        clues.add(new GridClue(grid));  // Add grid clue
    }
}