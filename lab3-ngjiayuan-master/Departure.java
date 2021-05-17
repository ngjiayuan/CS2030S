/**
 * @author Ng Jia Yuan (Group 08L)
 * Departure IS-A ShopEvent
 */
class Departure extends ShopEvent {
  
  public Departure(double time, Customer customer, Shop shop) {
    super(time, customer, shop);
  }

  @Override
  public String toString() {
    return super.toString() + String.format(": %s departed", this.getCustomer());
  }
  
  @Override
  public Event[] simulate() {
    return new Event[] {};
  }

} 
