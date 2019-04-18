package ntu.scse.cz2002.restaurant.view;

import java.util.Scanner;
import ntu.scse.cz2002.restaurant.model.Order;
import ntu.scse.cz2002.restaurant.model.Staff;
import ntu.scse.cz2002.restaurant.util.Utilities;
import ntu.scse.cz2002.restaurant.control.OrderController;
import ntu.scse.cz2002.restaurant.control.StaffController;

public class OrderView {
	
	private OrderController orderManager = new OrderController();
	private StaffController sCtrl = new StaffController();

	public void OrderUI() {
		Scanner sc = new Scanner(System.in);
		String choice = "";
		int orderID;
		Order currentOrder;
		
		
		System.out.println("// Order Management // ---------------------------\n" +
                           "--------------------------------------------------\n" +
                           " Option\t| Option Description\n" +
                           "--------------------------------------------------\n" +
                           "  (A)\t| Add an order\n" + 
                           "  (E)\t| Edit an order\n" + 
                           "  (V)\t| View an order\n");

		System.out.println("---------------------------------------------------\n" +
                           "(<) Back\t\n" +
                           "---------------------------------------------------");
		System.out.print("> ");
		do {
			
			choice = sc.next();
			
			switch (choice.toUpperCase()) {
				case "A":
					Order order = inputNewOrderConfig();
					editOrderUI(order, orderManager);
					break;
				case "E":
					System.out.println("Enter orderID: ");
					orderID = sc.nextInt();
					currentOrder = orderManager.findOrder(orderID);
					editOrderUI(currentOrder, orderManager);
					break;
				case "V":
					System.out.println("Enter orderID: ");
					orderID = sc.nextInt();
					currentOrder = orderManager.findOrder(orderID);
					orderManager.displayOrder(currentOrder);
					break;
				case "<":
					Utilities.clearScreen(); MainRestaurantView.show();
					break;
				default:
					System.out.println("Invalid input. Refer to the option table.");
					System.out.print("> ");
					break;
			}

		} while (!choice.equalsIgnoreCase("<"));
	}
	
	public Order inputNewOrderConfig() {
		
		char ans;
		
		Scanner sc = new Scanner(System.in);
		
		Staff currentStaff = sCtrl.getStaff();
		Utilities.newScreenHeader();
		System.out.println("// Make Order  // --------------------------------\n" +   
				           "--------------------------------------------------\n" +
                           "(1/2) Please Provide Basic Order Details\n" + 
                           "--------------------------------------------------"); 
		
		
		System.out.print("Make order under operator " + sCtrl.getStaff().getName() + "? (y/N)\n");
		System.out.print("> ");
		
		do {
			ans = sc.next().charAt(0);
			
			if (ans == 'y' || ans == 'N')
				break;
			else {
				System.out.println("Invalid input. Expected 2 options: y or N ");
				System.out.print("> ");
			}
				
		} while (ans != 'y' || ans != 'N');
		
		if (ans == 'N') {
			System.out.println("\n");
			StaffView.showChangeStaffForm();
			inputNewOrderConfig();
		}
		
		System.out.print("Table ID\t: ");
		int tableID = sc.nextInt();
		
		Order order = orderManager.createOrder(currentStaff, tableID);
		
		return order;
	}

	public void editOrderUI(Order order, OrderController orderManager) {
		String choice;
		Scanner sc = new Scanner(System.in);
		String itemName;
		
		System.out.println("--------------------------------------------------\n" +
                           "(2/2) Order Items Management \n" + 
                           "--------------------------------------------------\n" +
                           "Order Details\n" +
                           "--------------------------------------------------\n" +
                           "\n\nOrder is currently empty.\n\n\n" +
                           "--------------------------------------------------\n" +
                           "\tItems\t| (AI) Add\t(RI) Remove\n" +  
                           "   Promotions\t| (AP) Add\t(RP) Remove\n" +
                           "--------------------------------------------------\n" +
                           "\t(DONE)\t| Save Changes. \n\t\t| Add to Table's Invoice\n" +
                           "--------------------------------------------------\n" +
                           "      (CANCEL)  | Discard Changes.\n\t\t| Cancel Order\n" + 
                           "--------------------------------------------------");
		System.out.print("> ");
		do {
			choice = sc.next();

			switch (choice.toUpperCase()) {
				case "AI":
					Utilities.newScreenHeader();
					System.out.println("// Make Order  // --------------------------------\n" +   
                                       "--------------------------------------------------\n" +
                                       "Add Item to Order\n" +
                                       "--------------------------------------------------");
					System.out.print("Item name: ");
					itemName = sc.next();
					orderManager.addOrderItem(order, itemName);
					System.out.println(itemName + " has been added to order");
					break;
				case "RI":
					System.out.println("Enter name of item: ");
					itemName = sc.next();
					orderManager.removeOrderItem(order, itemName);
					System.out.println(itemName + " has been removed from order");
					break;
				case "AP":
					System.out.println("Enter name of promotion: ");
					itemName = sc.next();
					orderManager.addPromotion(order, itemName);
					System.out.println(itemName + " has been added to order");
					break;
				case "RP":
					System.out.println("Enter name of promotion: ");
					itemName = sc.next();
					orderManager.removePromotion(order, itemName);
					System.out.println(itemName + " has been removed from order");
					break;
				case "DONE":
					break;
				case "CANCEL":
					System.out.println("Cancel Order. Confirm? (Y/n)");
					break;
				default:
					System.out.println("Invalid input. Refer to the option table.");
					System.out.print("> ");
					break;
			}
		} while (!choice.equalsIgnoreCase("CANCEL"));
	}

}
