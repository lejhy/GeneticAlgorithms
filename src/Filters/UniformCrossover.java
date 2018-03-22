package Filters;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class UniformCrossover implements CrossoverFilter {

    private Random random;

    public UniformCrossover() {
        this.random = new Random();
    }

    public List<String> filter(List<String> population, int multiplier) {
        List<String> offsprings = new ArrayList<>();
        int parentIndex;
        for (int i = 0; i < population.size()*multiplier; i++) {
            parentIndex = random.nextInt(population.size());
            String parent1 = population.get(parentIndex);
            parentIndex = random.nextInt(population.size());
            String parent2 = population.get(parentIndex);
            String offspring = crossover(parent1, parent2);
            offsprings.add(offspring);
        }
        return offsprings;
    }

    private String crossover(String parent1, String parent2) {
        String result = "";
        for (int i = 0; i < parent1.length(); i++) {
            if (random.nextBoolean()) {
                result += parent1.charAt(i);
            } else {
                result += parent2.charAt(i);
            }
        }
        return result;
    }
}
