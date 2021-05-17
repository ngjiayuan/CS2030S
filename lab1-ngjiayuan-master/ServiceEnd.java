/**
 * @author Ng Jia Yuan (Group 08L)
 * ServiceEnd IS-A ShopEvent
 */
class ServiceEnd extends ShopEvent {
  private int counterId;
  private double serviceTime;
  private Counter[] shopCounters;

  public ServiceEnd(double time, Customer customer, double serviceTime,
		  int counterId, Counter[] shopCounters) {
    super(time, customer);
    this.serviceTime = serviceTime;
    this.counterId = counterId;
    this.shopCounters = shopCounters;
  }

  @Override
  public String toString() {
    return super.toString() + String.format(": %s service done (by Counter %d)",
		    this.customer, this.counterId);
  }

  @Override
  public Event[] simulate() {
    this.shopCounters[this.counterId].setAvailable();
    return new Event[] {
      new Departure(this.getTime(), this.customer)
    };
  }

}
