/**
 * @author Ng Jia Yuan (Group 08L)
 */
class JoinCounterQueue extends ShopEvent {
  private Counter currentCounter;

  public JoinCounterQueue(double time, Customer customer, Shop shop, Counter counter) {
    super(time, customer, shop);
    this.currentCounter = counter;
  }

  @Override
  public String toString() {
    return super.toString() + String.format(": %s joined counter queue (at %s)",
        this.getCustomer(), this.currentCounter);
  }

  @Override
  public Event[] simulate() {
    this.currentCounter.joinQueue(this.getCustomer());
    return new Event[] {};
  }
  
}
