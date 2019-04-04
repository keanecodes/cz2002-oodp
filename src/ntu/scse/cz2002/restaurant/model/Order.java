package ntu.scse.cz2002.restaurant.model;

import java.util.ArrayList;

import cz2002.assignment.MenuItem;
import cz2002.assignment.Staff;

public class Order {
	private ArrayList<MenuItem> items = new ArrayList<MenuItem>();
	private Staff staff;
	private int orderId;
	private int tableId;

	public Order(Staff staff, int orderId, int tableId) {
		this.staff = staff;
		this.orderId = orderId;
		this.tableId = tableId;
	}

	public ArrayList<MenuItem> getItems() { return this.items; }
	public String getStaffId() { return this.staffId; }
	public int getOrderId() { return this.orderId; }
	public String getTableId() { return this.tableId; }

	public void addItem(MenuItem i) { this.items.add(i); }
	public void removeItem(MenuItem i) {this.items.remove(i);}
	public void setStaffId(String s) { this.staffId = s; }
	public void setOrderId(int i) { this.orderId = i; }
	public void setTableId(String i) {this.tableId = i; };
}
