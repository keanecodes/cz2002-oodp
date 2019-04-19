package ntu.scse.cz2002.restaurant.view;

import java.util.Scanner;

import ntu.scse.cz2002.restaurant.control.TableController;
import ntu.scse.cz2002.restaurant.util.Utilities;

public class MainRestaurantView {
	
	static TableController tCtrl = new TableController();
	
	public static void show() {
		Scanner sc = new Scanner(System.in);

		String choice = "";
		
		printMainRestaurantView();
			
		do {
			choice = sc.next();
		      
			switch(choice.toUpperCase()) {
				case "O": 
					Utilities.newScreenHeader();
					(new OrderView(tCtrl)).OrderUI(); break;
				case "R":
					Utilities.newScreenHeader();
					(new ReservationView()).ReservationUI(); break;
				case "M":
					Utilities.newScreenHeader();
					(new MenuView()).MenuUI(); break;
				//Restaurant review systems
				case "I":
					//(newInvoiceView(tCtrl)).InvoiceUI()?
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
