package cs2030s.fp;

import java.util.ArrayList;
import java.util.List;

/**
 * InfiniteList implementation with Maybe and Lazy.
 *
 *
 * @author Ng Jia Yuan (Group 08L)
 *
 * @param <T> the type of the item in the InfiniteList
 */
public class InfiniteList<T> {

  private final Lazy<Maybe<T>> head;
  private final Lazy<InfiniteList<T>> tail;
  private static final InfiniteList<?> EMPTY = new EmptyList();

  InfiniteList() { 
    head = null; 
    tail = null;
  }

  /**
   * Generate an InfiniteList with a given producer.
   *
   * @param <T> the type of the items in the InfiniteList
   * @param producer the Producer to generate the items in the InfiniteList
   * @return the generated InfiniteList
   */
  public static <T> InfiniteList<T> generate(Producer<T> producer) {
    Lazy<Maybe<T>> h = Lazy.<Maybe<T>>of(() -> Maybe.<T>some(producer.produce()));
    Lazy<InfiniteList<T>> t = Lazy.<InfiniteList<T>>of(() -> generate(producer));
    return new InfiniteList<>(h, t);
  }

  /**
   * Generate an InfiniteList by iterating with a Transformer.
   *
   * @param <T> the type of the items in the InfiniteList
   * @param seed the first item in the InfiniteList and used the generate next item
   * @param next the Transformer to iterate the next item in the InfiniteList
   * @return the generated InfiniteList
   */
  public static <T> InfiniteList<T> iterate(T seed, Transformer<T, T> next) {
    return new InfiniteList<>(seed, () -> iterate(next.transform(seed), next));
  }

  /**
   * Constructor of InfiniteList.
   * params are not of type Lazy
   *
   * @param head the head of the InfiniteList
   * @param tail the tail of the InfiniteList
   */
  private InfiniteList(T head, Producer<InfiniteList<T>> tail) {
    this.head = Lazy.<Maybe<T>>of(() -> Maybe.<T>some(head));
    this.tail = Lazy.<InfiniteList<T>>of(tail);
  }

  /**
   * Constructor of InfiniteList.
   * params are Lazy type
   *
   * @param head the head of the InfiniteList
   * @param tail the tail of the InfiniteList
   */
  private InfiniteList(Lazy<Maybe<T>> head, Lazy<InfiniteList<T>> tail) {
    this.head = head;
    this.tail = tail;
  }

  /**
   * Returns the head of the InfiniteList.
   *
   * @return the head of the InfiniteList
   */
  public T head() {
    Maybe<T> h = this.head.get();
    return h.orElseGet(() -> this.tail.get().head());
  }

  /**
   * Returns the tail of the InfiniteList.
   *
   * @return the tail of the InfiniteList
   */
  public InfiniteList<T> tail() {
    Maybe<T> h = this.head.get();
    return h.map(x -> this.tail.get()).orElseGet(() -> this.tail.get().tail());
  }

  /**
   * Map the items in the current InfiniteList to a new InfiniteList
   * with a given Transformer.
   *
   * @param <R> the type of the items in the new InfiniteList
   * @param mapper the Transformer used to map the items
   * @return the new InfiniteList
   */
  public <R> InfiniteList<R> map(Transformer<? super T, ? extends R> mapper) {
    Lazy<Maybe<R>> h = Lazy.<Maybe<R>>of(() -> Maybe.<T>some(this.head()).map(mapper));
    Lazy<InfiniteList<R>> t = Lazy.<InfiniteList<R>>of(() -> this.tail().map(mapper));
    return new InfiniteList<>(h, t);
  }

  /**
   * Filter the items in the current InfiniteList to a new InfiniteList
   * with a given BooleanCondition.
   *
   * @param predicate the BooleanCondition used to filter the items
   * @return the filtered InfiniteList
   */
  public InfiniteList<T> filter(BooleanCondition<? super T> predicate) {
    Lazy<Maybe<T>> h = Lazy.<Maybe<T>>of(() -> Maybe.<T>some(this.head()).filter(predicate));
    Lazy<InfiniteList<T>> t = Lazy.<InfiniteList<T>>of(() -> this.tail().filter(predicate));
    return new InfiniteList<>(h, t);
  }

  /**
   * An EmptyList to mark the end of an InfiniteList.
   */
  private static class EmptyList extends InfiniteList<Object> {
    /**
     * Constructor for EmptyList.
     */
    public EmptyList() {
      super(Lazy.<Maybe<Object>>of(() -> null), Lazy.<InfiniteList<Object>>of(() -> null));
    }

    @Override
    public Object head() {
      throw new java.util.NoSuchElementException();
    }

