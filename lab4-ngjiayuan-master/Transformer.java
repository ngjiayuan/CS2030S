/**
 * @author Ng Jia Yuan (08L)
 */
public interface Transformer<T, U> {
  public abstract U transform(T t);
}
