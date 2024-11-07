package com.cardapi.cardapi.puzzlysis.lettergrid;

import com.cardapi.cardapi.puzzlysis.anagram.FindAnagrams;
import com.cardapi.cardapi.puzzlysis.common.DictionaryLoader;
import com.cardapi.cardapi.puzzlysis.common.Pipeline;
import com.cardapi.cardapi.puzzlysis.common.nuggets.WordList;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class LetterGridPipelineTest {

    @Test
    public void test() {
        Pipeline pipeline = new Pipeline();

        var expectedWordList = new WordList(Arrays.asList("paple", "nabana", "rryche")); // this is result 0

        var dictionary = new DictionaryLoader().loadDictionary("words_alpha.txt");

        pipeline.addStep(new FindAnagrams(dictionary), List.of(0)); // this is result 1
        pipeline.addStep(new WordListToCharacterGrid(), List.of(1)); // this is result 2
        pipeline.addStep(new WordListAndCharacterGridToCoordinatePairList(), List.of(1,2)); // this result 3
        pipeline.addStep(new LetterGridSolver(), List.of(2,3)); // this is result 4

        var results = pipeline.execute(List.of(expectedWordList));         //it should return 4 total

        assertThat(results.get(1)).usingRecursiveComparison().isEqualTo(results.getLast());
    }
}
