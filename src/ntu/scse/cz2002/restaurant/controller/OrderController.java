package ntu.scse.cz2002.restaurant.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import ntu.scse.cz2002.restaurant.model.Menu;
import ntu.scse.cz2002.restaurant.model.MenuItem;
import ntu.scse.cz2002.restaurant.model.Order;
import ntu.scse.cz2002.restaurant.model.Staff;

public class OrderController {
	private Order order;
	private static int orderID = 0;
	StaffController staffController;
	public static ArrayList <Order> orderArr = new ArrayList <Order>();
	
	public OrderController() throws IOException, ParseException {
		staffController = new StaffController();
	}
	
	public Order createOrder(int staffID, int tableID) {
		Staff corrStaff = staffController.findStaff(staffID);
		orderID += 1;
		order = new Order(corrStaff, orderID, tableID);
		orderArr.add(order);
		return order;
	}
	
	/*public void addOrderItem(Order order, String itemName) {
		MenuItem item = Menu.getMenuItem(itemName);
		if (item != null)
			order.addItem(item);
		else
			System.out.println("Item does not exist!");
	}*/
	
	/*public void removeOrderItem(Order order, String itemName) {
		MenuItem item = Menu.getMenuItem(itemName);
		if (item != null)
			order.removeItem(item);
		else
			System.out.println("Item does not exist!");
	}*/
	
	public void displayOrder(Order o) {
		System.out.println("Staff Information: ");
		System.out.println("Staff name: " + o.getStaff().getName());
		System.out.println("Staff gender: " + o.getStaff().getGender());
		System.out.println("Staff ID: " + o.getStaff().getStaffID());
		System.out.println("Staff Job Title: " + o.getStaff().getJobTitle());
		System.out.println("Order ID: " + o.getOrderId());
		System.out.println("Table ID: " + o.getTableId());
		System.out.println("Items ordered: " + o.getItems());
	}
	
	public Order findOrder(int orderID) {
		Order corrOrder = null;
		for (int i = 0; i < orderArr.size(); i ++) {
			if (orderArr.get(i).getOrderId() == orderID) {
				corrOrder = orderArr.get(i);
				break;
			}
		}
		if (corrOrder == null)
			System.out.println("Invalid staff ID");
		return corrOrder;
	}
}
