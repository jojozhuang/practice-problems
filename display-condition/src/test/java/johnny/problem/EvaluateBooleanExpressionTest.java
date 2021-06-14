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
    assertEquals(false, ebe.evaluate2("&(t,f,t)"));
    assertEquals(false, ebe.evaluate2("&(t,&(t,f),t)"));
    assertEquals(true, ebe.evaluate2("|(f,t)"));
    assertEquals(true, ebe.evaluate2("|(t,t)"));
    assertEquals(true, ebe.evaluate2("|(f,t)"));
    assertEquals(false, ebe.evaluate2("|(&(t,f,t),!(t))"));
    assertEquals(true, ebe.evaluate2("&(t,!(f),t)"));
  }

  @Test
  public void testValidation() {
    EvaluateBooleanExpression ebe = new EvaluateBooleanExpression();
    assertEquals(true, ebe.validateExpression("a"));
    assertEquals(true, ebe.validateExpression("!(a)"));
    assertEquals(true, ebe.validateExpression("&(a,b)"));
    assertEquals(true, ebe.validateExpression("&(a,b,c)"));
    assertEquals(true, ebe.validateExpression("&(c,d)"));
    assertEquals(true, ebe.validateExpression("&(a,a)"));
    assertEquals(true, ebe.validateExpression("&(a,a,a,a)"));
    assertEquals(true, ebe.validateExpression("&(a,c,q,z)"));
    assertEquals(true, ebe.validateExpression("&(a,&(a,&(b,&(d,e))))"));
    assertEquals(true, ebe.validateExpression("|(a,b)"));
    assertEquals(true, ebe.validateExpression("|(a,b,c)"));
    assertEquals(true, ebe.validateExpression("|(c,d)"));
    assertEquals(true, ebe.validateExpression("|(a,a)"));
    assertEquals(true, ebe.validateExpression("|(a,a,a,a)"));
    assertEquals(true, ebe.validateExpression("|(a,c,q,z)"));
    assertEquals(true, ebe.validateExpression("|(a,|(b,c))"));
    assertEquals(true, ebe.validateExpression("|(|(|(|(e,d),c),b),a)"));
    assertEquals(true, ebe.validateExpression("&(a,!(b),c)"));
    assertEquals(true, ebe.validateExpression("&(a,!(a),a)"));
    assertEquals(true, ebe.validateExpression("&(a,|(b,c))"));
    assertEquals(true, ebe.validateExpression("|(&(a,b,a),!(a))"));
    assertEquals(true, ebe.validateExpression("&(!(a),|(b,c,d,e))"));
    assertEquals(true, ebe.validateExpression("|(|(a,b,c,d),|(e,!(f)))"));
    assertEquals(true, ebe.validateExpression("&(|(!(a),&(b,c)),|(d,e))"));
    assertEquals(false, ebe.validateExpression("("));
    assertEquals(false, ebe.validateExpression(")"));
    assertEquals(false, ebe.validateExpression("!"));
    assertEquals(false, ebe.validateExpression("&"));
    assertEquals(false, ebe.validateExpression(","));
    assertEquals(false, ebe.validateExpression("()"));
    assertEquals(false, ebe.validateExpression("(,)"));
    assertEquals(false, ebe.validateExpression("(a,)"));
    assertEquals(false, ebe.validateExpression("(,a)"));
    assertEquals(false, ebe.validateExpression("(,a,b)"));
    assertEquals(false, ebe.validateExpression("(a,b)"));
    assertEquals(false, ebe.validateExpression("!a"));
    assertEquals(false, ebe.validateExpression("!(a"));
    assertEquals(false, ebe.validateExpression("!a)"));
    assertEquals(false, ebe.validateExpression("!()"));
    assertEquals(false, ebe.validateExpression("!(,)"));
    assertEquals(false, ebe.validateExpression("!(a,)"));
    assertEquals(false, ebe.validateExpression("!(a,b)"));
    assertEquals(false, ebe.validateExpression("&a"));
    assertEquals(false, ebe.validateExpression("&(a"));
    assertEquals(false, ebe.validateExpression("&()"));
    assertEquals(false, ebe.validateExpression("&(,)"));
    assertEquals(false, ebe.validateExpression("&(a,)"));
    assertEquals(false, ebe.validateExpression("&(,a)"));
    assertEquals(false, ebe.validateExpression("&(a,,b)"));
    assertEquals(false, ebe.validateExpression("&(ab)"));
    assertEquals(false, ebe.validateExpression("&()a"));
    assertEquals(false, ebe.validateExpression("|a"));
    assertEquals(false, ebe.validateExpression("|(a"));
    assertEquals(false, ebe.validateExpression("|()"));
    assertEquals(false, ebe.validateExpression("|(,)"));
    assertEquals(false, ebe.validateExpression("|(a,)"));
    assertEquals(false, ebe.validateExpression("|(,a)"));
    assertEquals(false, ebe.validateExpression("|(a,,b)"));
    assertEquals(false, ebe.validateExpression("|(ab)"));
    assertEquals(false, ebe.validateExpression("|()a"));
    assertEquals(false, ebe.validateExpression("a()"));
    assertEquals(false, ebe.validateExpression("&a()"));
    assertEquals(false, ebe.validateExpression("|a()"));
    assertEquals(false, ebe.validateExpression(",a()"));
    assertEquals(false, ebe.validateExpression("a,()"));
    assertEquals(false, ebe.validateExpression("a&b"));
    assertEquals(false, ebe.validateExpression("(a&b)"));
    assertEquals(false, ebe.validateExpression("a|b"));
    assertEquals(false, ebe.validateExpression("(a|b)"));
    assertEquals(false, ebe.validateExpression("|(a,b,c,)"));
    assertEquals(false, ebe.validateExpression("&(a,c&(a,b))"));
    assertEquals(false, ebe.validateExpression("!(|(a,b))),&(a,c)"));
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

  @Test
  public void testExpected() {
    EvaluateBooleanExpression ebe = new EvaluateBooleanExpression();
    List<Boolean> actual = ebe.getExpected("a");
    List<Boolean> expected = List.of(false, true);
    assertEquals(expected, actual);

    actual = ebe.getExpected("!(a)");
    expected = List.of(true, false);
    assertEquals(expected, actual);

    actual = ebe.getExpected("&(a,b)");
    expected = List.of(false, false, false, true);
    assertEquals(expected, actual);

    actual = ebe.getExpected("!(&(a,b))");
    expected = List.of(true, true, true, false);
    assertEquals(expected, actual);

    actual = ebe.getExpected("|(a,b)");
    expected = List.of(false, true, true, true);
    assertEquals(expected, actual);

    actual = ebe.getExpected("!(|(a,b))");
    expected = List.of(true, false, false, false);
    assertEquals(expected, actual);

    actual = ebe.getExpected("&(a,b,c)");
    expected = List.of(false, false, false, false, false, false, false, true);
    assertEquals(expected, actual);

    actual = ebe.getExpected("|(a,b,c)");
    expected = List.of(false, true, true, true, true, true, true, true);
    assertEquals(expected, actual);

    actual = ebe.getExpected( "&(a,|(b,c))");
    expected = List.of(false, false, false, false, false, true, true, true);
    assertEquals(expected, actual);

    actual = ebe.getExpected( "&(|(!(a),!(b)),&(c,d))");
    expected = List.of(false, false, false, true, false, false, false, true, false, false, false, true, false, false, false, false);
    assertEquals(expected, actual);


  }

  @Test
  public void testUniqueDimensions() {
    EvaluateBooleanExpression ebe = new EvaluateBooleanExpression();
    assertEquals("a", ebe.getUniqueDimension("a"));
    assertEquals("abc", ebe.getUniqueDimension("&(a,b,c)"));
    assertEquals("abc", ebe.getUniqueDimension("|(a,b,c)"));
    assertEquals("abc", ebe.getUniqueDimension("&(a,|(b,c))"));
    assertEquals("tf", ebe.getUniqueDimension("|(&(t,f,t),!(t))"));
    assertEquals("abct", ebe.getUniqueDimension("|(&(a,b,c),!(t))"));
  }
}
