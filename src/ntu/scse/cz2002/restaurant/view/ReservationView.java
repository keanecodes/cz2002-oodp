package ntu.scse.cz2002.restaurant.view;

import java.util.InputMismatchException;
import java.util.Scanner;

import ntu.scse.cz2002.restaurant.control.ReservationController;

public class ReservationView {

	private static Scanner sc = new Scanner(System.in);

	ReservationController reserveMangager = new ReservationController();

	private void displayReservationOptions() {
		System.out.print("Restaurant Reservation Submenu");
		System.out.println("________________________________");

		System.out.println("1. View table availability");
		System.out.println("2. View reservations");
		System.out.println("3. Add a new reservation");
		System.out.println("4. Remove a existing reservation");
		System.out.println("5. Quit");
	}

	public void ReservationUI() {
		displayReservationOptions();

		int restaurantChoice = -1;
		do {
			try {
				System.out.println("Please enter your choice (1 - 4): ");
				restaurantChoice = sc.nextInt();
			} catch (InputMismatchException ex) {
				System.out.println("Invalid input! Please try again..");
				sc.nextLine(); // Clear the garbage input
				continue;
			}
			
			switch (restaurantChoice) {
			case 1:
				reserveMangager.viewTableAvailability();
				break;
			case 2:
				reserveMangager.viewReservations();
				break;
			case 3:
				reserveMangager.addReservation();
				break;
			case 4:
				reserveMangager.removeReservation();
				break;
			case 5:
				break;
			default:
				System.out.println("Invalid choice!");
				break;
			}
		} while (restaurantChoice != 5);
	}

}
