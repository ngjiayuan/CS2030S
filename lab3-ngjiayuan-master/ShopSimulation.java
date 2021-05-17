import java.util.Scanner;

/**
 * This class implements a shop simulation.
 *
 * @author Ng Jia Yuan (Group 08L)
 * @version CS2030S AY20/21 Semester 2
 */ 
class ShopSimulation extends Simulation {
  /** 
   * Initialise shop. 
   */
  private Shop shop;

  /** 
   * The list of customer arrival events to populate
   * the simulation with.
   */
  private Event[] initEvents;

  /** 
   * Constructor for a shop simulation. 
   *
   * @param sc A scanner to read the parameters from.  The first
   *           integer scanned is the number of customers; followed
   *           by the number of service counters.  Next is a 
   *           sequence of (arrival time, service time) pair, each
   *           pair represents a customer.
   */
  public ShopSimulation(Scanner sc) {
    this.initEvents = new Event[sc.nextInt()];
    int numOfCounters = sc.nextInt();
    int maxCounterQueue = sc.nextInt();
    int maxShopQueue = sc.nextInt();
    this.shop = new Shop(numOfCounters, maxCounterQueue, maxShopQueue);

    int i = 0;
    while (sc.hasNextDouble()) {
      double arrivalTime = sc.nextDouble();
      double serviceTime = sc.nextDouble();
      Customer customer = new Customer(Customer.getCurrentId(), serviceTime);
      this.initEvents[i] = new Arrival(arrivalTime, customer, shop);
      i++;
    }
  }

  /**
   * Retrieve an array of events to populate the 
   * simulator with.
   *
   * @return An array of events for the simulator.
   */
  @Override
  public Event[] getInitialEvents() {
    return initEvents;
  }
}
