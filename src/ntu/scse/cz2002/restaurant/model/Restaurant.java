package ntu.scse.cz2002.restaurant.model;

import java.util.ArrayList;

import ntu.scse.cz2002.restaurant.data.DataAccessor;

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
