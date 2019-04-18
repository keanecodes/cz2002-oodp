package ntu.scse.cz2002.restaurant;

import ntu.scse.cz2002.restaurant.model.Invoice;
import ntu.scse.cz2002.restaurant.model.Order;
import ntu.scse.cz2002.restaurant.model.Staff;
import ntu.scse.cz2002.restaurant.model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import ntu.scse.cz2002.restaurant.dataAccess.*;

public class TestMain {

	public static void main(String[] args) throws IOException {
		Staff s = new Staff("Name", 'F', 3, "JobTitle");
		Order o = new Order(s, 2, 5); // order id, table id
		Invoice i = new Invoice(o);
		MenuItem m = new MenuItem("Pasta", "YumYum", 3.50, "Main Course");
		o.addItem(m);

		i.printReceipt();
		ArrayList<DataAccessible> invoices = new ArrayList<DataAccessible>();
		invoices.add(i);
		invoices.add(i);

		DataArray da = new DataArray();
		da.save("invoices.txt", invoices);

		ArrayList<String> test = new ArrayList<String>();
		test.add("Tester");
		test.add("Testers");
		test.add("Im tired");

		DataAccessHelper.write("test.txt", test);

		ArrayList<Invoice> ii = new ArrayList<Invoice>();
		ArrayList<String> istring = da.read("this is a name.txt");

		for (String str : istring) {
			ii.add(new Invoice(str));
		}

		System.out.print(ii);

		Calendar startdate = Calendar.getInstance();
		startdate.set(2019, 3, 14);
		Calendar enddate = Calendar.getInstance();
		enddate.set(2019, 3, 17);
		RestaurantRevenue rr = new RestaurantRevenue(startdate, enddate);
		rr.PrintSalesReport();
	}

}
