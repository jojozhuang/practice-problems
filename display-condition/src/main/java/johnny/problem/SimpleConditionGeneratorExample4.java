package johnny.problem;

import java.util.List;
import java.util.Map;

public class SimpleConditionGeneratorExample4 {
    public static void main(String[] args) {
        System.out.println("Create 2 dimensions with 1 conditions");
        /*
        [scc_true, Buyer_Forecast_Visibility_true, buyersAllowForecastCollaboration_null, buyersAllowForecastView_null]
        [scc_true, Buyer_Forecast_Visibility_true, buyersAllowForecastCollaboration_null, buyersAllowForecastView_true]
        [scc_true, Buyer_Forecast_Visibility_true, buyersAllowForecastCollaboration_true, buyersAllowForecastView_null]
        [scc_true, Buyer_Forecast_Visibility_true, buyersAllowForecastCollaboration_true, buyersAllowForecastView_true]
        */
        SimpleCondition condition1 = new SimpleCondition(DimensionType.ORG_PARAMETERS, "scc", true);
        SimpleCondition condition2 = new SimpleCondition(DimensionType.PERMISSIONS, "Buyer_Forecast_Visibility", true);
        SimpleCondition condition3 = new SimpleCondition(DimensionType.PERMISSIONS, "buyersAllowForecastCollaboration");
        SimpleCondition condition4 = new SimpleCondition(DimensionType.PERMISSIONS, "buyersAllowForecastView");

        List<List<SimpleCondition>> result = SimpleConditionGenerator4.getDimensionCombinations(List.of(condition1, condition2, condition3, condition4));
        result.forEach(System.out::println);

        /*
        {serviceSubscriptions={}, orgTypes={}, orgParameters={scc=true}, permissions={Buyer_Forecast_Visibility=true}, orgEntitlements={}, userTypes={}, featureToggles={}}
        {serviceSubscriptions={}, orgTypes={}, orgParameters={scc=true}, permissions={Buyer_Forecast_Visibility=true, buyersAllowForecastView=true}, orgEntitlements={}, userTypes={}, featureToggles={}}
        {serviceSubscriptions={}, orgTypes={}, orgParameters={scc=true}, permissions={Buyer_Forecast_Visibility=true, buyersAllowForecastCollaboration=true}, orgEntitlements={}, userTypes={}, featureToggles={}}
        {serviceSubscriptions={}, orgTypes={}, orgParameters={scc=true}, permissions={Buyer_Forecast_Visibility=true, buyersAllowForecastCollaboration=true, buyersAllowForecastView=true}, orgEntitlements={}, userTypes={}, featureToggles={}}
        */
        List<Map<String, Object>> dimensionList = DimensionUtility2.buildDimension(result);
        dimensionList.forEach(System.out::println);

        List<Boolean> expected = DimensionUtility2.getExpected("000111");
        System.out.println(expected);

        List<Boolean> expected2 = DimensionUtility2.getPositiveExpected(8);
        System.out.println(expected2);

        List<Boolean> expected3 = DimensionUtility2.getNegativeExpected(8);
        System.out.println(expected3);
    }
}
