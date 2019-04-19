package ntu.scse.cz2002.restaurant.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author moongee
 *
 */
public class Staff implements Comparable<Staff>, Serializable {
	/**
	 * 
	 */
	private String name;
	/**
	 * 
	 */
	private char gender;
	/**
	 * 
	 */
	private int staffID;
	/**
	 * 
	 */
	private String jobTitle;
	/**
	 * 
	 */
	private Date lastUsed;

	/**
	 * @param name
	 * @param gender
	 * @param staffID
	 * @param jobTitle
	 * @param lastUsed
	 */
	public Staff(String name, char gender, int staffID, String jobTitle, Date lastUsed) {
		this.name = name;
		this.gender = gender;
		this.staffID = staffID;
		this.jobTitle = jobTitle;
		this.lastUsed = lastUsed;
	}

	/**
	 * @param name
	 * @param gender
	 * @param staffID
	 * @param jobTitle
	 */
	public Staff(String name, char gender, int staffID, String jobTitle) {
		this.name = name;
		this.gender = gender;
		this.staffID = staffID;
		this.jobTitle = jobTitle;
		this.lastUsed = new Date();
	}
	
	/**
	 * @param name
	 * @param staffID
	 */
	public Staff(String name, int staffID) {
		this.name = name;
		this.staffID = staffID;
	}

	/**
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return
	 */
	public char getGender() {
		return gender;
	}

	/**
	 * @return
	 */
	public int getStaffID() {
		return staffID;
	}

	/**
	 * @return
	 */
	public String getJobTitle() {
		return jobTitle;
	}

	/**
	 * @return
	 */
	public Date getLastUsed() {
		return lastUsed;
	}

	/**
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param gender
	 */
	public void setGender(char gender) {
		this.gender = gender;
	}

	public void setStaffID(int staffID) {
		this.staffID = staffID;
	}

	/**
	 * @param jobTitle
	 */
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	/**
	 * @param lastUsed
	 */
	public void setLastUsed(Date lastUsed) {
		this.lastUsed = lastUsed;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
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
