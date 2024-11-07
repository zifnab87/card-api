package com.cardapi.cardapi.puzzlysis.common.solutions;

import java.util.List;

public class SolutionList<T> {
    private List<T> items;

    public SolutionList(List<T> words) {
        this.items = words;
    }

    public List<T> getItems() {
        return items;
    }

    public void print() {
        System.out.println("\nSolution ->");
        for (var item : this.items) {
            System.out.println(item);
        }
    }
}