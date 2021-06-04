package johnny.problem;

import java.util.ArrayList;
import java.util.List;

public class DisplayConditionGenerator {

    public static List<List<String>> combine(String[] keys) {
        List<List<String>> ans = new ArrayList<>();

        List<String> list = new ArrayList<>();
        dfs(0, keys, list, ans);

        return ans;
    }

    private static void dfs(int pos, String[] keys, List<String> list, List<List<String>> ans) {
        if (list.size() == keys.length) {
            ans.add(new ArrayList<>(list));
            return;
        }

        for(int i = pos; i < keys.length; i++) {
            list.add(keys[i] + "0");
            dfs(i + 1, keys, list, ans);
            list.remove(list.size() - 1);
            list.add(keys[i] + "1");
            dfs(i + 1, keys, list, ans);
            list.remove(list.size() - 1);
        }
    }
}
