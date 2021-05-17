/**
 * @author Ng Jia Yuan (Group 08L)
 */
public class BoxIt<T> implements Transformer<T, Box<T>> {
  public Box<T> transform(T t) {
    return Box.ofNullable(t);
  }
}
