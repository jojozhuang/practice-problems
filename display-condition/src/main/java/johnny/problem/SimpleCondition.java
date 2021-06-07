package johnny.problem;

public class SimpleCondition {
  private DimensionType dimensionType;
  private String key;
  private boolean positiveOnly;
  private Object value;

  public SimpleCondition(DimensionType dimensionType, String key) {
    this.dimensionType = dimensionType;
    this.key = key;
  }

  public SimpleCondition(DimensionType dimensionType, String key, boolean positiveOnly) {
    this.dimensionType = dimensionType;
    this.key = key;
    this.positiveOnly = positiveOnly;
  }

  public SimpleCondition(DimensionType dimensionType,
                         String key,
                         boolean positiveOnly,
                         Object value) {
    this.dimensionType = dimensionType;
    this.key = key;
    this.positiveOnly = positiveOnly;
    this.value = value;
  }

  public DimensionType getDimensionType() {
    return this.dimensionType;
  }

  public String getKey() {
    return this.key;
  }

  public boolean getPositiveOnly() {
    return this.positiveOnly;
  }

  public Object getValue() {
    return this.value;
  }

  @Override
  public String toString() {
    if (this.value instanceof String) {
      return String.format("type=%s, key=%s, value=\"%s\", positiveOnly=%s",
              this.dimensionType, this.key, this.value, this.positiveOnly);
    } else {
      return String.format("type=%s, key=%s, value=%s, positiveOnly=%s",
              this.dimensionType, this.key, this.value, this.positiveOnly);
    }
    /*
    if (this.value instanceof String) {
      return this.key + "_'" + this.value + "'";
    } else {
      return this.key + "_" + this.value;
    }*/
  }
}
