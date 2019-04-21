package ntu.scse.cz2002.restaurant.model;

import java.io.Serializable;
import java.util.Date;

/**
 * staff who work at the restaurant
 * @author Goh Ying Ting
 * @version 1.0
 * @since 2019-4-17
 * 
 */
public class Staff implements Comparable<Staff>, Serializable {
	/**
	 * name of staff
	 */
	private String name;
	/**
	 * gender of staff
	 */
	private char gender;
	/**
	 * ID of staff
	 */
	private int staffID;
	/**
	 * jobTitle of staff
	 */
	private String jobTitle;
	/**
	 * when staff was last used
	 */
	private Date lastUsed;

	/**
	 * Staff constructor
	 * @param name name of staff
	 * @param gender gender of staff
	 * @param staffID ID of staff
	 * @param jobTitle jobTitle of staff
	 * @param lastUsed when staff was last used
	 * 
	 */
	public Staff(String name, char gender, int staffID, String jobTitle, Date lastUsed) {
		this.name = name;
		this.gender = gender;
		this.staffID = staffID;
		this.jobTitle = jobTitle;
		this.lastUsed = lastUsed;
	}

	/**
	 * Staff constructor
	 * @param name name of staff
	 * @param gender gender of staff
	 * @param staffID ID of staff
	 * @param jobTitle jobTitle of staff
	 */
	public Staff(String name, char gender, int staffID, String jobTitle) {
		this.name = name;
		this.gender = gender;
		this.staffID = staffID;
		this.jobTitle = jobTitle;
		this.lastUsed = new Date();
	}
	
	/**
	 * Staff constructor
	 * @param name name of staff
	 * @param staffID ID of staff
	 */
	public Staff(String name, int staffID) {
		this.name = name;
		this.staffID = staffID;
	}

	/**
	 * returns name of staff
	 * @return returns name of staff
	 */
	public String getName() {
		return name;
	}

	/**
	 * returns gender of staff
	 * @return returns gender of staff
	 */
	public char getGender() {
		return gender;
	}

	/**
	 * returns ID of staff
	 * @return returns ID of staff
	 */
	public int getStaffID() {
		return staffID;
	}

	/**
	 * returns jobTitle of staff
	 * @return returns jobTitle of staff
	 */
	public String getJobTitle() {
		return jobTitle;
	}

	/**
	 * returns lastUsed of staff
	 *
	 * @return returns lastUsed of staff
	 */
	public Date getLastUsed() {
		return lastUsed;
	}

	/**
	 * changes name of staff
	 * @param name new name of staff
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Changes gender of staff
	 * @param gender new gender of staff
	 */
	public void setGender(char gender) {
		this.gender = gender;
	}

	/**
	 * Changes ID of staff
	 * @param staffID new ID of staff
	 */
	public void setStaffID(int staffID) {
		this.staffID = staffID;
	}

	/**
	 * Changes jobTitle of staff
	 * @param jobTitle new jobTitle of staff
	 */
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	/**
	 * Changes when staff was last used
	 * @param lastUsed new when staff was last used
	 */
	public void setLastUsed(Date lastUsed) {
		this.lastUsed = lastUsed;
	}

	/**
	 * compares the details of two staff to check if it is the right staff
	 * @param s other staff that is compared to
	 */
	public int compareTo(Staff s) {
		int result;
		if ((result = this.getLastUsed().compareTo(s.getLastUsed())) != 0)
			return result;
		else if ((result = this.getName().compareTo(s.getName())) != 0)
			return result;
		else
			return 0;
	}

}
