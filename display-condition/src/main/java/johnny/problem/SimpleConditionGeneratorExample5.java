package johnny.problem;

import java.util.List;
import java.util.Map;

public class SimpleConditionGeneratorExample5 {
    public static void main(String[] args) {
        SimpleCondition condition1 = new SimpleCondition(DimensionType.ORG_PARAMETERS, "scc", true);
        SimpleCondition condition2 = new SimpleCondition(DimensionType.ORG_ENTITLEMENTS, "csc.allowQualityCollaboration", false, "true");
        SimpleCondition condition3 = new SimpleCondition(DimensionType.PERMISSIONS, "buyersAllowForecastCollaboration");

        /*
        [scc_'true', csc.allowQualityCollaboration_'false', buyersAllowForecastCollaboration_'null']
        [scc_'true', csc.allowQualityCollaboration_'false', buyersAllowForecastCollaboration_'true']
        [scc_'true', csc.allowQualityCollaboration_'true', buyersAllowForecastCollaboration_'null']
        [scc_'true', csc.allowQualityCollaboration_'true', buyersAllowForecastCollaboration_'true']
         */
        List<List<SimpleCondition>> result = SimpleConditionGenerator4.getDimensionCombinations(List.of(condition1, condition2, condition3));
        result.forEach(System.out::println);

        /*
        {serviceSubscriptions={}, orgTypes={}, orgParameters={scc=true}, permissions={}, orgEntitlements={csc.allowQualityCollaboration=false}, userTypes={}, featureToggles={}}
        {serviceSubscriptions={}, orgTypes={}, orgParameters={scc=true}, permissions={buyersAllowForecastCollaboration=true}, orgEntitlements={csc.allowQualityCollaboration=false}, userTypes={}, featureToggles={}}
        {serviceSubscriptions={}, orgTypes={}, orgParameters={scc=true}, permissions={}, orgEntitlements={csc.allowQualityCollaboration=true}, userTypes={}, featureToggles={}}
        {serviceSubscriptions={}, orgTypes={}, orgParameters={scc=true}, permissions={buyersAllowForecastCollaboration=true}, orgEntitlements={csc.allowQualityCollaboration=true}, userTypes={}, featureToggles={}}
         */
        List<Map<String, Object>> dimensionList = DimensionUtility2.buildDimension(result);
        dimensionList.forEach(System.out::println);

        SimpleCondition condition21 = new SimpleCondition(DimensionType.ORG_PARAMETERS, "scc", true);
        SimpleCondition condition22 = new SimpleCondition(DimensionType.ORG_ENTITLEMENTS, "csc.allowQualityCollaboration", true, "true");
        SimpleCondition condition23 = new SimpleCondition(DimensionType.PERMISSIONS, "buyersAllowForecastCollaboration");

        /*
        [scc_'true', csc.allowQualityCollaboration_'true', buyersAllowForecastCollaboration_'null']
        [scc_'true', csc.allowQualityCollaboration_'true', buyersAllowForecastCollaboration_'true']
         */
        List<List<SimpleCondition>> result2 = SimpleConditionGenerator4.getDimensionCombinations(List.of(condition21, condition22, condition23));
        result2.forEach(System.out::println);

        /*
        {serviceSubscriptions={}, orgTypes={}, orgParameters={scc=true}, permissions={}, orgEntitlements={csc.allowQualityCollaboration=true}, userTypes={}, featureToggles={}}
        {serviceSubscriptions={}, orgTypes={}, orgParameters={scc=true}, permissions={buyersAllowForecastCollaboration=true}, orgEntitlements={csc.allowQualityCollaboration=true}, userTypes={}, featureToggles={}}
         */
        List<Map<String, Object>> dimensionList2 = DimensionUtility2.buildDimension(result2);
        dimensionList2.forEach(System.out::println);
    }
}
