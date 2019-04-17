package ntu.scse.cz2002.restaurant.model;

import java.util.Calendar;
import ntu.scse.cz2002.restaurant.util.DateUtil;

public class Reservation {

	private Calendar startDateTime; // Date and time of reservation

	private int noOfPax; // The number of people that this reservation is for

	private String customerName; // The name of customer for the reservation

	private int customerContactNo; // The contact number of customer for the reservation

	private int tableNo; // The table number of the reservation

	private int duration; // The duration of the reservation

	public Reservation(Calendar startDateTime, int noOfPax, String customerName, // Creation of a reservation object
			int customerContactNo, int tableNo, int duration) {
		this.startDateTime = startDateTime;

		this.noOfPax = noOfPax;

		this.customerName = customerName;

		this.customerContactNo = customerContactNo;

		this.tableNo = tableNo;

		this.duration = duration;
	}

	public Calendar getStartDateTime() // Gets the date and time of the reservation
	{
		return startDateTime;
	}

	public void setStartDateTime(Calendar newStartDateTime) // Changes the date and time of the reservation
	{
		startDateTime = newStartDateTime;
	}

	public int getNoOfPax() // Gets the number of people for the reservation
	{
		return noOfPax;
	}

	public void setNoOfPax(int newNoOfPax) // Changes the number of people for the reservation
	{
		noOfPax = newNoOfPax;
	}

	public String getCustomerName() // Gets the name of customer who made the reservation
	{
		return customerName;
	}

	public int getCustomerContactNo() // Gets the contact number of customer who made the reservation
	{
		return customerContactNo;
	}

	public int getTableNo() // Gets the assigned table number for the reservation
	{
		return tableNo;
	}

	public void setTableNo(int newTableNo) // Gets the assigned table number for the reservation
	{
		tableNo = newTableNo;
	}

	public int getDuration() // Gets the duration of the reservation
	{
		return duration;
	}

	public void setDuration(int newDuration) // Changes the duration of the reservation
	{
		duration = newDuration;
	}

	public void displayReservationSummary() // Displays the reservation details including table number, customer name,
											// contact number
	{
		// Table Number
		System.out.print("Table Number: " + getTableNo() + "\n");

		// Customer Name and Contact Number
		System.out.print("Customer Name: " + getCustomerName() + "Contact Number: " + getCustomerContactNo() + "\n");

		// Reservation Date/Time
		System.out.print("Date/Time: " + DateUtil.format(startDateTime.getTime(), "datetime") + "\n");

		// Number Of Diners
		System.out.print("No. Of Pax: " + getNoOfPax() + "\n");

		// Reservation Duration
		System.out.print("Reservation Duration " + getDuration() + "\n");
	}

}
