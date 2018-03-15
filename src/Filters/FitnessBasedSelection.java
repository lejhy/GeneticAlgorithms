package Filters;

import Fitness.Fitness;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FitnessBasedSelection implements SurvivorFilter {

    private Fitness fitness;

    public FitnessBasedSelection(){
        this.fitness = fitness;
    }

    public List<String> filter(List<String> population, int populationSize, Fitness fitness) {
        Map<String, Integer> fitnessMap = new HashMap<>();
        for (String string: population) {
            fitnessMap.put(string, fitness.fitness(string));
        }

        population.sort((s1, s2) -> {
            int s1Fitness = fitnessMap.get(s1);
            int s2Fitness = fitnessMap.get(s2);
            if (s1Fitness < s2Fitness) {
                return 1;
            } else if (s1Fitness == s2Fitness) {
                return 0;
            } else {
                return -1;
            }
        });
        return population.subList(0, populationSize);
    }
}
