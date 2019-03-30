package ntu.scse.cz2002.restaurant.model;

public class Staff {
	private String name;
	private char gender;
	private int staffID;
	private int jobTitle;
	
	public Staff(String name, char gender, int staffID, int jobTitle) {
		this.name = name;
		this.gender = gender;
		this.staffID = staffID;
		this.jobTitle = jobTitle;
	}
	
	public void printStaffInfo() {
		System.out.println("Name: " + name);
		System.out.println("Gender: " + gender);
		System.out.println("Staff ID: " + staffID);
		System.out.println("Job Title: " + jobTitle);
	}
}
