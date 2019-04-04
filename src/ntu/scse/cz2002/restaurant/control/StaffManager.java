package ntu.scse.cz2002.restaurant.control;
import java.util.ArrayList;

public class StaffManager {
	
	private ArrayList <Staff> staffArr = StaffDA.readAllStaff("staff.txt");
	
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
	
	StaffDA.saveStaffs("staff.txt", staffArr);
}
