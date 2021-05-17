/**
 *
 * @author Ng Jia Yuan (Group 08L)
 */
class Array<T extends Comparable<T>> {
  private T[] array;

  public Array(int size) {
    @SuppressWarnings("unchecked")
    T[] arr = (T[]) new Comparable[size];
    this.array = arr;
  }

  public void set(int index, T item) {
    this.array[index] = item;
  }

  public T get(int index) {
    return this.array[index];
  }

  public T[] getArray() {
    return this.array;
  }

  public T min() {
    T minimum = this.array[0];
    for (T item : this.array) {
      // minimum.compareTo(item) > 0
      // means minimum > item
      if (minimum.compareTo(item) > 0) {
        minimum = item;
      }
    }
    return minimum;
  }

  @Override
  public String toString() {
    String str = "[ ";
    for (T item : this.array) {
      str += String.format("%s ", item);
    }
    str += "]";
    return str;
  }
}
