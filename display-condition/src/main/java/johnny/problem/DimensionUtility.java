package johnny.problem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DimensionUtility {
  public static Map<String, Object> getPositiveDimension(DimensionType ad, String key) {
    return getPositiveDimensionsForAnd(ad, List.of(key));
  }

  public static Map<String, Object> getNegativeDimension(DimensionType ad, String key) {
    return getNegativeDimensionsForAnd(ad, List.of(key));
  }

  public static Map<String, Object> getPositiveDimensionsForAnd(DimensionType ad, List<String> keys) {
    return getDimensionsForAnd(ad, keys, true);
  }

  public static Map<String, Object> getNegativeDimensionsForAnd(DimensionType ad, List<String> keys) {
    return getDimensionsForAnd(ad, keys, false);
  }

  public static Map<String, Object> getDimensionsForAnd(DimensionType ad, List<String> keys, Object value) {
    Map<String, Object> dimensions = getEmptyDimensions();
    dimensions.put(ad.getDimensionKey(), buildDimensionList(keys, value));
    return dimensions;
  }

  public static List<Map<String, Object>> getPositiveDimensionsForOr(DimensionType ad, List<String> keys) {
    List<Map<String, Object>> dimensionList = new ArrayList<>();
    for (String key : keys) {
      Map<String, Object> dimensions = getEmptyDimensions();
      dimensions.put(ad.getDimensionKey(),  buildDimensionValue(key, true));
      dimensionList.add(dimensions);
    }
    return dimensionList;
  }

  public static Map<String, Object> getEmptyDimensions() {
    Map<String, Object> dimensions = new HashMap<>();
    for (DimensionType ad : DimensionType.values()) {
      dimensions.put(ad.getDimensionKey(), Collections.emptyMap());
    }

    return dimensions;
  }

  public static Map<String, Object> buildSingleDimension(DimensionType ac, List<String> keys, Object value) {
    Map<String, Object> dimensions = new HashMap<>();
    Map<String, Object> dimension = new HashMap<>();
    keys.forEach(key -> dimension.put(key, value));
    dimensions.put(ac.getDimensionKey(), dimension);
    return dimensions;
  }

  public static Map<String, Object> buildDimensionList(List<String> keys, Object value) {
    Map<String, Object> dimension = new HashMap<>();
    keys.forEach(key -> dimension.put(key, value));
    return dimension;
  }

  public static Map<String, Object> buildDimensionValue(String key, Object value) {
    Map<String, Object> dimension = new HashMap<>();
    dimension.put(key, value);

    return dimension;
  }

  public static SimpleCondition getOrgParameterCondition(String key) {
    return new SimpleCondition(DimensionType.ORG_PARAMETERS, key);
  }

  public static SimpleCondition getSimpleCondition(DimensionType accessDimension, String key) {
    return new SimpleCondition(accessDimension, key);
  }

  public static List<Map<String, Map<String, Object>>> buildDimension(List<List<SimpleCondition>> singleDimensions) {
    List<Map<String, Map<String, Object>>> dimensionList = new ArrayList<>();
    for (List<SimpleCondition> list : singleDimensions) {
      Map<String, Map<String, Object>> dimensions = getEmptyDimensionsMap();
      for (SimpleCondition item : list) {
        if (item.getValue() != null) {
          Map<String, Object> single = dimensions.get(item.getDimensionType().getDimensionKey());
          single.put(item.getKey(), item.getValue());
        }
      }
      dimensionList.add(dimensions);
    }
    return dimensionList;
  }

  public static Map<String, Map<String, Object>> getEmptyDimensionsMap() {
    Map<String, Map<String, Object>> dimensions = new HashMap<>();
    for (DimensionType ad : DimensionType.values()) {
      dimensions.put(ad.getDimensionKey(), new HashMap<>());
    }

    return dimensions;
  }
}
