/**
 * @author Ng Jia Yuan (Group 08L)
 * Arrival IS-A ShopEvent
 */
class Arrival extends ShopEvent {

  public Arrival(double time, Customer customer, Shop shop) {
    super(time, customer, shop);
  }

  @Override
  public String toString() {
    return super.toString() + String.format(": %s arrived %s", this.getCustomer(),
        this.getShop().getQueue());
  }

  @Override
  public Event[] simulate() { 
    if (this.getShop().counterAvailable()) { 
      // if counter is available serve customer
      Counter counter = this.getShop().getAvailableCounter();
      return new Event[] {
        new ServiceBegin(this.getTime(), this.getCustomer(), this.getShop(), 
            counter)
      };
    } else if (! this.getShop().isAllCounterQueueFull()) {
      // if not all counterQueue is full join counterQueue
      return new Event[] {
        new JoinCounterQueue(this.getTime(), this.getCustomer(), this.getShop(),
            this.getShop().getMinCounter())
      };
    } else if (this.getShop().isAllCounterQueueFull() && ! this.getShop().isQueueFull()) {
      // if all counterQueue is full join shopQueue
      return new Event[] {
        new JoinShopQueue(this.getTime(), this.getCustomer(), this.getShop())
      };
    } else {
      // if both Queues full Depart
      return new Event[] {
        new Departure(this.getTime(), this.getCustomer(), this.getShop())
      };
    }
  }

}
