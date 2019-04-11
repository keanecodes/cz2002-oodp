package ntu.scse.cz2002.restaurant.model;

import java.util.ArrayList;

import ntu.scse.cz2002.restaurant.model.MenuItem;
import ntu.scse.cz2002.restaurant.model.Staff;

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
	public Staff getStaff() { return staff; }
	public int getOrderId() { return this.orderId; }
	public int getTableId() { return this.tableId; }

	public void addItem(MenuItem i) { this.items.add(i); }
	public void removeItem(MenuItem i) {this.items.remove(i);}
	public void setStaff(Staff s) { this.staff = s; }
	public void setOrderId(int i) { this.orderId = i; }
	public void setTableId(int i) {this.tableId = i; }
}
