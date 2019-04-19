package ntu.scse.cz2002.restaurant.control;

import java.util.ArrayList;
import java.util.List;

import ntu.scse.cz2002.restaurant.model.Order;
import ntu.scse.cz2002.restaurant.model.Table;

public class TableController {
	
	
	
	private static final int[] TABLE_SIZE = { 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 
											4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 
											8, 8, 8, 8, 8,
											10, 10, 10, 10, 10 };
	
	private ArrayList<Table> tList = new ArrayList<Table>();
	
	ReservationController rCtrl;
	TableController tCtrl;
	InvoiceController iCtrl;
	
	public TableController() { 
		setUpTables();
	}
	
	/* Create the list of tables with the indicated sizes */
	private void setUpTables() {
		int tableNumber = 1;
		for (int tableSize : TABLE_SIZE) {
			Table newTable;
			
			tableNumber++;
			// Constructor: tableNunber, numOfSeats, isOccupied, order
			newTable = new Table(tableNumber, tableSize, false, new Order(tableNumber));

			tList.add(newTable);
		}
	}

	
	public TableController(ReservationController rCtrl) {
		super();
		this.rCtrl = rCtrl;
	}
	
	public TableController(TableController tCtrl) {
		super();
		this.tCtrl = tCtrl;
	}
	
	public ArrayList<Table> getTables () { return this.tList; }
	
	public boolean isTableNotReservedAndOccupied(int tableId) {
		Table t = findTableById(tableId);
		if (!rCtrl.isTableCurrentlyReserved(tableId) && !t.getIsOccupied()) {
			return true;
		} else if (rCtrl.isTableCurrentlyReserved(tableId)) {
			System.out.println("Table is reserved.");
			return false;
		} 
		
		System.out.println("Table is occupied.");
		return false;
	}
	
	public void setUpforOrder(int tableId) {
		Table t = findTableById(tableId);
		t.assignTable(tableId);
	}
	
	public boolean releaseTable(int tableId) {
		Table t = findTableById(tableId);
		
		if (t.getOrder() != null) {
			t.freeTable();
			iCtrl.addInvoice(t.getOrder());
		}
		
		return true;
	}
	
	public Table findTableById(int id) {
		for(int i = 0; i < tList.size(); i++)
			if (tList.get(i).getTableId() == id) 
				return tList.get(i);
		
		return null;
	}
}
