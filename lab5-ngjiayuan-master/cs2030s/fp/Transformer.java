/**
 * CS2030S Lab 5
 * AY20/21 Semester 2
 *
 * @author Ng Jia Yuan (Lab Group 08L)
 */

package cs2030s.fp;

public interface Transformer<T, U> {
  public U transform(T t);
}
