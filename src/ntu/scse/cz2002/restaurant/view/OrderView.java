package ntu.scse.cz2002.restaurant.view;
import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

import ntu.scse.cz2002.restaurant.controller.OrderController;
import ntu.scse.cz2002.restaurant.model.Order;

public class OrderView {

	public void OrderUI() throws IOException, ParseException {
		Scanner sc = new Scanner(System.in);
		int choice, orderID;
		Order currentOrder;
		OrderController orderController = new OrderController();
		do {
			System.out.println("Select an option to proceed: ");
			System.out.println("1. Add an order");
			System.out.println("2. Edit an order");
			System.out.println("3. View an order");
			System.out.println("4. Back");
			
			choice = sc.nextInt();
			switch(choice) {
				case 1:
					System.out.println("Enter staffID: ");
					int staffID = sc.nextInt();
					System.out.println("Enter tableID: ");
					int tableID = sc.nextInt();
					System.out.println("Creating Order ...");

					Order order = orderController.createOrder(staffID, tableID);
					editOrderUI(order, orderController);
					break;
				case 2:
					System.out.println("Enter orderID: ");
					orderID = sc.nextInt();
					currentOrder = orderController.findOrder(orderID);
					editOrderUI(currentOrder, orderController);
					break;
				case 3:
					System.out.println("Enter orderID: ");
					orderID = sc.nextInt();
					currentOrder = orderController.findOrder(orderID);
					orderController.displayOrder(currentOrder);
					break;
				case 4:
					break;
				default: 
					System.out.println("Invalid choice!");
					break;
			} 
		
		} while(choice != 4);
	}
	
	public void editOrderUI(Order order, OrderController orderController) {
		int itemChoice;
		Scanner sc = new Scanner(System.in);
		String itemName;
		do {
			System.out.println("Select an option to proceed: ");
			System.out.println("1. Add an item");
			System.out.println("2. Remove an item");
			System.out.println("3. Done");
			itemChoice = sc.nextInt();
			
			switch(itemChoice) {
				case 1:
					System.out.println("Enter name of item: ");
					itemName = sc.next();
					//orderController.addOrderItem(order, itemName);
					System.out.println(itemName + " has been added to order");
					break;
				case 2:
					System.out.println("Enter name of item: ");
					itemName = sc.next();
					//orderController.removeOrderItem(order, itemName);
					System.out.println(itemName + " has been removed from order");
					break;
				case 3:
					break;
				default: 
					System.out.println("Invalid choice!");
					break;
			}
		} while (itemChoice != 3);
	}

}
