package ntu.scse.cz2002.restaurant.model;

import java.util.ArrayList;

import ntu.scse.cz2002.restaurant.data.DataAccessor;

/**
 * @author moongee
 *
 */
public class Restaurant {
	/**
	 * 
	 */
	private int noOfTable = 30;
	/**
	 * 
	 */
	private Staff currentStaff;
	/**
	 * 
	 */
	private Menu menu;

	/**
	 * 
	 */
	public Restaurant() { }

	/**
	 * @param noOfTable
	 * @param currentStaff
	 */
	public Restaurant(int noOfTable, Staff currentStaff) {
		this.noOfTable = noOfTable;
		this.currentStaff = currentStaff;
	}

	/**
	 * @return
	 */
	public int getNoOfTable() { return noOfTable; }

	/**
	 * @return
	 */
	public Staff getCurrentStaff() { return currentStaff; }

	/**
	 * @param currentStaff
	 */
	public void setCurrentStaff(Staff currentStaff) { this.currentStaff = currentStaff; };

	/**
	 * @param filename_Staff
	 * @param filename_
	 * @param filename
	 */
	public void initialize(String filename_Staff, String filename_, String filename) {

	}
}
