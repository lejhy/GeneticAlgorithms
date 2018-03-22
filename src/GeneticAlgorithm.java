import Filters.CrossoverFilter;
import Filters.MutationFilter;
import Filters.SurvivorFilter;
import Fitness.Fitness;

import java.util.List;

public class GeneticAlgorithm {

    private CrossoverFilter crossover;
    private MutationFilter mutation;
    private SurvivorFilter survivorSelection;
    private Fitness fitness;

    public GeneticAlgorithm(CrossoverFilter crossover, MutationFilter mutation, SurvivorFilter survivorSelection, Fitness fitness) {
        this.crossover = crossover;
        this.mutation = mutation;
        this.survivorSelection = survivorSelection;
        this.fitness = fitness;
    }

    public List<String> crossover(List<String> population, int generationMultiplier) {
        return crossover.filter(population, generationMultiplier);
    }

    public List<String> mutation(List<String> population, String charSet) {
        return mutation.filter(population, charSet);
    }

    public List<String> survivorSelection(List<String> population, int populationSize) {
        return survivorSelection.filter(population, populationSize, fitness);
    }

    public int fitness(String candidate) {
        return fitness.fitness(candidate);
    }
}
