package ntu.scse.cz2002.restaurant.view;

import java.util.Scanner;
import ntu.scse.cz2002.restaurant.control.MenuController;
import ntu.scse.cz2002.restaurant.model.*;
import ntu.scse.cz2002.restaurant.util.Utilities;

import java.util.InputMismatchException;
import java.util.ArrayList;

public class MenuView {
	public static MenuController menuMan;
	public static int quit;

	public static void MenuUI() {
		int c;
    quit = 0;

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
		System.out.println("7. Clear all items in menu.");
		System.out.println("8. Exit menu.");
		System.out.println("");
	}

	public static int getUserInput() {
		System.out.printf("Please select what to do (enter \"0\" to view options): ");

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
		double price;
		String type;
		Scanner sc = new Scanner(System.in);

		switch (c) {
    case 0:
        printInputList();
        break;
		case 1:
        System.out.println(">> Menu Contents by Category:");
			menuMan.printItemsByCategory();
			System.out.println();
			break;
		case 2:
        System.out.println(">> Menu Contents by Name:");
			menuMan.printItemsByName();
			System.out.println();
			break;
		case 3:
			MenuItem item;

      System.out.println(">> New individual item");

			System.out.printf("Enter item name: ");
			name = sc.nextLine();

			System.out.printf("Enter item description: ");
			description = sc.nextLine();

			System.out.println("Enter item type: ");
			type = sc.nextLine();

			System.out.printf("Enter item price: ");

      try{
          price = sc.nextDouble();
      } catch(InputMismatchException ex){
          System.out.println("Invalid price input!");
          break;
      }

			item = new MenuItem(name, description, price, type);

			menuMan.addItem(item);
			break;
		case 4:
        int breakOut = 0;
        int itemNum;
        String itemName;
        MenuItem promoItem;
        ArrayList<MenuItem> items = new ArrayList<MenuItem>();
			Promotion promotion;

      System.out.println(">> New promotion item");

			System.out.printf("Enter promotion name: ");
			name = sc.nextLine();

      System.out.printf("Enter the number of items in the promotion: ");

      try{
          itemNum = sc.nextInt();
          sc.nextLine();
      } catch(InputMismatchException ex) {
          System.out.println("Invalid number of items input!");
          break;
      }

      for(int i=0;i<itemNum;i++){
          System.out.printf("Enter the name of item %d\n", i+1);
          itemName = sc.nextLine();

          promoItem = menuMan.getMenu().getItem(itemName);

          if(promoItem != null){
              items.add(promoItem);
          }
          else{
              System.out.println("Item not found!");
              breakOut = 1;
              break;
          }
      }

      if(breakOut == 1) break;

			System.out.printf("Enter promotion description: ");
			description = sc.nextLine();

			System.out.printf("Enter promotion price: ");

      try{
          price = sc.nextDouble();
      } catch(InputMismatchException ex){
          System.out.println("Invalid price input!");
          break;
      }

			promotion = new Promotion(name, description, price, items);

			menuMan.addItem(promotion);
			break;
		case 5:
        System.out.println(">> Update item/promotion");

        System.out.printf("Enter item/promotion name: ");
			name = sc.nextLine();

			System.out.printf("Enter new description (enter '-1' to leave this unchanged): ");
			description = sc.nextLine();

			System.out.printf("Enter new price (enter '-1' to leave this unchanged): ");
			price = sc.nextDouble();

      try{
          price = sc.nextDouble();
      } catch(InputMismatchException ex){
          System.out.println("Invalid price input!");
          break;
      }

			menuMan.updateItem(name, description, price);
			break;
		case 6:

        System.out.println(">> Remove item/promotion");

        System.out.printf("Enter item/promotion name: ");
			name = sc.nextLine();

			menuMan.removeItem(name);
			break;
    case 7:
        char ans='0';

        System.out.println(">> Remove ALL items/promotions");
        System.out.println("Are you sure you want to remove all current items in the menu? (y/n)");

        c = sc.next().charAt(0);

        while(c != 'y' && c != 'Y' && c != 'n' && c != 'N'){
            System.out.println("Please answer with 'y' or 'n' only!");

            System.out.println("Are you sure you want to remove all current items in the menu? (y/n)");
            c = sc.next().charAt(0);
        }

        if(c == 'y' || c == 'Y')menuMan.clearMenu();
        break;
		case 8:
        menuMan.saveItems();
			quit = 1;
			Utilities.clearScreen(); MainRestaurantView.show();
			break;
		default:
			System.out.println("Invalid option!");
		}
	}
}
