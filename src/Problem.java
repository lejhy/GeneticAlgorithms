
import java.util.*;

public class Problem {
    private int solutionLength;
    private int populationSize;
    private int generationMultiplier;
    private String charSet;
    private List<String> initialPopulation;

    private RandomStringGenerator generator;
    private Random random;

    private GeneticAlgorithm algorithm;

    public Problem (int solutionLength, int populationSize, int generationMultiplier, String charSet, GeneticAlgorithm algorithm) {
        this.solutionLength = solutionLength;
        this.populationSize = populationSize;
        this.generationMultiplier = generationMultiplier;
        this.charSet = charSet;
        initialPopulation = new ArrayList<>();
        generator = new RandomStringGenerator(charSet);
        for (int i = 0; i < populationSize; i++) {
            initialPopulation.add(generator.getString(solutionLength));
        }
        random = new Random();
        this.algorithm = algorithm;
    }

    public List<String> solve(int searchDepth) {
        List<String> population = initialPopulation;

        for (int i = 0; i < searchDepth; i++) {
            population.addAll(algorithm.crossover(population, generationMultiplier));
            population = algorithm.mutation(population, charSet);
            population = algorithm.survivorSelection(population, populationSize);
        }
        return population;
    }
}
