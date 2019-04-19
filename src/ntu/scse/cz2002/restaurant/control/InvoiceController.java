package ntu.scse.cz2002.restaurant.control;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

import ntu.scse.cz2002.restaurant.data.DataAccessor;
import ntu.scse.cz2002.restaurant.model.*;

public class InvoiceController {
	private final static String DATA_FILE = "invoice.dat";	
	private ArrayList<Invoice> invoiceArr;

	public InvoiceController(){
    try{
        this.loadItems(DATA_FILE);
    } catch (Exception e){
    	System.out.println("Please create an invoice.dat.");
    	}
	}
	
	public void addInvoice(Order o) {
		invoiceArr.add(new Invoice(o));
		this.saveItems();
	}
	
	
	private void loadItems(String filename) {
		 invoiceArr= (ArrayList<Invoice>) DataAccessor.readList(filename);
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
	
	public void printItemsByID() {
		Collections.sort(invoiceArr);
		
        if(invoiceArr.size()==0){
            System.out.println("No item to print.");
            }
        else {
        	System.out.println("InvoiceID" + "\tTimestamp");
        	for(Invoice InvoiceItem: invoiceArr){
        		System.out.println(InvoiceItem.getInvoiceID() + "\t" + InvoiceItem.getTimestamp());
        	}
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
	
	private ArrayList<Invoice> getInvoicelist(Calendar startdate, Calendar enddate) {
		ArrayList<Invoice> inperiod = new ArrayList<Invoice>();
		Calendar times;
		for (Invoice i : invoiceArr) {
			times = i.getTimestamp();
			if (times.before(enddate) && times.after(startdate)) {
				inperiod.add(i);
			}
		}

		return inperiod;
	}
	
	

}
