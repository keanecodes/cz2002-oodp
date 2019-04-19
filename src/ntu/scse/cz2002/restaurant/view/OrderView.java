package ntu.scse.cz2002.restaurant.view;

import java.util.InputMismatchException;
import java.util.Scanner;
import ntu.scse.cz2002.restaurant.model.Order;
import ntu.scse.cz2002.restaurant.model.Table;
import ntu.scse.cz2002.restaurant.util.Utilities;
import ntu.scse.cz2002.restaurant.control.MenuController;
import ntu.scse.cz2002.restaurant.control.OrderController;
import ntu.scse.cz2002.restaurant.data.DataAccessor;

public class OrderView {
	
	private OrderController orderManager = new OrderController();
	private MenuController mCtrl = new MenuController();

	public void OrderUI() {
		Scanner sc = new Scanner(System.in);
		String choice = "";
		int orderID, tableID;
		Order currentOrder;
		
		do {
		orderManager.refreshOrderArr();
		System.out.println("// Order Management // ---------------------------\n" +
                           "--------------------------------------------------\n" +
                           " Option\t| Option Description\n" +
                           "--------------------------------------------------\n" +
                           "  (M)\t| Manage an order\n" +  
                           "  (V)\t| View an order\n" +
                           "  (F)\t| Finalise an order\n");

		System.out.println("---------------------------------------------------\n" +
                           "(<) Back\t\n" +
                           "---------------------------------------------------");
		System.out.print("> ");
		
			
			choice = sc.next();
			
			switch (choice.toUpperCase()) {
				case "M":
					System.out.println("Enter tableID: ");
					try{
						tableID = sc.nextInt();
					}catch(InputMismatchException ex) {
						System.out.println("Invalid tableID input!");
						break;
					}
					currentOrder = orderManager.findOrder(tableID);
					if (currentOrder != null) 
						editOrderUI(currentOrder, orderManager);
					break;
				case "V":
					System.out.println("Enter tableID: ");
					try{
						tableID = sc.nextInt();
					}catch(InputMismatchException ex) {
						System.out.println("Invalid tableID input!");
						break;
					}
					currentOrder = orderManager.findOrder(tableID);
					if (currentOrder != null) 
						orderManager.displayOrder(currentOrder);
					break;
				case "F":
					System.out.println("Enter tableID: ");
					try{
						tableID = sc.nextInt();
					}catch(InputMismatchException ex) {
						System.out.println("Invalid tableID input!");
						break;
					}
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

	public void editOrderUI(Order order, OrderController orderManager) {
		String choice;
		Scanner sc = new Scanner(System.in);
		String itemName;
		int count;
		boolean flag = false;
		
		if (order.getItems().size() == 0) {
			System.out.println("--------------------------------------------------\n" +
	                           "(2/2) Order Items Management \n" + 
	                           "--------------------------------------------------\n" +
	                           "Order Details\n" +
	                           "--------------------------------------------------\n" +
	                           "\n\nOrder is currently empty.\n\n\n" +
	                           "--------------------------------------------------\n" +
	                           "(A) Add Menu Items\t\n" +  
	                           "--------------------------------------------------\n" +
	                           "(<) Back to Order Management\n" + 
	                           "--------------------------------------------------");
			System.out.print("> ");
			
		} else printOverviewNControls(order);


		do {
			choice = sc.next();
			sc.nextLine();
			
			switch (choice.toUpperCase()) {
				case "A":
					Utilities.newScreenHeader();
					System.out.println("// Manage Order - Add item to order //------------\n" +   
                                       "--------------------------------------------------\n" +
                                       "List of menu items/ promotions to add to order\n" +
                                       "--------------------------------------------------");
					mCtrl.printItemsByName();
					System.out.println("--------------------------------------------------\n" +
					                   "Key in respective item name/ promotions to add \n" +
					                   "--------------------------------------------------");
					System.out.print("Item name\t: ");
					itemName = sc.nextLine();
					System.out.print("Quantity\t: ");					
					try{
						count = sc.nextInt();
					}catch(InputMismatchException ex) {
						System.out.println("Invalid item count input!");
						break;
					}
					for (int i = 0; i < count; i++) {
						orderManager.addOrderItem(order, itemName);
						flag = true;
					}
					if (flag == true)
						System.out.println(itemName + " has been added to order");
					
					printOverviewNControls(order); 
					break;
				case "R":
					Utilities.newScreenHeader();
					System.out.println("// Manage Order - Remove item from order //-------\n" +
                                       "--------------------------------------------------\n" +
                                       "List of menu items/ promotions to remove from order\n" +
                                       "--------------------------------------------------");
					orderManager.printItemsOf(order);
					System.out.println("--------------------------------------------------\n" +
			                           "Key in respective item name/ promotions to remove \n" +
			                           "--------------------------------------------------");
					System.out.print("Item name\t: ");
					itemName = sc.nextLine();
					System.out.print("Quantity\t: ");
					try{
						count = sc.nextInt();
					}catch(InputMismatchException ex) {
						System.out.println("Invalid item count input!");
						break;
					}
					for (int i = 0; i < count; i++) {
						orderManager.removeOrderItem(order, itemName);
						flag = true;
					}
					if (flag == true)
						System.out.println(itemName + " has been removed from order");
					
					printOverviewNControls(order); 
					break;
				case "DONE":
					orderManager.saveOrderArray();
					Utilities.newScreenHeader(); OrderUI();
					break;
				case "CANCEL": case "<":
					if (choice.equalsIgnoreCase("CANCEL")) {
						System.out.println("Cancel Order. Confirm? (Y/n)");
						System.out.print("> "); 
						choice = sc.next();
						if (choice.equalsIgnoreCase("Y")) {
							Utilities.newScreenHeader(); OrderUI(); break;
						} else if (choice.equalsIgnoreCase("n")) {
							System.out.print("> "); break;
						} else {
							System.out.println("Invalid input. Expected 2 options: Y or n ");
							System.out.print("> "); break;
						}
					} else {
						Utilities.newScreenHeader(); OrderUI(); break;
					}
					
				default:
					System.out.println("Invalid input. Refer to the option table.");
					System.out.print("> ");
					break;
			}
		} while (!choice.equalsIgnoreCase("CANCEL"));
	}
	
	private void printOverviewNControls(Order order) {
		Utilities.newScreenHeader();
		System.out.println("// Manage Order  // ------------------------------\n" +   
				           "--------------------------------------------------\n" +
				           "Order ID: " + order.getOrderId() + "\tTable ID: " + order.getTableId() + "\tServer: " + order.getStaff().getName() + "\n" +
                           "--------------------------------------------------\n");
        orderManager.printItemsOf(order);

		System.out.println("--------------------------------------------------\n" +
                           "   Menu Items\t| (A) Add\t(R) Remove\n" +  
                           "--------------------------------------------------\n" +
                           "\t(DONE)\t| Save Changes. \n\t\t| Add to Table's Invoice\n" +
                           "--------------------------------------------------\n" +
                           "      (CANCEL)  | Discard Changes.\n\t\t| Back to Order Management\n" + 
                           "--------------------------------------------------");
		System.out.print("> ");
	}
	

}