    @Override
    public InfiniteList<Object> tail() {
      throw new java.util.NoSuchElementException();
    }

    @Override
    public <R> InfiniteList<R> map(Transformer<? super Object, ? extends R> mapper) {
      @SuppressWarnings("unchecked")
      InfiniteList<R> newEmpty = (InfiniteList<R>) super.empty();
      return newEmpty;
    }

    @Override
    public InfiniteList<Object> filter(BooleanCondition<? super Object> predicate) {
      @SuppressWarnings("unchecked")
      InfiniteList<Object> newEmpty = (InfiniteList<Object>) super.empty();
      return newEmpty;
    } 

    @Override
    public InfiniteList<Object> limit(long n) {
      @SuppressWarnings("unchecked")
      InfiniteList<Object> newEmpty = (InfiniteList<Object>) super.empty();
      return newEmpty;
    }

    @Override
    public InfiniteList<Object> takeWhile(BooleanCondition<? super Object> predicate) {
      @SuppressWarnings("unchecked")
      InfiniteList<Object> newEmpty = (InfiniteList<Object>) super.empty();
      return newEmpty;
    } 
    
    @Override
    public List<Object> toList() {
      return new ArrayList<>();
    }

    @Override
    public long count() {
      return 0L;
    }

    @Override
    public <U> U reduce(U identity, Combiner<U, ? super Object, U> accumulator) {
      return identity;
    }
  }

  /**
   * Returns the EmptyList.
   *
   * @param <T> the type of items in the InfiniteList
   * @return the EmptyList
   */
  public static <T> InfiniteList<T> empty() {
    @SuppressWarnings("unchecked")
    InfiniteList<T> emptylist = (InfiniteList<T>) EMPTY;
    return emptylist;
  }

  /**
   * Truncate the InfiniteList to a finite list with at most n elements.
   *
   * @param n the number of elements in the finite list
   * @return the finite list
   */
  public InfiniteList<T> limit(long n) {
    return n <= 0
      ? empty()
      : new InfiniteList<>(Lazy.<Maybe<T>>of(() -> Maybe.<T>some(this.head())),
          Lazy.<InfiniteList<T>>of(() -> this.tail().limit(n - 1)));
  }

  /**
   * Truncate the InfiniteList as soon as an element evaluates the given
   * BooleanCondition to false.
   *
   * @param predicate the BooleanCondition that determines where to truncate the list
   * @return the truncated InfiniteList
   */
  public InfiniteList<T> takeWhile(BooleanCondition<? super T> predicate) {
    Lazy<Boolean> bool = Lazy.<T>of(() -> this.head()).filter(predicate);
    Lazy<Maybe<T>> h = Lazy.<Maybe<T>>of(() -> bool.get() 
        ? Maybe.<T>some(this.head())
        : Maybe.none());
    Lazy<InfiniteList<T>> t = Lazy.<InfiniteList<T>>of(() -> bool.get()
        ? this.tail().takeWhile(predicate) 
        : empty());
    return new InfiniteList<>(h, t);

  }

  /**
   * Checks if the InfiniteList is an instanceof EmptyList.
   *
   * @return true if the InfiniteList is an instanceof EmptyList
   */
  public boolean isEmpty() {
    return (this instanceof EmptyList);
  }

  /**
   * Reduce the List according to a given Combiner.
   *
   * @param <U> the return type of the reduced result
   * @param identity the initial element to be accumulated with
   * @param accumulator the given Combiner to reduce the List
   * @return the reduced result
   */
  public <U> U reduce(U identity, Combiner<U, ? super T, U> accumulator) {
    U result = identity;
    for (T item : this.toList()) {
      result = accumulator.combine(result, item);
    }
    return result;
  }

  /**
   * Count and return the number of elements in the List.
   *
   * @return the number of elements in List
   */
  public long count() {
    return this.reduce(0L, (x, y) -> x + 1);
  }

  /**
   * Collects all elements in the InfiniteList into a List.
   *
   * @return a List of elements from the InfiniteList
   */
  public List<T> toList() {
    List<T> list = new ArrayList<>();
    InfiniteList<T> infList = this;
    while (!infList.isEmpty()) {
      try {
        list.add(infList.head());
        infList = infList.tail();
      } catch (java.util.NoSuchElementException e) {
        break;
      }
    }
    return list;
    /*
    while (!infList.isEmpty()) {
      if (infList.head.get().equals(Maybe.none()) && infList.tail.get().isEmpty()) {
        break;
      }
      list.add(infList.head());
      infList = infList.tail();
    }
    return list;
    */
  }

  public String toString() {
    return "[" + this.head + " " + this.tail + "]";
  }
}
