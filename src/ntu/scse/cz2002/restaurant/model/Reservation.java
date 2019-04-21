package ntu.scse.cz2002.restaurant.model;

import java.io.Serializable;
import java.util.Calendar;
import ntu.scse.cz2002.restaurant.util.DateUtil;

/**
Handles the methods of Reservation class
@author  Gee Cheng Mun
@version 1.0
@since   2019-04-17
*/
public class Reservation implements Serializable {

	/**
	 * Date and time of reservation
	 */
	private Calendar startDateTime;

	/**
	 * The number of people that this reservation is for
	 */
	private int noOfPax; 

	/**
	 * The name of customer for the reservation
	 */
	private String customerName;  

	/**
	 * The contact number of customer for the reservation
	 */
	private int customerContactNo;  

	/**
	 * The table number of the reservation
	 */
	private int tableNo; 

	/**
	 * The duration of the reservation
	 */
	private int duration;  

	/**
	 * @param startDateTime: The reservation's date and time 
	 * @param noOfPax: The number of pax for the reservation
	 * @param customerName: Name of the customer
	 * @param customerContactNo: Contact number of the customer
	 * @param tableNo: Assigned table number 
	 * @param duration: Duration of the reservation
	 */
	public Reservation(Calendar startDateTime, int noOfPax, String customerName, // Creation of a reservation object
			int customerContactNo, int tableNo, int duration) {
		this.startDateTime = startDateTime;

		this.noOfPax = noOfPax;

		this.customerName = customerName;

		this.customerContactNo = customerContactNo;

		this.tableNo = tableNo;

		this.duration = duration;
	}

	/**
	 * @return Gets the date and time of the reservation
	 */
	public Calendar getStartDateTime()  
	{
		return startDateTime;
	}

	/**
	 * @param newStartDateTime: Changes the date and time of the reservation
	 */
	public void setStartDateTime(Calendar newStartDateTime)  
	{
		startDateTime = newStartDateTime;
	}

	/**
	 @return Gets the number of people for the reservation
	 */
	public int getNoOfPax() 
	{
		return noOfPax;
	}

	/**
	 * @param newNoOfPax: The new number of pax for the reservation
	 */
	public void setNoOfPax(int newNoOfPax)  
	{
		noOfPax = newNoOfPax;
	}

	/**
	 * @return the name of customer who made the reservation
	 */
	public String getCustomerName() 
	{
		return customerName;
	}

	/**
	 * @return the contact number of customer who made the reservation
	 */
	public int getCustomerContactNo()
	{
		return customerContactNo;
	}
	
	/**
	 * @param customerNo: The contact number of customer who made the reservation
	 */
	public void setCustomerContactNo(int customerNo) 
	{
		customerContactNo = customerNo;
	}

	/**
	 * @return the assigned table number for the reservation
	 */
	public int getTableNo() 
	{
		return tableNo;
	}

	/**
	 * @param newTableNo: The new table number for the reservation
	 */
	public void setTableNo(int newTableNo)  
	{
		tableNo = newTableNo;
	}

	/**
	 * @return the duration of the reservation
	 */
	public int getDuration()  
	{
		return duration;
	}

	/**
	 * @param newDuration: The new duration of the reservation
	 */
	public void setDuration(int newDuration)
	{
		duration = newDuration;
	}

	/**
	 * Displays the reservation details including table number, customer name, contact number and duration
	 */
	public void displayReservationSummary() 
	{
		/* Table Number */
		System.out.print("Table Number: " + getTableNo() + "\n");

		/* Customer Name and Contact Number */
		System.out.print("Customer Name: " + getCustomerName() + ", Contact Number: " + getCustomerContactNo() + "\n");

		/* Reservation Date/Time */
		System.out.print("Date/Time: " + DateUtil.format(startDateTime.getTime(), "datetime") + "\n");

		/* Number Of Diners */
		System.out.print("No. Of Pax: " + getNoOfPax() + "\n");

		/* Reservation Duration */
		System.out.print("Reservation Duration: " + getDuration() + " hours\n");
	}

}
