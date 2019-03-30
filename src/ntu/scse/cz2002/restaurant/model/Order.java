package ntu.scse.cz2002.restaurant.model;

import java.util.ArrayList;

public class Order {
  private ArrayList<MenuItem> items = new ArrayList<MenuItem>();
  private String staffId;
  private int orderId;
  private String tableId;

  public Order() { super(); }

  public Order(String staffId, String tableId) {
    this.staffId = staffId;
    this.tableId = tableId;
  }

  public Order(String staffId, int orderId, String tableId) {
    this.staffId = staffId;
    this.orderId = orderId;
    this.tableId = tableId;
  }

  public ArrayList<MenuItem> getItems() { return this.items; }
  public String getStaffId() { return this.staffId; }
  public int getOrderId() { return this.orderId; }
  public String getTableId() { return this.tableId; }

  public void addItem(MenuItem i) { this.items.add(i); }
  public void setStaffId(String s) { this.staffId = s; }
  public void setOrderId(int i) { this.orderId = i; }
  public void setTableId(String i) {this.tableId = i; };
}
