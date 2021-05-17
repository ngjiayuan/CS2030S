/**
 * @author Ng Jia Yuan (Group 08L)
 * created a shop class to initialise counters
 * and shopQueue
 */
class Shop {
  private int numOfCounters;
  private int maximumQueue;
  private Counter[] counters;
  private ShopQueue shopQueue;

  public Shop(int numOfCounters, int maximumQueue) {
    this.numOfCounters = numOfCounters;
    this.maximumQueue = maximumQueue;
    this.counters = new Counter[numOfCounters]; 
    for (int i = 0; i < numOfCounters; i++) {
      this.counters[i] = new Counter(Counter.getCurrentId());
    }
    this.shopQueue = new ShopQueue(maximumQueue);
  }

  public Counter[] getCounters() {
    return counters;
  }

  public ShopQueue getQueue() {
    return shopQueue;
  }

  public boolean counterAvailable() {
    for (Counter i : counters) {
      if (i.getAvailability()) {
        return true;
      }
    }
    return false;
  }

  public Counter getAvailableCounter() {
    for (Counter i : counters) {
      if (i.getAvailability()) {
        return i;
      }
    }
    return null;
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

  @Override
  public String toString() {
    return String.format("Shop with %d counters and a queue length of %d", 
        this.numOfCounters, this.maximumQueue);
  }
}
