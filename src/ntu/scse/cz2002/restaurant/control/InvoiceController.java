package ntu.scse.cz2002.restaurant.control;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

import ntu.scse.cz2002.restaurant.data.DataAccessor;
import ntu.scse.cz2002.restaurant.model.*;
import ntu.scse.cz2002.restaurant.util.CalendarFormatter;

public class InvoiceController {
	private final static String DATA_FILE = "invoice.dat";	
	private ArrayList<Invoice> invoiceArr = new ArrayList<Invoice>();

	public InvoiceController(){
		
		super();
		invoiceArr = loadItems(DATA_FILE);
		if(invoiceArr == null) {
			System.out.println("null");
			invoiceArr = new ArrayList<Invoice>();
		}
	}
	
	public void addInvoice(Order o) {
		Invoice newInvoice = new Invoice(o, invoiceArr.size());

		invoiceArr.add(newInvoice);
			//System.out.println("InvoiceArr is not null");
		newInvoice.printReceipt();
		this.saveItems();
	}
	
	
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
	
	private void saveItems() {
		int itemSave = DataAccessor.write(DATA_FILE, invoiceArr);
		
		if(itemSave != 1){
            System.out.println("Invoice contents successfully saved!");
        }
        else{
            System.out.println("Failed to save items!");
        }
    }
	
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
	
	public Invoice findInvoicebytime(Calendar time) {
		for (Invoice InvoiceItem: invoiceArr) {
			if (InvoiceItem.getTimestamp() == time) {
				return InvoiceItem;
			}
		}
		System.out.println("Error, can't find the Invoice");
		return null;
	}
	
	public Invoice findInvoicebyID(int ID) {
		for (Invoice InvoiceItem: invoiceArr) {
			if (InvoiceItem.getInvoiceID() == ID) {
				return InvoiceItem;
			}
		}
		System.out.println("Error, can't find the Invoice");
		return null;
	}
	
	public void printInvoicebyID(int ID) {
		Invoice toPrint = this.findInvoicebyID(ID);
		if (toPrint !=null) {
			toPrint.printReceipt();
		}
		else {
			System.out.println("Error, can't find Invoice");
		}
	}
	
	public ArrayList<Invoice> getInvoicelist(Calendar startdate, Calendar enddate) {
		ArrayList<Invoice> inperiod = new ArrayList<Invoice>();
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
	
	

}
