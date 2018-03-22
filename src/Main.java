import Filters.FitnessBasedSelection;
import Filters.RandomResettingMutation;
import Filters.UniformCrossover;
import Fitness.*;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // String matching
        System.out.println("Input a solution: \n");
        String solution = scanner.nextLine();

        System.out.println("Input a population size: \n");
        int populationSize = scanner.nextInt();

        System.out.println("Input a population multiplier: \n");
        int populationMultiplier = scanner.nextInt();

        String charSet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        GeneticAlgorithm algorithm = new GeneticAlgorithm(new UniformCrossover(), new RandomResettingMutation(), new FitnessBasedSelection(), new ExactMatchFitness(solution));
        Problem problem = new Problem(solution.length(), populationSize, populationMultiplier, charSet, algorithm);

        System.out.println("Input a search depth: \n");
        int searchDepth = scanner.nextInt();

        List<String> results = problem.solve(searchDepth);
        System.out.println("Results:");
        for (String result : results) {
            System.out.println(result + " --- " + algorithm.fitness(result));
        }

        // Highest integer value
        System.out.println("String to max int ("+Integer.MAX_VALUE+")problem: \n");
        System.out.println("Input a number of characters in a solution");
        int solutionLenghth = scanner.nextInt();

        System.out.println("Input a population size: \n");
        populationSize = scanner.nextInt();

        System.out.println("Input a population multiplier: \n");
        populationMultiplier = scanner.nextInt();

        algorithm = new GeneticAlgorithm(new UniformCrossover(), new RandomResettingMutation(), new FitnessBasedSelection(), new IntegerBasedFitness());
        problem = new Problem(solutionLenghth, populationSize, populationMultiplier, charSet, algorithm);

        System.out.println("Input a search depth: \n");
        searchDepth = scanner.nextInt();

        results = problem.solve(searchDepth);
        System.out.println("Results:");
        for (String result : results) {
            System.out.println(result + " --- " + algorithm.fitness(result));
        }


    }
}
