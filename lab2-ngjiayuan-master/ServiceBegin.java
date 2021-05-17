/**
 * @author Ng Jia Yuan (Group 08L)
 * ServiceBegin IS-A ShopEvent
 */
class ServiceBegin extends ShopEvent {
  private Counter currentCounter;

  public ServiceBegin(double time, Customer customer, Shop shop,
      Counter currentCounter) {
    super(time, customer, shop);
    this.currentCounter = currentCounter;
  }

  @Override
  public String toString() {
    return super.toString() + String.format(": %s service begin (by %s)",
        this.getCustomer(), this.currentCounter);
  }

  @Override
  public Event[] simulate() {
    this.currentCounter.setUnavailable();
    double endTime = this.getTime() + this.getCustomer().getServiceTime();
    return new Event[] {
      new ServiceEnd(endTime, this.getCustomer(), this.getShop(), this.currentCounter)
    };
  }

}
