package Filters;

import java.util.List;

public interface CrossoverFilter {
    List<String> filter(List<String> population);
}
