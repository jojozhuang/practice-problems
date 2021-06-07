package johnny.problem;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class EvaluateBooleanExpressionTest {
  @Test
  public void testOriginal() {
    EvaluateBooleanExpression ebe = new EvaluateBooleanExpression();
    assertEquals(true, ebe.evaluate("t"));
    assertEquals(false, ebe.evaluate("f"));
    assertEquals(false, ebe.evaluate("!(t)"));
    assertEquals(true, ebe.evaluate("!(f)"));
    assertEquals(false, ebe.evaluate("&(t,f)"));
    assertEquals(true, ebe.evaluate("|(f,t)"));
    assertEquals(false, ebe.evaluate("|(&(t,f,t),!(t))"));
  }

  @Test
  public void testRefined() {
    EvaluateBooleanExpression ebe = new EvaluateBooleanExpression();
    assertEquals(true, ebe.evaluate2("t"));
    assertEquals(false, ebe.evaluate2("f"));
    assertEquals(false, ebe.evaluate2("!(t)"));
    assertEquals(true, ebe.evaluate2("!(f)"));
    assertEquals(false, ebe.evaluate2("&(t,f)"));
    assertEquals(true, ebe.evaluate2("|(f,t)"));
    assertEquals(true, ebe.evaluate2("|(t,t)"));
    assertEquals(true, ebe.evaluate2("|(f,t)"));
    assertEquals(false, ebe.evaluate2("|(&(t,f,t),!(t))"));
    assertEquals(true, ebe.evaluate2("&(t,!(f),t)"));
  }

  @Test
  public void testCustom() {
    EvaluateBooleanExpression ebe = new EvaluateBooleanExpression();
    List<Map<Character, Boolean>> list = ebe.getCombinations("abc");
    List<Boolean> actual = new ArrayList<>();
    for (Map<Character, Boolean> map : list) {
      actual.add(ebe.evaluateCondition("&(a,b,c)", map));
    }
    List<Boolean> expect = List.of(false, false, false, false, false, false, false, true);
    assertEquals(expect, actual);

    actual.clear();
    for (Map<Character, Boolean> map : list) {
      actual.add(ebe.evaluateCondition("|(a,b,c)", map));
    }
    expect = List.of(false, true, true, true, true, true, true, true);
    assertEquals(expect, actual);

    actual.clear();
    for (Map<Character, Boolean> map : list) {
      actual.add(ebe.evaluateCondition("&(a,|(b,c))", map));
    }
    expect = List.of(false, false, false, false, false, true, true, true);
    assertEquals(expect, actual);
  }
}
