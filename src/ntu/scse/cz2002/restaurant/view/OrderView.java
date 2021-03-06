package ntu.scse.cz2002.restaurant.view;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import ntu.scse.cz2002.restaurant.control.MenuController;
import ntu.scse.cz2002.restaurant.control.OrderController;
import ntu.scse.cz2002.restaurant.control.TableController;
import ntu.scse.cz2002.restaurant.model.MenuItem;
import ntu.scse.cz2002.restaurant.model.Order;
import ntu.scse.cz2002.restaurant.model.Table;
import ntu.scse.cz2002.restaurant.util.Utilities;
/**
*Boundary Class for Orders
*@author  Goh Ying Ting
*@version 1.0
*@since   2019-04-17
*/
public class OrderView {
	/**
	 * creating an orderController
	 */
	private OrderController orderManager;
	/**
	 * creating a menuController
	 */
	private MenuController mCtrl = new MenuController();
	/**
	 * table controller attribute
	 */
	TableController tCtrl;

	/**
	 * @param tCtrl passes in the table controller
	 */
	public OrderView(TableController tCtrl) {
		this.tCtrl = tCtrl;
		orderManager = new OrderController(tCtrl);
	}
	
	Scanner sc = new Scanner(System.in);

	Order o;
	
	/**
	 * displays the main options that can be done with an order
	 */
	public void OrderUI() {
		String choice = "";
		int orderID, tableID;
		Table currentTable;
		

		do {
		System.out.println("\n// Order Management Control Center // --------------\n" +
                           "----------------------------------------------------\n" +
                           " Option\t| Option Description\n" +
                           "----------------------------------------------------\n" +
                           "  (C)\t| Create an order for a new table\n" +
                           "  (E)\t| Edit order for an existing table\n" +
                           "  (V)\t| View an order\n" +
                           "  (F)\t| Finalise an order\n");

		System.out.println("-----------------------------------------------------\n" +
                           "(<) Back\t\n" +
                           "-----------------------------------------------------");
		System.out.print("> ");
		
			
			choice = sc.next();
			
			switch (choice.toUpperCase()) {
				case "C":
					tableID = scanTableID();
					if (tableID == -1) continue;
					else {
						this.o = tCtrl.findTableById(tableID).getOrder();
						if (this.o.isOnGoing() == true) {
							System.out.println("This table has already made an order.\nTry editing the order.");
							continue;
						}
						this.o = orderManager.createOrder(tableID);
						if (this.o != null) 
							editOrderUI(this.o, orderManager);
					}
					break;
				case "E":
					tableID = scanTableID();
					
					if (tableID == -1) continue;
					else {
						this.o = tCtrl.findTableById(tableID).getOrder();
						
						if (this.o.isOnGoing() == true) 
							editOrderUI(this.o, orderManager);
						else
							System.out.println("This table has yet to make an order.");
					}
					break;
				case "V":
					tableID = scanTableID();
					
					if (tableID == -1) continue;
					else {
						this.o = tCtrl.findTableById(tableID).getOrder();
						if (this.o.getItems().size() > 0) 
							orderManager.displayOrder(this.o);
						else System.out.println("This table has not make an order");
					}
					break;
				case "F":			
					tableID = scanTableID();
					
					if (tableID == -1) continue;
					else {
						currentTable = tCtrl.findTableById(tableID);
						
						if (currentTable.getOrder().getItems().size() > 0) {
							System.out.println("Order has been sent for processing!\n");
							tCtrl.releaseTable(currentTable);
							this.o = currentTable.getOrder();
						} else System.out.println("This table has not make an order");
					}
					break;
				case "<":
					Utilities.clearScreen(); MainRestaurantView.show();
					break;
				default:
					System.out.println("\nInvalid input. Refer to the option table.");
					System.out.print("> ");
					break;
			}

		} while (!choice.equalsIgnoreCase("<"));
	}

