/**
 * @author Ng Jia Yuan (Group 08L)
 * ShopSimulation HAS-A Customer class
 */
class Customer {
  private int id;
  private static int currentId = 0;
  private double serviceTime;

  public Customer(int id, double serviceTime) {
    this.id = id;
    this.currentId++;
    this.serviceTime = serviceTime;
  }

  public int getId() {
    return this.id;
  }

  public static int getCurrentId() {
    return currentId;
  }

  public double getServiceTime() {
    return this.serviceTime;
  }

  @Override
  public String toString() {
    return String.format("C%d", this.id);
  }

}
