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
   */
  public Customer customer;

  public ShopEvent(double time, Customer customer) {
    super(time);
    this.customer = customer;
  }

  @Override
  public String toString() {
    return super.toString();
  }

  @Override
  abstract public Event[] simulate();
}
