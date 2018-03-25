import Filters.*;
import Fitness.*;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Select a fitness filter:");
        System.out.println("(0) ExactMatchFitness");
        System.out.println("(1) IntegerBasedFitness");
        int fitnessFilterIndex = scanner.nextInt();
        scanner.nextLine();
        int solutionLength;
        Fitness fitnessFilter;
        switch (fitnessFilterIndex) {
            case 0:
                System.out.println("Input a solution: \n");
                String solution = scanner.nextLine();
                solutionLength = solution.length();
                fitnessFilter = new ExactMatchFitness(solution);
                break;
            case 1:
                System.out.println("Solution is: "+ Integer.MAX_VALUE );
                System.out.println("Input a number of characters in a solution");
                solutionLength = scanner.nextInt();
                fitnessFilter = new IntegerBasedFitness();
                break;
            default:
                throw new Exception("Invalid input");
        }

        System.out.println("Select crossover filter:");
        System.out.println("(0) UniformCrossover");
        System.out.println("(1) TwoHalvesCrossover");
        int crossoverFilterIndex = scanner.nextInt();
        scanner.nextLine();
        CrossoverFilter crossoverFilter;
        switch (crossoverFilterIndex) {
            case 0:
                crossoverFilter = new UniformCrossover();
                break;
            case 1:
                crossoverFilter = new TwoHalvesCrossover();
                break;
            default:
                throw new Exception("Invalid input");
        }

        System.out.println("Select mutation filter:");
        System.out.println("(0) RandomResettingMutation");
        System.out.println("(1) ScrambleMutation");
        int mutationFilterIndex = scanner.nextInt();
        scanner.nextLine();
        MutationFilter mutationFilter;
        switch (mutationFilterIndex) {
            case 0:
                mutationFilter = new RandomResettingMutation();
                break;
            case 1:
                mutationFilter = new ScrambleMutation();
                break;
            default:
                throw new Exception("Invalid input");
        }

        System.out.println("Input a population size: \n");
        int populationSize = scanner.nextInt();

        System.out.println("Input a population multiplier: \n");
        int populationMultiplier = scanner.nextInt();

        String charSet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        GeneticAlgorithm algorithm = new GeneticAlgorithm(crossoverFilter, mutationFilter, new FitnessBasedSelection(), fitnessFilter);
        Problem problem = new Problem(solutionLength, populationSize, populationMultiplier, charSet, algorithm);

        System.out.println("Input a search depth: \n");
        int searchDepth = scanner.nextInt();

        List<String> results = problem.solve(searchDepth);
        System.out.println("Results:");
        double resultTotalFitness = 0;
        int resultMaxFitness = 0;
        for (String result : results) {
            int resultFitness = algorithm.fitness(result);
        	resultTotalFitness += resultFitness;
        	if (resultFitness > resultMaxFitness) resultMaxFitness = resultFitness;
            System.out.println(result + " --- " + resultFitness);
        }

        System.out.println("\nMaximum Fitness Value: " + resultMaxFitness);
        System.out.println("Average Fitness Value: " + resultTotalFitness/results.size());
    }
}
