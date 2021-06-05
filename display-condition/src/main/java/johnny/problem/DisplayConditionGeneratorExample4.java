package johnny.problem;

import java.util.List;
import java.util.Map;

public class DisplayConditionGeneratorExample4 {
    public static void main(String[] args) {
        System.out.println("Create 2 dimensions with 1 conditions");
        /*
        [scc_true, Buyer_Forecast_Visibility_true, buyersAllowForecastCollaboration_null, buyersAllowForecastView_null]
        [scc_true, Buyer_Forecast_Visibility_true, buyersAllowForecastCollaboration_null, buyersAllowForecastView_true]
        [scc_true, Buyer_Forecast_Visibility_true, buyersAllowForecastCollaboration_true, buyersAllowForecastView_null]
        [scc_true, Buyer_Forecast_Visibility_true, buyersAllowForecastCollaboration_true, buyersAllowForecastView_true]
        */
        DisplayCondition condition1 = new DisplayCondition(DimensionType.ORG_PARAMETERS, "scc", true);
        DisplayCondition condition2 = new DisplayCondition(DimensionType.PERMISSIONS, "Buyer_Forecast_Visibility", true);
        DisplayCondition condition3 = new DisplayCondition(DimensionType.PERMISSIONS, "buyersAllowForecastCollaboration");
        DisplayCondition condition4 = new DisplayCondition(DimensionType.PERMISSIONS, "buyersAllowForecastView");

        List<List<DisplayCondition>> result = DisplayConditionGenerator4.getDimensionCombinations(List.of(condition1, condition2, condition3, condition4));
        result.forEach(System.out::println);

        /*
        {serviceSubscriptions={}, orgTypes={}, orgParameters={scc=true}, permissions={Buyer_Forecast_Visibility=true}, orgEntitlements={}, userTypes={}, featureToggles={}}
        {serviceSubscriptions={}, orgTypes={}, orgParameters={scc=true}, permissions={Buyer_Forecast_Visibility=true, buyersAllowForecastView=true}, orgEntitlements={}, userTypes={}, featureToggles={}}
        {serviceSubscriptions={}, orgTypes={}, orgParameters={scc=true}, permissions={Buyer_Forecast_Visibility=true, buyersAllowForecastCollaboration=true}, orgEntitlements={}, userTypes={}, featureToggles={}}
        {serviceSubscriptions={}, orgTypes={}, orgParameters={scc=true}, permissions={Buyer_Forecast_Visibility=true, buyersAllowForecastCollaboration=true, buyersAllowForecastView=true}, orgEntitlements={}, userTypes={}, featureToggles={}}
        */
        List<Map<String, Object>> dimensionList = DimensionMockUtility2.buildDimension(result);
        dimensionList.forEach(System.out::println);

        List<Boolean> expected = DimensionMockUtility2.getExpected("000111");
        System.out.println(expected);

        List<Boolean> expected2 = DimensionMockUtility2.getPositiveExpected(8);
        System.out.println(expected2);

        List<Boolean> expected3 = DimensionMockUtility2.getNegativeExpected(8);
        System.out.println(expected3);
    }
}
