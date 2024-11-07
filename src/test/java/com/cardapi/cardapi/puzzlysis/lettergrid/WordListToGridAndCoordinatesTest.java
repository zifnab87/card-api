package com.cardapi.cardapi.puzzlysis.lettergrid;

import com.cardapi.cardapi.AbstractUnitTest;
import com.cardapi.cardapi.puzzlysis.common.nuggets.CharacterGrid;
import com.cardapi.cardapi.puzzlysis.common.nuggets.CoordinateList;
import com.cardapi.cardapi.puzzlysis.common.nuggets.WordList;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class WordListToGridAndCoordinatesTest extends AbstractUnitTest {

    @Test
    void test() {
        var expectedWordList = new WordList<>(Arrays.asList("apple", "banana", "cherry"));
        var proposedGrid = (CharacterGrid) new WordListToGridClueProvider(expectedWordList).getClues().getFirst();
        proposedGrid.print();

        var clueProvider = new WordListAndGridToCoordinatesClueProvider(proposedGrid, expectedWordList);

        expectedWordList.print();

        var wordCoordinates= clueProvider.getClues();
        wordCoordinates.forEach(c -> c.print());

        System.out.println("<-- solution ---->");

        var solver = new LetterGridSolver(proposedGrid,wordCoordinates.stream().map(t -> (CoordinateList) t).collect(Collectors.toList()));

        var solverSolution = solver.getSolution();
        solverSolution.print();

        assertThat(expectedWordList).usingRecursiveComparison().isEqualTo(solverSolution);
    }

}