package cs2030s.fp;

/**
 * Lazy class to encapsulate values.
 * 
 *
 * @author Ng Jia Yuan (Group 08L)
 *
 * @param <T> The type of the value to be encapsulated
 */
public class Lazy<T> {
  private Producer<T> producer;
  private Maybe<T> value;

  /**
   * Constructor of Lazy class to initialise value.
   *
   * @param v the value to be encapsulated
   */
  private Lazy(T v) {
    this.value = Maybe.<T>some(v);
    this.producer = () -> v;
  }

  /**
   * Constructor of Lazy class to initialise producer.
   *
   * @param s the producer to be encapsulated
   */
  private Lazy(Producer<T> s) {
    this.value = Maybe.none();
    this.producer = s;
  }

  /**
   * static of method that initialises the Lazy object with the given value.
   *
   * @param <T> the type of the value to be encapsulated
   * @param v the value to be encapsulated
   * @return an instance of Lazy
   */
  public static <T> Lazy<T> of(T v) {
    return new Lazy<>(v);
  }

  /**
   * static of method that takes in a producer that produces the value when needed.
   *
   * @param <T> the type of the value to be encapsulated
   * @param s the Producer to be encapsulated
   * @return an instance of Lazy
   */
  public static <T> Lazy<T> of(Producer<T> s) {
    return new Lazy<>(s);
  }

  /**
   * method that is called when the value is needed. 
   * If value is already available, return that value;
   * otherwise, compute the value and return it.
   *
   * @return the value that is encapsulated
   */
  public T get() {
    if (! value.equals(Maybe.none())) {
      return value.orElseGet(producer);
    } else {
      T val = producer.produce();
      this.value = Maybe.<T>of(val);
      return val;
    }
  }

  /**
   * method to lazily transform the encapsulated value to a new value of type U.
   * map does not return flattened Lazy
   *
   * @param <U> the return type of the transformed value
   * @param transformer the Transformer used to transform the encapsulated value
   * @return a transformed instance of Lazy
   */
  public <U> Lazy<U> map(Transformer<? super T, ? extends U> transformer) {
    Producer<U> prod = () -> transformer.transform(this.get());
    return Lazy.<U>of(prod);
  }

  /**
   * method to lazily transform the encapsulated value to a new value of type U.
   * flatMap returns flattened Lazy
   *
   * @param <U> the return type of the transformed value
   * @param transformer the Transformer used to transform the encapsulated value
   * @return a transformed and flattened instance of Lazy
   */
  public <U> Lazy<U> flatMap(Transformer<? super T, 
      ? extends Lazy<? extends U>> transformer) {
    Producer<U> prod = () -> transformer.transform(this.get()).get();
    return Lazy.<U>of(prod);
  }

  /**
   * method to lazily test if the value passes a given condition.
   *
   * @param condition the given condition used to test the encapsulated value
   * @return a Lazy Boolean object
   */
  public Lazy<Boolean> filter(BooleanCondition<? super T> condition) {
    Producer<Boolean> prod = () -> condition.test(this.get());
    return Lazy.<Boolean>of(prod);
  }

  /**
   * method to combine the values of two Lazy objects into a new Lazy object.
   *
   * @param <S> type of the second Lazy object to be combined
   * @param <R> return type of the combined Lazy object
   * @param laze the second Lazy object to be combined
   * @param comb the Combiner used to combine the two Lazy objects
   * @return a Lazy R object containing the combined value
   */
  public <S, R> Lazy<R> combine(Lazy<S> laze, 
      Combiner<? super T, ? super S, ? extends R> comb) {
    Producer<R> prod = () -> comb.combine(this.get(), laze.get());
    return Lazy.<R>of(prod);
  }

  /**
   * Overridden equals method.
   * An eager operation that evaluates the value.
   * returns true only if both objects are Lazy and the value contained
   * within are equals according to their equals method.
   */
  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Lazy) {
      Lazy<?> laze = (Lazy<?>) obj;
      return this.get().equals(laze.get());
    } else {
      return false;
    }
  }

  /**
   * Overridden toString method.
   * If value is already available, return its string representation;
   * otherwise, return ?.
   */
  @Override
  public String toString() {
    return (value.equals(Maybe.none()))
      ? "?"
      : "" + this.get();
  }
}
