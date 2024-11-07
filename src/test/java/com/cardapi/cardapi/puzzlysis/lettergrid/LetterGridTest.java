package com.cardapi.cardapi.puzzlysis.lettergrid;

import com.cardapi.cardapi.AbstractUnitTest;
import com.cardapi.cardapi.puzzlysis.common.nuggets.Nugget;
import com.cardapi.cardapi.puzzlysis.common.nuggets.WordList;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class LetterGridTest extends AbstractUnitTest {

    @Test
    void test() {
        var expectedWordList = new WordList(Arrays.asList("apple", "banana", "cherry"));
        expectedWordList.print();

        var proposedGrid = new WordListToCharacterGrid().apply(
                expectedWordList.wrapToList()
        );
        proposedGrid.getFirst().print();

        var args = new ArrayList<Nugget>();
        args.add(expectedWordList);
        args.add(proposedGrid.getFirst());

        var wordCoordinates = new WordListAndCharacterGridToCoordinatePairList().apply(
                args
        );

        wordCoordinates.forEach(c -> c.print());

        System.out.println("<-- solution ---->");

        var solverArgs = new ArrayList<Nugget>();
        solverArgs.add(proposedGrid.getFirst());
        solverArgs.addAll(wordCoordinates);

        var solver = new LetterGridSolver().apply(solverArgs);

        var solverSolution = solver.getFirst();
        solverSolution.print();

        assertThat(solverSolution).usingRecursiveComparison().isEqualTo(expectedWordList);
    }

}