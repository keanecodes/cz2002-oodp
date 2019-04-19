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
		String c;
		quit = 0;

		menuMan = new MenuController();

		printInputList();

		while (quit == 0) {
			c = getUserInput();
			processUserInput(c);
		}
	}

	public static void printInputList() {
		System.out.println("// Restaurant Menu Item Management // ------------\n" +
			     		   "--------------------------------------------------\n" +
			     		   " Option\t| Option Description\n" +
			     		   "--------------------------------------------------\n" +
			     		   " (PC)\t| (P)rint menu content by (c)ategory\n" + 
			     		   " (PN)\t| (P)rint meny content by (n)ame\n" +
			     		   " (AI)\t| (A)dd an individual (i)tem to menu\n" +
			     		   " (PI)\t| (A)dd a (p)romotion item to menu\n" +
			     		   " (U)\t| (U)pdate an item in menu\n" +
			     		   " (R)\t| (R)emove an item from menu\n" +
			     		   " (CLEAR)| Clear all items in menu\n");
	}

	public static String getUserInput() {
		System.out.printf("Please select what to do (enter \"0\" to view options):\n");
		System.out.println("---------------------------------------------------\n" +
						   "(<) Back\t\n" +
	     		   		   "---------------------------------------------------");
		System.out.print("> ");

		Scanner sc = new Scanner(System.in);

		String c;

    	c = sc.next();
		
    	return c;
	}

	public static void processUserInput(String c) {
		String name;
		String description;
		double price;
		String type;
		Scanner sc = new Scanner(System.in);

		switch (c.toUpperCase()) {
		case "0":
			printInputList(); break;
		case "PC":
			System.out.println(">> Menu Contents by Category:");
			menuMan.printItemsByCategory();
			System.out.println();
			break;
		case "PN":
			System.out.println(">> Menu Contents by Name:");
			menuMan.printItemsByName();
			System.out.println();
			break;
		case "AI":
			MenuItem item;

			System.out.println(">> New individual item");

			System.out.printf("Enter item name: ");
			name = sc.nextLine();

			System.out.printf("Enter item description: ");
			description = sc.nextLine();

			System.out.printf("Enter item type: ");
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
		case "PI":
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
          System.out.printf("Enter the name of item %d:\n", i+1);
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
		case "U":
        System.out.println(">> Update item/promotion");

        System.out.printf("Enter item/promotion name: ");
			name = sc.nextLine();

			System.out.printf("Enter new description (enter '-1' to leave this unchanged): ");
			description = sc.nextLine();

			System.out.printf("Enter new price (enter '-1' to leave this unchanged): ");

		    try{
		        price = sc.nextDouble();
		    } catch(InputMismatchException ex){
		        System.out.println("Invalid price input!");
		        break;
		    }

			menuMan.updateItem(name, description, price);
			break;
		case "R":

	        System.out.println(">> Remove item/promotion");
	
	        System.out.printf("Enter item/promotion name: ");
			name = sc.nextLine();

			menuMan.removeItem(name);
			break;
		case "CLEAR":
	        char ans='0';
	
	        System.out.println(">> Remove ALL items/promotions");
	        System.out.println("Are you sure you want to remove all current items in the menu? (y/n)");
	
	
	        while(c.equalsIgnoreCase("Y") && c.equalsIgnoreCase("N")){
	            System.out.println("Please answer with 'y' or 'n' only!");
	
	            System.out.println("Are you sure you want to remove all current items in the menu? (y/n)");
	            c = sc.next();
	        }
	
	        if(c.equalsIgnoreCase("y"))
	        		menuMan.clearMenu();
	        break;
		case "<":
			menuMan.saveItems();
			quit = 1;
			Utilities.clearScreen(); MainRestaurantView.show();
			break;
		default:
			System.out.println("Invalid input. Refer to the option table.");
			System.out.print("> ");
			break;
		}
	}
}
