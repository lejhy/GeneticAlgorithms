package Filters;

import java.util.List;

public interface MutationFilter {
    List<String> filter(List<String> population, String charSet);
}
