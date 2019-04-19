package ntu.scse.cz2002.restaurant.control;

import ntu.scse.cz2002.restaurant.model.Order;
import ntu.scse.cz2002.restaurant.model.Staff;
import ntu.scse.cz2002.restaurant.model.MenuItem;
import ntu.scse.cz2002.restaurant.data.DataAccessor;
import ntu.scse.cz2002.restaurant.model.Menu;
import ntu.scse.cz2002.restaurant.model.Promotion;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class OrderController {
	private Order order;
	StaffController staffManager = new StaffController();
	MenuController menuManager = new MenuController();
	private TableController tCtrl;
	
	Menu menu = menuManager.getMenu();
	private ArrayList<Order> orderArr = new ArrayList<Order>();
	
	public OrderController() { }
	
	public OrderController(TableController tCtrl) {
		this.tCtrl = tCtrl;
	}
	
	public void setOrderArr(ArrayList<Order> oList) {
		this.orderArr = oList;
	}
	
	public Order createOrder(Staff staff, int tableID) {
		
		int orderID = orderArr.size();
		order = new Order(staff, orderID, tableID);
		orderArr.add(order);
		
		return order;
	}

	public void addOrderItem(Order order, String itemName) {
		MenuItem item = menu.getItem(itemName);
		if (item != null)
			order.addItem(item);
		else
			System.out.println("Item does not exist!");
	}

	public void removeOrderItem(Order order, String itemName) {
		MenuItem item = menu.getItem(itemName);
		if (item != null)
			order.removeItem(item);
		else
			System.out.println("Item does not exist!");
	}

	public void displayOrder(Order o) {
		System.out.println("Staff Information: ");
		System.out.println("Staff name: " + o.getStaff().getName());
		System.out.println("Staff gender: " + o.getStaff().getGender());
		System.out.println("Staff ID: " + o.getStaff().getStaffID());
		System.out.println("Staff Job Title: " + o.getStaff().getJobTitle());
		System.out.println("Order ID: " + o.getOrderId());
		System.out.println("Table ID: " + o.getTableId());
		printItemsOf(o);
	}
	
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

	public Order findOrder(int tableID) {
		Order corrOrder = null;
		for (int i = 0; i < orderArr.size(); i++) {
			if (orderArr.get(i).getTableId() == tableID) {
				corrOrder = orderArr.get(i);
				break;
			}
		}
		if (corrOrder == null)
			System.out.println("Invalid table ID");
		return corrOrder;
	}
}
