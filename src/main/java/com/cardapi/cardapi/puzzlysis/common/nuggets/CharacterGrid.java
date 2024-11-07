package com.cardapi.cardapi.puzzlysis.common.nuggets;

public class CharacterGrid implements Nugget {
    private char[][] grid;

    public CharacterGrid(char[][] grid) {
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