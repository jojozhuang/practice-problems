package johnny.problem;

import java.util.List;
import java.util.Map;

public class DisplayConditionGeneratorExample5 {
    public static void main(String[] args) {
        DisplayCondition condition1 = new DisplayCondition(DimensionType.ORG_PARAMETERS, "scc", true);
        DisplayCondition condition2 = new DisplayCondition(DimensionType.ORG_ENTITLEMENTS, "csc.allowQualityCollaboration");
        DisplayCondition condition3 = new DisplayCondition(DimensionType.PERMISSIONS, "buyersAllowForecastCollaboration");

        List<List<DisplayCondition>> result = DisplayConditionGenerator4.getDimensionCombinations(List.of(condition1, condition2, condition3));
        result.forEach(System.out::println);

        List<Map<String, Object>> dimensionList = DimensionMockUtility2.buildDimension(result);
        dimensionList.forEach(System.out::println);

        DisplayCondition condition21 = new DisplayCondition(DimensionType.ORG_PARAMETERS, "scc", true);
        DisplayCondition condition22 = new DisplayCondition(DimensionType.ORG_ENTITLEMENTS, "csc.allowQualityCollaboration", "true");
        DisplayCondition condition23 = new DisplayCondition(DimensionType.PERMISSIONS, "buyersAllowForecastCollaboration");

        List<List<DisplayCondition>> result2 = DisplayConditionGenerator4.getDimensionCombinations(List.of(condition21, condition22, condition23));
        result2.forEach(System.out::println);

        List<Map<String, Object>> dimensionList2 = DimensionMockUtility2.buildDimension(result2);
        dimensionList2.forEach(System.out::println);
    }
}
