package johnny.problem;

import java.util.ArrayList;
import java.util.List;

public class DisplayConditionGenerator2 {

    public static List<List<String>> getDimensionCombinations(List<DisplayCondition> conditions) {
        List<List<String>> ans = new ArrayList<>();

        List<String> list = new ArrayList<>();
        dfs(0, conditions, list, ans);

        return ans;
    }

    private static void dfs(int pos, List<DisplayCondition> conditions, List<String> list, List<List<String>> ans) {
        if (list.size() == conditions.size()) {
            ans.add(new ArrayList<>(list));
            return;
        }

        for (int i = pos; i < conditions.size(); i++) {
            DisplayCondition condition = conditions.get(i);
            list.add(condition.getKey() + '_' + condition.getDimensionType().getPositiveValue());
            dfs(i + 1, conditions, list, ans);
            list.remove(list.size() - 1);

            list.add(condition.getKey() + '_' + condition.getDimensionType().getNegativeValue());
            dfs(i + 1, conditions, list, ans);
            list.remove(list.size() - 1);
        }
    }
}
