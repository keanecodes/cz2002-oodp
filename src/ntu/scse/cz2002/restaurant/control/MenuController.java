package ntu.scse.cz2002.restaurant.control;

import ntu.scse.cz2002.restaurant.dataAccess.DataAccessor;
import ntu.scse.cz2002.restaurant.model.Menu;
import ntu.scse.cz2002.restaurant.model.MenuItem;
import ntu.scse.cz2002.restaurant.model.Promotion;

import java.util.ArrayList;
import java.util.Collections;

/**
 Handles information flow for Menu class
 @author  Johan Tjuatja
 @version 1.0
 @since   2019-04-17
 */
public class MenuController{

    /**
     * Contents of menu (items & promotions)
     */
    private Menu menu;

    /**
     * Types of items in the menu (main, drinks, dessert, etc.)
     */
    private ArrayList<String> types;

    /**
     * Create a controller for menu contents.
     * Add/update/remove/display contents of menu.
     * @param items      The list of ala carte items
     * @param promotions The list of promotion items
     */
    public MenuController(ArrayList<MenuItem> items, ArrayList<Promotion> promotions){
        // String itemFilename = "items.dat";
        // String promoFilename = "promos.dat";

        // try{
        //     this.loadItems(itemFilename, promoFilename);
        // } catch (Exception e){System.out.println("No menu data found.");}

        if(items != null || promotions != null){
            this.menu = new Menu(items, promotions);

            System.out.println("Menu contents successfully loaded!");
            updateTypesList(items);
        }
        else{
            this.menu = new Menu();

            System.out.println("Menu is empty");
        }
    }

    // deprecated
    private void loadItems(String itemFilename, String promoFilename){
        ArrayList<MenuItem> items = (ArrayList<MenuItem>)DataAccessor.read(itemFilename);
        ArrayList<Promotion> promotions = (ArrayList<Promotion>)DataAccessor.read(promoFilename);

        if(items != null || promotions != null){
            this.menu = new Menu(items, promotions);

            System.out.println("Menu contents successfully loaded!");
            updateTypesList(items);
        }
        else{
            this.menu = new Menu();

            System.out.println("Menu is empty");
        }
    }

    /**
     * Compile the existing types of items in the menu.
     * @return types of items in menu
     */
    private void updateTypesList(ArrayList<MenuItem> items){
        for(int i=0;i<items.size();i++){
            if(this.types.contains(items.get(i).getType())){
                continue;
            }
            else{
                this.types.add(items.get(i).getType());
            }
        }

        System.out.println("Types list successfully updated!");
    }

    /**
     * Gets menu contents.
     * @return contents of the menu.
     */
    public Menu getMenu() {
    	return menu;
    }

    /**
     * Add an ala carte item into the menu.
     */
    public void addItem(MenuItem item){
        this.menu.addMenuItem(item);
        System.out.println("The following menu item has been added to the menu:");
        System.out.println("  Name: " + item.getName());
        System.out.println("  Description: " + item.getDescription());
        System.out.println("  Price: $" +  String.valueOf(item.getPrice()));
        System.out.println("  Type: " + item.getType());
    }

    /**
     * Add a promotion item into the menu.
     */
    public void addItem(Promotion promotion){
        this.menu.addPromotion(promotion);
        System.out.println("The following set has been addded to the menu:");
        System.out.println("  Name: " + promotion.getPromotionName());
        System.out.println("  Description: " + promotion.getPromotionDescription());
        System.out.println("  Price: $" + String.valueOf(promotion.getPrice()));
    }

    /**
     * Remove an item (ala carte/promotion) from the menu.
     */
    public void removeItem(String name){
        if(this.menu.removeMenuItem(name) == 0){
            if(this.menu.removePromotion(name) == 0){
                System.out.println(name + " does not exist in menu!");
                return;
            }
        }

        System.out.println(name + " has been successfully deleted from menu!");
    }

    /**
     * Update the information for an item.
     */
    public void updateItem(String name, String description, int price){
        MenuItem item;
        Promotion promotion;

        if((item = this.menu.getMenuItem(name)) != null){
            item.setDescription(description);
            item.setPrice(price);
            System.out.println("Item susccessfully updated!");
        }
        else if((promotion = this.menu.getPromotion(name)) != null){
            promotion.setPromotionDescription(description);
            promotion.setPrice(price);
            System.out.println("Item susccessfully updated!");
        }
        else{
            System.out.println(name + " does not exist in menu!");
        }
    }

    /**
     * Print contents of menu, sorted by item type.
     */
    public void printItemsByCategory(){
        for(int i=0;i<this.types.size();i++){
            System.out.println(this.types.get(i));
            for(int j=0;j<this.menu.getItemCount();j++){
                MenuItem item = this.menu.getItemList().get(j);

                if(item.getType().equals(this.types.get(i))){
                    System.out.println(item.getName() + "  $" + item.getPrice());
                    System.out.println(item.getDescription());
                }
            }
        }

        System.out.printf("\nPromotion\n");

        for(int k=0;k<this.menu.getPromotionCount();k++){
            Promotion promotion = this.menu.getPromotionList().get(k);
            System.out.println(promotion.getPromotionName() + " $" + String.valueOf(promotion.getPrice()));
            System.out.println("  " + promotion.getPromotionDescription());
        }
    }

    /**
     * Print contents of menu, sorted by name.
     */
    public void printItemsByName(){
        ArrayList<String> names = new ArrayList<String>();
        MenuItem item;
        Promotion promotion;

        for(int i=0;i<this.menu.getItemCount();i++){
            names.add(this.menu.getItemList().get(i).getName());
        }

        for(int j=0;j<this.menu.getPromotionCount();j++){
            names.add(this.menu.getPromotionList().get(j).getPromotionName());
        }

        Collections.sort(names);

        for(int k=0;k<names.size();k++){
            if(this.menu.getMenuItem(names.get(k)) == null){
                if(this.menu.getPromotion(names.get(k)) == null){
                    System.out.println(names.get(k) + " does not exist in menu!");
                }
                else{
                    promotion = this.menu.getPromotion(names.get(k));
                    System.out.println(names.get(k) + " $" + String.valueOf(promotion.getPrice()));
                    System.out.println("  " + promotion.getPromotionDescription());
                }
            }
            else{
                item = this.menu.getMenuItem(names.get(k));
                System.out.println(names.get(k) + " $" + String.valueOf(item.getPrice()));
                System.out.println("  " + item.getDescription());
            }
        }
    }
}
