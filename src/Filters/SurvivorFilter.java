package Filters;

import Fitness.Fitness;
import java.util.List;

public interface SurvivorFilter {
    List<String> filter (List<String> population, int popultaionSize, Fitness fitness);
}
