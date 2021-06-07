package johnny.problem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EvaluateBooleanExpression {
    public boolean evaluate(String expression) {
        if (expression.equals("t")) return true;
        if (expression.equals("f")) return false;
        char[] arr = expression.toCharArray();
        char op = arr[0];
        boolean result = op != '|';
        int count = 0;
        for (int i = 1, pre = 2; i < arr.length; i++) {
            char c = arr[i];
            if (c == '(') count++;
            if (c == ')') count--;
            if (c == ',' && count == 1 || c == ')' && count == 0) {
                boolean next = evaluate(expression.substring(pre, i));
                pre = i + 1;
                if (op == '|') result |= next;
                else if (op == '&') result &= next;
                else result = !next;
            }
        }
        return result;
    }

    public boolean evaluate2(String expression) {
        return helper2(expression.toCharArray(), 0, expression.length() - 1);
    }

    public boolean helper2(char[] arr, int start, int end) {
        if (start == end) {
            if (arr[start] == 't') {
                return true;
            }
            if (arr[start] == 'f') {
                return false;
            }
        }
        char op = arr[start];
        boolean result = op != '|';
        int count = 0;
        for (int i = start + 1, pre = 2; i <= end; i++) {
            char c = arr[i];
            if (c == '(') {
                count++;
            } else if (c == ')') {
                count--;
            }
            if (c == ',' && count == 1 || c == ')' && count == 0) {
                boolean next = helper2(arr, start + pre, i - 1);
                pre = i - start + 1;
                if (op == '|') {
                    result |= next;
                } else if (op == '&') {
                    result &= next;
                } else {
                    result = !next;
                }
            }
        }
        return result;
    }

    public List<Map<Character, Boolean>> getCombinations(String condition) {
        List<Map<Character, Boolean>> combinations = new ArrayList<>();
        Map<Character, Boolean> map = new HashMap<>();

        dfs(0, condition.toCharArray(), map, combinations);

        return combinations;
    }

    private void dfs(int pos, char[] conditions, Map<Character, Boolean> map, List<Map<Character, Boolean>> combinations) {
        if (map.size() == conditions.length) {
            combinations.add(new HashMap<>(map));
            return;
        }

        for(int i = pos; i < conditions.length; i++) {
            map.put(conditions[i], false);
            dfs(i + 1, conditions, map, combinations);
            map.remove(conditions[i]);

            map.put(conditions[i], true);
            dfs(i + 1, conditions, map, combinations);
            map.remove(conditions[i]);
        }
    }

    public boolean evaluateCondition(String expression, Map<Character, Boolean> map) {
        return helper(expression.toCharArray(), 0, expression.length() - 1, map);
    }

    public boolean helper(char[] arr, int start, int end, Map<Character, Boolean> map) {
        if (start == end) {
            return map.get(arr[start]);
        }
        char op = arr[start];
        boolean result = op != '|';
        int count = 0;
        for (int i = start + 1, pre = 2; i <= end; i++) {
            char c = arr[i];
            if (c == '(') {
                count++;
            } else if (c == ')') {
                count--;
            }
            if (c == ',' && count == 1 || c == ')' && count == 0) {
                boolean next = helper(arr, start + pre, i - 1, map);
                pre = i - start + 1;
                if (op == '|') {
                    result |= next;
                } else if (op == '&') {
                    result &= next;
                } else {
                    result = !next;
                }
            }
        }
        return result;
    }
}
