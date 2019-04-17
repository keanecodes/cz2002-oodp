package ntu.scse.cz2002.restaurant.view;

import java.util.InputMismatchException;
import java.util.Scanner;

import ntu.scse.cz2002.restaurant.control.ReservationManager;

public class ReservationView {

	private static Scanner sc;

	private enum ManagerFunctions {
		ViewTableAvailability, ViewReservations, AddReservation, RemoveReservation
	}

	ReservationManager reserveMangager = new ReservationManager();

	private void displayReservationOptions() {
		System.out.print("Restaurant Reservation Submenu");
		System.out.println("________________________________:");

		System.out.println("1. View Table Availability");
		System.out.println("2. View reservations");
		System.out.println("3. Add a new reservation");
		System.out.println("4. Remove a existing reservation");
	}

	public int getReservationChoice() {
		displayReservationOptions();

		int maxRestaurantChoices = ManagerFunctions.values().length;
		int restaurantChoice = -1;
		do {
			try {
				System.out.printf("Please enter your choice (1 - 4): ");
				restaurantChoice = sc.nextInt();
				sc.nextLine();
			} catch (InputMismatchException ex) {
				System.out.println("Invalid input! Please try again..");
				sc.nextLine(); // Clear the garbage input
				continue;
			} catch (Exception ex) {
				System.out.println("Invalid input! Please try again..");
				sc.nextLine(); // Clear the garbage input
				continue;
			}

			if (restaurantChoice < 1 || restaurantChoice > maxRestaurantChoices)
				System.out.println("Invalid choice! Please try again..");

		} while (restaurantChoice < 0 || restaurantChoice > maxRestaurantChoices);

		if (restaurantChoice == 0)
			return restaurantChoice; // Go back to main menu
		else {
			switch (ManagerFunctions.values()[restaurantChoice - 1]) {
			case ViewTableAvailability:
				reserveMangager.viewTableAvailability();
				break;

			case ViewReservations:
				reserveMangager.viewReservations();
				break;

			case AddReservation:
				reserveMangager.addReservation();
				break;

			case RemoveReservation:
				reserveMangager.removeReservation();
				break;
			}
		}

		return restaurantChoice;
	}

}
