package com.cardapi.cardapi.puzzlysis.common.clues;

import java.util.List;

public class CoordinatesClue implements Clue {
    private List<int[]> coordinates;

    public CoordinatesClue(List<int[]> coordinates) {
        this.coordinates = coordinates;
    }

    public List<int[]> getCoordinates() {
        return coordinates;
    }

    @Override
    public void print() {
        System.out.println("\nCoordinates -> ");
        for (int[] coord : coordinates) {
            System.out.print("[" + coord[0] + ", " + coord[1] + "] ");
        }
        System.out.println();
    }
}