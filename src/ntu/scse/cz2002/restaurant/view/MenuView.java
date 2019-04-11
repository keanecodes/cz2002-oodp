package ntu.scse.cz2002.restaurant.view;

import java.util.Scanner;
import ntu.scse.cz2002.restaurant.control.MenuManager;
import ntu.scse.cz2002.restaurant.model.*;

public class MenuView{
    public static MenuManager menuMan;

    public static void main(String args[]){
        int quit = 0;
        char c;

        menuMan = new MenuManager();

        printInputList();

        while(quit==0){
            c = getUserInput();
            processUserInput(c);
        }
    }

    public static void printInputList(){
        System.out.println("--- Menu ---");
        System.out.println("1. Print menu content by category.");
        System.out.println("2. Print meny content by name.");
        System.out.println("3. Add an individual item to menu.");
        System.out.println("4. Add a promotion item to menu.");
        System.out.println("5. Update an item in menu.");
        System.out.println("6. Remove an item from menu.");
        System.out.println("7. Exit menu.");
        println("");
    }

    public static char getUserInput(){
        System.out.printf("Please select what to do (enter \"h\" to view options): ");

        Scanner sc = new Scanner(System.in);
        char c = sc.next().charAt(0);

        return c;
    }

    public static void processUserInput(char c){
        switch(c){
        case '1':
            menuMan.printItemsByCategory();
            System.out.println();
            break;
        case '2':
            menuMan.printItemsByName();
            System.out.println();
            break;
        case '3':
            MenuItem item;
            String name;
            String description;
            int price;
            String type;

            System.out.printf("Enter item name: ");
            name = sc.nextLine();

            System.out.printf("Enter item description: ");
            description = sc.nextLine();

            System.out.printf("Enter item price: ");
            price = sc.nextInt();

            System.out.println("Enter item type: ");
            type = sc.nextLine();

            item = new MenuItem(name, description, price, type);

            menuMan.addMenuItem(item);
            break;
        case '4':
            Promotion promotion;
            String name;
            String description;
            int price;
            String type;

            System.out.printf("Enter promotion name: ");
            name = sc.nextLine();

            System.out.printf("Enter promotion description: ");
            description = sc.nextLine();

            System.out.printf("Enter promotion price: ");
            price = sc.nextInt();

            System.out.println("Enter promotion type: ");
            type = sc.nextLine();

            promotion = new Promotion(name, description, price, type);

            menuMan.addPromotion(promotion);
            break;
        case'5':
            String name;
            String description;
            int price;

            System.out.printf("Enter item/promotion name: ");
            name = sc.nextLine();

            System.out.printf("Enter new description (enter '-1' to leave this unchanged): ");
            description = sc.nextLine();

            System.out.printf("Enter new price (enter '-1' to leave this unchanged): ");
            price = sc.nextInt();

            menuMan.updateItem(name, description, price);
            break;
        case '6':
            String name;

            System.out.printf("Enter item/promotion name: ");
            name = sc.nextLine();

            menuMan.removeItem(name);
            break;
        case '7':
            quit = 1;
            break;
        default:
            System.out.println("Invalid option!");
        }
    }
}
