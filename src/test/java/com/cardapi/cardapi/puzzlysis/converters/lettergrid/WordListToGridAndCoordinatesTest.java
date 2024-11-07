package com.cardapi.cardapi.puzzlysis.converters.lettergrid;

import com.cardapi.cardapi.AbstractUnitTest;
import com.cardapi.cardapi.puzzlysis.common.solutions.SolutionList;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class WordListToGridAndCoordinatesTest extends AbstractUnitTest {

    @Test
    void test() {
        var expectedSolution = new SolutionList<>(Arrays.asList("apple", "banana", "cherry"));

        var clueProvider = new WordListToCoordinatesClueProvider(expectedSolution);

        expectedSolution.print();

        var clues= clueProvider.getClues();
        clues.forEach(c -> c.print());

        System.out.println("<-- solution ---->");

        var solver = new WordListToGridAndCoordinatesSolver(clues);

        var solverSolution = solver.getSolution();
        solverSolution.print();

        assertThat(expectedSolution).usingRecursiveComparison().isEqualTo(solverSolution);
    }

}