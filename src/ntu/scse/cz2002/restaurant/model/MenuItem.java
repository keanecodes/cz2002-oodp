package ntu.scse.cz2002.restaurant.model;

public class MenuItem{
    private String name;
    private String description;
    private double price;
    private String type;

    public MenuItem(String name, String description, double price, String type){
        this.name = name;
        this.description = description;
        this.price = price;
        this.type = type;
        
    }

    public String getName(){
        return this.name;
        
    }

    public String getDescription(){
        return this.description;
        
    }

    public double getPrice(){
        return this.price;
        
    }

    public String getType(){
        return this.type;
        
    }

    public void setName(String name){
        this.name = name;
        System.out.println("Name has been changed succesfully!");
        
    }

    public void setDescription(String description){
        this.description = description;
        System.out.println("Description has been changed succesfully!");
        
    }

    public void setPrice(double price){
        this.price = price;
        System.out.println("Price has been changed succesfully!");
        
    }

    public void setType(String type){
        this.type = type;
        System.out.println("Type has been changed succesfully!");
        
    }
}
