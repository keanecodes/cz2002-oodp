package ntu.scse.cz2002.restaurant.control;

import ntu.scse.cz2002.restaurant.model.Reservation;
import ntu.scse.cz2002.restaurant.model.Table;

import java.util.List;
import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.InputMismatchException;
import java.util.Iterator;

public class ReservationManager {

	/* A list of all tables in the Restaurant */
	private static final int[] TABLE_SIZE = { 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 8, 8, 8, 8, 8,
			10, 10, 10, 10, 10 };

	/* Opening and closing timing of the AM session */
	private static final int RESTAURANT_AM_OPENING_HOUR = 11;
	private static final int RESTAURANT_AM_CLOSING_HOUR = 15;

	/* Opening and closing timing of the PM session */
	private static final int RESTAURANT_PM_OPENING_HOUR = 18;
	private static final int RESTAURANT_PM_CLOSING_HOUR = 22;

	/* A static instance of restaurant manager */
	private static ReservationManager reserveMgr = null;

	/* A list of all physical tables in the restaurants */
	private static List<Table> tables;

	/* A list of all reservations in the restaurant */
	private static List<Reservation> reservations;

	/* Standard Java Scanner process user input */
	private static Scanner sc;

	/* A simple date format for formatting all date/time related information */
	private static SimpleDateFormat dateFormatter = null;

	/*
	 * A constructor to set up all the array lists of the tables and reservations
	 * and create the tables
	 */
	public ReservationManager() {
		sc = new Scanner(System.in);
		tables = new ArrayList<Table>();
		reservations = new ArrayList<Reservation>();
		dateFormatter = new SimpleDateFormat("E, dd/MM/yyyy, HH:mm");
		setUpTables();
	}

	/* Static instance of the Reservation Manager */
	public static ReservationManager getReservationManager() {
		if (reserveMgr == null) {
			reserveMgr = new ReservationManager();
		}

		return reserveMgr;
	}

	/* Create the list of tables with the indicated sizes */
	private void setUpTables() {
		int tableNumber = 1;
		for (int tableSize : TABLE_SIZE) {
			Table newTable;

			// Constructor: tableNunber, numOfSeats, isReserved, isOccupied, customerID
			newTable = new Table(tableNumber++, tableSize, false, false, 0);

			tables.add(newTable);
		}
	}

	public void viewTableAvailability() {
		checkReservations();

		System.out.printf("%n%-20s", "Table Number");
		System.out.printf("%-20s", "Number of Seats");
		System.out.printf("%-20s", "Table Status");
		System.out.printf("%-20s%n", "Customer ID");

		for (Table table : tables) {
			table.displayStatus();
		}
	}

	/* Displays a list of all valid reservations */
	public void viewReservations() {
		checkReservations();

		if (reservations.isEmpty()) {
			System.out.print("There are no reservations made at the moment!");
			return;
		}

		int currReservationNo = 1;

		for (Reservation reservation : reservations) {
			System.out.printf("%n%-5s", "(" + (currReservationNo++) + ")");
			reservation.displayReservationSummary();
		}

	}

