/**
 * @author Ng Jia Yuan (Group 08L)
 * ShopSimulation HAS-A Counter class
 */
class Counter {
  private int id;
  //initialise as true, counter is available when first created
  private boolean availability = true;
  private static int currentId = 0;

  public Counter(int id) {
    this.id = id;
    this.currentId++;
  }

  public int getId() {
    return this.id;
  }

  public static int getCurrentId() {
    return currentId;
  }

  public boolean getAvailability() {
    return this.availability;
  }

  public void setAvailable() {
    this.availability = true;
  }

  public void setUnavailable() {
    this.availability = false;
  }

  @Override
  public String toString() {
    return String.format("S%d", this.id);
  }
}
