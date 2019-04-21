package ntu.scse.cz2002.restaurant.control;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

import ntu.scse.cz2002.restaurant.data.DataAccessor;
import ntu.scse.cz2002.restaurant.model.*;
import ntu.scse.cz2002.restaurant.util.CalendarFormatter;

/**
*InvoiceController Control Class
*tracks and handles past invoices
*@author  He Zeqing
*@version 1.0
*@since   2019-04-17
*/
public class InvoiceController {
	private final static String DATA_FILE = "invoice.dat";	
	private ArrayList<Invoice> invoiceArr = new ArrayList<Invoice>();

	
	/**
	*Constructor;
	*<br> loads data from invoice.dat file
	*/
	public InvoiceController(){
		
		super();
		invoiceArr = loadItems(DATA_FILE);
		if(invoiceArr == null) {
			System.out.println("null");
			invoiceArr = new ArrayList<Invoice>();
		}
	}
	
	
	/**
	*Add a new Invoice, called by TblManager when a customer leaves & fulfils an order
	*@param o Add finalized order to create an invoice
	*/
	public void addInvoice(Order o) {
		Invoice newInvoice = new Invoice(o, invoiceArr.size());

		invoiceArr.add(newInvoice);
			//System.out.println("InvoiceArr is not null");
		newInvoice.printReceipt();
		this.saveItems();
	}
	
	/**
	*loadItems from file, handles exceptions of when file not open
	*@param filename of datafile
	*/
	private ArrayList<Invoice> loadItems(String filename) {
	    try{
	    return (ArrayList) DataAccessor.read(filename); 
	    } 
	    catch (Exception e){
	    e.printStackTrace();
	    System.out.println("Please create an invoice.dat.");
	    return new ArrayList<Invoice>();
	    }
	}
	
	/**
	*saves the array into dat
	*/
	private void saveItems() {
		int itemSave = DataAccessor.write(DATA_FILE, invoiceArr);
		
		if(itemSave != 1){
            System.out.println("Invoice contents successfully saved!");
        }
        else{
            System.out.println("Failed to save items!");
        }
    }
	
	/**
	*printItems using InvoiceID for the view class
	*@return true if there are invoices to print, false if its empty
	*/
	public boolean printItemsByID() {
        if(invoiceArr ==null){
            System.out.println("No item to print.");
            return false;
        } else {
        	if (invoiceArr.size() > 0) {
	        	Collections.sort(invoiceArr);
	        	System.out.println("InvoiceID" + "\t" + "Timestamp");
	        	for(Invoice InvoiceItem: invoiceArr){
	        		System.out.println(InvoiceItem.getInvoiceID() + "\t" + CalendarFormatter.toString(InvoiceItem.getTimestamp()));
        	}
        	}
	        	else {
	        		System.out.println("No item to print");
	        		return false;
	        	}
        	return true;
        }
	}
	
	/**
	*find a invoice using its timestamp
	*@param time Calendar of the exact time it was generated
	**/
	public Invoice findInvoicebytime(Calendar time) {
		for (Invoice InvoiceItem: invoiceArr) {
			if (InvoiceItem.getTimestamp() == time) {
				return InvoiceItem;
			}
		}
		System.out.println("Error, can't find the Invoice");
		return null;
	}
	
	/**
	*find a invoice using its ID
	*@param invoiceID, which just ++
	**/
	public Invoice findInvoicebyID(int ID) {
		for (Invoice InvoiceItem: invoiceArr) {
			if (InvoiceItem.getInvoiceID() == ID) {
				return InvoiceItem;
			}
		}
		System.out.println("Error, can't find the Invoice");
		return null;
	}
	
	/**
	*print Invoiceby ID
	*print a Invoice using ID. 
	*/
	public void printInvoicebyID(int ID) {
		Invoice toPrint = this.findInvoicebyID(ID);
		if (toPrint !=null) {
			toPrint.printReceipt();
		}
		else {
			System.out.println("Error, can't find Invoice");
		}
	}
	
	/**
	*edit down the list to return a list of invoices within a time period
	*@param startdate & enddate of Calendar class 
	*
	**/
	public ArrayList<Invoice> getInvoicelist(Calendar startdate, Calendar enddate) {
		ArrayList<Invoice> inperiod = new ArrayList<Invoice>();
		enddate.roll(Calendar.HOUR_OF_DAY, false);
		enddate.roll (Calendar.MINUTE, false);
		enddate.roll (Calendar.SECOND, false);
		enddate.roll (Calendar.MILLISECOND, false);
		//System.out.println(CalendarFormatter.toString(enddate));
		Calendar times;
		if (invoiceArr ==null) {
			return null;
		}
		
		for (Invoice i : invoiceArr) {
			times = i.getTimestamp();
			if (times.before(enddate) && times.after(startdate)) {
				inperiod.add(i);
			}
		}
		return inperiod;
	}
	
	public int getInvoiceListSize() {
		return this.invoiceArr.size();
	}

}
