package Filters;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomResettingMutation implements MutationFilter {

    private Random random;

    public RandomResettingMutation() {
        this.random = new Random();
    }

    public List<String> filter(List<String> population, String charSet) {
        List <String> offsprings = new ArrayList<>();
        for (int i = 0; i < population.size(); i++) {
            String offspring = mutation(population.get(i),charSet);
            offsprings.add(offspring);
        }
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
