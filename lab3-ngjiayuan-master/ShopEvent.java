/**
 * This class encapsulates an event in the shop
 * simulation.  Your task is to replace this
 * class with new classes, following proper OOP principles.
 *
 * @author Ng Jia Yuan (Group 08L)
 * @version CS2030S AY20/21 Semester 2
 */


abstract class ShopEvent extends Event {
  /**
   * ShopEvent IS-A Event
   * Act as an abstract class for Arrival, Departure,
   * ServiceBegin and ServiceEnd classes
   * has shop and customer
   */
  private Customer customer;
  private Shop shop;

  public ShopEvent(double time, Customer customer, Shop shop) {
    super(time);
    this.customer = customer;
    this.shop = shop;
  }

  public Customer getCustomer() {
    return this.customer;
  }

  public Shop getShop() {
    return this.shop;
  }

  @Override
  public String toString() {
    return super.toString();
  }

  @Override
  public abstract Event[] simulate();
}
