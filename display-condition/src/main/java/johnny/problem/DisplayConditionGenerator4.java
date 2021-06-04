package johnny.problem;

import java.util.ArrayList;
import java.util.List;

public class DisplayConditionGenerator4 {

    public static List<List<SimpleCondition>> getDimensionCombinations(List<SimpleCondition> conditions) {
        List<List<SimpleCondition>> combination = new ArrayList<>();

        List<SimpleCondition> list = new ArrayList<>();
        dfs(0, conditions, list, combination);

        return combination;
    }

    private static void dfs(int pos, List<SimpleCondition> conditions, List<SimpleCondition> list, List<List<SimpleCondition>> combination) {
        if (list.size() == conditions.size()) {
            combination.add(new ArrayList<>(list));
            return;
        }

        for (int i = pos; i < conditions.size(); i++) {
            SimpleCondition condition = conditions.get(i);
            if (condition.getValue() == null) {
                list.add(new SimpleCondition(condition.getAccessDimension(), condition.getKey(), condition.getAccessDimension().getNegativeValue()));
                dfs(i + 1, conditions, list, combination);
                list.remove(list.size() - 1);
            }

            if (condition.getValue() == null) {
                list.add(new SimpleCondition(condition.getAccessDimension(), condition.getKey(), condition.getAccessDimension().getPositiveValue()));
            } else {
                list.add(new SimpleCondition(condition.getAccessDimension(), condition.getKey(), condition.getValue()));
            }
            dfs(i + 1, conditions, list, combination);
            list.remove(list.size() - 1);
        }
    }
}
