package ntu.scse.cz2002.restaurant.view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

import ntu.scse.cz2002.restaurant.model.RestaurantRevenue;
import ntu.scse.cz2002.restaurant.model.Staff;

import java.util.Date;
/**
*Boundary Class for SalesReport*/
public class SalesReportView {
	static Scanner sc= new Scanner(System.in);
	static Calendar start = Calendar.getInstance();
	static Calendar end = Calendar.getInstance();
	static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

	/**
	*Constructor for SalesReport for which staff is unknown
	*/
	public static void showSalesReport() {
		Staff unknown = new Staff("Unknown", 0);
		showSalesReport (unknown);
	}
	
	/**
	*Constructor for SalesReport
	*@param staff generating the report
	*/
	public static void showSalesReport(Staff stf) {
		System.out.println("// Print SalesReport//------------\n" +
				"--------------------------------------------------");   
		
		int count =4;
		do {
		
		if (count <4) {
		System.out.println("StartDate, EndDate not Valid! You have "+ count +"more tries.");
		System.out.println("-------------------------------------");
		}
		
		System.out.print("Key in Start Date (yyyy-mm-dd):" );
		String sdate = sc.next();
		try {
			start.setTime(format.parse(sdate));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			System.out.println("Error, Date set to 1990-1-1");
			start.set(1990,0,1);
		}
		/*System.out.print("Key in Year:" );
		int yy = sc.nextInt();
		System.out.print("Key in Month:" );
		int mm = sc.nextInt();
		System.out.print("Key in Day:" );
		int dd = sc.nextInt();
		start.set(yy, mm, dd);*/
		
		
		System.out.print("Key in End Date(yyyy-mm-dd):");
		sdate = sc.next();
		try {
			end.setTime(format.parse(sdate));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			System.out.println("Error, Date set to currenttime");
		}
		
		count --;
		}
		while(!end.after(start) && count >0);
		
		System.out.println("--------------------------------------------------");   
		RestaurantRevenue rr = new RestaurantRevenue(start, end);
		rr.PrintSalesReport(stf);
	}
	
}
