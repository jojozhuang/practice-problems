package johnny.problem;

public class SimpleCondition {
  private AccessDimension accessDimension;
  private String key;

  SimpleCondition(AccessDimension accessDimension, String key) {
    this.accessDimension = accessDimension;
    this.key = key;
  }

  public AccessDimension getAccessDimension() {
    return this.accessDimension;
  }

  public String getKey() {
    return this.key;
  }
}
