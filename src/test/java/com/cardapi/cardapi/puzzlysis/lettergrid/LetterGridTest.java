package com.cardapi.cardapi.puzzlysis.lettergrid;

import com.cardapi.cardapi.AbstractUnitTest;
import com.cardapi.cardapi.puzzlysis.common.nuggets.WordList;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LetterGridTest extends AbstractUnitTest {

    @Test
    void test() {
        var expectedWordList = new WordList(Arrays.asList("apple", "banana", "cherry"));
        expectedWordList.print();

        var proposedGrid = new WordListToCharacterGrid(
                expectedWordList.wrapToList()
        ).getOutputNuggets().getFirst();
        proposedGrid.print();

        var wordCoordinates = new WordListAndCharacterGridToCoordinatePairList(
                List.of(expectedWordList, proposedGrid)
        ).getOutputNuggets();

        wordCoordinates.forEach(c -> c.print());

        System.out.println("<-- solution ---->");

        var solver = new LetterGridSolver(proposedGrid, wordCoordinates);

        var solverSolution = solver.getSolution();
        solverSolution.print();

        assertThat(expectedWordList).usingRecursiveComparison().isEqualTo(solverSolution);
    }

}