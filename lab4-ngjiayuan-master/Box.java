/**
 * @author Ng Jia Yuan (Group 08L)
 */
public class Box<T> {
  private final T content;
  private static final Box<?> EMPTY_BOX = new Box<>(null);

  private Box(T content) {
    this.content = content;
  }

  public static <T> Box<T> of(T content) {
    if (content == null) {
      return null;
    } else {
      Box<T> box = new Box<T>(content);
      return box;
    }
  }

  public static <T> Box<T> empty() {
    @SuppressWarnings("unchecked")
    Box<T> empty = (Box<T>) EMPTY_BOX;
    return empty;
  }

  public boolean isPresent() {
    return this.content != null;
  }

  public static <T> Box<T> ofNullable(T item) {
    if (item == null) {
      return empty();
    } else {
      Box<T> box = new Box<T>(item);
      return box;
    }
  }

  // <? super T> because Integer, Double, Long
  public Box<T> filter(BooleanCondition<? super T> condition) {
    if (! this.isPresent()) { // if empty return empty box
      return empty();
    } else if (condition.test(this.content)) {
      return this;
    } else {
      return empty();
    }
  }

  // T consumes input, U produce output
  public <U> Box<U> map(Transformer<? super T, ? extends U> transformer) {
    if (! this.isPresent()) { // if empty return empty box
      return empty();
    } else {
      U result = (U) transformer.transform(this.content);
      return new Box<U>(result);
    }
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    } else if (other instanceof Box) {
      @SuppressWarnings("unchecked")
      Box<T> otherBox = (Box<T>) other;
      if (this.content == null) {
        return otherBox.content == null;
      } else {
        return this.content.equals(otherBox.content);
      }
    } else {
      return false;
    }
  }

  @Override
  public String toString() {
    if (this.isPresent()) {
      return "[" + this.content + "]";
    } else {
      return "[]";
    }
  }
}
