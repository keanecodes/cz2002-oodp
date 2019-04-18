package ntu.scse.cz2002.restaurant.view;

import java.util.InputMismatchException;
import java.util.Scanner;

import ntu.scse.cz2002.restaurant.control.ReservationController;
import ntu.scse.cz2002.restaurant.util.Utilities;

public class ReservationView {

	private static Scanner sc = new Scanner(System.in);

	ReservationController reserveMangager = new ReservationController();

	private void displayReservationOptions() {
		System.out.println("// Reservation Management // ---------------------\n" +
			     		   "--------------------------------------------------\n" +
			     		   " Option\t| Option Description\n" +
			     		   "--------------------------------------------------\n" +
			     		   "  (T)\t| Table availability\n" + 
			     		   "  (V)\t| View reservations\n" +
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
				case "T":
					reserveMangager.viewTableAvailability();
					break;
				case "V":
					reserveMangager.viewReservations();
					break;
				case "A":
					reserveMangager.addReservation();
					break;
				case "R":
					reserveMangager.removeReservation();
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
