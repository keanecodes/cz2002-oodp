package ntu.scse.cz2002.restaurant.view;

import java.util.InputMismatchException;
import java.util.Scanner;

import ntu.scse.cz2002.restaurant.control.*;


/**
 * *Boundary Class to Print Invoices
 * @author He Zeqing
 *@version 1.0
 *@since 2019-4-17
 */

public class InvoiceView{

	//private OrderController orderManager = new OrderController();
	//private StaffController sCtrl = new StaffController();
	private static InvoiceController iCtrl = new InvoiceController();
	static Scanner sc = new Scanner(System.in);

	
	/**
	Prints list of Past Invoices, & user chooses invoices to print based on invoice id
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
				
				int invID = -1;
				int invoiceRange = -1;
				do {
					System.out.print("\nInvoice ID\t: ");
					try{
						invID = sc.nextInt();
						
						invoiceRange = iCtrl.getInvoiceListSize()-1;
						
						if (invID > invoiceRange || invID < 0 )
							System.out.println("\nInvalid quanity input. Invoice range 0 - " + invoiceRange + " expected.");
					}catch(InputMismatchException ex) {
						System.out.println("\nInvalid quanity input. Non-decimal value expected.");
						sc.nextLine();
						continue;
					}
					
				} while ((invID != -1) && invID > invoiceRange|| invID < 0);
				iCtrl.printInvoicebyID(invID);}
	}
	
}
