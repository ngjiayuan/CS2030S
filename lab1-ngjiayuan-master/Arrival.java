/**
 * @author Ng Jia Yuan (Group 08L)
 * Arrival IS-A ShopEvent
 */
class Arrival extends ShopEvent {
  private double serviceTime;
  private Counter[] shopCounters;
  
  public Arrival(double time, Customer customer, double serviceTime,
		  Counter[] shopCounters) {
    super(time, customer);
    this.serviceTime = serviceTime;
    this.shopCounters = shopCounters;
  }
  
  @Override
  public String toString() {
    return super.toString() + String.format(": %s arrives", this.customer);
  }

  @Override
  public Event[] simulate() {
    int counter = -1;
    for (Counter i: this.shopCounters) {
      if (i.getAvailability()) {
        counter = i.getId();
	break;
      }
    }
    if (counter == -1) {
      return new Event[] {
	new Departure(this.getTime(), this.customer)
      };
    } else {
      return new Event[] {
	new ServiceBegin(this.getTime(), this.customer, this.serviceTime,
			counter, this.shopCounters)
      };
    }
  }

}