	public void addReservation() {
		checkReservations();

		System.out.println("\nRestaurant's opening hours: " + "AM: 11AM - 3PM, PM: 6PM - 10PM");
		System.out.println("NOTE: Reservations will be automatically be removed 30 minutes"
				+ " after reservation date/time if you do not show up! :-)");

		try {
			SimpleDateFormat sdf = new SimpleDateFormat();
			sdf.applyPattern("dd/MM/yyyy HH:mm");
			sdf.setLenient(false);

			System.out.print("\nEnter reservation date (dd/mm/yyyy): ");
			String reservationDateStr = sc.next();

			System.out.print("Enter reservation time," + " in 24-hour format (hh:mm): ");
			String reservationTimeStr = sc.next();

			System.out.print("Enter reservation duration," + " in 24-hour format (hh:mm): ");
			int duration = sc.nextInt();

			System.out.print("Enter Customer name: ");
			String custName = sc.next();

			System.out.print("Enter Customer's contact number: ");
			int custNo = sc.nextInt();

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

			if ((startDateTime.before(amRestOpeningTime)) || (endDateTime.after(amRestClosingTime))
					|| (startDateTime.before(pmRestOpeningTime)) || (endDateTime.after(pmRestClosingTime))) {
				System.out.print("\nInvalid reservation date/time! ");
				System.out.println("Failed to add new reservation!");
				System.out
						.println("NOTE: The restaurant only operates in 2 sessions (AM: 11am - 3pm, PM: 6pm - 10 pm)");
				return;
			}

			Calendar currentInstant = GregorianCalendar.getInstance();
			Date currentDateTime = currentInstant.getTime();
			currentInstant.setTime(currentDateTime);

			if (startDateTime.before(currentInstant)) {
				System.out.print("\nInvalid reservation date/time! ");
				System.out.println("Failed to add new reservation," + " please try again..");
				System.out.println("NOTE: Reservation can only be made in advance!");
				return;
			}

			System.out.print("Enter number of people (2-10): ");
			int numOfPeople = sc.nextInt();

			if (numOfPeople < 2 || numOfPeople > 10) {
				System.out.println("Invalid number of people, unable to add new reservation!");
				return;
			}

			// Attempt to allocate a available table
			int availableTableNumber = 0;
			int tableNumber = 1;

			for (int tableSize : TABLE_SIZE) {
				if (tableSize >= numOfPeople) {
					boolean isReserved = false;

					for (Reservation reservation : reservations) {
						Calendar resStartDateTime = reservation.getStartDateTime();

						Calendar resEndDateTime = (Calendar) resStartDateTime.clone();
						resEndDateTime.add(Calendar.HOUR_OF_DAY, reservation.getDuration());

						// Check for existing reservation for this table number
						if (reservation.getTableNo() == tableNumber) {

							if (startDateTime.before(resEndDateTime) && endDateTime.after(resStartDateTime)) {
								isReserved = true;
								break;
							}
						}
					}

					if (!isReserved) {
						availableTableNumber = tableNumber;
						break;
					}
				}

				tableNumber++;
			}

			if (availableTableNumber == 0) {
				System.out.printf("Sorry, there are no tables available"
						+ " at the selected date/time that can accommodate" + " %d people!%n", numOfPeople);
			} else {
				Reservation newReservation;
				newReservation = new Reservation(startDateTime, numOfPeople, custName, custNo, availableTableNumber,
						duration);

				reservations.add(newReservation);

				System.out.printf("\nSuccessfully allocated Table" + " '%d' to '%s'!%n", availableTableNumber,
						custName);
				System.out.printf("Reservation Date/Time: %s," + " Reservation Duration: %d Hours%n",
						dateFormatter.format(startDateTime.getTime()), duration);
			}
		} catch (ParseException ex) {
			System.out.print("\nInvalid reservation date/time! ");
			System.out.println("Failed to add new reservation," + " please try again..");
			System.out.println("NOTE: Reservation date should" + " be in dd/mm/yyyy, e.g. 25/12/2014 and"
					+ "\n      reservation time should be in" + " hh:mm (24-hour format), e.g. 19:30!");

			return;
		} catch (InputMismatchException ex) {
			System.out.print("\nInvalid input! ");
			System.out.println("Failed to add new reservation," + " please try again..");
			return;
		} catch (Exception ex) {
			System.out.print("\nInvalid input! ");
			System.out.println("Failed to add new reservation," + " please try again..");
			return;
		}
	}

