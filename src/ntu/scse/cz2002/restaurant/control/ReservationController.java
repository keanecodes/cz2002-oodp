package ntu.scse.cz2002.restaurant.control;

import ntu.scse.cz2002.restaurant.model.Reservation;
import ntu.scse.cz2002.restaurant.model.Table;
import ntu.scse.cz2002.restaurant.util.Utilities;
import ntu.scse.cz2002.restaurant.view.ReservationView;
import ntu.scse.cz2002.restaurant.data.DataAccessor;


import java.util.List;
import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.InputMismatchException;
import java.util.Iterator;

/**
Handles the responsibilities of the reservation manager
@author  Gee Cheng Mun
@version 1.0
@since   2019-04-17
*/
public class ReservationController {


	/**
	 * A list of all tables in the Restaurant 
	 */
	private static final int[] TABLE_SIZE = { 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 8, 8, 8, 8, 8,
			10, 10, 10, 10, 10 };

	/**
	 * Opening timing of the AM session: 11am
	 */
	private static final int RESTAURANT_AM_OPENING_HOUR = 11;
	
	/**
	 * Closing timing of the AM session: 3pm
	 */
	private static final int RESTAURANT_AM_CLOSING_HOUR = 15;
	
	/**
	 * Opening timing of the PM session: 6pm 
	 */
	private static final int RESTAURANT_PM_OPENING_HOUR = 18;
	
	/**
	 * Opening timing of the PM session: 10pm
	 */
	private static final int RESTAURANT_PM_CLOSING_HOUR = 22;
	
    /**
     * The name of the file containing reservation's data.
     * To be passed into the addReservation(), removeReservation() and ReservationController() methods.
     */
	private final static String DATA_FILE = "reservation.dat";

	/**
	 * A static instance of the restaurant manager 
	 */
	private static ReservationController reserveMgr = null;
	
	/**
	 * An instance of table manager
	 */
	TableController tCtrl;

	/**
	 * A list of all physical tables in the restaurants 
	 */
	private static List<Table> tables;

	/**
	 * A list of all reservations in the restaurant
	 */
	private static List<Reservation> reservations;

	/**
	 * A standard Java Scanner used for processing user input
	 */
	private static Scanner sc;

	/**
	 * A simple date format for formatting all date/time related information
	 */
	private static SimpleDateFormat dateFormatter = null;

	/**
	 * A constructor to set up all the array list reservations
	 */
	public ReservationController() {
		sc = new Scanner(System.in);
		reservations = (ArrayList<Reservation>) DataAccessor.read(DATA_FILE);
		dateFormatter = new SimpleDateFormat("E, dd/MM/yyyy, HH:mm");
	}
	
	/**
	 * A constructor which takes in the table controller that is passed around our program as an argument
	 * @param tCtrl Parsing the table controller as an argument
	 */
	public ReservationController (TableController tCtrl) {
		super();
		this.tCtrl = tCtrl;

		tables = tCtrl.getTables();
	}
	
	/**
	 * Static instance of the Reservation Manager
	 * @return returns an instance of the reservation controller
	 */
	public static ReservationController getReservationManager() {
		if (reserveMgr == null) {
			reserveMgr = new ReservationController();
		}

		return reserveMgr;
	}
	
	
	/**
	 * gets list of all the current reservations
	 * @return a list of the all the current reservations
	 */
	public List<Reservation> getAllReservations(){
		return reservations;
	}

	/**
	 * Displays a list of all valid reservations for the staff's perusal
	 */
	public void viewReservations() {
		checkReservations();

		if (reservations.isEmpty()) {
			System.out.println("There are no reservations made at the moment!");
			Utilities.newScreenHeader();
			(new ReservationView()).ReservationUI();
			return;
		}

		int currReservationNo = 1;

		for (Reservation reservation : reservations) {
			System.out.printf("%n%-5s", "(" + (currReservationNo++) + ")");
			reservation.displayReservationSummary();
		}

	}
	
