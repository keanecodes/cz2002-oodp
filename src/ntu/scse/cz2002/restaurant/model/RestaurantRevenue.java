package ntu.scse.cz2002.restaurant.model;
import java.util.Date;

import restaurantApp.Invoice;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;


public class RestaurantRevenue {
	Invoice[] invoice_list;
	int period;
	Menuitem[] thingssold;
	double amount;
//	public static DateTimeFormatter timeformat = DateTimeFormatter.ofPattern("dd-MM-yyy HH:mm:ss");

	private double sumAmount(Invoice [] invoicelist) {
		double totalAmt=0;
		for (int i = 0; i< invoicelist.length; i++) {
			totalAmt += invoicelist[i].getAmt();
		}
		return totalAmt;
	}
	
	private Menuitem[] getitems (Invoice[] ils) {
		Menuitem[] item;
		for (int i =0; i<ils.length; i++) {
			orders = ils[i].getOrder();
			for (int i = 0; i<orders.length; i++) {
				item.append(orders[i]);
			}
		}
		return item;
	}
	
	RestaurantRevenue(Invoice[] invoicelist, int month){
		period = month;
		invoice_list = invoicelist;
		amount = sumAmount(invoice_list);
		thingssold = getitems(invoice_list);
	}
	
	public long getPeriod() {
		return period;
	}
	public void setPeriod(int month) {
		period = month;
	}
	
/*	private Invoice [] findInvoiceList(int month) {
		invoice[] = 
				
		for ()
		
		return 
	}
*/
	

