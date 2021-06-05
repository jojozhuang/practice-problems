package johnny.problem;

public class DisplayCondition {
  private DimensionType dimensionType;
  private String key;
  private Object value;

  public DisplayCondition(DimensionType accessDimension, String key) {
    this.dimensionType = accessDimension;
    this.key = key;
  }

  public DisplayCondition(DimensionType accessDimension, String key, Object value) {
    this.dimensionType = accessDimension;
    this.key = key;
    this.value = value;
  }

  public DimensionType getDimensionType() {
    return this.dimensionType;
  }

  public String getKey() {
    return this.key;
  }

  public Object getValue() {
    return this.value;
  }

  @Override
  public String toString() {
    if (this.value instanceof String) {
      return this.key + "_'" + this.value + "'";
    } else {
      return this.key + '_' + this.value;
    }
  }
}
