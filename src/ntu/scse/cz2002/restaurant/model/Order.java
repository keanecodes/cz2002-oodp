package ntu.scse.cz2002.restaurant.model;

import java.io.Serializable;
import java.util.ArrayList;

import ntu.scse.cz2002.restaurant.model.MenuItem;
import ntu.scse.cz2002.restaurant.model.Staff;

/**
 * Contains all the attributes and methods for an order entity. 
 * @author Goh Ying Ting
 * @version 1.0
 * @since 2019-4-1
 * 
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
	 * Indicator of whether the order is currently on-going
	 */
	private boolean isOnGoing;
	/**
	 * Constructor for order
	 */
	public Order() { }
	/**
	 * Constructor for order
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
	 * Constructor for order
	 * @param orderId The associated id with the order
	 * @param tableId The associated tableId with the order
	 */
	public Order(int orderId, int tableId) {
		this.orderId = orderId;
		this.tableId = tableId;
		this.isOnGoing = false;
	}
	
	/**
	 * Constructor for order
	 * @param tableId The associated tableId with the order
	 */
	public Order(int tableId) {
		this.tableId = tableId;
		this.isOnGoing = false;
	}

	/**
	 * gets MenuItems that have been ordered
	 * @return returns the menu items which have been ordered
	 */
	public ArrayList <MenuItem> getItems() {
		return items;
	}
	
	/**
	 * gets staff that made the order
	 * @return returns the staff who made the order
	 */
	public Staff getStaff() {
		return staff;
	}

	/**
	 * gets orderID of order
	 * @return returns the orderId associated with the order
	 */
	public int getOrderId() {
		return this.orderId;
	}

	/**
	 * gets TableId of table placing order
	 * @return returns the tableId associated with the order
	 */
	public int getTableId() {
		return this.tableId;
	}
	
	/**
	 * gets the status of order, true if its on-going
	 * @return returns if the order is on-going
	 */
	public boolean isOnGoing() {
		return isOnGoing;
	}
	
	/**
	 * changes the Staff attribute
	 * @param s sets the Staff who made the order
	 */
	public void setStaff(Staff s) {
		this.staff = s;
	}

	/**
	 * changes the orderID 
	 * @param i sets the orderId associated with the order
	 */
	public void setOrderId(int i) {
		this.orderId = i;
	}

	/**
	 * changes the tableID attribute
	 * @param i sets the tableId associated with the order
	 */
	public void setTableId(int i) {
		this.tableId = i;
	}

	/**
	 * sets order finalised flag
	 * @param isOnGoing whether the order is ongoing
	 */
	public void setIsOnGoing(boolean isOnGoing) {
		this.isOnGoing = isOnGoing;
	}
	
	/**
	 * adds a menu item to order
	 * @param i menuitem to be added
	 */
	public void addItem(MenuItem i) {
		this.items.add(i);
	}

	/**
	 * removes menu item from the order
	 * @param j menu item to be removed
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
