package Filters;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RandomResettingMutation implements MutationFilter {

    private Random random;

    public RandomResettingMutation() {
        this.random = new Random();
    }

    public List<String> filter(List<String> population, String charSet) {

        // Concurrency code
        List <String> offsprings = Collections.synchronizedList(new ArrayList<>());
        int threadCount = 4;
        List<Thread> threads = new ArrayList<>();
        int populationPerThread = population.size()/threadCount;
        int populationOfLastThread = populationPerThread + population.size()%threadCount;
        for (int i = 0; i < threadCount; i++) {
            final int populationOfCurrentThread = i == (threadCount - 1) ? populationOfLastThread : populationPerThread;
            final int threadNumber = i;
            Thread thread = new Thread(() -> {
                for (int j = 0; j < populationOfCurrentThread; j++) {
                    int index = threadNumber * populationPerThread + j;
                    String offspring = mutation(population.get(index),charSet);
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

    private String mutation(String parent, String charSet) {
        String result = "";
        for (int i = 0; i < parent.length(); i++) {
            if (random.nextInt(10) == 0) {
                result += charSet.charAt(random.nextInt(charSet.length()));
            } else {
                result += parent.charAt(i);
            }
        }
        return result;
    }
}
