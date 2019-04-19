package ntu.scse.cz2002.restaurant.view;

import java.util.Scanner;

import ntu.scse.cz2002.restaurant.control.InvoiceController;
import ntu.scse.cz2002.restaurant.control.MenuController;
import ntu.scse.cz2002.restaurant.control.OrderController;
import ntu.scse.cz2002.restaurant.control.StaffController;
import ntu.scse.cz2002.restaurant.model.RestaurantRevenue;

public class InvoiceView {

	//private OrderController orderManager = new OrderController();
	//private StaffController sCtrl = new StaffController();
	private static InvoiceController iCtrl = new InvoiceController();
	Scanner sc = new Scanner(System.in);
	
	
	public void showInvoice() {
		System.out.println("// Print Past Invoices//------------\n" +   
                "--------------------------------------------------\n" +
                "Past Invoices\n" +
                "--------------------------------------------------");
				iCtrl.printItemsByID();
				System.out.println("--------------------------------------------------\n" +
                "Key in respective item name to add \n" +
                "--------------------------------------------------");
				System.out.print("Invoice ID\t: ");
				int invID = sc.nextInt();
				iCtrl.findInvoicebyID(invID).printReceipt();
				break;
	}
	
}