	public void removeReservation() {
		checkReservations();

		if (reservations.isEmpty()) {
			System.out.print("There are no reservations made at the moment!");
			return;
		}

		try {
			int numOfReservations = 0;

			System.out.println();
			System.out.printf("%5s%-15s", "", "Table Number");
			System.out.printf("%-30s", "Customer Name");
			System.out.printf("%-25s", "Reservation Date/Time");
			System.out.printf("%-20s%n", "Reservation Duration");

			// Display reservations
			for (Reservation reservation : reservations) {
				System.out.printf("%-5s", "(" + (++numOfReservations) + ")");
				reservation.displayReservationSummary();
			}

			System.out.printf("%nPlease select a reservation to remove " + "(0 to cancel): ");

			int reservationIndex = sc.nextInt();
			sc.nextLine();

			// User chooses not to remove any reservation
			if (reservationIndex == 0) {
				System.out.println("No reservation removed! :-)");
				return;
			}

			// Valid reservationIndex from 1 to numOfReservations
			if (reservationIndex < 1 || reservationIndex > numOfReservations) {
				System.out.print("\nInvalid input! ");
				System.out.println("Unable to remove reservation");
				return;
			}

			Reservation removedReservation = reservations.get(reservationIndex - 1);

			if (removedReservation != null) {
				Table table = getTableByNumber(removedReservation.getTableNo());

				if (table.getIsReserved() && (table.getCustomerNo() == removedReservation.getCustomerContactNo())) {
					table.releaseTable();
				}

				System.out.printf("%nSuccessfully removed reservation made by" + " \"%s\"!%n",
						removedReservation.getCustomerName());
			}

			reservations.remove(removedReservation);
		} catch (InputMismatchException ex) {
			System.out.print("\nInvalid input! ");
			System.out.println("Failed to remove reservation," + " please try again..");

			sc.nextLine(); // Clear the garbage input
			return;
		} catch (Exception ex) {
			System.out.println("Failed to remove reservation," + " please try again..");

			sc.nextLine(); // Clear the garbage input
			return;
		}
	}

	private void checkReservations() {
		if (reservations.isEmpty())
			return;

		Calendar currentInstant = GregorianCalendar.getInstance();
		Date currentDateTime = currentInstant.getTime();
		currentInstant.setTime(currentDateTime);

		Iterator<Reservation> resIter = reservations.iterator();
		Reservation reservation = null;

		while (resIter.hasNext()) {

			reservation = resIter.next();

			Calendar restStartDateTime = reservation.getStartDateTime();
			Calendar restClone = (Calendar) restStartDateTime.clone();
			restClone.add(Calendar.MINUTE, 30);

			// If currentInstant is after restStartDateTime by 30 minutes
			if (restClone.before(currentInstant)) {
				Table table = getTableByNumber(reservation.getTableNo());

				if ((!table.getIsOccupied()) && table.getIsReserved()
						&& (table.getCustomerNo() == reservation.getCustomerContactNo())) {
					table.releaseTable();
				}

				// Remove expired reservation
				resIter.remove();
			} else {
				// If reservationTime <= currentInstant & not expired
				// Set table as reserved
				restStartDateTime = reservation.getStartDateTime();
				if (restStartDateTime.before(currentInstant)) {
					Table table = getTableByNumber(reservation.getTableNo());

					if (!table.getIsReserved())
						table.reserveTable(reservation.getCustomerContactNo());

					if (table.getIsOccupied()) {
						// Remove expired reservation
						resIter.remove();
					}
				}
			}
		}
	}

	public Table getTableByNumber(int tableNumber) {
		for (Table table : tables) {
			if (table.getTableId() == tableNumber)
				return table;
		}

		return null;
	}

	public Table getReservedTable(int customerNo) {
		checkReservations();

		for (Table table : tables) {
			if (table.getIsReserved()) {
				if (table.getCustomerNo() == customerNo) {
					table.assignTable(customerNo);
					return table;
				}
			}
		}

		return null;
	}

	public int getPaxFromReservation(int customerID) {
		if (reservations.isEmpty())
			return 0;

		for (Reservation reservation : reservations) {
			if (reservation.getCustomerContactNo() == customerID) {
				return reservation.getNoOfPax();
			}
		}

		return 0;
	}

	public Table getAvailableTable(int customerID, int numOfPeople) {
		checkReservations();

		for (Table table : tables) {
			if (!table.getIsOccupied()) {
				if (!table.getIsReserved()) {
					if (table.getNumOfSeats() >= numOfPeople) {
						table.assignTable(customerID);
						return table;
					}
				}
			}
		}

		System.out.println("\nNo available tables!");
		return null;
	}

}
