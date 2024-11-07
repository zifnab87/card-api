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
    public String toString() {
        StringBuilder sb = new StringBuilder("\n\t"+this.getClass().getSimpleName()+" [\n");
        for (var item : this.items) {
            sb.append("\t\t"+item).append("\n");
        }
        return sb.append("\t]\n").toString();
    }
}
