package ntu.scse.cz2002.restaurant.control;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;

import ntu.scse.cz2002.restaurant.model.Order;
import ntu.scse.cz2002.restaurant.model.Table;
import ntu.scse.cz2002.restaurant.model.Reservation;

/**
 * Control Class to control Table occupancy and reservation;
 <br> communicates with Order and Reservation Controllers to control the flow of orders;
 * @author Nguyen Kim Xuyen
 *@version 1.0
 *@since 2019-04-17
 */
public class TableController {
	
	
	
	/**
	 * List of table sizes.
	 */
	private static final int[] TABLE_SIZE = { 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 
											4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 
											8, 8, 8, 8, 8,
											10, 10, 10, 10, 10 };
	
	/**
	 * List of tables.
	 */
	private ArrayList<Table> tList = new ArrayList<Table>();
	
	/**
	 * Controller to modify reservations.
	 */
	ReservationController rCtrl = new ReservationController(this);
	/**
	 * Controller to modify invoices.
	 */
	InvoiceController iCtrl = new InvoiceController();
	
	/**
	 * Creates a new controller to modify tables.
	 */
	public TableController() { 
		setUpTables();
	}
	
	/**
   * Creates a new controller to modify tables.
   * This constructor is able to receive external reservation controller.
	 * @param rCtrl External reservation controller.
	 */
	public TableController(ReservationController rCtrl) {
		super();
		this.rCtrl = rCtrl;
	}
	
    /**
     * Creates the list of tables with the indicated sizes
     */
    private void setUpTables() {
		
        for (int i = 0; i < TABLE_SIZE.length; i++){
            //for (int tableSize : TABLE_SIZE) 
            Table newTable;
            // Constructor: tableNunber, numOfSeats, isOccupied, order
            newTable = new Table(i+1, TABLE_SIZE[i], false, new Order(i+1));

            tList.add(newTable);
        }
    }

	/**
   * Gets the list of tables.
	 * @return the list of tables.
	 */
	public ArrayList<Table> getTables () { return this.tList; }
	
	/**
   * Checks if a specified table is not occupied and not reserved.
	 * @param t Specified table.
	 * @return the vacancy status of the specified table.
	 */
	public boolean isTableNotReservedAndOccupied(Table t) {
		//Table t = findTableById(tableId);
		if (t != null) {
			if (!rCtrl.isTableCurrentlyReserved(t.getTableId()) && !t.getIsOccupied()) {
				return true;
			} else if (rCtrl.isTableCurrentlyReserved(t.getTableId())) {

				System.out.print("Table is reserved.\nHas the customer arrived? (y/n) ");
				
				Scanner sc = new Scanner(System.in);
				if (sc.next().equalsIgnoreCase("Y"))  {
					if(rCtrl.removeCustArrived()) {
						return true;
					} 
					return false;
				} 
				
				return false;
			} 
			System.out.println("Table has already made an order. Try editing.");
		}
			
		return false;
	}
	
	public void getTableUpdatedStatus() {
		
		String status;
		
		for (int i = 0; i < this.tList.size(); i++) {
			Table t = this.tList.get(i);
			
			status = t.getIsOccupied() ? "Occupied" : "Available";
			status = rCtrl.isTableCurrentlyReserved(t.getTableId()) ? "Reserved" : status;
			
			System.out.println("   " + (t.getTableId()) + 
					"\t       " + t.getNumOfSeats() + "\t " +
					status);
		}
		
	}
	
	/**
   * Sets table as occupied.
	 * @param tableId ID of specified table.
	 */
	public void setUpforOrder(int tableId) {
		Table t = findTableById(tableId);
		t.assignTable(tableId);
	}
	
	/**
   * Free a specified table after customers are done eating.
	 * @param t Specified table.
	 * @return true if successful, false if unsuccessful.
	 */
	public boolean releaseTable(Table t) {
		if (t != null) {
			Order currentOrder = t.getOrder();
			if (currentOrder != null) {
				iCtrl.addInvoice(currentOrder);
				currentOrder.setIsOnGoing(false);
				t.freeTable();
				
				t.makeOrder(new Order(getLargestOrderId(), t.getTableId()));
				return true;
			}
		}
		
		return false;
	}
	
	public int getLargestOrderId() {
		int largest = 0;
		
		for (int i = 0; i < tList.size(); i++) {
			int temp = tList.get(i).getTableId();
			if (temp > largest)
				largest = temp;
		}
		
		return largest;
	}
	
	/**
   * Gets a table specified table object.
	 * @param id ID of specified table.
	 * @return the table object.
	 */
	public Table findTableById(int id) {
		for(int i = 0; i < tList.size(); i++)
			if (tList.get(i).getTableId() == id) 
				return tList.get(i);
		
		return null;
	}
	
	public void setReservedTablesAtCurrentTime() {
		List<Reservation> allReservations = rCtrl.getAllReservations();
		Calendar currentInstant = GregorianCalendar.getInstance();
		Date currentDateTime = currentInstant.getTime();
		currentInstant.setTime(currentDateTime);
		for (int i = 0; i < allReservations.size(); i++) {
			
			Calendar restStartDateTime = allReservations.get(i).getStartDateTime();
			Calendar restClone = (Calendar) restStartDateTime.clone();
			restClone.add(Calendar.MINUTE, 15); // change this for reservation time
			
			int tableID = allReservations.get(i).getTableNo();
			Table t = findTableById(tableID);
			
			if (currentInstant.after(restStartDateTime) && currentInstant.before(restClone)) {
				t.setIsReserved(true);
			}
			else {
				t.setIsReserved(false);
			}
		}
	}
}
