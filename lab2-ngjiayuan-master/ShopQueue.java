/**
 * @author Ng Jia Yuan (Group 08L)
 * ShopQueue IS-A Queue
 * to override deq() method
 * for future development
 */
class ShopQueue extends Queue {

  public ShopQueue(int size) {
    super(size);
  }

  public boolean enq(Customer customer) {
    return super.enq(customer);
  }

  @Override
  public Customer deq() {
    return (Customer) super.deq();
  }

  @Override
  public String toString() {
    return super.toString();
  }
}
