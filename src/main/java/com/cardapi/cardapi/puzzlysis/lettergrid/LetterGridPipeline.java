package com.cardapi.cardapi.puzzlysis.lettergrid;

import com.cardapi.cardapi.puzzlysis.common.Pipeline;
import com.cardapi.cardapi.puzzlysis.common.nuggets.Nugget;
import com.cardapi.cardapi.puzzlysis.common.nuggets.WordList;

import java.util.Arrays;
import java.util.List;

public class LetterGridPipeline {

    public static void main(String ergs[] ) {
        Pipeline pipeline = new Pipeline();

        var expectedWordList = new WordList(Arrays.asList("apple", "banana", "cherry")); // this is input 0

        pipeline.addStep(new WordListToCharacterGrid(), List.of(0)); // this is result 1
        pipeline.addStep(new WordListAndCharacterGridToCoordinatePairList(), List.of(0,1)); // this result 2
        pipeline.addStep(new LetterGridSolver(), List.of(1,2)); // this is result 3

        //it should return 3


        var results = pipeline.execute(List.of(expectedWordList));         //it should return 3

        System.out.println(results);
    }
}
