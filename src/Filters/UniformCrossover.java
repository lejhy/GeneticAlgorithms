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

        // Concurrency code
        List <String> offsprings = Collections.synchronizedList(new ArrayList<>());
        int threadCount = 4;
        List<Thread> threads = new ArrayList<>();
        int populationPerThread = population.size()/threadCount;
        int populationOfLastThread = populationPerThread + population.size()%threadCount;
        for (int i = 0; i < threadCount; i++) {
            final int populationOfCurrentThread = i == (threadCount - 1) ? populationOfLastThread : populationPerThread;
            Thread thread = new Thread(() -> {
                for (int j = 0; j < populationOfCurrentThread*multiplier; j++) {
                    int firstParentIndex = random.nextInt(population.size());
                    String firstParent = population.get(firstParentIndex);
                    int secondParentIndex = random.nextInt(population.size());
                    String secondParent = population.get(secondParentIndex);
                    String offspring = crossover(firstParent, secondParent);
                    offsprings.add(offspring);
                }
            });
            thread.start();
            threads.add(thread);
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                System.out.println("The thread joining was interrupted! Results could be inconsistent...");
                e.printStackTrace();
            }
        }
        // End of concurrency code

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
