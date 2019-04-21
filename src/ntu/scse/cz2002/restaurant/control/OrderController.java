package ntu.scse.cz2002.restaurant.control;

import ntu.scse.cz2002.restaurant.model.Order;
import ntu.scse.cz2002.restaurant.model.Staff;
import ntu.scse.cz2002.restaurant.model.MenuItem;
import ntu.scse.cz2002.restaurant.data.DataAccessor;
import ntu.scse.cz2002.restaurant.model.Menu;
import ntu.scse.cz2002.restaurant.model.Table;
import ntu.scse.cz2002.restaurant.model.Promotion;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Control Class to manage orders
 * @author YingTing
 * @version 1.0
 * @since 2019-04-17
 */
public class OrderController {
	/**
	 * creating a staff controller
	 */
	StaffController staffManager = new StaffController();
	/**
	 * creating a menu controller
	 */
	MenuController menuManager = new MenuController();
	/**
	 * table controller attribute
	 */
	TableController tCtrl;
	/**
	 * static orderID variable
	 */
	private static int orderID = 0;
	
	/**
	 * obtaining the menu from menuManager
	 */
	Menu menu = menuManager.getMenu();
	/**
	 * creating an arraylist of orders
	 */
	private ArrayList<Order> orderArr = new ArrayList<Order>();
	
	/**
	 * constructor for orderController
	 */
	public OrderController(TableController tCtrl) {
		this.tCtrl = tCtrl;
	}
	/**
	 * @param oList set the orderArr with another arraylist of orders
	 */
	public void setOrderArr(ArrayList<Order> oList) {
		this.orderArr = oList;
	}
	
	/**
	 * @param tableController table controller which manages the table
	 * @param tableID associated tableId of the order
	 * @return returns order found with the corresponding tableID
	 */
	public Order createOrder(int tableID) {
		Order currentOrder = null;
		Table t;
		//tCtrl = tableController;
		if (tCtrl.isTableNotReservedAndOccupied(tableID)) {
			t = tCtrl.findTableById(tableID);
			t.setIsOccupied();
			currentOrder = t.getOrder();
			currentOrder.setStaff(staffManager.getStaff());
			currentOrder.setOrderId(++orderID);
			orderArr.add(currentOrder);
		}
		return currentOrder;
	}
	
	/**
	 * @param tableID takes in associated tableID of order
	 * removes order from orderArray
	 */
	public void removeOrder(int tableID) {
		for (int i = 0; i < this.orderArr.size(); i++) {
			if (this.orderArr.get(i).getTableId() == tableID) {
				this.orderArr.remove(i);
			}
		}
	}

	/**
	 * @param order takes in the corresponding order
	 * @param itemName the name of an item to be added
	 * adds item to order
	 */
	public boolean addOrderItem(Order order, String itemName) {
		MenuItem item = menu.getItem(itemName);
		if (item != null) {
			order.addItem(item);
			return true;
		}
		
		return false;
	}

	/**
	 * @param order takes in the corresponding order
	 * @param itemName the name of an item to be added
	 * removes item from order
	 */
	public boolean removeOrderItem(Order order, String itemName) {
		MenuItem item = menu.getItem(itemName);
		if (item != null) {
			order.removeItem(item);
			return true;
		}
		
		System.out.println("Item does not exist!");
		return false;
	}

	/**
	 * @param o takes in order
	 * displays associated staff information and order information
	 */
	public void displayOrder(Order o) {
		System.out.println("Staff Information: ");
		System.out.println("Staff name: " + o.getStaff().getName());
		System.out.println("Staff ID: " + o.getStaff().getStaffID());
		System.out.println("Order ID: " + o.getOrderId());
		System.out.println("Table ID: " + o.getTableId());
		printItemsOf(o);
	}
	
	/**
	 * @param order takes in order
	 * prints out number of items for each item
	 */
	public void printItemsOf(Order order) {
		ArrayList<MenuItem> items = order.getItems(); 
		
		boolean visited[] = new boolean[items.size()]; 
	      
	    Arrays.fill(visited, false); 
	  
	    // Traverse through array elements and 
	    // count frequencies 
	    for (int i = 0; i < items.size(); i++) { 
	  
	        // Skip this element if already processed 
	        if (visited[i] == true) 
	            continue; 
	  
	        // Count frequency 
	        int count = 1; 
	        for (int j = i + 1; j < items.size(); j++) { 
	            if (items.get(i) == items.get(j)) { 
	                visited[j] = true; 
	                count++; 
	            } 
	        } 
	        System.out.println(count + "\t" + items.get(i).getName()); 
	    } 
		System.out.println("");
	} 

	/**
	 * @param tableID takes in associated tableID
	 * @return finds corresponding order with tableID
	 */
	public Order findOrder(int tableID) {
		Order corrOrder = null;
		for (int i = 0; i < orderArr.size(); i++) {
			//System.out.println(orderArr.get(i).getTableId());			
			if (orderArr.get(i).getTableId() == tableID) {
				corrOrder = orderArr.get(i);
				break;
			}
		}
		if (corrOrder == null)
			System.out.println("Invalid table ID");
		return corrOrder;
	}
	
	public MenuItem getMenuItemFromOrder(ArrayList<MenuItem> items, String name) {
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).getName().equalsIgnoreCase(name)) {
				return items.get(i);
			}
		}

		return null;
	}
}
