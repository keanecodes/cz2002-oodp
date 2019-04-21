package ntu.scse.cz2002.restaurant.model;

import java.util.*;

/**
 * promotional items in menu
 * @author Goh Ying Ting
 * @version 1.0
 * @since 2019-4-2
 *  
 */
public class Promotion extends MenuItem{
	/**
	 * creates an arraylist of menuitems
	 */
	ArrayList<MenuItem> PromotionItems = new ArrayList<MenuItem>();

	/**
	 * Constructor
	 * @param promotionName name of promotion
	 * @param promotionDescription description of promotion
	 * @param price price of promotion
	 * @param PromotionItems items in promotion
	 */
	public Promotion(String promotionName, String promotionDescription, double price, ArrayList <MenuItem> PromotionItems) {
		super(promotionName, promotionDescription, price, "Promotion");
		this.PromotionItems = PromotionItems;
	}

	/**
	 * add item to the promotion
	 * @param o item to be added
	 */
	public void addItems(MenuItem o) {
		PromotionItems.add(o);
		System.out.println(o + " was added to promotion");
	}

	/**
	 * remove item from promotion
	 * @param o item to be removed
	 */
	public void removeItem(MenuItem o) {
		PromotionItems.remove(o);
		System.out.println(o + " was removed from promotion");
	}

	/**
	 * print items in promotion
	 */
	public void printPromotion() {
		for (int i = 0; i < PromotionItems.size(); i++)
			System.out.println("The menu items are " + PromotionItems.get(i).getName());
	}

	
}