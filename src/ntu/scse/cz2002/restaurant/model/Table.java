package ntu.scse.cz2002.restaurant.model;

public class Table {

	private int tableId;
	private int numOfSeats;
	private boolean isReserved;
	private boolean isOccupied;
	private int customerNo;

	public Table(int tableId, int numOfSeats, boolean isReserved, boolean isOccupied, int customerNo) {
		this.tableId = tableId;
		this.numOfSeats = numOfSeats;
		this.isReserved = isReserved;
		this.isOccupied = isOccupied;
		this.customerNo = customerNo;
	}

	public int getTableId() {
		return tableId;
	}

	public void setTableID(int newTableId) {
		tableId = newTableId;
	}

	public int getNumOfSeats() {
		return numOfSeats;
	}

	public void setNumOfSeats(int newNumOfSeats) {
		numOfSeats = newNumOfSeats;
	}

	public boolean getIsReserved() {
		return isReserved;
	}

	public boolean getIsOccupied() {
		return isOccupied;
	}

	public int getCustomerNo() {
		return customerNo;
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

	public void displayStatus() {
		System.out.printf("%-20s", getTableId());
		System.out.printf("%-20s", getNumOfSeats());

		System.out.printf("%-20s", getIsOccupied() ? "Occupied" : (getIsReserved() ? "Reserved" : "Available"));

		System.out.printf("%-20s%n", (getCustomerNo() != 0) ? getCustomerNo() : "N/A");
	}
}
