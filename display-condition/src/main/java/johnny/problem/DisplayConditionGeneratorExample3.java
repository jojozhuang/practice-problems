package johnny.problem;

import java.util.List;
import java.util.Map;

public class DisplayConditionGeneratorExample3 {
    public static void main(String[] args) {
        System.out.println("Create 2 dimensions with 1 conditions");
        /*
        [scc_true, Buyer_Forecast_Visibility_true, Inventory_Collaboration_Visibility_true]
        [scc_true, Buyer_Forecast_Visibility_true, Inventory_Collaboration_Visibility_null]
        [scc_true, Buyer_Forecast_Visibility_null, Inventory_Collaboration_Visibility_true]
        [scc_true, Buyer_Forecast_Visibility_null, Inventory_Collaboration_Visibility_null]
        [scc_false, Buyer_Forecast_Visibility_true, Inventory_Collaboration_Visibility_true]
        [scc_false, Buyer_Forecast_Visibility_true, Inventory_Collaboration_Visibility_null]
        [scc_false, Buyer_Forecast_Visibility_null, Inventory_Collaboration_Visibility_true]
        [scc_false, Buyer_Forecast_Visibility_null, Inventory_Collaboration_Visibility_null]
        */
        SimpleCondition condition1 = new SimpleCondition(AccessDimension.ORG_PARAMETERS, "scc");
        SimpleCondition condition2 = new SimpleCondition(AccessDimension.PERMISSIONS, "Buyer_Forecast_Visibility");
        SimpleCondition condition3 = new SimpleCondition(AccessDimension.PERMISSIONS, "Inventory_Collaboration_Visibility");

        List<List<SingleDimension>> result = DisplayConditionGenerator3.combine(new SimpleCondition[]{condition1, condition2, condition3});
        result.forEach(System.out::println);

        /*
        {serviceSubscriptions={}, orgTypes={}, orgParameters={scc=true}, permissions={Inventory_Collaboration_Visibility=true, Buyer_Forecast_Visibility=true}, orgEntitlements={}, userTypes={}, featureToggles={}}
        {serviceSubscriptions={}, orgTypes={}, orgParameters={scc=true}, permissions={Buyer_Forecast_Visibility=true}, orgEntitlements={}, userTypes={}, featureToggles={}}
        {serviceSubscriptions={}, orgTypes={}, orgParameters={scc=true}, permissions={Inventory_Collaboration_Visibility=true}, orgEntitlements={}, userTypes={}, featureToggles={}}
        {serviceSubscriptions={}, orgTypes={}, orgParameters={scc=true}, permissions={}, orgEntitlements={}, userTypes={}, featureToggles={}}
        {serviceSubscriptions={}, orgTypes={}, orgParameters={scc=false}, permissions={Inventory_Collaboration_Visibility=true, Buyer_Forecast_Visibility=true}, orgEntitlements={}, userTypes={}, featureToggles={}}
        {serviceSubscriptions={}, orgTypes={}, orgParameters={scc=false}, permissions={Buyer_Forecast_Visibility=true}, orgEntitlements={}, userTypes={}, featureToggles={}}
        {serviceSubscriptions={}, orgTypes={}, orgParameters={scc=false}, permissions={Inventory_Collaboration_Visibility=true}, orgEntitlements={}, userTypes={}, featureToggles={}}
        {serviceSubscriptions={}, orgTypes={}, orgParameters={scc=false}, permissions={}, orgEntitlements={}, userTypes={}, featureToggles={}}
        */
        List<Map<String, Object>> dimensionList = DimensionMockUtility2.buildDimension(result);
        dimensionList.forEach(System.out::println);
    }
}
