package ntu.scse.cz2002.restaurant.model;

import java.util.ArrayList;
import java.util.Arrays;

public class Menu{
    private ArrayList<MenuItem> menuItems = new ArrayList<MenuItem>();
    private ArrayList<Set> sets = new ArrayList<Set>();

    public Menu(MenuItem[] menuItems, Set[] sets){
        this.menuItems = new ArrayList<MenuItem>(Arrays.asList(menuItems));
        this.sets = new ArrayList<Set>(Arrays.asList(sets));
        
    }

    public void printMenu(){
        System.out.println("Menu item:");

        for(int i=0;i<this.menuItems.size();i++){
            System.out.println(i + ". " + this.menuItems.get(i).getName());
            
        }

        System.out.println("Set item:");

        for(int j=0;j<this.sets.size();j++){
            System.out.println(j +". " + this.sets.get(j).getName());
            
        }
    }

    public void addMenuItem(MenuItem item){
        this.menuItems.add(item);
        System.out.println("The following menu item has been added to the menu:");
        System.out.println("  Name: " + item.getName());
        System.out.println("  Description: " + item.getDescription());
        System.out.println("  Price: $" + item.getPrice());
        System.out.println("  Type: " + item.getType());
        
    }

    public void addSet(Set set){
        this.sets.add(set);
        System.out.println("The following set has been addded to the menu:");
        System.out.println("  Name: " + set.getName());
        System.out.println("  Description: " + set.getDescription());
        System.out.println("  Price: $" + set.getPrice());
        System.out.println("  Type: " + set.getType());
        
    }

    public ArrayList<MenuItem> getMenuItem(String name) {
        /*for(int i=0;i<this.menuItems.size();i++){
            if(this.menuItems.get(i).getName().equals(name)){
                return this.menuItems.get(i);
                
            }
        }*/
    	return this.menuItems;
    }

    public ArrayList<Set> getSet(String name){
        /*for(int i=0;i<this.sets.size();i++){
            if(this.sets.get(i).getName().equals(name)){
                return this.sets.get(i);
                
            }
        }*/
    	return this.sets;
    }

    public void removeMenuItem(String name){
        for(int i=0;i<this.menuItems.size();i++){
            if(this.menuItems.get(i).getName().equals(name)){
                this.menuItems.remove(i);
                System.out.println(name + " has been successfully deleted from menu!");
                
                return;
                
            }
        }

        System.out.println("Item not found!");
        
    }

    public void removeSet(String name){
        for(int i=0;i<this.sets.size();i++){
            if(this.sets.get(i).getName().equals(name)){
                this.sets.remove(i);
                System.out.println(name + " has been successfully deleted from menu!");
                return;
                
            }
        }

        System.out.println("Set not found!");
        
    }


}
