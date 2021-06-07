package johnny.problem;

import java.util.List;

public class SimpleConditionGeneratorExample2 {
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
        SimpleCondition condition1 = new SimpleCondition(DimensionType.ORG_PARAMETERS, "scc");
        SimpleCondition condition2 = new SimpleCondition(DimensionType.PERMISSIONS, "Buyer_Forecast_Visibility");
        SimpleCondition condition3 = new SimpleCondition(DimensionType.PERMISSIONS, "Inventory_Collaboration_Visibility");

        List<List<String>> result = SimpleConditionGenerator2.getDimensionCombinations(List.of(condition1, condition2, condition3));
        result.forEach(System.out::println);
    }
}
