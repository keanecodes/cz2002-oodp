package ntu.scse.cz2002.restaurant.view;

import java.text.ParseException;
import java.util.Scanner;

import ntu.scse.cz2002.restaurant.model.Staff;

public class RestaurantView {
	static Scanner sc = new Scanner(System.in);
	
	
	public static String showMainOptions(Staff staff) throws ParseException {
		
		StaffView.showWelcomeHeadingFor(staff);
		
		
		System.out.println("//Point of Sale// -----------------\n" + 
							"(1) Create Order\n" +
							"(2) View Orders\n" +  
							"(3) Print Sale Revenue Report\n");
		System.out.println("//Reservation// -------------------\n" + 
							"(4) Create Reservation\n" +
							"(5) View Reservation\n" +  
							"(6) Search Reservation\n");
		System.out.println("//Settings// ----------------------\n" + 
							"(7) Maintain Menu Items\n" +
							"(8) Maintain Promotions\n" +  
							"(9) Change User\n");
		
		showPersistOptionsAndInputPanel();
		
		String option  = "";
		
		return option;
		
	}
	
	public static void showPersistOptionsAndInputPanel() throws ParseException {
		System.out.println("(H) Home\t(B) Back");
		System.out.print("> ");
	}

}
