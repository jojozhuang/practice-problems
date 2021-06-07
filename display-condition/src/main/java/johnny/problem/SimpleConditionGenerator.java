package johnny.problem;

import java.util.ArrayList;
import java.util.List;

public class SimpleConditionGenerator {

public static List<List<String>> getDimensionCombinations(List<String> keys) {
    List<List<String>> combinations = new ArrayList<>();

    List<String> list = new ArrayList<>();
    dfs(0, keys, list, combinations);

    return combinations;
}

private static void dfs(int pos, List<String> keys, List<String> list, List<List<String>> combinations) {
    if (list.size() == keys.size()) {
        combinations.add(new ArrayList<>(list));
        return;
    }

    for(int i = pos; i < keys.size(); i++) {
        list.add(keys.get(i) + "0");
        dfs(i + 1, keys, list, combinations);
        list.remove(list.size() - 1);

        list.add(keys.get(i) + "1");
        dfs(i + 1, keys, list, combinations);
        list.remove(list.size() - 1);
    }
}
}
