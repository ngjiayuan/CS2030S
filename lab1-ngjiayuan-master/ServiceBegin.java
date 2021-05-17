/**
 * @author Ng Jia Yuan (Group 08L)
 * ServiceBegin IS-A ShopEvent
 */
class ServiceBegin extends ShopEvent {
  private int counterId;
  private double serviceTime;
  private Counter[] shopCounters;

  public ServiceBegin(double time, Customer customer, double serviceTime,
		  int counterId, Counter[] shopCounters) {
    super(time, customer);
    this.serviceTime = serviceTime;
    this.counterId = counterId;
    this.shopCounters = shopCounters;
  }

  @Override
  public String toString() {
    return super.toString() + String.format(": %s service begin (by Counter %d)",
		    this.customer, this.counterId);
  }

  @Override
  public Event[] simulate() {
    this.shopCounters[this.counterId].setUnavailable();
    double endTime = this.getTime() + this.serviceTime;
    return new Event[] {
      new ServiceEnd(endTime, this.customer, this.serviceTime, this.counterId, this.shopCounters)
    };
  }

}
