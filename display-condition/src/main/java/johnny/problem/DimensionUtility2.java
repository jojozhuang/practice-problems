package johnny.problem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DimensionUtility2 {
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

  public static List<Map<String, Object>> buildDimension(List<List<SimpleCondition>> singleDimensions) {
    List<Map<String, Object>> dimensionList = new ArrayList<>();
    for (List<SimpleCondition> list : singleDimensions) {
      Map<String, Object> dimensions = getEmptyDimensionsMap();
      for (SimpleCondition item : list) {
        if (item.getValue() != null) {
          Object single = dimensions.get(item.getDimensionType().getDimensionKey());
          Map<String, Object> map = (Map<String, Object>) single;
          map.put(item.getKey(), item.getValue());
        }
      }
      dimensionList.add(dimensions);
    }
    return dimensionList;
  }

  public static Map<String, Object> getEmptyDimensionsMap() {
    Map<String, Object> dimensions = new HashMap<>();
    for (DimensionType ad : DimensionType.values()) {
      dimensions.put(ad.getDimensionKey(), new HashMap<>());
    }

    return dimensions;
  }

  public static List<Boolean> getExpected(String exp) {
    if (exp == null || exp.isEmpty()) {
      return new ArrayList<>();
    }

    return exp.chars()
            .mapToObj(c -> (c == '1' ? true: false))
            .collect(Collectors.toList());
  }

  // input: 8
  // output: [false, true, true, true, true, true, true, true]
  public static List<Boolean> getPositiveExpected(int num) {
    if ((num & (num-1)) != 0) {
      throw new IllegalArgumentException(String.format("input value %s is invalid, must be pow of 2.", num));
    }
    List<Boolean> list = new ArrayList<>();
    list.add(false);
    list.addAll(Stream.generate(() -> Boolean.TRUE).limit(num - 1).collect(Collectors.toList()));
    return list;
  }

  // input: 8
  // output: [false, false, false, false, false, false, false, true]
  public static List<Boolean> getNegativeExpected(int num) {
    if ((num & (num-1)) != 0) {
      throw new IllegalArgumentException(String.format("input value %s is invalid, must be pow of 2.", num));
    }
    List<Boolean> list = new ArrayList<>();
    list.addAll(Stream.generate(() -> Boolean.FALSE).limit(num - 1).collect(Collectors.toList()));
    list.add(true);
    return list;
  }
}