	/**
	 * Retrieves reservation's details based on Customer's contact number 
	 @return if Customer Reservations was found
	 */
	public boolean obtainCustReservation() {
		checkReservations();

		if (reservations.isEmpty()) {
			System.out.println("There are no reservations made at the moment!");
			return false;
		}
		
		try 
		{
			System.out.print("Please enter Customer's contact number: ");
			int custNum = sc.nextInt();
			
			int found = 0;
			for (int index = 0; index < reservations.size(); index++)
			{
				if (reservations.get(index).getCustomerContactNo() == custNum)
				{
					reservations.get(index).displayReservationSummary();
					System.out.print(" ");
					found = 1; 
					return true;
					//break;
				}
				
			}	
			if (found == 0)
				System.out.println("There are no reservations made by this contact number!\n"
						+ "Please try again or proceed to make another reservation");
		}
		catch (InputMismatchException ex) 
		{
			System.out.println("Invalid input! Please try again..");
			return false;
			
		} 
		catch (Exception ex) 
		{
			System.out.print("\nInvalid input! ");
			System.out.println("Please try again..");
			return false;
		}
		return false;
	}
	
		
	/**
	 * Verifies reservation's details based on Customer's contact number on arrival and removes the reservation
	 @return true if Reservations successfully removed
	 */
	public boolean removeCustArrived()
	{
		checkReservations();

			if (reservations.isEmpty()) {
				System.out.println("There are no reservations made at the moment!");
				return false;
			}
			
			try 
			{
				System.out.print("Please enter Customer's contact number: ");
				int custNum = sc.nextInt();
				
				int found = 0;
				for (int index = 0; index < reservations.size(); index++)
				{
					if (reservations.get(index).getCustomerContactNo() == custNum)
					{
						reservations.get(index).displayReservationSummary();
						System.out.print(" ");
						found = 1;
						reservations.remove(index);
						DataAccessor.write(DATA_FILE, reservations);
						return true;
						//break;
					}
					
				}	
				if (found == 0)
					System.out.println("There are no reservations made by this contact number!\n"
							+ "Please try again or proceed to make another reservation");
			}
			catch (InputMismatchException ex) 
			{
				System.out.println("Invalid input! Please try again..");
				return false;
				
			} 
			catch (Exception ex) 
			{
				System.out.print("\nInvalid input! ");
				System.out.println("Please try again..");
				return false;
			}
			return false;
		}		


