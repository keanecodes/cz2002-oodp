package ntu.scse.cz2002.restaurant.model;



/**
 * Entity class to track restaurant data
 * @author keane
 *@version 1.0
 *@since 2019-4-17
 *
 */
public class Restaurant {
	/**
	 * Number of tables in this restaurant.
	 */
	private int noOfTable = 30;
	/**
	 * The current staff using this app.
	 */
	private Staff currentStaff;
	/**
	 * This restaurant's menu.
	 */
	private Menu menu;

	/**
	 * Creates an empty restaurant object.
	 */
	public Restaurant() { }

	/**
   * Creates a new restaurant object.
	 * @param noOfTable    Number of tables in this restaurant.
	 * @param currentStaff The current staff using this app.
	 */
	public Restaurant(int noOfTable, Staff currentStaff) {
		this.noOfTable = noOfTable;
		this.currentStaff = currentStaff;
	}

	/**
   * Gets the number of tables in this restaurant.
	 * @return number of tables in this restaurant.
	 */
	public int getNoOfTable() { return noOfTable; }

	/**
   * Gets the current staff using this app.
	 * @return the current staff using this app.
	 */
	public Staff getCurrentStaff() { return currentStaff; }

	/**
   * Sets the current staff using this app.
	 * @param currentStaff The current staff using this app.
	 */
	public void setCurrentStaff(Staff currentStaff) { this.currentStaff = currentStaff; };
}
