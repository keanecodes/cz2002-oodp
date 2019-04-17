package ntu.scse.cz2002.restaurant.model;

import java.util.*;

public class Promotion {
	private double price;
	private String promotionName;
	private String promotionDescription;
	ArrayList<Object> PromotionItems = new ArrayList<Object>();

	public Promotion(String promotionName, String promotionDescription, double price) {
		this.promotionName = promotionName;
		this.promotionDescription = promotionDescription;
		this.price = price;
	}

	public void addItems(Object o) {
		PromotionItems.add(o);
		System.out.println(o + " was added to promotion");
	}

	public void removeItem(Object o) {
		PromotionItems.remove(o);
		System.out.println(o + " was removed from promotion");
	}

	public void printPromotion() {
		System.out.println("The price of promotion is " + price);
		System.out.println("The items are: ");
		for (int i = 0; i < PromotionItems.size(); i++)
			System.out.println(PromotionItems.get(i));
	}

	public String getPromotionName() {
		return promotionName;
	}

	public String getPromotionDescription() {
		return promotionDescription;
	}

	public double getPrice() {
		return price;
	}

	public void setPromotionName(String promotionName) {
		this.promotionName = promotionName;
		System.out.println("Promotion name has been changed succesfully!");
	}

	public void setPromotionDescription(String promotionDescription) {
		this.promotionDescription = promotionDescription;
		System.out.println("Promotion description has been changed succesfully!");
	}

	public void setPrice(double price) {
		this.price = price;
		System.out.println("Promotion price has been changed succesfully!");
	}
}