	/**
	 * Adds a reservation to the list
	 */
	public void addReservation() 
	{
		checkReservations();

		System.out.println("\nRestaurant's opening hours: " + "AM: 11AM - 3PM, PM: 6PM - 10PM");
		System.out.println("NOTE: Reservations will be automatically be removed 30 minutes"
				+ " after reservation date/time if you do not show up!");

		try {
			SimpleDateFormat sdf = new SimpleDateFormat();
			sdf.applyPattern("dd/MM/yyyy HH:mm");
			sdf.setLenient(false);

			System.out.print("\nEnter reservation date (dd/mm/yyyy): ");
			String reservationDateStr = sc.next();
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
			LocalDate reservationDate = LocalDate.parse(reservationDateStr, formatter);

			System.out.print("\nEnter reservation time," + " in 24-hour format (hh:mm): ");
			String reservationTimeStr = sc.next();

			System.out.print("\nEnter reservation duration (in hours): ");
			int duration = sc.nextInt();

			Date reservationDateTime = sdf.parse(reservationDateStr + " " + reservationTimeStr);

			Calendar startDateTime = GregorianCalendar.getInstance();
			startDateTime.setTime(reservationDateTime);

			Calendar endDateTime = (Calendar) startDateTime.clone();
			endDateTime.add(Calendar.HOUR_OF_DAY, duration);

			/* AM Session: 11am - 3pm */
			Calendar amRestOpeningTime = (Calendar) startDateTime.clone();
			amRestOpeningTime.set(Calendar.HOUR_OF_DAY, (RESTAURANT_AM_OPENING_HOUR - 1));
			amRestOpeningTime.set(Calendar.MINUTE, 59);

			Calendar amRestClosingTime = (Calendar) amRestOpeningTime.clone();
			amRestClosingTime.set(Calendar.HOUR_OF_DAY, RESTAURANT_AM_CLOSING_HOUR);
			amRestClosingTime.set(Calendar.MINUTE, 1);

			/* PM Session: 6pm - 10pm */
			Calendar pmRestOpeningTime = (Calendar) startDateTime.clone();
			pmRestOpeningTime.set(Calendar.HOUR_OF_DAY, (RESTAURANT_PM_OPENING_HOUR - 1));
			pmRestOpeningTime.set(Calendar.MINUTE, 59);

			Calendar pmRestClosingTime = (Calendar) pmRestOpeningTime.clone();
			pmRestClosingTime.set(Calendar.HOUR_OF_DAY, RESTAURANT_PM_CLOSING_HOUR);
			pmRestClosingTime.set(Calendar.MINUTE, 1);
			
			/* 
			 * The start timings for both AM and PM sessions must be before the opening times and 
			 * the end timings must not be after the closing times for both sessions
			 */
			if (((startDateTime.before((amRestOpeningTime)) || (endDateTime.after(amRestClosingTime)))
					&& (startDateTime.before(pmRestOpeningTime)) || (endDateTime.after(pmRestClosingTime))))
			{
				System.out.print("\nInvalid reservation date/time! ");
				System.out.println("\nFailed to add new reservation!");
				System.out
						.println("NOTE: The restaurant only operates in 2 sessions (AM: 11am - 3pm, PM: 6pm - 10 pm)");
				return;
			}

			Calendar currentInstant = GregorianCalendar.getInstance();
			Date currentDateTime = currentInstant.getTime();
			currentInstant.setTime(currentDateTime);
			
			/* Reservations could not be made if the date/time is before the current date/time */
			if (startDateTime.before(currentInstant))
			{
				System.out.print("\nInvalid reservation date/time! ");
				System.out.println("Failed to add new reservation," + " please try again..");
				return;
			}
			
			/* Reservations could not be made more than one month in advance */
			long daysBetween = ChronoUnit.DAYS.between(LocalDate.now(), reservationDate);			
			if (daysBetween > 30)
			{
				System.out.println("NOTE: Reservation can only be made 1 month in advance!");
				return;
			}
			
			System.out.print("\nEnter Customer name: ");
			String custName = sc.next();

			System.out.print("\nEnter Customer's contact number: ");
			int custNo = sc.nextInt();

			System.out.print("\nEnter number of people (1-10): ");
			int numOfPeople = sc.nextInt();

			if (numOfPeople < 0 || numOfPeople > 10) {
				System.out.println("Invalid number of people, unable to add new reservation!");
				return;
			}

			/* Allocate an available table if available*/
			int availableTableNumber = 0;
			int tableNumber = 1;

			for (int tableSize : TABLE_SIZE) 
			{
				
				if (tableSize >= numOfPeople) 
				{
					boolean hasReservation = false;
					for (Reservation reservation : reservations) 
					{
						Calendar resStartDateTime = reservation.getStartDateTime();

						Calendar resEndDateTime = (Calendar) resStartDateTime.clone();
						resEndDateTime.add(Calendar.HOUR_OF_DAY, reservation.getDuration());

						/* Check for any existing reservation for this table number */
						if (reservation.getTableNo() == tableNumber) {

							if (startDateTime.before(resEndDateTime) && endDateTime.after(resStartDateTime)) {
								hasReservation = true;
								break;
							}
						}
					}

					if (!(hasReservation)) 
					{
						availableTableNumber = tableNumber;
						break;
					}
				}

				tableNumber++;
			}
			
			/* Provides message to user if there are no tables available */
			if (availableTableNumber == 0) 
			{
				System.out.printf("Sorry, there are no tables available"
						+ " at the selected date/time that can accommodate" + " %d people!%n", numOfPeople);
			} 
			else 
			{
				Reservation newReservation;
				newReservation = new Reservation(startDateTime, numOfPeople, custName, custNo, availableTableNumber,
						duration);
			
				/* Add new reservation and write it into the reservation's data file for keeping track*/
				reservations.add(newReservation);
				
				/* Update the table's status with the reservation details */
				for (int index = 0; index < tables.size(); index++)
				{
					if (tables.get(index).getTableId() == availableTableNumber)
					{
						//tables.get(index).setIsReserved(true);
						tables.get(index).setCustomerNo(custNo);
					}
				}
			
				/* Updates the reservation data file when a reservation is being added */
				DataAccessor.write(DATA_FILE, reservations);
				
				
				System.out.printf("\nSuccessfully allocated Table" + " '%d' to '%s'!%n", availableTableNumber,
						custName);
				System.out.printf("\nReservation Date/Time: %s," + " Reservation Duration: %d Hours%n",
						dateFormatter.format(startDateTime.getTime()), duration);
			}
			
		} catch (ParseException ex) {
			System.out.print("\nInvalid reservation date/time! ");
			System.out.println("\nFailed to add new reservation," + " please try again..");
			System.out.println("\nNOTE: Reservation date should" + " be in dd/mm/yyyy, e.g. 13/05/2019 and"
					+ "\n      reservation time should be in" + " hh:mm (24-hour format), e.g. 11:00!");

			return;
		} catch (InputMismatchException ex) {
			System.out.print("\nInvalid input! ");
			System.out.println("\nFailed to add new reservation," + " please try again..");
			return;
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.print("\nInvalid input! ");
			System.out.println("\nFailed to add new reservation," + " please try again..");
			return;
		}
	}
	
