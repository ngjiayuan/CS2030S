/**
 * @author Ng Jia Yuan (Group 08L)
 * created a shop class to initialise counters
 * and shopQueue
 */
class Shop {
  private int numOfCounters;
  private int maxShopQueue;
  private Array<Counter> counters;
  private Queue<Customer> shopQueue;

  public Shop(int numOfCounters, int maxCounterQueue, int maxShopQueue) {
    this.numOfCounters = numOfCounters;
    this.maxShopQueue = maxShopQueue;
    this.shopQueue = new Queue<Customer>(maxShopQueue);
    this.counters = new Array<Counter>(numOfCounters); 
    for (int i = 0; i < numOfCounters; i++) {
      this.counters.set(i, new Counter(Counter.getCurrentId(), maxCounterQueue));
    }
  }

  public Array<Counter> getCounters() {
    return counters;
  }

  public Queue<Customer> getQueue() {
    return shopQueue;
  }

  public boolean counterAvailable() {
    for (int i = 0; i < numOfCounters; i++) {
      if (counters.get(i).getAvailability()) {
        return true;
      }
    }
    return false;
  }

  public Counter getAvailableCounter() {
    for (int i = 0; i < numOfCounters; i++) {
      if (counters.get(i).getAvailability()) {
        return counters.get(i);
      }
    }
    return null;
  }

  public boolean isAllCounterQueueFull() {
    for (int i = 0; i < numOfCounters; i++) {
      if (! counters.get(i).isQueueFull()) {
        return false;
      }
    }
    return true;
  }

  public void joinQueue(Customer customer) {
    this.shopQueue.enq(customer);
  }

  public Customer nextInQueue() {
    return this.shopQueue.deq();
  }

  public boolean isQueueFull() {
    return this.shopQueue.isFull();
  }

  public boolean isQueueEmpty() {
    return this.shopQueue.isEmpty();
  }

  public Counter getMinCounter() {
    return this.counters.min();
  }

  @Override
  public String toString() {
    return String.format("Shop with %d counters and a shop queue length of %d", 
        this.numOfCounters, this.maxShopQueue);
  }
}
