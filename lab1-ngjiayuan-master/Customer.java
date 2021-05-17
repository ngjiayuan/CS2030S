/**
 * @author Ng Jia Yuan (Group 08L)
 * ShopSimulation HAS-A Customer class
 */
class Customer {
  private int id;
  private static int currentId = 0;

  public Customer(int id) {
    this.id = id;
    this.currentId++;
  }

  public int getId() {
    return this.id;
  }

  public static int getCurrentId() {
    return currentId;
  }
  
  @Override
  public String toString() {
    return String.format("Customer %d", this.id);
  }

}
