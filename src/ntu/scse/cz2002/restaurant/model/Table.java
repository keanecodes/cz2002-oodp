package ntu.scse.cz2002.restaurant.model;

import ntu.scse.cz2002.restaurant.model.Reservation;
import java.util.ArrayList;

/**
 * Entity class to track table details
 * @author Gee Cheng Mun
 *@version 1.0
 *@since 2019-4-17
 */
public class Table {

	/**
	 * This table's ID.
	 */
	private int tableId;
	/**
	 * This table's seats capacity.
	 */
	private int numOfSeats;
	/**
	 * Indicator of whether the table is waiting for reservation customers.
	 */
	private boolean isReserved;
	/**
	 * Indicator of whether the table is currently occupied.
	 */
	private boolean isOccupied;
	/**
	 * Contact number of reservation customers.
	 */
	private int customerNo;
	/**
	 * The order list of this table's customers.
	 */
	private Order order;


	/**
   * Creates a table for reservation customers.
	 * @param tableId    This table's ID.
	 * @param numOfSeats This table's seats capacity.
	 * @param isReserved This table's reservation indicator.
	 * @param isOccupied This table's occupancy indicator.
	 * @param customerNo This table's reservation customer contact no.
	 */
	public Table(int tableId, int numOfSeats, boolean isReserved, boolean isOccupied, int customerNo) {
		this.tableId = tableId;
		this.numOfSeats = numOfSeats;
		this.isReserved = isReserved;
		this.isOccupied = isOccupied;
		this.customerNo = customerNo;
	}
	
	/**
   * Creates a table for walk-in customers.
	 * @param tableId    This table's ID.
	 * @param numOfSeats This table's seats capacity.
	 * @param isOccupied This table's occupancy indicator.
	 * @param o          This table's order list.
	 */
	public Table(int tableId, int numOfSeats, boolean isOccupied, Order o) {
		this.tableId = tableId;
		this.numOfSeats = numOfSeats;
		this.isOccupied = isOccupied;
		this.order = o;
	}

	/**
   * Gets the table's ID.
	 * @return table's ID.
	 */
	public int getTableId() { return tableId; }

	/**
   * Sets the table's ID.
	 * @param newTableId The new ID for the table.
	 */
	public void setTableID(int newTableId) { tableId = newTableId; }

	/**
   * Gets seats capacity of this table.
	 * @return this table's seats capacity.
	 */
	public int getNumOfSeats() { return numOfSeats; }

	/**
   * Sets the number of seats of this table.
	 * @param newNumOfSeats THe new seats capacity for this table.
	 */
	public void setNumOfSeats(int newNumOfSeats) { numOfSeats = newNumOfSeats; }

	/**
   * Gets the order list of this table.
	 * @return the order list of this table.
	 */
	public Order getOrder() { return this.order; }
	/**
   * Sets the order list for this table.
	 * @param o The new order list for this table.
	 */
	public void makeOrder(Order o) { this.order = o; }

	/**
   * Checks if table is under reservation.
	 * @return table's reservation status.
	 */
	public boolean getIsReserved() { return isReserved; }

	/**
	 * Sets this table as reserved.
	 */
	public void setIsReserved() { this.isReserved = true; }

	/**
   * Checks if table is currently occupied.
	 * @return table's occupancy status.
	 */
	public boolean getIsOccupied() { return isOccupied; }

	/**
	 * Sets this table as occupied.
	 */
	public void setIsOccupied() { this.isOccupied = true; }

	/**
   * Gets the reservation customer's contact no.
	 * @return reservation customer's contact no.
	 */
	public int getCustomerNo() { return customerNo; }

	/**
   * Sets the reservation customer's contact no.
	 * @param customerNo The customer's contact no.
	 */
	public void setCustomerNo(int customerNo)
	{
		this.customerNo = customerNo;
	}

	/**
   * Sets this table as reserved with customer contact no.
	 * @param custNo This table's customer contact no.
	 */
	public void reserveTable(int custNo) {
		isReserved = true;
		customerNo = custNo;
	}

	/**
	 * Free this table when reservation customers do not show up.
	 */
	public void releaseTable() {
		isReserved = false;
		customerNo = 0;
	}

	/**
   * Allocate table to walk-in customers.
	 * @param custNo This table's customer contact no.
	 */
	public void assignTable(int custNo) {
		isReserved = false;
		isOccupied = true;
		customerNo = custNo;
	}

	/**
	 * Free this table after customers are done eating.
	 */
	public void freeTable() {
		isReserved = false;
		isOccupied = false;
		customerNo = 0;
	}

	/**
	 * Display this table's status.
	 */
	public void displayStatus()
	{
		System.out.printf("%-20s", getTableId());
		System.out.printf("%-20s", getNumOfSeats());

		System.out.printf("%-20s", getIsOccupied() ? "Occupied" : (getIsReserved() ? "Reserved" : "Available"));

		System.out.printf("%-20s%n", (getCustomerNo() != 0) ? getCustomerNo() : "N/A");
	}
}
