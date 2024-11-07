package com.cardapi.cardapi.puzzlysis.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class DictionaryLoader {

    // Method to load dictionary from a resource file into a Set
    public Set<String> loadDictionary(String fileName) {
        Set<String> dictionary = new HashSet<>();

        // Using ClassLoader to get the resource
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(getClass().getClassLoader().getResourceAsStream(fileName)))) {

            if (reader == null) {
                throw new IOException("File not found: " + fileName);
            }

            String line;
            while ((line = reader.readLine()) != null) {
                dictionary.add(line.trim());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return dictionary;
    }
}