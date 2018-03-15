import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Input a solution: \n");
        String solution = scanner.nextLine();

        System.out.println("Input a population size: \n");
        int populationSize = scanner.nextInt();

        Problem problem = new Problem(solution, populationSize, "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789");

        System.out.println("Input a search depth: \n");
        int searchDepth = scanner.nextInt();

        List<String> results = problem.solve(searchDepth);
        System.out.println("Results:");
        for (String result: results) {
            System.out.println(result+" --- "+problem.fitness(result));
        }

    }
}
