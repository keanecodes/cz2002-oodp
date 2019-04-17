package ntu.scse.cz2002.restaurant.control;

import java.util.ArrayList;

import ntu.scse.cz2002.restaurant.model.MenuItem;
import ntu.scse.cz2002.restaurant.model.Staff;
import ntu.scse.cz2002.restaurant.dataAccess.DataAccessor;

public class StaffController {
	private static ArrayList<Staff> staffArr;

	public void loadStaff(String staffFilename) {
		staffArr = (ArrayList<Staff>) DataAccessor.read(staffFilename);
	}

	public ArrayList<Staff> getStaffArr() {
		return staffArr;
	}

	public void addStaff(Staff s) {
		staffArr.add(s);
	}

	public void removeStaff(Staff s) {
		staffArr.remove(s);
	}

	public Staff findStaff(int staffID) {
		Staff corrStaff = null;
		for (int i = 0; i < staffArr.size(); i++) {
			if (staffArr.get(i).getStaffID() == staffID) {
				corrStaff = staffArr.get(i);
				break;
			}
		}
		if (corrStaff == null)
			System.out.println("Invalid staff ID");
		return corrStaff;
	}

}
