package ntu.scse.cz2002.restaurant.control;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

import ntu.scse.cz2002.restaurant.data.DataAccessor;
import ntu.scse.cz2002.restaurant.model.*;

public class InvoiceController {
	private final static String DATA_FILE = "invoice.dat";	
	private ArrayList<Invoice> invoiceArr;
	private TableController tCtrl; 

	public InvoiceController(){
		invoiceArr = loadItems(DATA_FILE);
	}
	
	public InvoiceController(TableController tCtrl) {
		this.tCtrl = tCtrl;
	}
	
	public void addInvoice(Order o) {
		invoiceArr.add(new Invoice(o));
		this.saveItems();
	}
	
	
	private ArrayList<Invoice> loadItems(String filename) {
	    try{
	    return (ArrayList<Invoice>) DataAccessor.readList(filename); 
	    } 
	    catch (Exception e){
	    System.out.println("Please create an invoice.dat.");
	    return null;
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
            }
        else {
        	Collections.sort(invoiceArr);
        	System.out.println("InvoiceID" + "\tTimestamp");
        	for(Invoice InvoiceItem: invoiceArr){
        		System.out.println(InvoiceItem.getInvoiceID() + "\t" + InvoiceItem.getTimestamp());
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
