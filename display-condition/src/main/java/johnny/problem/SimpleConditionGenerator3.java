package johnny.problem;

import java.util.ArrayList;
import java.util.List;

public class SimpleConditionGenerator3 {

    public static List<List<SimpleCondition>> getDimensionCombinations(List<SimpleCondition> conditions) {
        List<List<SimpleCondition>> ans = new ArrayList<>();

        List<SimpleCondition> list = new ArrayList<>();
        dfs(0, conditions, list, ans);

        return ans;
    }

    private static void dfs(int pos, List<SimpleCondition> conditions, List<SimpleCondition> list, List<List<SimpleCondition>> ans) {
        if (list.size() == conditions.size()) {
            ans.add(new ArrayList<>(list));
            return;
        }

        for (int i = pos; i < conditions.size(); i++) {
            SimpleCondition condition = conditions.get(i);
            list.add(new SimpleCondition(condition.getDimensionType(), condition.getKey(), false, condition.getDimensionType().getNegativeValue()));
            dfs(i + 1, conditions, list, ans);
            list.remove(list.size() - 1);

            list.add(new SimpleCondition(condition.getDimensionType(), condition.getKey(), false, condition.getDimensionType().getPositiveValue()));
            dfs(i + 1, conditions, list, ans);
            list.remove(list.size() - 1);
        }
    }
}
