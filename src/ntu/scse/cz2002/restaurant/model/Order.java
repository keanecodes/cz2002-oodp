package ntu.scse.cz2002.restaurant.model;

import java.io.Serializable;
import java.util.ArrayList;

import ntu.scse.cz2002.restaurant.model.MenuItem;
import ntu.scse.cz2002.restaurant.model.Staff;

/**
 * @author YingTing
 * Contains all the attributes and methods for an order entity. 
 */
public class Order implements Serializable {
	/**
	 * An array to store all ordered menu items
	 */
	private ArrayList<MenuItem> items = new ArrayList<MenuItem>();
	/**
	 * Staff who made the order
	 */
	private Staff staff;
	/**
	 * Associated id with the order
	 */
	private int orderId;
	/**
	 * Associated tableId with the order
	 */
	private int tableId;
	/**
	 * Constructor for order
	 */
	public Order() { }
	/**
	 * @param staff The staff who created the order
	 * @param orderId The associated id with the order
	 * @param tableId The associated tableId with the order
	 */
	public Order(Staff staff, int orderId, int tableId) {
		this.staff = staff;
		this.orderId = orderId;
		this.tableId = tableId;
	}
	
	/**
	 * @param tableId The associated tableId with the order
	 */
	public Order(int tableId) {
		this.tableId = tableId;
	}

	/**
	 * @return returns the menu items which have been ordered
	 */
	public ArrayList <MenuItem> getItems() {
		return items;
	}
	
	/**
	 * @return returns the staff who made the order
	 */
	public Staff getStaff() {
		return staff;
	}

	/**
	 * @return returns the orderId associated with the order
	 */
	public int getOrderId() {
		return this.orderId;
	}

	/**
	 * @return returns the tableId associated with the order
	 */
	public int getTableId() {
		return this.tableId;
	}

	/**
	 * @param s sets the Staff who made the order
	 */
	public void setStaff(Staff s) {
		this.staff = s;
	}

	/**
	 * @param i sets the orderId associated with the order
	 */
	public void setOrderId(int i) {
		this.orderId = i;
	}

	/**
	 * @param i sets the tableId associated with the order
	 */
	public void setTableId(int i) {
		this.tableId = i;
	}

	/**
	 * @param i adds menu item to the order
	 */
	public void addItem(MenuItem i) {
		this.items.add(i);
	}

	/**
	 * @param j removes menu item from the order
	 */
	public void removeItem(MenuItem j) {
		String name = j.getName();
		for (int i = 0; i < this.items.size(); i++) {
			if (this.items.get(i).getName().equals(name)) {
				this.items.remove(i);
			}
		}
	}
}
