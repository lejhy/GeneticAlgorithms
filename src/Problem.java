
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
        generator = new RandomStringGenerator(charSet);

        // Concurrency code
        initialPopulation = Collections.synchronizedList(new ArrayList<>());
        int threadCount = 4;
        List<Thread> threads = new ArrayList<>();
        int populationPerThread = populationSize/threadCount;
        int populationOfLastThread = populationPerThread + populationSize%threadCount;
        for (int i = 0; i < threadCount; i++) {
            final int populationOfCurrentThread = i == (threadCount - 1) ? populationOfLastThread : populationPerThread;
            Thread thread = new Thread(() -> {
                for (int j = 0; j < populationOfCurrentThread; j++) {
                    String initialParent = generator.getString(solutionLength);
                    initialPopulation.add(initialParent);
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
