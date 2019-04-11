package ntu.scse.cz2002.restaurant.model;

import java.util.ArrayList;
import java.util.Arrays;

public class Menu{
    private ArrayList<MenuItem> menuItems = new ArrayList<MenuItem>();
    private ArrayList<Promotion> promotions = new ArrayList<Promotion>();
    private int itemCount, promotionCount;

    public Menu(ArrayList<MenuItem> menuItems, ArrayList<Promotion> promotions){
        this.menuItems = menuItems;
        this.promotions = promotions;
        this.itemCount = 0;
        this.promotionCount = 0;
    }

    public void addMenuItem(MenuItem item){
        this.menuItems.add(item);
        this.itemCount += 1;
    }

    public void addPromotion(Promotion promotion){
        this.promotions.add(promotion);
        this.promotionCount += 1;
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
            if(this.promotions.get(i).getPromotionName().equals(name)){
                return this.promotions.get(i);
            }
        }

        return null;
    }

    public int getItemCount(){
        return this.itemCount;
    }

    public int getPromotionCount(){
        return this.promotionCount;
    }

    public ArrayList<MenuItem> getItemList(){
        return this.menuItems;
    }

    public ArrayList<Promotion> getPromotionList(){
        return this.promotions;
    }

    public int removeMenuItem(String name){
        for(int i=0;i<this.menuItems.size();i++){
            if(this.menuItems.get(i).getName().equals(name)){
                this.menuItems.remove(i);
                this.itemCount -= 1;
                return 0;
            }
        }

        System.out.println("Item not found!");
        return 1;
    }

    public int removePromotion(String name){
        for(int i=0;i<this.promotions.size();i++){
            if(this.promotions.get(i).getPromotionName().equals(name)){
                this.promotions.remove(i);
                this.promotionCount =- 1;
                return 0;
            }
        }

        System.out.println("Promotion not found!");
        return 1;
    }


}
