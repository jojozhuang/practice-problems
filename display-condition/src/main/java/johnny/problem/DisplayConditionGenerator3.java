package johnny.problem;

import java.util.ArrayList;
import java.util.List;

public class DisplayConditionGenerator3 {

    public static List<List<SingleDimension>> combine(SimpleCondition[] conditions) {
        List<List<SingleDimension>> ans = new ArrayList<>();

        List<SingleDimension> list = new ArrayList<>();
        dfs(0, conditions, list, ans);

        return ans;
    }

    private static void dfs(int pos, SimpleCondition[] conditions, List<SingleDimension> list, List<List<SingleDimension>> ans) {
        if (list.size() == conditions.length) {
            ans.add(new ArrayList<>(list));
            return;
        }

        for (int i = pos; i < conditions.length; i++) {
            list.add(new SingleDimension(conditions[i].getAccessDimension(), conditions[i].getKey(), conditions[i].getAccessDimension().getPositiveValue()));
            dfs(i + 1, conditions, list, ans);
            list.remove(list.size() - 1);

            list.add(new SingleDimension(conditions[i].getAccessDimension(), conditions[i].getKey(), conditions[i].getAccessDimension().getNegativeValue()));
            dfs(i + 1, conditions, list, ans);
            list.remove(list.size() - 1);
        }
    }
}
