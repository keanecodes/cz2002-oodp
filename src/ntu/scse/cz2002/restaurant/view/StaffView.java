package ntu.scse.cz2002.restaurant.view;

import java.util.Scanner;

import ntu.scse.cz2002.restaurant.control.StaffController;
import ntu.scse.cz2002.restaurant.model.Staff;
import ntu.scse.cz2002.restaurant.util.DateUtil;

/**
 * @author YingTing
 * @version 1.0
 * @since 2019-4-17
 * displaying of staff options 
 */
public class StaffView {
	
	/**
	 * creating a staff controller
	 */
	static StaffController ctrl = new StaffController();
	
	
	/**
	 * displaying staff name using the system
	 */
	public static void showCurrentStaffInfo() {
		Staff s = ctrl.getStaff();
		System.out.println(DateUtil.now() + "\t\tOperator: " + s.getName() + "\n\n");
	}
	
	/**
	 * enables the switching of staff to another user
	 */
	public static void showChangeStaffForm() {
		String name;
		int id;
		
		Scanner sc = new Scanner(System.in);
		System.out.println("// Change Current Session Operator // ------------\n" +
                           "--------------------------------------------------\n" + 		
                           "Please Enter New Operator Information\n" + 
                           "--------------------------------------------------"); 
		System.out.print("Name\t: ");
		name = sc.nextLine();
		System.out.print("ID\t: ");
		id = sc.nextInt();
		
		//TODO try catch staff info retrieve
		//TODO limit character
		ctrl.changeCurrentStaffTo(new Staff(name, id));
		
		System.out.println("\nSuccessfully changed operator.\n");
	}
}
