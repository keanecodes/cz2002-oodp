package ntu.scse.cz2002.restaurant.controller;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import ntu.scse.cz2002.restaurant.dataAccess.StaffDA;
import ntu.scse.cz2002.restaurant.model.Staff;

public class StaffController {
	
	private ArrayList <Staff> staffArr;
	
	public StaffController() throws IOException, ParseException {
		staffArr = StaffDA.readAllStaff("staff.txt");
	}
	
	public void addStaff(Staff s) {
		staffArr.add(s);
	}
	
	public void removeStaff(Staff s) {
		staffArr.remove(s);
	}
	
	public Staff findStaff(int staffID) {
		Staff corrStaff = null;
		for (int i = 0; i < staffArr.size(); i ++) {
			if (staffArr.get(i).getStaffID() == staffID) {
				corrStaff = staffArr.get(i);
				break;
			}
		}
		if (corrStaff == null)
			System.out.println("Invalid staff ID");
		return corrStaff;
	}
	
//	StaffDA.saveStaffs("staff.txt", staffArr);
}
