package ntu.scse.cz2002.restaurant.model;
//package DateUtil.java; //idk why this is here

import java.util.ArrayList;
import java.util.Calendar;
//import java.util.Date;
import java.util.StringTokenizer;

import ntu.scse.cz2002.restaurant.control.OrderController;
import ntu.scse.cz2002.restaurant.dataAccess.DataAccessible;
import ntu.scse.cz2002.restaurant.util.CalendarFormatter;
//import ntu.scse.cz2002.restaurant.util.DateUtil;

public class Invoice implements DataAccessible {

	Calendar timestamp = Calendar.getInstance();
	String tableid;
	Order order1;
	double amount;
	int invoice_id = 3941; // test
	// public static DateTimeFormatter timeformat =
	// DateTimeFormatter.ofPattern("dd-MM-yyy HH:mm:ss");

	public Invoice(String txt) {
		DataDecoder(txt);
	}

	public Invoice(Table t) {
		timestamp = Calendar.getInstance();
		tableid = t.getTableId();
		order1 = t.getOrder();
		amount = calAmt(order1);
	}

	public Invoice(Order o) {
		// Date date = new Date();
		timestamp = Calendar.getInstance();
		order1 = o;
		amount = calAmt(o);
		tableid = "none";
	}

	public Order getOrder() {
		return order1;
	}

	private double calAmt(Order o) { // this can be put in order entity < best that it is called by order...
		ArrayList<MenuItem> items = o.getItems();
		double totalAmt = 0;
		for (MenuItem item : items) {
			totalAmt += item.getPrice(); // menuitem.getprice
		}

		return totalAmt;
	}

	public Calendar getTimestamp() {
		return timestamp;
	}

	public String toString() {
		return "Timestamp: " + CalendarFormatter.toString(timestamp) + "Table: " + tableid + " " + "Amount: " + amount;
	}

	public double getAmt() {
		return amount;
	}

	// implemented types - for dataAccess
	public String DataFormatter() {

		// (Year)|(Month)|(Day)|(Time)|(OrderID)|(Amount)
		ArrayList<String> format = new ArrayList<String>();
		format.add("" + timestamp.get(Calendar.YEAR));
		format.add("" + timestamp.get(Calendar.MONTH));
		format.add("" + timestamp.get(Calendar.DAY_OF_MONTH));
		format.add("" + timestamp.get(Calendar.HOUR_OF_DAY) + ":" + timestamp.get(Calendar.MINUTE) + ":"
				+ +timestamp.get(Calendar.SECOND));
		format.add("" + order1.getOrderId());
		format.add("" + amount);
		// System.out.print(format);

		String txt = "";
		for (String attribute : format) {
			txt = txt + attribute + SEPARATOR;
			// System.out.print(txt);
		}
		/*
		 * ArrayList<Object> format = new ArrayList<Object>; format.add(timestamp);
		 * format.add(table1); format.add(order1); format.add(amount); for (Object
		 * attribute : format) { txt.append((String)attribute); txt.append(SEPARATOR); }
		 */
		System.out.println(SEPARATOR);
		return txt;
	}

	@Override
	public void DataDecoder(String item) {
		StringTokenizer star = new StringTokenizer(item, SEPARATOR);

		// (Year)|(Month)|(Day)|(Time)|(OrderID)|(Amount)
		int yyyy = Integer.parseInt(star.nextToken().trim());
		int MM = Integer.parseInt(star.nextToken().trim());
		int dd = Integer.parseInt(star.nextToken().trim());
		String smalltime = star.nextToken().trim();
		int orderid = Integer.parseInt(star.nextToken().trim());
		amount = Double.parseDouble(star.nextToken().trim());
		OrderController oc = new OrderController(); // oc shld be initialized in the main class
		order1 = oc.findOrder(orderid);

		StringTokenizer tim = new StringTokenizer(smalltime, ":");
		int HH = Integer.parseInt(tim.nextToken().trim());
		int mm = Integer.parseInt(tim.nextToken().trim());
		int sec = Integer.parseInt(tim.nextToken().trim());

		System.out.println("" + yyyy + MM + dd + HH + mm + sec);

		this.timestamp.set(yyyy, MM, dd, HH, mm, sec);

	}

	public void printReceipt() {
		System.out.println("Restaurant Name");
		System.out.println("Address");
		System.out.print(invoice_id);
		System.out.println("");
		// System.out.print(CalendartoString(timestamp));

		final Object[][] tableheader = new String[2][];
		tableheader[0] = new String[] { "Server: Deb", "Date: " + CalendarFormatter.toString(timestamp, 2) }; // im
																												// considering
																												// just
																												// keeping
																												// table
																												// id
																												// information,
																												// not
																												// entire
																												// table.
		tableheader[1] = new String[] { "Table: " + tableid, "Time: " + CalendarFormatter.toString(timestamp, 3) };

		for (final Object[] row : tableheader) {
			System.out.format("%-15s%-15s\n", row);
		}

		System.out.println("----------------------------------");

		for (MenuItem item : order1.getItems()) {
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
	public static void main() { // for testing
		Staff s = new Staff("Name", 'F', 3, "JobTitle");
		Order o = new Order(s, 2, 5); // order id, table id
		Invoice i = new Invoice(o);

		i.printReceipt();
	}

}
