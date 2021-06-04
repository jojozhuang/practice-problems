package johnny.problem;

import java.util.ArrayList;
import java.util.List;

public class DisplayConditionGenerator {

    public static List<List<String>> combine(String[] keys) {
        List<List<String>> ans = new ArrayList<>();

        List<String> list = new ArrayList<>();
        dfs(keys.length, 1, keys, list, ans);

        return ans;
    }

    private static void dfs(int n, int pos, String[] keys, List<String> list, List<List<String>> ans) {
        if (list.size() == n) {
            ans.add(new ArrayList<>(list));
            return;
        }

        for(int i = pos; i <= n; i++) {
            list.add(keys[i]);
            dfs(n, i + 1, keys, list, ans);
            list.remove(list.size() - 1);
        }
    }
}
