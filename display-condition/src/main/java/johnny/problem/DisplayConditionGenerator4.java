package johnny.problem;

import java.util.ArrayList;
import java.util.List;

public class DisplayConditionGenerator4 {

    public static List<List<DisplayCondition>> getDimensionCombinations(List<DisplayCondition> conditions) {
        List<List<DisplayCondition>> combination = new ArrayList<>();

        List<DisplayCondition> list = new ArrayList<>();
        dfs(0, conditions, list, combination);

        return combination;
    }

    private static void dfs(int pos, List<DisplayCondition> conditions, List<DisplayCondition> list, List<List<DisplayCondition>> combination) {
        if (list.size() == conditions.size()) {
            combination.add(new ArrayList<>(list));
            return;
        }

        for (int i = pos; i < conditions.size(); i++) {
            DisplayCondition condition = conditions.get(i);
            if (condition.getValue() == null) {
                list.add(new DisplayCondition(condition.getDimensionType(), condition.getKey(), condition.getDimensionType().getNegativeValue()));
                dfs(i + 1, conditions, list, combination);
                list.remove(list.size() - 1);
            }

            if (condition.getValue() == null) {
                list.add(new DisplayCondition(condition.getDimensionType(), condition.getKey(), condition.getDimensionType().getPositiveValue()));
            } else {
                list.add(new DisplayCondition(condition.getDimensionType(), condition.getKey(), condition.getValue()));
            }
            dfs(i + 1, conditions, list, combination);
            list.remove(list.size() - 1);
        }
    }
}
