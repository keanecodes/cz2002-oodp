package ntu.scse.cz2002.restaurant.control;

import java.util.ArrayList;

import ntu.scse.cz2002.restaurant.data.DataAccessor;
import ntu.scse.cz2002.restaurant.model.Staff;

/**
 * manages the staff
 * @author Goh Ying Ting
 * @version 1.0
 * @since 2019-04-17
 */
public class StaffController {
	
	/**
	 * data file containing staff data
	 */
	private final static String DATA_FILE = "staff.dat";
	
	/**
	 * array list of staff
	 */
	private static ArrayList<Staff> staffArr;
	
	/**
	 * current staff using the system
	 */
	private Staff currentStaff;
	
	/**
	 * constructor for staff controller
	 */
	public StaffController() {
		try {
			getStaff();
			
		} catch (Exception e){System.out.println("No staff data found.");}
	}
	

	/**
	 *returns the current staff
	 * @return returns current staff using the system
	 */
	public Staff getStaff() { 
		return currentStaff = (Staff) DataAccessor.read(DATA_FILE);
	}
	
	/**
	 * Changes staff using the system to staff specified
	 * @param s Staff specified
	 */
	public void changeCurrentStaffTo(Staff s) {
		DataAccessor.write(DATA_FILE, s);
	}

	/**
	 * finds staff based on staffID
	 * @param staffID ID associated with a staff
	 * @return returns the staff object based on staffID
	 */
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