	/**
	 * displays the options associated with editing the order 
	 * @param order takes in the order to be edited
	 * @param orderManager takes in orderManager to edit orders
	 * 
	 */
	public void editOrderUI(Order order, OrderController orderManager) {
		String choice;
		String itemName;
		int count = -1;
		boolean pass = false;
		
		printManagementControls(order);


		do {
			choice = sc.next();
			sc.nextLine();
			
			switch (choice.toUpperCase()) {
				case "A":
					Utilities.newScreenHeader();
					System.out.println("\n// Manage Order - Add item to order //--------------\n" +   
                                       "----------------------------------------------------\n" +
                                       "List of menu items/ promotions to add to order\n" +
                                       "----------------------------------------------------");
					mCtrl.printItemsByName();
					System.out.println("----------------------------------------------------\n" +
					                   "Key in respective item name/ promotions to add \n" +
					                   "----------------------------------------------------");
					
					itemName = scanItemName("add");
					count = scanItemQty();
					
					
					for (int i = 0; i < count; i++) { 
						if (!orderManager.addOrderItem(order, itemName)) {
							pass = false;
							break;
						}
						this.o = order;
					}
					
					
					if (pass)
						System.out.println(count + " " + itemName + " has been added to order");
					
					printManagementControls(order);
					break;
				case "R":
					Utilities.newScreenHeader();
					System.out.println("\n// Manage Order - Remove item from order //---------\n" +
                                       "----------------------------------------------------\n" +
                                       "List of menu items/ promotions to remove from order\n" +
                                       "----------------------------------------------------");
					orderManager.printItemsOf(order);
					System.out.println("----------------------------------------------------\n" +
			                           "Key in respective item name/ promotions to remove \n" +
			                           "----------------------------------------------------");
					
					itemName = scanItemName("remove");
					count = scanItemQty();
					
					for (int i = 0; i < count; i++) 
						if (!orderManager.removeOrderItem(order, itemName)) {
							pass = false;
							break;
						}
					
					
					if (pass)
						System.out.println(count + " " + itemName + " has been removed from order");
					
					printManagementControls(order); 
					break;
				case "<":
					if (this.o.getItems().size() == 0) {
						System.out.println("Order is empty. Cancelling order..");
						if (orderManager.cancelOrder(this.o.getTableId()))
							System.out.println("Order cancelled.");
					}
					
					Utilities.newScreenHeader(); OrderUI(); break;
				default:
					System.out.println("\nInvalid input. Refer to the option table.");
					System.out.print("> ");
					break;
			}
		} while (!choice.equalsIgnoreCase("CANCEL"));
	}
	
	private int scanTableID() {
		int tableID = 0;
		
		do {
			System.out.println("\nEnter tableID (-1 to cancel)");
			System.out.print("> ");
			try{
				tableID = sc.nextInt();
				
				if (tableID == -1) break;
				else if (tableID > 30 || tableID <= 0 )
					System.out.println("\nInvalid tableID input. TableID range 1 - 30 expected.");
				
			} catch(InputMismatchException ex) {
				System.out.println("\nInvalid tableID input. Non-decimal value expected.\n");
				sc.nextLine();
				continue;
			}
		} while ((tableID != -1) && tableID > 30 || tableID <= 0);
		
		return tableID;
	}
	
	private int scanItemQty() {
		int count = 0;
		
		do {
			System.out.print("\nQuantity (-1 to cancel)\t: ");
			try{
				count = sc.nextInt();
				
				if (count == -1) break;
				else if (count > 50 || count <= 0 )
					System.out.println("\nInvalid quanity input. quantity range 1 - 50 expected.");
			}catch(InputMismatchException ex) {
				System.out.println("\nInvalid quanity input. Non-decimal value expected.");
				sc.nextLine();
				continue;
			}
			
		} while ((count != -1) && count > 50 || count <= 0);
		
		return count;
	}
	
	private String scanItemName(String usecase) {
		String itemName = null;
		
		do {
			System.out.print("Item name\t: ");
			itemName = sc.nextLine();
			if (usecase == "add") {
				if (mCtrl.getMenu().getItem(itemName) == null) {
					System.out.println("\nItem does not exist!\n");
					itemName = null;
				}				
			} else if (usecase == "remove"){
				
				ArrayList<MenuItem> orderMenuItems = this.o.getItems();
				
				for (int i = 0; i < orderMenuItems.size(); i++) 
					if (orderMenuItems.get(i).getName().equalsIgnoreCase(itemName)) 
						return itemName;
				
				
				System.out.println("\nItem does not exist!\n");
				itemName = null;
			}

			
		} while (itemName == null); 
		
		return itemName;
	}
	
	/**
	 * @param order takes in the order
	 * prints out associated items
	 */
	private void printManagementControls(Order order) {
		//Utilities.newScreenHeader();
		if (order.getItems().size() < 1) {
			System.out.println("\n----------------------------------------------------\n" +
							   "// Order Items Management //------------------------\n" + 
							   "----------------------------------------------------\n" +
							   "Order Details\n" +
							   "----------------------------------------------------\n" +
							   "\n\nOrder is currently empty.\n\n\n" +
							   "----------------------------------------------------\n" +
							   "(A) Add Menu Items" );
		} else  {
			System.out.println("\n// Order Items Management  // ----------------------\n" +   
							   "----------------------------------------------------\n" +
							   "Order ID: " + order.getOrderId() + "\tTable ID: " + order.getTableId() + "\tServer: " + order.getStaff().getName() + "\n" +
							    "----------------------------------------------------");
			
			orderManager.printItemsOf(order);
			
			System.out.println("----------------------------------------------------\n" +
							   "   Menu Items\t| (A) Add\t(R) Remove\n");
		}
		System.out.println("----------------------------------------------------\n" +  
						 "(<) Back to Order Management\n" + 
						 "----------------------------------------------------");
		System.out.print("> ");
	}
	

}
