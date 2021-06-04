package johnny.problem;

import java.util.ArrayList;
import java.util.List;

public class DisplayConditionGenerator2 {

    public static List<List<String>> combine(SimpleCondition[] conditions) {
        List<List<String>> ans = new ArrayList<>();

        List<String> list = new ArrayList<>();
        dfs(0, conditions, list, ans);

        return ans;
    }

    private static void dfs(int pos, SimpleCondition[] conditions, List<String> list, List<List<String>> ans) {
        if (list.size() == conditions.length) {
            ans.add(new ArrayList<>(list));
            return;
        }

        for (int i = pos; i < conditions.length; i++) {
            list.add(conditions[i].getKey() + '_' + conditions[i].getAccessDimension().getPositiveValue());
            dfs(i + 1, conditions, list, ans);
            list.remove(list.size() - 1);

            list.add(conditions[i].getKey() + '_' + conditions[i].getAccessDimension().getNegativeValue());
            dfs(i + 1, conditions, list, ans);
            list.remove(list.size() - 1);
        }
    }
}
