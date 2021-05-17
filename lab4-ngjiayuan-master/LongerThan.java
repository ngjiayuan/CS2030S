/**
 * @author Ng Jia Yuan (Group 08L)
 */
public class LongerThan implements BooleanCondition<String> {
  private int limit;

  public LongerThan(int limit) {
    this.limit = limit;
  }

  public boolean test(String str) {
    return str.length() > limit;
  }
}
