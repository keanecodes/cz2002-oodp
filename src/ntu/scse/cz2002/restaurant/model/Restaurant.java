package ntu.scse.cz2002.restaurant.model;

import ntu.scse.cz2002.restaurant.dataAccess.DataAccessor;
import java.util.ArrayList;

public class Restaurant {
	private int noOfTable = 30;
	private Staff currentStaff;
	private Menu menu;

	public Restaurant() { }

	public Restaurant(int noOfTable, Staff currentStaff) {
		this.noOfTable = noOfTable;
		this.currentStaff = currentStaff;
	}

	public int getNoOfTable() { return noOfTable; }

	public Staff getCurrentStaff() { return currentStaff; }

	public void setCurrentStaff(Staff currentStaff) { this.currentStaff = currentStaff; };

	public void initialize(String filename_Staff, String filename_, String filename) {

	}
}
