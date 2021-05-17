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
      Counter counter = this.getShop().getAvailableCounter();
      return new Event[] {
        new ServiceBegin(this.getTime(), this.getCustomer(), this.getShop(), 
            counter)
      };
    } else if (this.getShop().isQueueFull()) {
      return new Event[] {
        new Departure(this.getTime(), this.getCustomer(), this.getShop())
      };
    } else {
      return new Event[] {
        new Enqueue(this.getTime(), this.getCustomer(), this.getShop())
      };
    }
  }

}
