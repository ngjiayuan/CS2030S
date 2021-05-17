/**
 * @author Ng Jia Yuan (Group 08L)
 */
public class DivisibleBy implements BooleanCondition<Integer> {
  private int number;

  public DivisibleBy(int number) {
    this.number = number;
  }

  public boolean test(Integer num) {
    return (number % num == 0) || (num % number == 0);
  }
}
