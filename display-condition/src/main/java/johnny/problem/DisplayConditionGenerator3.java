package johnny.problem;

import java.util.ArrayList;
import java.util.List;

public class DisplayConditionGenerator3 {

    public static List<List<DisplayCondition>> getDimensionCombinations(List<DisplayCondition> conditions) {
        List<List<DisplayCondition>> ans = new ArrayList<>();

        List<DisplayCondition> list = new ArrayList<>();
        dfs(0, conditions, list, ans);

        return ans;
    }

    private static void dfs(int pos, List<DisplayCondition> conditions, List<DisplayCondition> list, List<List<DisplayCondition>> ans) {
        if (list.size() == conditions.size()) {
            ans.add(new ArrayList<>(list));
            return;
        }

        for (int i = pos; i < conditions.size(); i++) {
            DisplayCondition condition = conditions.get(i);
            list.add(new DisplayCondition(condition.getDimensionType(), condition.getKey(), condition.getDimensionType().getPositiveValue()));
            dfs(i + 1, conditions, list, ans);
            list.remove(list.size() - 1);

            list.add(new DisplayCondition(condition.getDimensionType(), condition.getKey(), condition.getDimensionType().getNegativeValue()));
            dfs(i + 1, conditions, list, ans);
            list.remove(list.size() - 1);
        }
    }
}
