/**
 * @author Ng Jia Yuan (Group 08L)
 */
public class LastDigitsOfHashCode implements Transformer<Object, Integer> {
  private int k;

  public LastDigitsOfHashCode(int k) {
    this.k = k;
  }

  // return last k digits of hashcode
  public Integer transform(Object obj) {
    return Math.abs(obj.hashCode()) % (int) Math.pow(10, k);
  }
}
