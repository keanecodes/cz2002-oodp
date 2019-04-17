package ntu.scse.cz2002.restaurant.model;

import java.io.Serializable;
import java.util.Date;

public class Staff implements Comparable<Staff>, Serializable {
	private String name;
	private char gender;
	private int staffID;
	private String jobTitle;
	private Date lastUsed;

	public Staff(String name, char gender, int staffID, String jobTitle, Date lastUsed) {
		this.name = name;
		this.gender = gender;
		this.staffID = staffID;
		this.jobTitle = jobTitle;
		this.lastUsed = lastUsed;
	}

	public Staff(String name, char gender, int staffID, String jobTitle) {
		this.name = name;
		this.gender = gender;
		this.staffID = staffID;
		this.jobTitle = jobTitle;
		this.lastUsed = new Date();
	}

	public String getName() {
		return name;
	}

	public char getGender() {
		return gender;
	}

	public int getStaffID() {
		return staffID;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public Date getLastUsed() {
		return lastUsed;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public void setStaffID(int staffID) {
		this.staffID = staffID;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public void setLastUsed(Date lastUsed) {
		this.lastUsed = lastUsed;
	}

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
