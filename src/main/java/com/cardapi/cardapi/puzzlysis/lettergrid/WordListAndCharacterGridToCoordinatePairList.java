package com.cardapi.cardapi.puzzlysis.lettergrid;

import com.cardapi.cardapi.puzzlysis.common.NuggetProvider;

import com.cardapi.cardapi.puzzlysis.common.nuggets.CoordinatePairList;
import com.cardapi.cardapi.puzzlysis.common.nuggets.CharacterGrid;
import com.cardapi.cardapi.puzzlysis.common.nuggets.Nugget;
import com.cardapi.cardapi.puzzlysis.common.nuggets.WordList;


import java.util.*;


public class WordListAndCharacterGridToCoordinatePairList implements NuggetProvider<CoordinatePairList> {
    CharacterGrid grid;
    private List<CoordinatePairList> coordinatePairList;  // List of Clue objects (grid and coordinates separately)
    private int n; // Number of rows
    private int m; // Number of columns
    private WordList wordList;  // Generalized solution interface
    private Random random;
    private final List<Nugget> inputNuggets = new ArrayList<>();

    public WordListAndCharacterGridToCoordinatePairList(List<Nugget> nuggets) {
        this(
            nuggets.stream().filter(n -> n instanceof CharacterGrid).map(n -> (CharacterGrid) n).findFirst()
            .orElseThrow(()-> { throw new IllegalArgumentException("no grid found");})
                ,
            nuggets.stream().filter(n -> n instanceof WordList).map(n -> (WordList) n).findFirst()
            .orElseThrow(()-> { throw new IllegalArgumentException("no wordlist found");})
        );
        this.addInput(nuggets);
    }

    public WordListAndCharacterGridToCoordinatePairList(CharacterGrid grid, WordList wordList) {
        this.wordList = wordList;
        this.coordinatePairList = new ArrayList<>();
        this.random = new Random();
        this.grid = grid;

        // Calculate n and m based on the number of unique letters
        this.n = grid.getGrid().length;
        this.m = grid.getGrid()[0].length;

        // Adjust m if necessary to ensure enough space
        createGrid();
        this.addInput(grid).addInput(wordList);
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
    @Override
    public List<Nugget> getInputNuggets() {
        return inputNuggets;
    }


    // Method to get the list of nuggets
    @Override
    public List<CoordinatePairList> getOutputNuggets() {
        return coordinatePairList;
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
            coordinatePairList.add(new CoordinatePairList(coordinates));  // Add coordinates clue
        }
    }

}
