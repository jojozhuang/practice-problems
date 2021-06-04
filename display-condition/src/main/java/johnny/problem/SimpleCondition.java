package johnny.problem;

public class SimpleCondition {
  private AccessDimension accessDimension;
  private String key;
  private Object value;

  public SimpleCondition(AccessDimension accessDimension, String key) {
    this.accessDimension = accessDimension;
    this.key = key;
  }

  public SimpleCondition(AccessDimension accessDimension, String key, Object value) {
    this.accessDimension = accessDimension;
    this.key = key;
    this.value = value;
  }

  public AccessDimension getAccessDimension() {
    return this.accessDimension;
  }

  public String getKey() {
    return this.key;
  }

  public Object getValue() {
    return this.value;
  }

  @Override
  public String toString() {
    return this.key + '_' + this.value;
  }
}
