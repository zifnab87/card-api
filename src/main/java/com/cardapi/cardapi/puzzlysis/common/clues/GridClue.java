package com.cardapi.cardapi.puzzlysis.common.clues;

public class GridClue implements Clue {
    private char[][] grid;

    public GridClue(char[][] grid) {
        this.grid = grid;
    }

    public char[][] getGrid() {
        return grid;
    }

    @Override
    public void print() {
        System.out.println("\nGrid ->");
        for (char[] line : this.grid) {
            System.out.println(new String(line));
        }
    }
}