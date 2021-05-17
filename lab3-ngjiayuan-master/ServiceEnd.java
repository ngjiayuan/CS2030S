/**
 * @author Ng Jia Yuan (Group 08L)
 * ServiceEnd IS-A ShopEvent
 */
class ServiceEnd extends ShopEvent {
  private Counter currentCounter;

  public ServiceEnd(double time, Customer customer, Shop shop, 
      Counter currentCounter) {
    super(time, customer, shop);
    this.currentCounter = currentCounter;
  }

  @Override
  public String toString() {
    return super.toString() + String.format(": %s service done (by %s)",
        this.getCustomer(), this.currentCounter);
  }

  @Override
  public Event[] simulate() {
    this.currentCounter.setAvailable();
    // if counterQueue is not empty and shopQueue is not empty, 
    // depart current customer
    // servicebegin next in line customer
    // shopQueue customer JoinCounterQueue
    if (! this.currentCounter.isQueueEmpty() && ! this.getShop().isQueueEmpty()) {
      return new Event[] {
        new Departure(this.getTime(), this.getCustomer(), this.getShop()),
          new ServiceBegin(this.getTime(), this.currentCounter.nextInQueue(), this.getShop(),
              this.currentCounter),
          new JoinCounterQueue(this.getTime(), this.getShop().nextInQueue(), this.getShop(),
              this.currentCounter)
      };
    } else if (! this.currentCounter.isQueueEmpty()) {
      return new Event[] {
        new Departure(this.getTime(), this.getCustomer(), this.getShop()),
          new ServiceBegin(this.getTime(), this.currentCounter.nextInQueue(), this.getShop(),
              this.currentCounter)
      };
    } else if (! this.getShop().isQueueEmpty()) {
      return new Event[] {
        new Departure(this.getTime(), this.getCustomer(), this.getShop()),
          new ServiceBegin(this.getTime(), this.getShop().nextInQueue(), this.getShop(),
              this.currentCounter)
      };
    } else {
      return new Event[] {
        new Departure(this.getTime(), this.getCustomer(), this.getShop())
      };
    }
  }

}
