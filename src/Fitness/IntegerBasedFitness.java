package Fitness;

public class IntegerBasedFitness implements Fitness {
    @Override
    public Integer fitness(String candidate) {
        int result = 1;
        for (int i = 0; i < candidate.length(); i++) {
            result *= candidate.charAt(i);
        }
        return result;
    }
}