	/**
	 * Remove any existing reservation made by the user when a valid index is being provided
	 */
	public void removeReservation() {
		checkReservations();

		if (reservations.isEmpty()) 
		{
			System.out.print("There are no reservations made at the moment!");
			return;
		}

		try 
		{
			int numOfReservations = 0;

			System.out.println();
			/* Display existing reservations */
			for (Reservation reservation : reservations) {
				System.out.printf("\n%-5s", "(" + (++numOfReservations) + ")");
				reservation.displayReservationSummary();
			}

			System.out.printf("%nPlease enter the reservation's index to be removed" + "(0 to cancel): ");

			int reservationIndex = sc.nextInt();
			sc.nextLine();

			/* User chooses not to remove any reservation by inputting 0 */
			if (reservationIndex == 0) {
				System.out.println("No reservation is being removed :D");
				return;
			}

			/* A valid reservationIndex will start from 1 and must not exceed the total number of reservations */
			if (reservationIndex < 1 || reservationIndex > numOfReservations) {
				System.out.print("\nInvalid input! ");
				System.out.println("Unable to remove reservation :(");
				return;
			}

			Reservation removedReservation = reservations.get(reservationIndex - 1);

			if (removedReservation != null) {
				System.out.println(removedReservation.getTableNo());
				Table table = getTableByNumber(removedReservation.getTableNo());
				if (table.getIsReserved() && (table.getCustomerNo() == removedReservation.getCustomerContactNo())) {
					table.releaseTable();
				}

				System.out.printf("%nSuccessfully removed reservation made for" + " \"%s\"!%n",
						removedReservation.getCustomerName());
			}
			
			/* Removes a reservation when the conditions above are being fulfilled */
			reservations.remove(removedReservation);
			/* Updates the data file when a reservation is being removed */
			DataAccessor.write(DATA_FILE, reservations);
		} 
		
		catch (InputMismatchException ex) 
		{
			System.out.print("\nInvalid input! ");
			System.out.println("Failed to remove reservation," + " please try again..");

			sc.nextLine(); 
			return;
		} 
		catch (Exception ex) 
		{
			ex.printStackTrace();
			System.out.println("Failed to remove reservation," + " please try again..");

			sc.nextLine(); 
			return;
		}
	}			

	/**
	 * Check through the list of existing reservations and this method must be called
	 * first the usage of the following functions above 
	 */
	private void checkReservations() 
	{
		if (reservations.isEmpty())
			return;

		Calendar currentInstant = GregorianCalendar.getInstance();
		Date currentDateTime = currentInstant.getTime();
		currentInstant.setTime(currentDateTime);

		Iterator<Reservation> reserveIter = reservations.iterator();
		Reservation reservation = null;

		while (reserveIter.hasNext()) 
		{

			reservation = reserveIter.next();

			Calendar restStartDateTime = reservation.getStartDateTime();
			Calendar restClone = (Calendar) restStartDateTime.clone();
			restClone.add(Calendar.MINUTE, 15); // change this for reservation time

			/* If currentInstant is after restStartDateTime by 15 minutes */
			if (restClone.before(currentInstant)) 
			{
				Table table = getTableByNumber(reservation.getTableNo());

				if ((!table.getIsOccupied()) && table.getIsReserved()
						&& (table.getCustomerNo() == reservation.getCustomerContactNo())) 
				{
					table.releaseTable();
				}

				/* The expired reservation will be removed */
				reserveIter.remove();
			} 
			else 
			{
				/* If reservationTime is before currentInstant and has not expired, the table should still be reserved */
				restStartDateTime = reservation.getStartDateTime();
				if (restStartDateTime.before(currentInstant)) {
					Table table = getTableByNumber(reservation.getTableNo());

					if (!table.getIsReserved())
						table.reserveTable(reservation.getCustomerContactNo());

				}
			}
		}
	}
	
	/**
	 * Checks whether Table is currently reserved
	 * @param tableId The table ID/Number object
	 * @return True when table is being reserved and False when table is not being reserved
	 */
	public boolean isTableCurrentlyReserved(int tableId) {
		checkReservations();
		Table t = getTableByNumber(tableId);
		if (!(t == null)) {
			return t.getIsReserved();
		} 
		
		return false;
	}

	/**
	 * gets the Table using tableID
	 * @param tableNumber The table ID/Number object
	 * @return table when table ID and tableNumber match, otherwise null
	 */
	public Table getTableByNumber(int tableNumber) {
		for (Table table : tables) {
			if (table.getTableId() == tableNumber)
				return table;
		}

		return null;
	}
	
}
