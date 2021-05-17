/**
 * @author Ng Jia Yuan (Group 08L)
 * ShopSimulation HAS-A Counter class
 */
class Counter {
  private int id;
  private boolean availability;
  private static int currentId = 0;

  public Counter(int id, boolean availability) {
    this.id = id;
    this.availability = availability;
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
    if (this.availability) {
      return String.format("Counter %d is available", this.id);
    } else {
      return String.format("Counter %d is not available", this.id);
    }
  }

}
