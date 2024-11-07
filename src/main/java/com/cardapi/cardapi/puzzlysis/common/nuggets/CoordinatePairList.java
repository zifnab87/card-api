package com.cardapi.cardapi.puzzlysis.common.nuggets;

import java.util.List;

public class CoordinatePairList implements Nugget {
    private List<int[]> coordinates;

    public CoordinatePairList(List<int[]> coordinates) {
        this.coordinates = coordinates;
    }

    public List<int[]> getCoordinates() {
        return coordinates;
    }



    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("\n\t"+this.getClass().getSimpleName()+" [\n");
        sb.append("\t\t");
        for (int[] coord : coordinates) {
            sb.append(" [").append(coord[0]).append(", ").append(coord[1]).append("] ");
        }
        return sb.append("\n\t]\n").toString();
    }
}