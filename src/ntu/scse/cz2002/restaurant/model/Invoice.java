package ntu.scse.cz2002.restaurant.model;
//package DateUtil.java; //idk why this is here

import java.util.ArrayList;
import java.util.Calendar;
//import java.util.Date;
import java.util.StringTokenizer;

import ntu.scse.cz2002.restaurant.control.OrderController;
import ntu.scse.cz2002.restaurant.util.CalendarFormatter;
//import ntu.scse.cz2002.restaurant.util.DateUtil;

/**
*Invoice entity class
*tracks orders that have been paid
*/
public class Invoice implements Comparable<Invoice>{

	Calendar timestamp = Calendar.getInstance();
	//int tableid;
	Order order1;
	double amount;
	//int invoice_id = 3941; // test
	// public static DateTimeFormatter timeformat =
	// DateTimeFormatter.ofPattern("dd-MM-yyy HH:mm:ss");
	
	

	/*public Invoice(Table t) {
		timestamp = Calendar.getInstance();
		tableid = t.getTableId();
		order1 = t.getOrder();
		amount = calAmt(order1);
	}*/
	
	
    /**
    *Constructor for Invoice Class. Constructs based on an order.
    * @see MyClass
    */
	public Invoice(Order o) {
		// Date date = new Date();
		timestamp = Calendar.getInstance();
		order1 = o;
		amount = calAmt(o);
	}
	
    /**
    *returns Order object which Invoice is built off of.
    */
	public Order getOrder() {
		return order1;
	}
	
    /**
    *uses order to return MenuItems ordered.
    */
	public ArrayList<MenuItem> getMenuItems(){
		return order1.getItems();
	}
	
	/**
	    *returns TableID of order.
	    */
	public int getTableID() {
		return order1.getTableId();
	}
	
	   /**
    *returns InvoiceID. InvoiceID is the same as the old orderID.
	    */
	public int getInvoiceID() {
		return order1.getOrderId();
	}
	
	
	   /**
	    *returns StaffID of Staff who took the order
	    */
	public int getStaffID() {
		return order1.getStaff().getStaffID();
	}
	
	/**
	*returns Amount paid on the Invoice
	*/
	public double getAmt() {
		return amount;
	}
	
	/**
	*returns timestamp of Invoice
	*/
	public Calendar getTimestamp() {
		return timestamp;
	}

	/**
	*calculates the total amount to be paid based on the order
	*/
	private double calAmt(Order o) { // this can be put in order entity < best that it is called by order...
		ArrayList<MenuItem> items = o.getItems();
		double totalAmt = 0;
		for (MenuItem item : items) {
			totalAmt += item.getPrice(); // menuitem.getprice
		}

		return totalAmt;
	}

	
	/**
	*prints Invoice attributes for backend access if needed.
	*/
	public String toString() {
		return "Timestamp: " + CalendarFormatter.toString(timestamp) + "InvoiceID: " + this.getInvoiceID() + " " + "Amount: " + amount;
		
	}

	/**
	*prints Receipt
	*/
	public void printReceipt() {
		System.out.println("Restaurant Name");
		System.out.println("Address");
		System.out.print(this.getInvoiceID());
		System.out.println("");
		// System.out.print(CalendartoString(timestamp));

		final Object[][] tableheader = new String[2][];
		tableheader[0] = new String[] { "Server: " + this.getStaffID(), "Date: " + CalendarFormatter.toString(timestamp, 2) }; // im
																											// considering
																												// just
																												// keeping																										// entire
																												// table.
		tableheader[1] = new String[] { "Table: " + this.getTableID(), "Time: " + CalendarFormatter.toString(timestamp, 3) };

		for (final Object[] row : tableheader) {
			System.out.format("%-15s%-15s\n", row);
		}

		System.out.println("----------------------------------");

		for (MenuItem item : this.getMenuItems()) {
			System.out.format("   %-20s$ %-20s\n", item.getName(), item.getPrice());
		}
		System.out.println("   ---------------------------");
		System.out.println("              Subtotal : " + amount);
		System.out.println("                   GST : " + 0.07 * amount);
		System.out.println("                 TOTAL : " + 1.07 * amount);
		System.out.println("----------------------------------");
		System.out.println("  Thank You for Dining with us!");
	}

	/*
	 * private Object[][] orderitems(Order op){ final Object[][] table= new
	 * String[2][]; int i = 0; for (MenuItem item: op.getItems()) { table[0][i] =
	 * item.getName(); table[1][i] = item.getPrice(); } i++; return table; }
	 */
	
	/**
	*For testing purposes
	*@deprecated for testing purposes
	*/
	public static void main() { // for testing
		Staff s = new Staff("Name", 'F', 3, "JobTitle");
		Order o = new Order(s, 2, 5); // order id, table id
		Invoice i = new Invoice(o);

		i.printReceipt();
	}

	
	/**
	 * @param Comparable to other Invoices
	*Comparable interface, invoices compared using timestamp
	*/
	@Override
	public int compareTo(Invoice i) {
		if ((this.getTimestamp()).before(i.getTimestamp())) {
			return -1;
		}
		else {
			if ((this.getTimestamp()).after(i.getTimestamp())) {
				return 1;	
			}
			else {
				return 0;	
			}
		}
		// TODO Auto-generated method stub
		//return 0;
	}

}
