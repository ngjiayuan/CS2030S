/**
 * @author Ng Jia Yuan (Group 08L)
 * Enqueue IS-A ShopEvent
 */
class JoinShopQueue extends ShopEvent {
  
  public JoinShopQueue(double time, Customer customer, Shop shop) {
    super(time, customer, shop);
  }

  @Override
  public String toString() {
    return super.toString() + String.format(": %s joined shop queue %s", this.getCustomer(), 
        this.getShop().getQueue());
  }

  @Override
  public Event[] simulate() {
    this.getShop().joinQueue(this.getCustomer());
    return new Event[] {};
  }
}
