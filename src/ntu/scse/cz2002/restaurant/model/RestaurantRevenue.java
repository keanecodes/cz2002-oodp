package ntu.scse.cz2002.restaurant.model;

import ntu.scse.cz2002.restaurant.control.InvoiceController;

import ntu.scse.cz2002.restaurant.util.CalendarFormatter;


//import java.time.*;
//import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;

/**
*Entity Class for SalesReport
*<br> currently not instantiated, as SalesReports need not be tracked or saved. This entity class can be used in the future.
*
*@author  He Zeqing
*@version 1.0
*@since   2019-04-17
*/
public class RestaurantRevenue { // needs to be reformatter & reworked.. is it a InvoiceManager or Does
									// SalesReport need its own class?
	// Invoice[] invoice_list;
	Calendar start;
	Calendar end;
	ArrayList<MenuItem> thingssold;
	double total_revenue;
	//String filename; //in invoice controller
	
//	public static DateTimeFormatter timeformat = DateTimeFormatter.ofPattern("dd-MM-yyy HH:mm:ss");

	// init entire Invoicelist
	//ArrayList<Invoice> invoice_list = (ArrayList<Invoice>) DataAccessor.readList(filename);

	/**
	*Constructor for SalesReport
	*@param invoicelist takes in any list of invoices to combine into a Sales Report
	*@deprecated not used, but might be needed in the future
	*/
	public RestaurantRevenue(ArrayList<Invoice> invoicelist) {
		start = null;
		end = null;
		total_revenue = sumAmount(invoicelist);
		thingssold = getitems(invoicelist);
	}

	/**
	*Constructor for SalesReport, by period
	*@param startdate FROM :Date of period in Calendar format
	*@param enddate TO:Date of period in Calendar format
	*/
	public RestaurantRevenue(Calendar startdate, Calendar enddate) {
		start = startdate;
		end = enddate;
		total_revenue = this.sumAmount(getInvListbyDatabase(startdate, enddate));
		thingssold = this.getitems(getInvListbyDatabase(startdate, enddate));
	}
	
	
	/**
	 * gets total Revenue of this SalesReport
	*@return total revenue for this SalesReport
	* 
	*/
	public double getttlrevnue() {
		return total_revenue;
	}
	
	/**
	*returns all items sold in this SalesReport
	*@return list of menuitmes that were sold
	*/
	public ArrayList<MenuItem> getthingssold() {
		return thingssold;
	}
	
	/**
	*returns time period for this report in a String for printing
	@return the formatted String
	*/
	public String getPeriod() {
		return CalendarFormatter.toString(start, 2) + " - " + CalendarFormatter.toString(end, 2);
	}
	
	/**
	*returns the appropriate list of invoices from database by period
	*used by constructor
	*/
	private ArrayList<Invoice> getInvListbyDatabase(Calendar star, Calendar endd) {
		InvoiceController iCtrl = new InvoiceController();
		return iCtrl.getInvoicelist(star, endd);
	}


	/**
	*calculates total revenue for this SalesReport
	*@param any list of invoices
	*/
	private double sumAmount(ArrayList<Invoice> invoicelist) {
		double totalAmt = 0;
		if (invoicelist ==null) {
			return 0;
		}
		for (Invoice _invoice : invoicelist) {
			totalAmt += _invoice.getAmt();
		}
		return totalAmt;
	}

	/**
	*gets all the menuItems from each of the Invoices in list
	*used by getMenuItems()
	*@param takes in any list of invoices
	*/
	private ArrayList<MenuItem> getitems(ArrayList<Invoice> ils) {
		ArrayList<MenuItem> items = new ArrayList<MenuItem>();
		if (ils == null){
			return null;
		}
		for (Invoice _invoice : ils) {
			Order _order = _invoice.getOrder();
			items.addAll(_order.getItems());
		}
		/*
		 * for (int i =0; i<ils.length; i++) { Order orders = ils[i].getOrder(); for
		 * (MenuItem item : orders.getItems()) { items.add(item); } }
		 */
		return items;
	}

	/**
	*Prints Sales report in appropriate format. 
	*@param stf takes in the staff that requested the sales report
	*	
	**/
	public void PrintSalesReport(Staff stf) {
		//System.out.println("Restaurant Name");
		//System.out.println("Address");
		System.out.println("");
		// System.out.print(CalendartoString(timestamp));

		final Object[][] tableheader = new String[2][];
		tableheader[0] = new String[] { "From:" + CalendarFormatter.toString(start, 2),
				"To: " + CalendarFormatter.toString(end, 2) }; // im considering just keeping table id information, not
																// entire table.
		tableheader[1] = new String[] { "Date of Request: " + CalendarFormatter.toString(Calendar.getInstance(), 2),
				"Time of Request: " + CalendarFormatter.toString(Calendar.getInstance(), 3) }; // what is the timestamp
																								// of request?

		for (final Object[] row : tableheader) {
			System.out.format("%-15s          %-15s\n", row);
		}

		System.out.println("----------------------------------");

		if (thingssold !=null) {
			
			boolean [] visited = new boolean [thingssold.size()];
			for (int i=0; i<thingssold.size(); i++) {
			if (visited[i]) {
				//System.out.println("Int i" + i);
			}
			else {
				visited[i] =true;
			int count = 1;
			for (int j=1; i+j<thingssold.size();j++) {
				if ((thingssold.get(i).getName()).equals(thingssold.get(i+j).getName())) {
					//System.out.println (i +" " +j);
					visited[i+j] = true;
					count ++;
				}
				//System.out.println (i +" " +j);
			}
			System.out.format(" %-5d  %-20s           $%-20s\n", count, thingssold.get(i).getName(), thingssold.get(i).getPrice()*count);	
				
			}
			}
		System.out.println("   ---------------------------");
		System.out.printf("                            Subtotal : $%.2f\n", total_revenue);
		System.out.printf("                      Service Charge : $%.2f\n", 0.1 * total_revenue);
		System.out.printf("                                 GST : $%.2f\n", 0.07 * 1.1* total_revenue);
		System.out.printf("                               TOTAL : $%.2f\n", 1.07 *1.1* total_revenue);
		System.out.println("----------------------------------");
		}
		else {
			System.out.println("No Items sold!");
		}
		System.out.println("  Sales Report Generated!");
	}

}

/*
 * private Invoice [] findInvoiceList(int month) { invoice[] =
 * 
 * for ()
 * 
 * return }
 */
