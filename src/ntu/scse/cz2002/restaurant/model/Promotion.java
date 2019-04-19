package ntu.scse.cz2002.restaurant.model;

import java.util.*;

public class Promotion extends MenuItem{
	/**
	 * 
	 */
	ArrayList<MenuItem> PromotionItems = new ArrayList<MenuItem>();

	/**
	 * @param promotionName
	 * @param promotionDescription
	 * @param price
	 * @param PromotionItems
	 */
	public Promotion(String promotionName, String promotionDescription, double price, ArrayList <MenuItem> PromotionItems) {
		super(promotionName, promotionDescription, price, "Promotion");
		this.PromotionItems = PromotionItems;
	}

	/**
	 * @param o
	 */
	public void addItems(MenuItem o) {
		PromotionItems.add(o);
		System.out.println(o + " was added to promotion");
	}

	/**
	 * @param o
	 */
	public void removeItem(MenuItem o) {
		PromotionItems.remove(o);
		System.out.println(o + " was removed from promotion");
	}

	/**
	 * 
	 */
	public void printPromotion() {
		for (int i = 0; i < PromotionItems.size(); i++)
			System.out.println("The menu items are " + PromotionItems.get(i).getName());
	}

	
}