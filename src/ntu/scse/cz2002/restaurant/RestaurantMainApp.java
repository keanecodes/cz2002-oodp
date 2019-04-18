package ntu.scse.cz2002.restaurant;

import java.io.*;
import java.text.*;
import java.util.InputMismatchException;
import java.util.Scanner;

import ntu.scse.cz2002.restaurant.data.*;
import ntu.scse.cz2002.restaurant.model.Restaurant;
//import ntu.scse.cz2002.restaurant.model.Staff;
import ntu.scse.cz2002.restaurant.view.*;

public class RestaurantMainApp {

	public static void main(String[] args) throws ParseException, IOException {

		// TODO Load data from previous session or initialise data
		Restaurant r = RestaurantDA.loadData();
		Scanner sc = new Scanner(System.in);
		int choice = 0;

		StaffView.showWelcomeHeadingFor(r.getCurrentStaff());
		do {
			System.out.println("What would you like to do today?");
			System.out.println("1. View and Edit Menu");
			System.out.println("2. View and Edit Orders");
			System.out.println("3. View and Edit Reservation");
			System.out.println("4. View Invoices");
			System.out.println("5. Print Revenue Report");
			System.out.println("6. Quit");
			System.out.println("Your choice: ");

			try {
				choice = sc.nextInt();
			} catch (InputMismatchException ex) {
				System.out.println("Invalid input! Please try again..");
				sc.nextLine(); // Clear the garbage input
				continue;
			}

			switch (choice) {
			case 1:
				(new MenuView()).MenuUI(); break;
			case 2:
				(new OrderView()).OrderUI(); break;
			case 3:
				(new ReservationView()).ReservationUI(); break;
			case 6:
				break;
			default:
				System.out.println("Invalid choice!");
				break;
			}
		} while (choice != 6);

		// TODO Save state before closing
		// RestaurantData.saveToFile();
	}

	public static void showPersistOptionsAndInputPanel() throws ParseException {
		System.out.println("(H) Home\t(B) Back");
		System.out.print("> ");
	}
}
