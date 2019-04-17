package ntu.scse.cz2002.restaurant.view;

import java.text.ParseException;

import ntu.scse.cz2002.restaurant.model.Staff;

public class StaffView {

	public static void showWelcomeHeadingFor(Staff s) throws ParseException {
		System.out.println("Hello, " + s.getName() + " (" + s.getStaffID() + ")\n");
	}

	public void printInfoOf(Staff s) {
		System.out.println("Name: " + s.getName());
		System.out.println("Gender: " + s.getGender());
		System.out.println("Staff ID: " + s.getStaffID());
		System.out.println("Job Title: " + s.getJobTitle());
	}
}
