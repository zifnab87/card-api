package com.cardapi.cardapi.puzzlysis.lettergrid;

import com.cardapi.cardapi.puzzlysis.common.Pipeline;
import com.cardapi.cardapi.puzzlysis.common.nuggets.WordList;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LetterGridPipelineTest {

    @Test
    public void test() {
        Pipeline pipeline = new Pipeline();

        var expectedWordList = new WordList(Arrays.asList("apple", "banana", "cherry")); // this is result 0

        pipeline.addStep(new WordListToCharacterGrid(), List.of(0)); // this is result 1
        pipeline.addStep(new WordListAndCharacterGridToCoordinatePairList(), List.of(0,1)); // this result 2
        pipeline.addStep(new LetterGridSolver(), List.of(1,2)); // this is result 3

        var results = pipeline.execute(List.of(expectedWordList));         //it should return 4 total

        assertThat(results.getFirst()).usingRecursiveComparison().isEqualTo(results.getLast());
    }
}
