package johnny.problem;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;

public class EvaluateBooleanExpression {
    private static final String VALID_CHARS = ",()!&|";
    private static final String VALID_DIMENSIONS = "abcdefghijklmnopqrstuvwxyz";
    private static final Set<Character> LETTERS_SET;
    private static final Set<Character> WHITE_SET;

    static {
        List<Character> letters = VALID_DIMENSIONS
                .chars()
                .mapToObj(ch -> (char) ch)
                .collect(Collectors.toList());
        List<Character> validChars = VALID_CHARS.concat(VALID_DIMENSIONS)
                .chars()
                .mapToObj(ch -> (char) ch)
                .collect(Collectors.toList());
        LETTERS_SET = new HashSet<>(letters);
        WHITE_SET = new HashSet<>(validChars);
    }

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
            if (op == '&' && result == false ||
                op == '|' && result == true) {
                break;
            }
        }
        return result;
    }

    public boolean validateExpression(String expression) {
        if (expression == null || expression.isEmpty()) {
            throw new IllegalArgumentException(
                    String.format("Expression %s is empty.", expression));
        }

        final int even = 2;
        Deque<Character> deque = new ArrayDeque<>();
        char[] arr = expression.toCharArray();
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            char ch = arr[i];
            if (!WHITE_SET.contains(ch)) {
                throw new IllegalArgumentException(
                        String.format("Expression %s contains invalid characters, must be [%s] or [%s]",
                                expression, VALID_DIMENSIONS, VALID_CHARS));
            }
            if (ch == '(') {
                if (deque.isEmpty()) {
                    return false;
                } else {
                    char top = deque.peek();
                    if (top != '!' && top != '&' && top != '|') {
                        return false;
                    }
                }
                count++;
                deque.push(ch);
            } else if (ch == ')') {
                if (count < 1) {
                    return false;
                }
                List<Character> conditionList = new ArrayList<>();
                while (deque.peek() != '(') {
                    conditionList.add(deque.pop());
                }
                deque.pop(); // left parenthesis
                char op = deque.pop();
                if (op == '!' && (conditionList.size() != 1 || !LETTERS_SET.contains(conditionList.get(0)))) {
                    return false;
                }
                if (conditionList.size() % even == 0) {
                    return false;
                }
                for (int j = 0; j < conditionList.size(); j++) {
                    if (j % even == 0 && !LETTERS_SET.contains(conditionList.get(j))) {
                        return false;
                    } else if (j % even != 0 && conditionList.get(j) != ',') {
                        return false;
                    }
                }
                deque.push('a');
                count--;
            } else {
                deque.push(ch);
            }
        }

        return deque.size() == 1 && LETTERS_SET.contains(deque.peek());
    }

    public List<Boolean> getExpected(String expression) {
        List<Map<Character, Boolean>> combinations = getCombinations(getUniqueDimension(expression));
        List<Boolean> expected = new ArrayList<>();
        for (Map<Character, Boolean> map : combinations) {
            expected.add(evaluateCondition(expression, map));
        }
        return expected;
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

    public String getUniqueDimension(String expression) {
        List<Character> list = List.of('(', ')', ',', '!', '|', '&');
        Set<Character> blackList = new HashSet<>(list);
        Set<Character> set = new HashSet<>();
        for (char c : expression.toCharArray()) {
            if (!blackList.contains(c)) {
                set.add(c);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (Character c : set) {
            sb.append(c);
        }

        return sb.toString();
    }
}
