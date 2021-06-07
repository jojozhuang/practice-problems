package johnny.problem;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EvaluateBooleanExpressionExample {
  public static void main(String[] args) {
    EvaluateBooleanExpression ebe = new EvaluateBooleanExpression();
    List<Map<Character, Boolean>> list = ebe.getCombinations("abc");
    list.forEach(System.out::println);
    List<Boolean> expected = new ArrayList<>();
    for (Map<Character, Boolean> map : list) {
      expected.add(ebe.evaluateCondition("&(a,b,c)", map));
    }
    System.out.println(expected);
    expected.clear();
    for (Map<Character, Boolean> map : list) {
      expected.add(ebe.evaluateCondition("|(a,b,c)", map));
    }
    System.out.println(expected);
    expected.clear();
    for (Map<Character, Boolean> map : list) {
      expected.add(ebe.evaluateCondition("&(a,|(b,c))", map));
    }
    System.out.println(expected);
  }
}
