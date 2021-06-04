package johnny.problem;

public class SingleDimension {
  private AccessDimension accessDimension;
  private String key;
  private Object value;

  SingleDimension(AccessDimension accessDimension, String key, Object value) {
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
