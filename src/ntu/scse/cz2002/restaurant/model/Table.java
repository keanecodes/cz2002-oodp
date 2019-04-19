package ntu.scse.cz2002.restaurant.model;

import ntu.scse.cz2002.restaurant.model.Reservation;
import java.util.ArrayList;

public class Table {

	private int tableId;
	private int numOfSeats;
	private boolean isReserved;
	private boolean isOccupied;
	private int customerNo;
	private Order order;


	public Table(int tableId, int numOfSeats, boolean isReserved, boolean isOccupied, int customerNo, Order o) {
		this.tableId = tableId;
		this.numOfSeats = numOfSeats;
		this.isReserved = isReserved;
		this.isOccupied = isOccupied;
		this.customerNo = customerNo;
		this.order = o;
	}

	public Table(int tableId, int numOfSeats, boolean isReserved, boolean isOccupied, int customerNo) {
		this.tableId = tableId;
		this.numOfSeats = numOfSeats;
		this.isReserved = isReserved;
		this.isOccupied = isOccupied;
		this.customerNo = customerNo;
	}
	
	public Table(int tableId, int numOfSeats, boolean isOccupied, Order o) {
		this.tableId = tableId;
		this.numOfSeats = numOfSeats;
		this.isOccupied = isOccupied;
		this.order = o;
	}

	//TableId Getter & Setters
	public int getTableId() { return tableId; }

	public void setTableID(int newTableId) { tableId = newTableId; }

	//NumofSeats Getter & Setters
	public int getNumOfSeats() { return numOfSeats; }

	public void setNumOfSeats(int newNumOfSeats) { numOfSeats = newNumOfSeats; }

	//Order Getter & Setters
	public Order getOrder() { return this.order; }
	public void makeOrder(Order o) { this.order = o; }

	public void setReservedTable()
	{
		this.isReserved = true;
		this.isOccupied = true;
	}

	public boolean getIsReserved() { return isReserved; }

	public void setIsReserved() { this.isReserved = true; }

	public boolean getIsOccupied() { return isOccupied; }

	public void setIsOccupied() { this.isOccupied = true; }

	public int getCustomerNo() { return customerNo; }

	public void setCustomerNo(int customerNo)
	{
		this.customerNo = customerNo;
	}

	public void reserveTable(int custNo) {
		isReserved = true;
		customerNo = custNo;
	}

	public void releaseTable() {
		isReserved = false;
		customerNo = 0;
	}

	public void assignTable(int custNo) {
		isReserved = false;
		isOccupied = true;
		customerNo = custNo;
	}

	public void freeTable() {
		isReserved = false;
		isOccupied = false;
		customerNo = 0;
	}

	public void displayStatus()
	{
		System.out.printf("%-20s", getTableId());
		System.out.printf("%-20s", getNumOfSeats());

		System.out.printf("%-20s", getIsOccupied() ? "Occupied" : (getIsReserved() ? "Reserved" : "Available"));

		System.out.printf("%-20s%n", (getCustomerNo() != 0) ? getCustomerNo() : "N/A");
	}
}
