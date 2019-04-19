package ntu.scse.cz2002.restaurant.model;

import java.io.Serializable;
import java.util.ArrayList;

import ntu.scse.cz2002.restaurant.model.MenuItem;
import ntu.scse.cz2002.restaurant.model.Staff;

/**
 * @author YingTing
 *
 */
public class Order implements Serializable {
	/**
	 * 
	 */
	private ArrayList<MenuItem> items = new ArrayList<MenuItem>();
	/**
	 * 
	 */
	private Staff staff;
	/**
	 * 
	 */
	private int orderId;
	/**
	 * 
	 */
	private int tableId;

	public Order() { }
	
	/**
	 * @param staff
	 * @param orderId
	 * @param tableId
	 */

	public Order(Staff staff, int orderId, int tableId) {
		this.staff = staff;
		this.orderId = orderId;
		this.tableId = tableId;
	}

	/**
	 * @return
	 */
	public ArrayList <MenuItem> getItems() {
		return items;
	}
	
	/**
	 * @return
	 */
	public Staff getStaff() {
		return staff;
	}

	/**
	 * @return
	 */
	public int getOrderId() {
		return this.orderId;
	}

	/**
	 * @return
	 */
	public int getTableId() {
		return this.tableId;
	}

	/**
	 * @param s
	 */
	public void setStaff(Staff s) {
		this.staff = s;
	}

	/**
	 * @param i
	 */
	public void setOrderId(int i) {
		this.orderId = i;
	}

	/**
	 * @param i
	 */
	public void setTableId(int i) {
		this.tableId = i;
	}

	/**
	 * @param i
	 */
	public void addItem(MenuItem i) {
		this.items.add(i);
	}
	
	/**
	 * @param j
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
