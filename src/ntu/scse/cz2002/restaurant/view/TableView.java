package ntu.scse.cz2002.restaurant.view;

import java.util.ArrayList;

import ntu.scse.cz2002.restaurant.control.ReservationController;
import ntu.scse.cz2002.restaurant.control.TableController;
import ntu.scse.cz2002.restaurant.model.Table;

/**
 * Boundary Class for viewing table status
 * @author Nguyen Kim Xuyen
 * @version 1.0
 * @since 2019-4-17
 */
public class TableView {
	TableController tCtrl;
	ReservationController rCtrl = new ReservationController();
	
	ArrayList<Table> tables = new ArrayList<Table>();
	
	public TableView(TableController tCtrl) {
		this.tCtrl = tCtrl;
	}
	
	/**
	 * Print table of table availability
	 */
	public void showTables() {
		
		System.out.println("----------------------------------------------------\n" +
						   "Table ID   |  Pax   |   Availability\n" + 
						   "----------------------------------------------------\n");
		
		tCtrl.getTableUpdatedStatus();
		
		System.out.println("\n\n\n");
	}
	
	

}
