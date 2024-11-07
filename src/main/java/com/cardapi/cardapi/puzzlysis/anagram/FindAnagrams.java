package com.cardapi.cardapi.puzzlysis.anagram;

import com.cardapi.cardapi.puzzlysis.common.ConverterTo;
import com.cardapi.cardapi.puzzlysis.common.nuggets.Nugget;
import com.cardapi.cardapi.puzzlysis.common.nuggets.WordList;

import java.util.*;
import java.util.stream.Collectors;

public class FindAnagrams implements ConverterTo<WordList> {
    private Set<String> dictionary;  // This will store valid words
    private Random random;  // Random instance with a fixed seed for reproducibility

    // Constructor to initialize the dictionary and the random object with a fixed seed
    public FindAnagrams(Set<String> dictionary) {
        this.dictionary = dictionary;
        this.random = new Random();  // Using a seedless Random for more variety
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

    @Override
    public List<WordList> apply(List<Nugget> nuggets) {
        var wordList = nuggets.stream()
                .filter(n -> n instanceof WordList)
                .map(n -> (WordList) n)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No wordlist found"));

        return apply(wordList);
    }

    // Main logic: applying anagram finding
    public List<WordList> apply(WordList wordList) {
        // For each word in the word list, shuffle and check for a valid anagram
        List<String> shuffledWords = wordList.getItems().stream()
                .map(this::findAnagram)
                .collect(Collectors.toList());

        // Convert the list of shuffled words back to a WordList and return
        return Collections.singletonList(new WordList(shuffledWords));
    }

    // Method to shuffle the word and check for an anagram in the dictionary
    private String findAnagram(String word) {
        String shuffledWord = word;
        int attempt = 0;
        List<String> possibleAnagrams = new ArrayList<>(Arrays.asList(word.split("")));

        // Try to shuffle multiple times, limit the number of attempts to avoid infinite loop
        while (!dictionary.contains(shuffledWord) && attempt < 500) {
            shuffleList(possibleAnagrams);
            shuffledWord = String.join("", possibleAnagrams);
            attempt++;
        }

        // If we exhaust the attempts and find no valid anagram, return the original word
        return dictionary.contains(shuffledWord) ? shuffledWord : word;
    }

    // Method to shuffle the list in a random way
    private void shuffleList(List<String> list) {
        // Shuffling using a Random object to make it unpredictable
        for (int i = 0; i < list.size(); i++) {
            int j = random.nextInt(list.size());
            Collections.swap(list, i, j);
        }
    }
}