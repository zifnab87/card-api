package com.cardapi.cardapi.puzzlysis.lettergrid;

import com.cardapi.cardapi.puzzlysis.common.NuggetProvider;
import com.cardapi.cardapi.puzzlysis.common.nuggets.CharacterGrid;
import com.cardapi.cardapi.puzzlysis.common.nuggets.Nugget;
import com.cardapi.cardapi.puzzlysis.common.nuggets.WordList;

import java.util.*;

public class WordListToCharacterGrid implements NuggetProvider<CharacterGrid> {
    private char[][] grid;
    private Random random;
    private final List<Nugget> inputNuggets = new ArrayList<>();

    private int n; // Number of rows
    private int m; // Number of columns
    private CharacterGrid characterGrid;  // List of Clue objects (grid only)


    public WordListToCharacterGrid(List<Nugget> nuggets) {
        this(
                nuggets.stream().filter(n -> n instanceof WordList).map(n -> (WordList) n).findFirst()
                        .orElseThrow(()-> { throw new IllegalArgumentException("no wordlist found");})
        );
        this.inputNuggets.addAll(nuggets);

    }

    // Constructor that calculates dimensions automatically based on unique letters
    public WordListToCharacterGrid(WordList wordList) {
        this.random = new Random();

        List<Character> uniqueLetters = collectUniqueLetters(wordList);
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
        createGrid(wordList);
    }

    // Constructor with specified dimensions
    public WordListToCharacterGrid(WordList wordList, int n, int m) {
        this.n = n;
        this.m = m;
        this.grid = new char[n][m];
        this.random = new Random();
        createGrid(wordList);
    }

    // Method to initialize and fill the grid
    private void createGrid(WordList wordList) {
        List<Character> letters = collectUniqueLetters(wordList);
        if (letters.size() > m * n) {
            throw new IllegalArgumentException("Unique letters exceed grid capacity.");
        }
        fillWithRandomLetters(letters);
        populateGrid(letters);
        characterGrid = new CharacterGrid(grid);
    }

    @Override
    public List<Nugget> getInputNuggets() {
        return inputNuggets;
    }

    // Method to get the list of nuggets
    @Override
    public List<CharacterGrid> getOutputNuggets() {
        return List.of(characterGrid);
    }

    // Collect unique letters from words
    private List<Character> collectUniqueLetters(WordList wordList) {
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
        int index = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                grid[i][j] = letters.get(index++);
            }
        }
    }


}