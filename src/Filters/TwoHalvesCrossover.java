package Filters;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TwoHalvesCrossover implements CrossoverFilter{

	private Random random;

    public TwoHalvesCrossover() {
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
	
	/**
	 * Performs crossover on the two strings, returning a new string. The function chooses
	 * which strings form the first and second halves randomly. Both strings should be of
	 * the same length.
	 */
	private String crossover(String parent1, String parent2) {
		String result = "";
		int stringLength = parent1.length();
		if (random.nextInt(2) == 0) {
			result += parent1.substring(0, (stringLength/2));
			result += parent2.substring((stringLength/2), stringLength);
		} else {
			result += parent2.substring(0, (stringLength/2));
			result += parent1.substring((stringLength/2), stringLength);
		}
        return result;
	}
}
