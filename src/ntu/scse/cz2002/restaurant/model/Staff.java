package ntu.scse.cz2002.restaurant.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author YingTing
 * @version 1.0
 * @since 2019-4-17
 * staff who work at the restaurant
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
	 * @param name name of staff
	 * @param gender gender of staff
	 * @param staffID ID of staff
	 * @param jobTitle jobTitle of staff
	 * @param lastUsed when staff was last used
	 * Staff constructor
	 */
	public Staff(String name, char gender, int staffID, String jobTitle, Date lastUsed) {
		this.name = name;
		this.gender = gender;
		this.staffID = staffID;
		this.jobTitle = jobTitle;
		this.lastUsed = lastUsed;
	}

	/**
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
	 * @param name name of staff
	 * @param staffID ID of staff
	 */
	public Staff(String name, int staffID) {
		this.name = name;
		this.staffID = staffID;
	}

	/**
	 * @return returns name of staff
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return returns gender of staff
	 */
	public char getGender() {
		return gender;
	}

	/**
	 * @return returns ID of staff
	 */
	public int getStaffID() {
		return staffID;
	}

	/**
	 * @return returns jobTitle of staff
	 */
	public String getJobTitle() {
		return jobTitle;
	}

	/**
	 * @return returns lastUsed of staff
	 */
	public Date getLastUsed() {
		return lastUsed;
	}

	/**
	 * @param name sets name of staff
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param gender sets gender of staff
	 */
	public void setGender(char gender) {
		this.gender = gender;
	}

	/**
	 * @param gender sets ID of staff
	 */
	public void setStaffID(int staffID) {
		this.staffID = staffID;
	}

	/**
	 * @param jobTitle sets jobTitle of staff
	 */
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	/**
	 * @param lastUsed sets when staff was last used
	 */
	public void setLastUsed(Date lastUsed) {
		this.lastUsed = lastUsed;
	}

	/**
	 * @param s compares the details of two staff to check if it is the right staff
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
