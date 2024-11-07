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
    public String toString() {
        StringBuilder sb = new StringBuilder("\n\t"+this.getClass().getSimpleName()+" [\n");
        for (char[] line : this.grid) {
            var newLine = String.join(" ", new String(line).split(""));
            sb.append("\t\t"+newLine).append("\n");
        }
        return sb.append("\t]\n").toString();
    }
}