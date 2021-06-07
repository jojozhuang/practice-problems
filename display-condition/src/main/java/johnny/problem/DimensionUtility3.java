package johnny.problem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DimensionUtility3 {
  public static List<Map<String, Object>> buildDimension(List<SimpleCondition> conditions) {
    List<List<SimpleCondition>> conditionCombinations = getConditionCombinations(conditions);
    List<Map<String, Object>> dimensionList = new ArrayList<>();
    for (List<SimpleCondition> list : conditionCombinations) {
      Map<String, Object> dimensions = getEmptyDimensions();
      for (SimpleCondition sc : list) {
        if (sc.getValue() != null) {
          Object single = dimensions.get(sc.getDimensionType().getDimensionKey());
          Map<String, Object> map = (Map<String, Object>) single;
          map.put(sc.getKey(), sc.getValue());
        }
      }
      dimensionList.add(dimensions);
    }
    return dimensionList;
  }

  public static Map<String, Object> getEmptyDimensions() {
    Map<String, Object> dimensions = new HashMap<>();
    for (DimensionType ad : DimensionType.values()) {
      dimensions.put(ad.getDimensionKey(), new HashMap<>());
    }

    return dimensions;
  }

  public static List<List<SimpleCondition>> getConditionCombinations(
          List<SimpleCondition> conditions) {
    List<List<SimpleCondition>> combination = new ArrayList<>();

    List<SimpleCondition> list = new ArrayList<>();
    dfs(0, conditions, list, combination);

    return combination;
  }

  private static void dfs(int pos,
                          List<SimpleCondition> conditions,
                          List<SimpleCondition> list,
                          List<List<SimpleCondition>> combination) {
    if (list.size() == conditions.size()) {
      combination.add(new ArrayList<>(list));
      return;
    }

    for (int i = pos; i < conditions.size(); i++) {
      SimpleCondition condition = conditions.get(i);
      if (!condition.getPositiveOnly()
              && (condition.getValue() == null
              || condition.getDimensionType().getNegativeType() == ValueType.STRING)) {
        list.add(new SimpleCondition(condition.getDimensionType(), condition.getKey(), false,
                condition.getDimensionType().getNegativeValue()));
        dfs(i + 1, conditions, list, combination);
        list.remove(list.size() - 1);
      }

      if (condition.getValue() == null) {
        list.add(new SimpleCondition(condition.getDimensionType(), condition.getKey(), false,
                condition.getDimensionType().getPositiveValue()));
      } else {
        list.add(new SimpleCondition(condition.getDimensionType(), condition.getKey(), false,
                condition.getValue()));
      }
      dfs(i + 1, conditions, list, combination);
      list.remove(list.size() - 1);
    }
  }

  public static Map<String, Object> getPositiveDimensionsForAnd(
          List<SimpleCondition> conditions,
          List<SimpleCondition> defaultConditions) {
    return getDimensionsForAnd(conditions, defaultConditions, true);
  }

  public static Map<String, Object> getNegativeDimensionsForAnd(
          List<SimpleCondition> conditions,
          List<SimpleCondition> defaultConditions) {
    return getDimensionsForAnd(conditions, defaultConditions, false);
  }

  public static Map<String, Object> getDimensionsForAnd(List<SimpleCondition> conditions,
                                                        List<SimpleCondition> defaultConditions,
                                                        boolean positive) {
    Map<String, Object> defaultDimensions = getEmptyDimensions();
    for (SimpleCondition sc : defaultConditions) {
      Object single = defaultDimensions.get(sc.getDimensionType().getDimensionKey());
      Map<String, Object> map = (Map<String, Object>) single;
      if (sc.getPositiveOnly()) {
        map.put(sc.getKey(), sc.getDimensionType().getPositiveValue());
      }
    }

    for (SimpleCondition sc : conditions) {
      Object single = defaultDimensions.get(sc.getDimensionType().getDimensionKey());
      Map<String, Object> map = (Map<String, Object>) single;
      if (positive && sc.getDimensionType().getPositiveValue() != null) {
        map.put(sc.getKey(), sc.getDimensionType().getPositiveValue());
      }
      if (!positive && sc.getDimensionType().getNegativeValue() != null) {
        map.put(sc.getKey(), sc.getDimensionType().getNegativeValue());
      }
    }
    return defaultDimensions;
  }

  public static List<Map<String, Object>> getNegativeDimensionsForOr(
          List<SimpleCondition> conditions,
          List<SimpleCondition> defaultConditions) {
    return getDimensionsForOr(conditions, defaultConditions, false);
  }

  public static List<Map<String, Object>> getPositiveDimensionsForOr(
          List<SimpleCondition> conditions,
          List<SimpleCondition> defaultConditions) {
    return getDimensionsForOr(conditions, defaultConditions, true);
  }

  public static List<Map<String, Object>> getDimensionsForOr(
          List<SimpleCondition> conditions,
          List<SimpleCondition> defaultConditions,
          boolean positive) {
    List<Map<String, Object>> dimensionList = new ArrayList<>();

    Map<String, Object> defaultDimensions = getEmptyDimensions();
    for (SimpleCondition sc : defaultConditions) {
      Object single = defaultDimensions.get(sc.getDimensionType().getDimensionKey());
      Map<String, Object> map = (Map<String, Object>) single;
      if (sc.getPositiveOnly()) {
        map.put(sc.getKey(), sc.getDimensionType().getPositiveValue());
      }
    }

    for (SimpleCondition sc : conditions) {
      Map<String, Object> dimensions = new HashMap<>(defaultDimensions);
      Object single = dimensions.get(sc.getDimensionType().getDimensionKey());
      Map<String, Object> map = (Map<String, Object>) single;
      if (positive) {
        map.put(sc.getKey(), sc.getDimensionType().getPositiveValue());
      } else {
        if (sc.getDimensionType().getNegativeValue() != null) {
          map.put(sc.getKey(), sc.getDimensionType().getNegativeValue());
        }
      }
      dimensionList.add(dimensions);
    }
    return dimensionList;
  }
}
