package ntu.scse.cz2002.restaurant.control;

import ntu.scse.cz2002.restaurant.data.DataAccessor;
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
    /**
     * 
     */
    private Menu menu;

    /**
     * Types of items in the menu (main, drinks, dessert, etc.)
     */
    /**
     * 
     */
    private ArrayList<String> types;

    /**
     * 
     */
    private String itemFilename = "items.dat";

    /**
     * Create a controller for menu contents.
     * Add/update/remove/display contents of menu.
     * @param items      The list of ala carte items
     * @param promotions The list of promotion items
     */
    /**
     * 
     */
    public MenuController(){
        types = new ArrayList<String>();

        try{
            this.loadItems(itemFilename);
        } catch (Exception e){System.out.println("No menu data found.");}
    }

    // deprecated
    /**
     * @param itemFilename
     */
    private void loadItems(String itemFilename){
        ArrayList<MenuItem> items = (ArrayList) DataAccessor.read(itemFilename);

        if(items != null){
            this.menu = new Menu(items);

            System.out.println("Menu contents successfully loaded!");
            updateTypesList(items);
        }
        else{
            this.menu = new Menu();

            System.out.println("Menu is empty");
        }
    }

    /**
     * 
     */
    public void saveItems(){
        int itemSave = DataAccessor.write(itemFilename, this.menu.getItemList());

        if(itemSave != 1){
            System.out.println("Menu contents successfully saved!");
        }
        else{
            System.out.println("Failed to save items!");
        }
    }

    /**
     * Compile the existing types of items in the menu.
     * @return types of items in menu
     */
    /**
     * @param items
     */
    private void updateTypesList(ArrayList<MenuItem> items){
        this.types.clear();

        for(int i=0;i<items.size();i++){
            if(this.types.contains(items.get(i).getType())){
                continue;
            }
            else{
                this.types.add(items.get(i).getType());
            }
        }

        System.out.println("Types list successfully updated!\n");
    }

    /**
     * Gets menu contents.
     * @return contents of the menu.
     */
    /**
     * @return
     */
    public Menu getMenu() {
    	return menu;
    }

    /**
     * Add an individual item into the menu.
     */
    /**
     * @param item
     */
    public void addItem(MenuItem item){
        this.menu.addItem(item);
        System.out.println("The following item has been added to the menu:");
        System.out.println("  Name: " + item.getName());
        System.out.println("  Description: " + item.getDescription());
        System.out.println("  Price: $" +  String.valueOf(item.getPrice()));
        System.out.println("  Type: " + item.getType());

        updateTypesList(this.menu.getItemList());
    }

    /**
     * Remove an item (individual/promotion) from the menu.
     */
    /**
     * @param name
     */
    public void removeItem(String name){
        if(this.menu.removeItem(name) == 1){
            System.out.println(name + " does not exist in menu!");
            return;
        }

        updateTypesList(this.menu.getItemList());

        System.out.println(name + " has been successfully deleted from menu!");
    }

    /**
     * 
     */
    public void clearMenu(){
        String target;

        if(this.menu.getItemCount() != 0){
            while(this.menu.getItemCount() != 0){
                    target = this.menu.getItemList().get(0).getName();
                    this.menu.removeItem(target);
            }

            updateTypesList(this.menu.getItemList());

            System.out.println("Menu is cleared.");
        }
        else{
            System.out.println("Menu is empty!");
        }
    }
    /**
     * Update the information for an item.
     */
    /**
     * @param name
     * @param description
     * @param price
     */
    public void updateItem(String name, String description, double price){
        MenuItem item;
        Promotion promotion;

        if((item = this.menu.getItem(name)) != null){
            if(description.equals("-1")==false)item.setDescription(description);
            if(price>=0)item.setPrice(price);
            System.out.println("Item susccessfully updated!");
        }
        else{
            System.out.println(name + " does not exist in menu!");
        }
    }

    /**
     * Print contents of menu, sorted by item type.
     */
    /**
     * 
     */
    public void printItemsByCategory(){
        if(this.menu.getItemCount() != 0){
            for(int i=0;i<this.types.size();i++){
                System.out.println("");
                System.out.println("-- " + this.types.get(i) + " --");
                for(int j=0;j<this.menu.getItemCount();j++){
                    MenuItem item = this.menu.getItemList().get(j);

                    if(item.getType().equals(this.types.get(i))){
                        System.out.println("");
                        System.out.println(item.getName() + "  $" + String.valueOf(item.getPrice()));
                        System.out.println("Desc: " + item.getDescription());
                    }
                }
            }
        }
        else{
            System.out.println("No item to print.");
        }
    }

    /**
     * Print contents of menu, sorted by name.
     */
    /**
     * 
     */
    public void printItemsByName(){
        ArrayList<String> names = new ArrayList<String>();
        MenuItem item;

        for(int i=0;i<this.menu.getItemCount();i++){
            names.add(this.menu.getItemList().get(i).getName());
        }

        if(this.menu.getItemCount() == 0){
            System.out.println("No item to print.");
        }

        if(names.size() == 0){return;}

        Collections.sort(names);

        for(int k=0;k<names.size();k++){
            if(this.menu.getItem(names.get(k)) == null){
                System.out.println(names.get(k) + " does not exist in menu!");
            }
            else{
                item = this.menu.getItem(names.get(k));
                System.out.println("");
                System.out.println(names.get(k) + " $" + String.valueOf(item.getPrice()) + " (" + item.getType() + ")");
                System.out.println("Desc: " + item.getDescription());
            }
        }
    }
}
