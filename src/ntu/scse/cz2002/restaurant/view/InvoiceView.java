package ntu.scse.cz2002.restaurant.view;

import java.util.Scanner;

import ntu.scse.cz2002.restaurant.control.InvoiceController;
import ntu.scse.cz2002.restaurant.control.MenuController;
import ntu.scse.cz2002.restaurant.control.OrderController;
import ntu.scse.cz2002.restaurant.control.StaffController;
import ntu.scse.cz2002.restaurant.control.TableController;
import ntu.scse.cz2002.restaurant.model.RestaurantRevenue;


/**
*Boundary Class to Print Invoices
*/
public class InvoiceView{

	//private OrderController orderManager = new OrderController();
	//private StaffController sCtrl = new StaffController();
	private static InvoiceController iCtrl = new InvoiceController();
	static Scanner sc = new Scanner(System.in);

	
	/**
	*Prints list of Past Invoices, & user chooses invoices to print based on invoice id
	*/
	public static void showInvoice() {
		System.out.println("// Print Past Invoices//------------\n" +   
                "--------------------------------------------------\n" +
                "Past Invoices\n" +
                "--------------------------------------------------");
				if (iCtrl.printItemsByID()) {
				System.out.println("--------------------------------------------------\n" +
                "Key in Invoice to Print: \n" +
                "--------------------------------------------------");
				System.out.print("Invoice ID\t: ");
				int invID = sc.nextInt();
				iCtrl.printInvoicebyID(invID);}
	}
	
}
