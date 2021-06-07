package johnny.problem;

public enum DimensionType {
  FEATURE_TOGGLES("featureToggles", ValueType.TRUE, ValueType.EMPTY),
  ORG_ENTITLEMENTS("orgEntitlements", ValueType.STRING, ValueType.STRING),
  ORG_PARAMETERS("orgParameters", ValueType.TRUE, ValueType.FALSE),
  ORG_TYPES("orgTypes", ValueType.TRUE, ValueType.EMPTY),
  PERMISSIONS("permissions", ValueType.TRUE, ValueType.EMPTY),
  SERVICE_SUBSCRIPTIONS("serviceSubscriptions", ValueType.TRUE, ValueType.EMPTY),
  USER_TYPES("userTypes", ValueType.TRUE, ValueType.EMPTY);

  private String dimensionKey;
  private ValueType positiveType;
  private ValueType negativeType;

  DimensionType(String dimensionKey, ValueType positiveType, ValueType negativeType) {
    this.dimensionKey = dimensionKey;
    this.positiveType = positiveType;
    this.negativeType = negativeType;
  }

  public String getDimensionKey() {
    return dimensionKey;
  }

  public ValueType getPositiveType() {
    return positiveType;
  }

  public ValueType getNegativeType() {
    return negativeType;
  }

  public Object getPositiveValue() {
    switch (this.positiveType) {
      case TRUE:
        return Boolean.TRUE;
      case STRING:
        return "true";
      default:
        return null;
    }
  }

  public Object getNegativeValue() {
    switch (this.negativeType) {
      case FALSE:
        return Boolean.FALSE;
      case STRING:
        return "false";
      default:
        return null;
    }
  }
}
