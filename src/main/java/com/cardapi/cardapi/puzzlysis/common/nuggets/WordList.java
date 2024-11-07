package com.cardapi.cardapi.puzzlysis.common.nuggets;

import java.util.List;

public class WordList<T> implements Nugget {
    private List<T> items;

    public WordList(List<T> words) {
        this.items = words;
    }

    public List<T> getItems() {
        return items;
    }

    @Override
    public void print() {
        System.out.println("\nSolution ->");
        for (var item : this.items) {
            System.out.println(item);
        }
    }
}
