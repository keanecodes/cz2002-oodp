package ntu.scse.cz2002.restaurant.model;

public class Restaurant {
	private int noOfTable = 30;
	private Staff currentStaff;
	
	public Restaurant() { super(); }
	
	public Restaurant(int noOfTable, Staff currentStaff) {
		super();
		this.noOfTable = noOfTable;
		this.currentStaff = currentStaff;
	}
	
	public int getNoOfTable() { return noOfTable; }

	public Staff getCurrentStaff() { return currentStaff; }

	public void setCurrentStaff(Staff currentStaff) { this.currentStaff = currentStaff; }

	
	
	
	
}
