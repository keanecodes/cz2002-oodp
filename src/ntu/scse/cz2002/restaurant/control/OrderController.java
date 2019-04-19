package ntu.scse.cz2002.restaurant.control;

import ntu.scse.cz2002.restaurant.model.Order;
import ntu.scse.cz2002.restaurant.model.Staff;
import ntu.scse.cz2002.restaurant.model.MenuItem;
import ntu.scse.cz2002.restaurant.data.DataAccessor;
import ntu.scse.cz2002.restaurant.model.Menu;
import ntu.scse.cz2002.restaurant.model.Promotion;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class OrderController {
	private final static String DATA_FILE = "order.dat";
	private Order order;
	StaffController staffManager = new StaffController();
	MenuController menuManager = new MenuController();
	Menu menu = menuManager.getMenu();
	private ArrayList<Order> orderArr = (ArrayList<Order>) DataAccessor.readList(DATA_FILE);

	public OrderController() {

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

	public void addPromotion(Order order, String itemName) {
		Promotion promotion = (Promotion) menu.getItem(itemName);
		if (promotion != null)
			order.addPromotion(promotion);
		else
			System.out.println("Promotion does not exist!");
	}

	public void removePromotion(Order order, String itemName) {
		Promotion promotion = (Promotion) menu.getItem(itemName);
		if (promotion != null)
			order.removePromotion(promotion);
		else
			System.out.println("Promotion does not exist!");
	}

	public void displayOrder(Order o) {
		System.out.println("Staff Information: ");
		System.out.println("Staff name: " + o.getStaff().getName());
		System.out.println("Staff gender: " + o.getStaff().getGender());
		System.out.println("Staff ID: " + o.getStaff().getStaffID());
		System.out.println("Staff Job Title: " + o.getStaff().getJobTitle());
		System.out.println("Order ID: " + o.getOrderId());
		System.out.println("Table ID: " + o.getTableId());
		System.out.println("Items ordered: " + o.getItems());
		System.out.println("Promotions ordered: " + o.getPromotions());
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
	
	public void saveOrderArray() {
		for (int i = 0; i < orderArr.size(); i++) {
			System.out.println(orderArr.get(i).getOrderId());
		}
		DataAccessor.write(DATA_FILE, orderArr);
		
	}

	public Order findOrder(int orderID) {
		Order corrOrder = null;
		for (int i = 0; i < orderArr.size(); i++) {
			if (orderArr.get(i).getOrderId() == orderID) {
				corrOrder = orderArr.get(i);
				break;
			}
		}
		if (corrOrder == null)
			System.out.println("Invalid order ID");
		return corrOrder;
	}
}
