package com.cardapi.cardapi.puzzlysis.common;

import com.cardapi.cardapi.puzzlysis.common.nuggets.Nugget;

import java.util.ArrayList;
import java.util.List;

public class Pipeline {

    private final List<ConverterStep> steps = new ArrayList<>();
    private final List<List<? extends Nugget>> results = new ArrayList<>();

    // Internal class to represent a pipeline step with its dependencies
    private static class ConverterStep {
        ConverterTo<? extends Nugget> converter;
        List<Integer> dependencies;

        public ConverterStep(ConverterTo<? extends Nugget> converter, List<Integer> dependencies) {
            this.converter = converter;
            this.dependencies = dependencies;
        }
    }

    // Method to add a step to the pipeline with specific dependencies
    public Pipeline addStep(ConverterTo<? extends Nugget> converter, List<Integer> dependencies) {
        System.out.println(converter + " was added");
        steps.add(new ConverterStep(converter, dependencies));
        return this;
    }

    // Execute the pipeline, starting with the initial input nuggets
    public List<List<? extends Nugget>> execute(List<? extends Nugget> initialNuggets) {
        results.add(initialNuggets); // Set the initial input as the first result
        System.out.println("\ninput/result 0: \n"+ initialNuggets);
        int i = 1;
        for (ConverterStep step : steps) {
            System.out.println("-----------------------------------");
            List<Nugget> inputForStep = new ArrayList<>();

            // Gather dependencies for the current step
            for (int index : step.dependencies) {
                inputForStep.addAll(results.get(index));
            }
            System.out.println("step: "+ step.converter);
            System.out.println("dependencies: "+  step.dependencies);
            System.out.println("inputs: \n"+ inputForStep);

            // Execute the step and store the result
            List<? extends Nugget> stepResult = step.converter.apply(inputForStep);
            System.out.println("output "+i+": \n"+ stepResult);
            results.add(stepResult);
            i++;
        }

        // Return the last result in the pipeline
        System.out.println("\n\n\nwhole-result: \n");
        System.out.println(results);
        return results;
    }
}