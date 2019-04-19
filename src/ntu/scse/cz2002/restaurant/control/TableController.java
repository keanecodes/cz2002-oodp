package ntu.scse.cz2002.restaurant.control;

import java.util.ArrayList;
import java.util.List;

import ntu.scse.cz2002.restaurant.model.Order;
import ntu.scse.cz2002.restaurant.model.Table;

/**
 * Control Class to control Table occupancy and reservation
 * <br> communicates with Order&Reservation Controllers to control the flow of orders
 * @author keane
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
            newTable = new Table(i, TABLE_SIZE[i], false, new Order(i));

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
	 * @param tableId ID of specified table.
	 * @return the vacancy status of the specified table.
	 */
	public boolean isTableNotReservedAndOccupied(int tableId) {
		Table t = findTableById(tableId);
		if (t != null) {
			if (!rCtrl.isTableCurrentlyReserved(tableId) && !t.getIsOccupied()) {
				return true;
			} else if (rCtrl.isTableCurrentlyReserved(tableId)) {
				System.out.println("Table is reserved.");
				return false;
			} 
			System.out.println("Table is occupied.");
		}
			
		return false;
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
	 * @param tableId ID of the specified table.
	 * @return true if successful, false if unsuccessful.
	 */
	public boolean releaseTable(int tableId) {
		Table t = findTableById(tableId);
		
		if (t != null) {
			if (t.getOrder() != null) {
				t.freeTable();
				iCtrl.addInvoice(t.getOrder());
        return true;
			}
		}
		
		return false;
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
}
