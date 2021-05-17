/**
 * @author Ng Jia Yuan (Group 08L)
 * ShopSimulation HAS-A Counter class
 * added implements Comparable
 */
class Counter implements Comparable<Counter> {
  private int id;
  //initialise as true, counter is available when first created
  private boolean availability = true;
  private static int currentId = 0;
  private int maxCounterQueue;
  private Queue<Customer> queue;

  public Counter(int id, int maxCounterQueue) {
    this.id = id;
    this.currentId++;
    this.queue = new Queue<Customer>(maxCounterQueue);
  }

  public int getId() {
    return this.id;
  }

  public static int getCurrentId() {
    return currentId;
  }

  public boolean getAvailability() {
    return this.availability;
  }

  public void setAvailable() {
    this.availability = true;
  }

  public void setUnavailable() {
    this.availability = false;
  }

  public Queue<Customer> getQueue() {
    return queue;
  }

  public void joinQueue(Customer customer) {
    this.queue.enq(customer);
  }

  public Customer nextInQueue() {
    return this.queue.deq();
  }

  public boolean isQueueFull() {
    return this.queue.isFull();
  }

  public boolean isQueueEmpty() {
    return this.queue.isEmpty();
  }

  public int getQueueLength() {
    return this.queue.length();
  }

  @Override
  // -1 choose this
  // 1 choose other
  public int compareTo(Counter other) {
    if (this.availability && other.getAvailability()) {
      return this.id < other.getId() 
        ? -1 
        : 1;
    } else if (this.availability) {
      return -1;
    } else if (other.getAvailability()) {
      return 1;
    } else {
      if (this.queue.isFull() && other.isQueueFull()) {
        return 0;
      } else if (this.queue.length() < other.getQueueLength()) {
        return -1;
      } else if (this.queue.length() > other.getQueueLength()) {
        return 1;
      } else {
        return this.id < other.getId()
          ? -1
          : 1;
      }
    }
  }

  @Override
  public String toString() {
    return String.format("S%d %s", this.id, this.queue);
  }
}
