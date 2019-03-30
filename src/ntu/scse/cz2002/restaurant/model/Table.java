package ntu.scse.cz2002.restaurant.model;

public class Table {
  private String tableId;
  private int noOfMaxPax;
  private boolean availability;
  private Order order;

  public Table() { super(); }

  public Table(String tableId, int noOfMaxPax, boolean a, Order o) {
    this.tableId = tableId;
    this.noOfMaxPax = noOfMaxPax;
    this.availability = a;
    this.order = o;
  }

  public String getTableId() { return this.tableId; }
  public int getNoOfMaxPax() { return this.noOfMaxPax; }
  public boolean isAvailable() { return this.availability; }
  public Order getOrder() { return this.order; }

  //No Setting of tableId and maxPax? //
  public void setNoOfMaxPax(int p) { this.noOfMaxPax = p; }
  public void setAvailability(boolean a) { this.availability = a; }
  public void makeOrder(Order o) { this.order = o; }
}
