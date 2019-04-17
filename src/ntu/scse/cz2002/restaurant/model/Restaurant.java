package ntu.scse.cz2002.restaurant.model;

import ntu.scse.cz2002.restaurant.dataAccess.DataAccessor;
import java.util.ArrayList;

public class Restaurant {
	private int noOfTable = 30;
	private Staff currentStaff;
  private Menu menu;
  private String itemsFilename = "items.dat";
  private String promosFilename = "promos.dat";


	public Restaurant() {
	}

	public Restaurant(int noOfTable, Staff currentStaff) {
		this.noOfTable = noOfTable;
		this.currentStaff = currentStaff;

    createMenu(this.itemsFilename, this.promosFilename);
	}

  private void createMenu(String itemsFilename, String promosFilename){
    ArrayList<MenuItem> items = (ArrayList<MenuItem>)DataAccessor.read(itemsFilename);
    ArrayList<Promotion> promotions = (ArrayList<Promotion>)DataAccessor.read(promosFilename);

    this.menu = new Menu(items, promotions);
  }

	public int getNoOfTable() { return noOfTable; }

	public Staff getCurrentStaff() { return currentStaff; }

	public void setCurrentStaff(Staff currentStaff) { this.currentStaff = currentStaff; };
	
	public void initialize(String filename_Staff, String filename_, String filename) {

	}
}
