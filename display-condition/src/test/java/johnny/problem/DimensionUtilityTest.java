package johnny.problem;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DimensionUtilityTest {

  @Test
  public void testOneCondition() {
    List<Object> expectedBoolean = List.of(false, true);
    List<Object> expectedEmpty = new ArrayList<>();
    expectedEmpty.add(null);
    expectedEmpty.add(true);
    List<Object> expectedString = List.of("false", "true");

    verifyOneCondition(DimensionType.ORG_PARAMETERS, "scc", expectedBoolean);
    verifyOneCondition(DimensionType.FEATURE_TOGGLES, "SCC_CAPACITY_COLLABORATION", expectedEmpty);
    verifyOneCondition(DimensionType.PERMISSIONS, "OrderManagement_InboxAccess", expectedEmpty);
    verifyOneCondition(DimensionType.USER_TYPES, "external_user", expectedEmpty);
    verifyOneCondition(DimensionType.ORG_ENTITLEMENTS, "csc.allowQualityCollaboration", expectedString);
  }

  private void verifyOneCondition(DimensionType type, String key, List<Object> expected) {
    SimpleCondition condition = new SimpleCondition(type, key);
    List<List<SimpleCondition>> result = DimensionUtility3.getConditionCombinations(List.of(condition));
    result.forEach(System.out::println);
    assertTrue(2 == result.size());
    for (int i = 0; i < result.size(); i++) {
      List<SimpleCondition> list = result.get(i);
      assertTrue(1 == list.size());
      assertEquals(type.getDimensionKey(), list.get(0).getDimensionType().getDimensionKey());
      assertEquals(key, list.get(0).getKey());
      assertEquals(expected.get(i), list.get(0).getValue());
    }
  }


}
