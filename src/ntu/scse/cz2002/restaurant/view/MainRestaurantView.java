package ntu.scse.cz2002.restaurant.view;

import java.util.Scanner;

import ntu.scse.cz2002.restaurant.control.TableController;
import ntu.scse.cz2002.restaurant.util.Utilities;

/**
 * 
 * @author moongee
 *@version 1.0
 *@since 2019-4-17
 */

public class MainRestaurantView {
	
	/**
	 * 
	 */
	private static TableController tCtrl = new TableController();
	
	private static TableView tView = new TableView(tCtrl);
	
	/**
	 * 
	 */
	private static OrderView oView = new OrderView(tCtrl);
	/**
	 * 
	 */
	private static ReservationView rView = new ReservationView(tCtrl);
	/**
	 * 
	 */
	private static MenuView mView = new MenuView();
	//private static InvoiceView iView = new InvoiceView();
	
	/**
	 loop to choose options from MainMenu
	 * 
	 */
	public static void show() {
		Scanner sc = new Scanner(System.in);

		String choice = "";
		
		printMainRestaurantView();
			
		do {
			choice = sc.next();
		      
			switch(choice.toUpperCase()) {
				case "O": 
					Utilities.newScreenHeader();
					oView.OrderUI(); break;
				case "T":
					//Utilities.newScreenHeader();
					tView.showTables();
					printMainRestaurantView(); break;
				case "R":
					Utilities.newScreenHeader();
					rView.ReservationUI(); break;
				case "M":
					Utilities.newScreenHeader();
					mView.MenuUI(); break;
				//Restaurant review systems
				case "I":
					Utilities.newScreenHeader();
					InvoiceView.showInvoice(); 
					printMainRestaurantView();
					break;
				case "P":
					Utilities.newScreenHeader();
					SalesReportView.showSalesReport();
					printMainRestaurantView();
					break;
				case "CHANGE":
					Utilities.newScreenHeader();
					StaffView.showChangeStaffForm();
					Utilities.clearScreen();
					printMainRestaurantView();
					break;
				case "END": 
					Utilities.newScreenHeader();
					System.out.println("RRPPS session ends ... Goodbye!");
					break;
				default:
					System.out.println("Invalid input. Refer to the option table.");
					System.out.print("> ");
					break;
			}
			
		} while (!choice.equalsIgnoreCase("End"));
	
	}
	
	/**
	 * print MainMenu
	 */
	public static void printMainRestaurantView() {
		System.out.println("**************************************************\n" + 
                           "************** Happy Kopitiam RRPPS **************\n" +
                           "**************************************************\n\n");

		StaffView.showCurrentStaffInfo();
		
		System.out.println("// Restaurant Management Systems // --------------\n" +
		                "--------------------------------------------------\n" +
		                " Option\t| Option Description\n" +
		                "--------------------------------------------------\n" +
		                "  (O)\t| Orders\n" + 
		                "  (T)\t| Table Availability\n" + 
		                "  (R)\t| Reservation\n" + 
		                "  (M)\t| Menu\n\n");
		
		System.out.println("// Restaurant Review Systems // -------------------\n" +
		                "---------------------------------------------------\n" +
		                " Option\t| Option Description\n" +
		                "---------------------------------------------------\n" +
		                "  (I)\t| Invoices\n" + 
		                "  (P)\t| Print Revenue Report\n\n");
		
		
		System.out.println("---------------------------------------------------\n" +
		                "(End) End Session\t (Change) Change Operator\n" +
		                "---------------------------------------------------");
		System.out.print("> ");
	}
	
}
