/**
 * CS2030S Lab 5
 * AY20/21 Semester 2
 *
 * @author Ng Jia Yuan (Lab Group 08L)
 */

package cs2030s.fp;

import java.util.NoSuchElementException;

public abstract class Maybe<T> {
  private static final None NONE = new None();

  // None class
  public static final class None extends Maybe<Object> {
    @Override
    public <S> S orElseGet(Producer<S> prod) {
      return prod.produce();
    }

    @Override
    public <S> S orElse(S value) {
      return value;
    }

    @Override
    public <U> Maybe<U> flatMap(Transformer<? super Object, 
        ? extends Maybe<? extends U>> transformer) {
      @SuppressWarnings("unchecked")
      Maybe<U> newNONE = (Maybe<U>) super.none();
      return newNONE;
    }

    @Override
    public <U> Maybe<U> map(Transformer<? super Object, ? extends U> transformer) {
      @SuppressWarnings("unchecked")
      Maybe<U> newNONE = (Maybe<U>) super.none();
      return newNONE;
    }
    
    @Override
    public Maybe<Object> filter(BooleanCondition<? super Object> condition) {
      @SuppressWarnings("unchecked")
      Maybe<Object> newNONE = (Maybe<Object>) super.none();
      return newNONE;
    }

    @Override
    protected Object get() throws NoSuchElementException {
      throw new NoSuchElementException();
    }

    @Override
    public boolean equals(Object other) {
      return (other instanceof None);
    }

    @Override
    public String toString() {
      return "[]";
    }
  }

  // Some<T> class
  public static final class Some<T> extends Maybe<T> {
    private final T content;

    private Some(T content) {
      this.content = content;
    }

    public static <T> Some<T> of(T content) {
      return new Some<>(content);
    }

    @Override
    public <S extends T> T orElseGet(Producer<S> prod) {
      return this.content;
    }

    @Override
    public <S extends T> T orElse(S value) {
      return this.content;
    }

    @Override
    public <U> Maybe<U> flatMap(Transformer<? super T, ? extends Maybe<? extends U>> transformer) {
      @SuppressWarnings("unchecked")
      Maybe<U> result = (Maybe<U>) transformer.transform(this.content);
      if (result instanceof None) {
        return result;
      } else {
        return this.of(result.get());
      }
    }

    @Override
    public <U> Maybe<U> map(Transformer<? super T, ? extends U> transformer) {
      U result = (U) transformer.transform(this.content);
      return this.of(result);
    }

    @Override
    public Maybe<T> filter(BooleanCondition<? super T> condition) {
      if (this.content != null) {
        if (condition.test(this.content)) {
          return this;
        } else {
          return super.none();
        }
      } else {
        return this;
      }
    }

    @Override
    protected T get() {
      return this.content;
    }

    @Override
    public boolean equals(Object other) {
      if (this == other) {
        return true;
      } else if (other instanceof Some) {
        Some<?> otherSome = (Some<?>) other;
        if (this.content == null) {
          return otherSome.content == null;
        } else {
          return this.content.equals(otherSome.content);
        }
      } else {
        return false;
      }
    }

    @Override
    public String toString() {
      return "[" + this.content + "]";
    }
  }

  // Maybe::methods
  public static <T> Maybe<T> none() {
    @SuppressWarnings("unchecked")
    Maybe<T> empty = (Maybe<T>) NONE;
    return empty;
  }

  public static <T> Some<T> some(T content) {
    return Some.of(content);
  }

  public static <T> Maybe<T> of(T content) {
    if (content == null) {
      return Maybe.none();
    } else {
      return Maybe.some(content);
    }
  }

  protected abstract T get();

  public abstract Maybe<T> filter(BooleanCondition<? super T> condition);

  public abstract <U> Maybe<U> map(Transformer<? super T, ? extends U> transformer);

  public abstract <U> Maybe<U> flatMap(Transformer<? super T, 
      ? extends Maybe<? extends U>> transformer);

  public abstract <S extends T> T orElse(S value);

  public abstract <S extends T> T orElseGet(Producer<S> prod);
}
