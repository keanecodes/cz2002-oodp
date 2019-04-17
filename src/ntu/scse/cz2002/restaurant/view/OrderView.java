package ntu.scse.cz2002.restaurant.view;

import java.util.Scanner;
import java.util.InputMismatchException;
import ntu.scse.cz2002.restaurant.model.Order;
import ntu.scse.cz2002.restaurant.control.OrderController;

public class OrderView {

	public void OrderUI() {
		Scanner sc = new Scanner(System.in);
		int choice = 0, orderID;
		Order currentOrder;
		OrderController orderManager = new OrderController();
		do {
			System.out.println("Order Submenu");
			System.out.println("_____________");
			System.out.println("Select an option to proceed: ");
			System.out.println("1. Add an order");
			System.out.println("2. Edit an order");
			System.out.println("3. View an order");
			System.out.println("4. Back");
			System.out.println("Please enter your choice (1 - 4): ");

			try {
				choice = sc.nextInt();
			} catch (InputMismatchException ex) {
				System.out.println("Invalid input! Please try again..");
				sc.nextLine(); // Clear the garbage input
				continue;
			}
			
			switch (choice) {
			case 1:
				System.out.println("Enter staffID: ");
				int staffID = sc.nextInt();
				System.out.println("Enter tableID: ");
				int tableID = sc.nextInt();
				System.out.println("Creating Order ...");

				Order order = orderManager.createOrder(staffID, tableID);
				editOrderUI(order, orderManager);
				break;
			case 2:
				System.out.println("Enter orderID: ");
				orderID = sc.nextInt();
				currentOrder = orderManager.findOrder(orderID);
				editOrderUI(currentOrder, orderManager);
				break;
			case 3:
				System.out.println("Enter orderID: ");
				orderID = sc.nextInt();
				currentOrder = orderManager.findOrder(orderID);
				orderManager.displayOrder(currentOrder);
				break;
			case 4:
				break;
			default:
				System.out.println("Invalid choice!");
				break;
			}

		} while (choice != 4);
	}

	public void editOrderUI(Order order, OrderController orderManager) {
		int itemChoice;
		Scanner sc = new Scanner(System.in);
		String itemName;
		do {
			System.out.println("Select an option to proceed: ");
			System.out.println("1. Add an item");
			System.out.println("2. Remove an item");
			System.out.println("3. Add a promotion");
			System.out.println("4. Remove a promotion");
			System.out.println("5. Done");
			itemChoice = sc.nextInt();

			switch (itemChoice) {
			case 1:
				System.out.println("Enter name of item: ");
				itemName = sc.next();
				orderManager.addOrderItem(order, itemName);
				System.out.println(itemName + " has been added to order");
				break;
			case 2:
				System.out.println("Enter name of item: ");
				itemName = sc.next();
				orderManager.removeOrderItem(order, itemName);
				System.out.println(itemName + " has been removed from order");
				break;
			case 3:
				System.out.println("Enter name of promotion: ");
				itemName = sc.next();
				orderManager.addPromotion(order, itemName);
				System.out.println(itemName + " has been added to order");
				break;
			case 4:
				System.out.println("Enter name of promotion: ");
				itemName = sc.next();
				orderManager.removePromotion(order, itemName);
				System.out.println(itemName + " has been removed from order");
				break;
			case 5:
				break;
			default:
				System.out.println("Invalid choice!");
				break;
			}
		} while (itemChoice != 3);
	}

}
