package johnny.problem;

public enum AccessDimension {
  FEATURE_TOGGLES("featureToggles", ValueType.TRUE_VALUE, ValueType.EMPTY_VALUE),
  ORG_ENTITLEMENTS("orgEntitlements", ValueType.TRUE_VALUE, ValueType.EMPTY_VALUE),
  ORG_PARAMETERS("orgParameters", ValueType.TRUE_VALUE, ValueType.FALSE_VALUE),
  ORG_TYPES("orgTypes", ValueType.TRUE_VALUE, ValueType.EMPTY_VALUE),
  PERMISSIONS("permissions", ValueType.TRUE_VALUE, ValueType.EMPTY_VALUE),
  SERVICE_SUBSCRIPTIONS("serviceSubscriptions", ValueType.TRUE_VALUE, ValueType.EMPTY_VALUE),
  USER_TYPES("userTypes", ValueType.TRUE_VALUE, ValueType.EMPTY_VALUE);

  private String name;
  private ValueType positiveType;
  private ValueType negativeType;

  AccessDimension(String name, ValueType positiveType, ValueType negativeType) {
    this.name = name;
    this.positiveType = positiveType;
    this.negativeType = negativeType;
  }

  public String getName() {
    return name;
  }

  public ValueType getPositiveType() {
    return positiveType;
  }

  public ValueType getNegativeType() {
    return negativeType;
  }

  public Object getPositiveValue() {
    switch(this.positiveType) {
      case TRUE_VALUE:
        return Boolean.TRUE;
      case STRING_VALUE:
        return "stringValue";
      default:
        return null;
    }
  }

  public Object getNegativeValue() {
    switch(this.negativeType) {
      case FALSE_VALUE:
        return Boolean.FALSE;
      case STRING_VALUE:
        return "stringValue";
      default:
        return null;
    }
  }
}
