package ntu.scse.cz2002.restaurant.view;

import java.util.Scanner;

import ntu.scse.cz2002.restaurant.control.ReservationController;
import ntu.scse.cz2002.restaurant.control.TableController;
import ntu.scse.cz2002.restaurant.util.Utilities;

/**
Handles the UI catered for Reservation as a sub-menu
@author  Gee Cheng Mun
@version 1.0
@since   2019-04-17
*/
public class ReservationView {

	/**
	 * A standard Java Scanner used for processing user input
	 */
	private static Scanner sc = new Scanner(System.in);

	/**
	 * Creation of a reservation controller object
	 */
	ReservationController reserveManager = new ReservationController();
	
	/**
	 * A table controller attribute
	 */
	TableController tCtrl;
	
	/**
	 * A constructor of the Reservation view
	 */
	public ReservationView() { }
	
	/**
	 * @param tCtrl: Passes in the table controller object
	 */
	public ReservationView(TableController tCtrl) { 
		this.tCtrl = tCtrl;
	}

	/**
	 * Displays the options available for 'Reservations'
	 */
	private void displayReservationOptions() {
		System.out.println("// Reservation Management // ---------------------\n" +
			     		   "--------------------------------------------------\n" +
			     		   " Option\t| Option Description\n" +
			     		   "--------------------------------------------------\n" +
			     		   "  (V)\t| View all reservations\n" +
			     		   "  (O)\t| Obtain reservation's details based on customer's contact number\n" +
			     		   "  (A)\t| Add a new reservation\n" +
			     		   "  (R)\t| Remove an existing reservation\n");

		System.out.println("---------------------------------------------------\n" +
			     		   "(<) Back\t\n" +
			     		   "---------------------------------------------------");
		System.out.print("> ");
	}

	/**
	 * Takes in the user input and perform the functionality according to the option selected
	 */
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
					Utilities.clearScreen(); (new MainRestaurantView()).show();
					break;
				default:
					System.out.println("Invalid input. Refer to the option table.");
					System.out.print("> ");
					break;
			}
		} while (!choice.equalsIgnoreCase("<"));
	}

}
