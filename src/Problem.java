import java.util.*;

public class Problem {
    private String solution;
    private List<String> initialPopulation;
    private RandomStringGenerator generator;
    private int populationSize;
    private Random random;

    public Problem (String solution, int populationSize, String charSet) {
        this.solution = solution;
        this.populationSize = populationSize;
        initialPopulation = new ArrayList<>();
        generator = new RandomStringGenerator(charSet);
        for (int i = 0; i < populationSize; i++) {
            initialPopulation.add(generator.getString(solution.length()));
        }
        random = new Random();
    }

    public List<String> solve(int searchDepth) {
        List<String> population = initialPopulation;

        for (int i = 0; i < searchDepth; i++) {
            population.addAll(crossover(population));
            population = mutation(population);
            population = survivorSelection(population);
        }
        return population;
    }

    private List<String> crossover(List<String> population) {
        int parentIndex;
        List<String> offsprings = new ArrayList<>();
        for (int i = 0; i < population.size(); i++) {
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

    private List<String> mutation(List<String> population) {
        List <String> offsprings = new ArrayList<>();
        for (int i = 0; i < population.size(); i++) {
            String offspring = mutation(population.get(i));
            offsprings.add(offspring);
        }
        return offsprings;
    }

    private String mutation(String parent) {
        String result = "";
        for (int i = 0; i < parent.length(); i++) {
            if (random.nextInt(10) == 0) {
                result += generator.getChar();
            } else {
                result += parent.charAt(i);
            }
        }
        return result;
    }

    private List<String> survivorSelection(List<String> population) {
        Map<String, Integer> fitnessMap = new HashMap<>();
        for (String string: population) {
            fitnessMap.put(string, fitness(string));
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

    public Integer fitness(String string) {
        int result = 0;
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == solution.charAt(i)) {
                result++;
            }
        }
        return result;
    }
}
