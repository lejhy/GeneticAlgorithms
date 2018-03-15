package Fitness;

public class ExactMatchFitness implements Fitness{

    private String solution;

    public ExactMatchFitness(String solution) {
        this.solution = solution;
    }

    public Integer fitness(String candidate) {
        int result = 0;
        for (int i = 0; i < candidate.length(); i++) {
            if (candidate.charAt(i) == solution.charAt(i)) {
                result++;
            }
        }
        return result;
    }
}
