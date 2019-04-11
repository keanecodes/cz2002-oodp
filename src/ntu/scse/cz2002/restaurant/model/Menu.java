package ntu.scse.cz2002.restaurant.model;

import java.util.ArrayList;
import java.util.Arrays;

public class Menu{
    private ArrayList<MenuItem> menuItems = new ArrayList<MenuItem>();
    private ArrayList<Promotion> promotions = new ArrayList<Promotion>();
    private int contentCount;

    public Menu(ArrayList<MenuItems> menuItems, ArrayList<Promotion> promotions){
        this.menuItems = menuItems;
        this.promotions = menuItems;
        this.contentCount = 0;
    }

    public void addMenuItem(MenuItem item){
        this.menuItems.add(item);
        this.contentCount += 1;
    }

    public void addPromotion(Promotion promotion){
        this.promotions.add(promotion);
        this.contentCount += 1;
    }

    public MenuItem getMenuItem(String name){
        for(int i=0;i<this.menuItems.size();i++){
            if(this.menuItems.get(i).getName().equals(name)){
                return this.menuItems.get(i);
            }
        }

        return null;
    }

    public Promotion getPromotion(String name){
        for(int i=0;i<this.promotions.size();i++){
            if(this.promotions.get(i).getName().equals(name)){
                return this.promotions.get(i);
            }
        }
    }

    public int getContentSize(){
        return this.contentCount;
    }

    public int removeMenuItem(String name){
        for(int i=0;i<this.menuItems.size();i++){
            if(this.menuItems.get(i).getName().equals(name)){
                this.menuItems.remove(i);
                this.contentCount -= 1;
                return 0;
            }
        }

        System.out.println("Item not found!");
        return 1;
    }

    public void removePromotion(String name){
        for(int i=0;i<this.promotions.size();i++){
            if(this.promotions.get(i).getName().equals(name)){
                this.promotions.remove(i);
                this.contentCount =- 1;
                return;
            }
        }

        System.out.println("Promotion not found!");
    }


}
