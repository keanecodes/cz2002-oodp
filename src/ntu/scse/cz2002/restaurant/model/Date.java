package model;
package util.DateUtil;
import java.util.Date;
import java.util.Calendar;

public class Date {
	
	private static Object timestamp;
	private static int formattype;
	
	private String[] formattypestring;
	formattypestring[0] = "";
	formattypestring[1] = "Calendar";
	formattypestring[2] = "Date";
	
	Date(String formatcase) {
		switch (formatcase) {
			case 'Calendar':
				timestamp = Calendar.getInstance();
				this.formattype = 1;
				break;
			case 'Date':
				timestamp = Date.timestamp 
				
		}

		}
			}
	

	private Object set(int ft) {
		switch (ft) {
			case 1:
			timestamp = Calendar.getInstance();
			this.formattype = 1;
			break;
			case 2:
			timestamp = Date.timestamp 
	
		
	}
	

	public void reset() {
		for formattype
	
	}
	
	public void changeFormatType (int ft) {
		formattype = ft;
		for 
	
	
	}
	
	
	public String toString() {
		
	}
}
