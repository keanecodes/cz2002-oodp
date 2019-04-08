import ntu.scse.cz2002.restaurant.util.SortingUtil;

public Class MenuManager{
    private Menu menu;
    private ArrayList<String> types;

    public MenuManager(){
        /* TODO: decide on file name */
        String fname = "";

        this.loadMenuContents(fname);
        return MenuManager;

    }

    public void loadItems(String fname){
        // Implements file IO and loads menu contents into menu
        // this.menu = ...
        // update 'types'
    }

    public void addItem(MenuItem item){
        this.menu.addMenuItem(item);
        System.out.println("The following menu item has been added to the menu:");
        System.out.println("  Name: ", item.getName());
        System.out.println("  Description: ", item.getDescription());
        System.out.println("  Price: $", item.getPrice());
        System.out.println("  Type: ", item.getType());
    }

    public void addItem(Promotion promotion){
        this.menu.addPromotion(promotion);
        System.out.println("The following set has been addded to the menu:");
        System.out.println("  Name: ", promotion.getName());
        System.out.println("  Description: ", promotion.getDescription());
        System.out.println("  Price: $", promotion.getPrice());
        System.out.println("  Type: ", promotion.getType());
    }

    public void removeItem(String name){
        if(this.menu.removeMenuItem(name)){
            if(this.menu.removePromotion(name)){
                System.out.println(name + " does not exist in menu!");
                return;
            }
        }

        System.out.println(item.getName() + " has been successfully deleted from menu!");
    }

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
    }

    public void printItemsByCategory(){
        for(int i=0;i<this.types.size();i++){
            System.out.println(this.types.get(i));
            for(int j=0;j<this.menu.menuItems.size();j++){
                MenuItem item = this.menu.menuItems.get(j);

                if(item.getType().equals(this.types.get(i))){
                    System.out.println(item.getName() + "  $" + item.getPrice());
                    System.out.println(item.getDescription());
                }
            }
        }

        System.out.printf("\nPromotion\n");

        for(int k=0;k<this.promotions.size();k++){
            Promotion promotion = this.menu.promotions.get(k);
            System.out.println(promotion.getPromotionName() + " $" + String.valueOf(promotion.getPrice()));
            System.out.println("  " + promotion.getPromotionDescription());
        }
    }

    public void printItemsByName(){
        ArrayList<String> names;
        MenuItem item;
        Promotion promotion;

        for(int i=0;i<this.menuItems.size();i++){
            names.add(this.menuItems.get(i).getName());
        }

        for(int j=0;j<this.promotions.size();j++){
            names.add(this.promotions.get(j).getPromotionName());
        }

        SortingUtil.selectionSort(names);

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
                System.out.println(names.get(k) + " $" + Strig.valueOf(item.getPrice()));
                System.out.println("  " + item.getDescription());
            }
        }
    }
}
