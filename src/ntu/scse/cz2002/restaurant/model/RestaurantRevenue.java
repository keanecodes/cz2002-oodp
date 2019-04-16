package ntu.scse.cz2002.restaurant.model;
import java.util.Date;

import ntu.scse.cz2002.restaurant.dataAccess.DataArray;
import ntu.scse.cz2002.restaurant.util.CalendarFormatter;

import java.io.IOException;
//import java.time.*;
//import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;


public class RestaurantRevenue{ //needs to be reformatter & reworked.. is it a InvoiceManager or Does SalesReport need its own class?
	//Invoice[] invoice_list;
	Calendar start;
	Calendar end;
	ArrayList<MenuItem> thingssold;
	double total_revenue;
//	public static DateTimeFormatter timeformat = DateTimeFormatter.ofPattern("dd-MM-yyy HH:mm:ss");
	
	//init entire Invoicelist
	DataArray da = new DataArray();
	//ArrayList<String> strInvoice = DataArray.read("Invoices.txt");
	ArrayList<Invoice> total_Invoices = new ArrayList<Invoice>();{
	try {
		ArrayList<String> strInvoice = da.read("invoices.txt");
		for (String str: strInvoice) total_Invoices.add(new Invoice(str));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}}


	public RestaurantRevenue(ArrayList<Invoice> invoicelist){
		start = null ; end = null;
		total_revenue = sumAmount(invoicelist);
		thingssold = getitems(invoicelist);
	}
	
	public RestaurantRevenue(Calendar startdate, Calendar enddate) {
		start = startdate ; end = enddate;
		total_revenue = sumAmount(getInvoicelist(startdate,enddate));
		thingssold = getitems(getInvoicelist(startdate,enddate));
	}
	

	private ArrayList<Invoice> getInvoicelist (Calendar startdate, Calendar enddate) {
		ArrayList<Invoice> inperiod = new ArrayList<Invoice>();
		Calendar times;
		for (Invoice i: total_Invoices) {
			times = i.getTimestamp();
			if (times.before(enddate) && times.after(startdate)) {
				inperiod.add(i);
			}
		}
		
		return inperiod;
	}
	
	

	
	public String getPeriod() {
		return CalendarFormatter.toString(start,2) + " - "+ CalendarFormatter.toString(end,2);
	}
	
	/*public void setPeriod(int month) {
		period = month;
	}*/
	
	private double sumAmount(ArrayList<Invoice> invoicelist) {
		double totalAmt=0;
		for (Invoice _invoice: invoicelist) {
			totalAmt += _invoice.getAmt();
		}
		return totalAmt;
	}
	
	private ArrayList<MenuItem> getitems (ArrayList<Invoice> ils) {
		ArrayList<MenuItem> items = new ArrayList<MenuItem>();
		for (Invoice _invoice: ils) {
			Order _order = _invoice.getOrder();
			items.addAll(_order.getItems());
		}
		/*for (int i =0; i<ils.length; i++) {
			Order orders = ils[i].getOrder();
			for (MenuItem item : orders.getItems()) {
				items.add(item);
			}
		}*/
		return items;
	}

	public void PrintSalesReport() {
		System.out.println("Restaurant Name");
		System.out.println("Address");
		System.out.println("");
		//System.out.print(CalendartoString(timestamp));
		
		final Object[][] tableheader = new String[2][];
		tableheader[0] = new String[] { "From:" +CalendarFormatter.toString(start,2), "To: " + CalendarFormatter.toString(end,2)}; //im considering just keeping table id information, not entire table.
		tableheader[1] = new String[] { "StaffReq: Deb" , "Time of Request: " + CalendarFormatter.toString(Calendar.getInstance(),3)}; //what is the timestamp of request?
		
		for (final Object[] row : tableheader) {
		    System.out.format("%-15s%-15s\n", row);
		}

		System.out.println("----------------------------------");
		
		for (MenuItem item : thingssold) {
		    System.out.format("   %-20s$ %-20s\n", item.getName(), item.getPrice());
		}
		System.out.println("   ---------------------------");
		System.out.println("              Subtotal : " + total_revenue);
		System.out.println("                   GST : " + 0.07*total_revenue);
		System.out.println("                 TOTAL : " + 1.07* total_revenue);
		System.out.println("----------------------------------");
		System.out.println("  Sales Report Generated!");
	}

}

	
/*	private Invoice [] findInvoiceList(int month) {
		invoice[] = 
				
		for ()
		
		return 
	}
*/
	

