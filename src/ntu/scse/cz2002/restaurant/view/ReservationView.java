package ntu.scse.cz2002.restaurant.view;

import java.util.Scanner;

import ntu.scse.cz2002.restaurant.control.ReservationController;
import ntu.scse.cz2002.restaurant.util.Utilities;

public class ReservationView {

	private static Scanner sc = new Scanner(System.in);

	ReservationController reserveManager = new ReservationController();

	private void displayReservationOptions() {
		System.out.println("// Reservation Management // ---------------------\n" +
			     		   "--------------------------------------------------\n" +
			     		   " Option\t| Option Description\n" +
			     		   "--------------------------------------------------\n" +
			     		   "  (V)\t| View all reservations\n" +
			     		   "  (O)\t| Obtain reservation's details based on customer's contact number\n" +
			     		   "  (A)\t| Add a new reservation\n" +
			     		   "  (R)\t| Remove a existing reservation\n");

		System.out.println("---------------------------------------------------\n" +
			     		   "(<) Back\t\n" +
			     		   "---------------------------------------------------");
		System.out.print("> ");
	}

	public void ReservationUI() {
		
		displayReservationOptions();
		
		String choice = "";
		do {
			choice = sc.next();

			
			switch (choice.toUpperCase()) {
				case "V":
					reserveManager.viewReservations();
					System.out.println(" ");
					displayReservationOptions();
					break;
				case "O":
					reserveManager.obtainCustReservation();
					System.out.println(" ");
					displayReservationOptions();
					break;
				case "A":
					reserveManager.addReservation();
					System.out.println(" ");
					displayReservationOptions();
					break;
				case "R":
					reserveManager.removeReservation();
					System.out.println(" ");
					displayReservationOptions();
					break;
				case "<":
					Utilities.clearScreen(); MainRestaurantView.show();
					break;
				default:
					System.out.println("Invalid input. Refer to the option table.");
					System.out.print("> ");
					break;
			}
		} while (!choice.equalsIgnoreCase("<"));
	}

}
