package ntu.scse.cz2002.restaurant.control;

import java.util.ArrayList;

import ntu.scse.cz2002.restaurant.data.DataAccessor;
import ntu.scse.cz2002.restaurant.model.MenuItem;
import ntu.scse.cz2002.restaurant.model.Staff;

/**
 * @author moongee
 *
 */
public class StaffController {
	
	/**
	 * 
	 */
	private final static String DATA_FILE = "staff.dat";
	
	/**
	 * 
	 */
	private static ArrayList<Staff> staffArr;
	
	/**
	 * 
	 */
	private Staff currentStaff;
	
	/**
	 * 
	 */
	public StaffController() {
		try {
			//Write dummy data
			/*staffArr = new ArrayList<Staff>();
			Staff s = new Staff("Alex", 1820377);

			staffArr.add(s);
			DataAccessor.write(DATA_FILE, s);*/
			
			getStaff();
			
		} catch (Exception e){System.out.println("No staff data found.");}
	}
	

	/**
	 * @return
	 */
	public Staff getStaff() { 
		return currentStaff = (Staff) DataAccessor.read(DATA_FILE);
	}
	
	/**
	 * @param s
	 */
	public void changeCurrentStaffTo(Staff s) {
		DataAccessor.write(DATA_FILE, s);
	}

	/**
	 * @param staffID
	 * @return
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
