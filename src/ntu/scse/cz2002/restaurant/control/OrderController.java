package ntu.scse.cz2002.restaurant.control;
import ntu.scse.cz2002.restaurant.model.Order;
import ntu.scse.cz2002.restaurant.model.Staff;
import ntu.scse.cz2002.restaurant.model.MenuItem;
import ntu.scse.cz2002.restaurant.model.Menu;
import ntu.scse.cz2002.restaurant.model.Promotion;
import java.util.ArrayList;


public class OrderController {
	private Order order;
	private static int orderID = 0;
	StaffController staffManager = new StaffController();
	MenuController menuManager = new MenuController();
	Menu menu = menuManager.getMenu();
	public static ArrayList <Order> orderArr = new ArrayList <Order>();
	
	public OrderController() {}
	
	public Order createOrder(int staffID, int tableID) {
		Staff corrStaff = staffManager.findStaff(staffID);
		orderID += 1;
		order = new Order(corrStaff, orderID, tableID);
		orderArr.add(order);
		return order;
	}
	
	public void addOrderItem(Order order, String itemName) {
		MenuItem item = menu.getMenuItem(itemName);
		if (item != null)
			order.addItem(item);
		else
			System.out.println("Item does not exist!");
	}
	
	public void removeOrderItem(Order order, String itemName) {
		MenuItem item = menu.getMenuItem(itemName);
		if (item != null)
			order.removeItem(item);
		else
			System.out.println("Item does not exist!");
	}
	
	public void addPromotion(Order order, String itemName) {
		Promotion promotion = menu.getPromotion(itemName);
		if (promotion != null)
			order.addPromotion(promotion);
		else
			System.out.println("Promotion does not exist!");
	}
	
	public void removePromotion(Order order, String itemName) {
		Promotion promotion = menu.getPromotion(itemName);
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
