package ntu.scse.cz2002.restaurant.view;

import java.util.Scanner;
import ntu.scse.cz2002.restaurant.control.MenuController;
import ntu.scse.cz2002.restaurant.model.*;
import java.util.InputMismatchException;

public class MenuView {
	public static MenuController menuMan;
	public static int quit = 0;

	public void MenuUI() {
		int c;

		menuMan = new MenuController();

		printInputList();

		while (quit == 0) {
			c = getUserInput();
			processUserInput(c);
		}
	}

	public static void printInputList() {
		System.out.println("--- Menu ---");
		System.out.println("1. Print menu content by category.");
		System.out.println("2. Print meny content by name.");
		System.out.println("3. Add an individual item to menu.");
		System.out.println("4. Add a promotion item to menu.");
		System.out.println("5. Update an item in menu.");
		System.out.println("6. Remove an item from menu.");
		System.out.println("7. Exit menu.");
		System.out.println("");
	}

	public static int getUserInput() {
		System.out.printf("Please select what to do (enter \"h\" to view options): ");

		Scanner sc = new Scanner(System.in);

    int c;

    try{
        c = sc.nextInt();
    } catch (InputMismatchException ex){
        System.out.println("Invalid option!");
        return -1;
    }

		return c;
	}

	public static void processUserInput(int c) {
		String name;
		String description;
		int price;
		String type;
		Scanner sc = new Scanner(System.in);

		switch (c) {
		case 1:
			menuMan.printItemsByCategory();
			System.out.println();
			break;
		case 2:
			menuMan.printItemsByName();
			System.out.println();
			break;
		case 3:
			MenuItem item;

			System.out.printf("Enter item name: ");
			name = sc.nextLine();

			System.out.printf("Enter item description: ");
			description = sc.nextLine();

			System.out.printf("Enter item price: ");
			price = sc.nextInt();

			System.out.println("Enter item type: ");
			type = sc.nextLine();

			item = new MenuItem(name, description, price, type);

			menuMan.addItem(item);
			break;
		case 4:
			Promotion promotion;

			System.out.printf("Enter promotion name: ");
			name = sc.nextLine();

			System.out.printf("Enter promotion description: ");
			description = sc.nextLine();

			System.out.printf("Enter promotion price: ");
			price = sc.nextInt();

			promotion = new Promotion(name, description, price);

			menuMan.addItem(promotion);
			break;
		case 5:
			System.out.printf("Enter item/promotion name: ");
			name = sc.nextLine();

			System.out.printf("Enter new description (enter '-1' to leave this unchanged): ");
			description = sc.nextLine();

			System.out.printf("Enter new price (enter '-1' to leave this unchanged): ");
			price = sc.nextInt();

			menuMan.updateItem(name, description, price);
			break;
		case 6:
			System.out.printf("Enter item/promotion name: ");
			name = sc.nextLine();

			menuMan.removeItem(name);
			break;
		case 7:
			quit = 1;
			break;
		default:
			System.out.println("Invalid option!");
		}
	}
}
