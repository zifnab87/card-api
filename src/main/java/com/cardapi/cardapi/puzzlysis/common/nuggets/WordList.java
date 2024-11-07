package com.cardapi.cardapi.puzzlysis.common.nuggets;

import java.util.List;

public class WordList implements Nugget {
    private List<String> items;

    public WordList(List<String> words) {
        this.items = words;
    }

    public List<String> getItems() {
        return items;
    }

    @Override
    public void print() {
        System.out.println("\nWord List ->");
        for (var item : this.items) {
            System.out.println(item);
        }
    }
}
