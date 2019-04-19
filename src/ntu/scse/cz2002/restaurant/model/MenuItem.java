package ntu.scse.cz2002.restaurant.model;

import java.io.Serializable;

/**
   Represents an item stored in the menu.
   @author  Johan Tjuatja
   @version 1.0
   @since   2019-03-15
 */
public class MenuItem implements Serializable{
	/**
	 * Item name.
	 */
	protected String name;
	/**
	 * Item description.
	 */
	protected String description;
	/**
	 * Item price.
	 */
	protected double price;
	/**
	 * Item type (e.g. Food, Drink, Dessert, etc.).
	 */
	private String type;

	/**
   * Creates a new item with specified attributes.
	 * @param name        This item's name.
	 * @param description This item's description.
	 * @param price       This item's price.
	 * @param type        This item's type.
	 */
	public MenuItem(String name, String description, double price, String type) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.type = type;

	}

	/**
   * Get item's name.
	 * @return This item's name.
	 */
	public String getName() {
		return this.name;

	}

	/**
   * Get item's description.
	 * @return This item's description.
	 */
	public String getDescription() {
		return this.description;

	}

	/**
   * Get item's price.
	 * @return This item's price.
	 */
	public double getPrice() {
		return this.price;

	}

	/**
   * Get item's type.
	 * @return This item's type.
	 */
	public String getType() {
		return this.type;

	}

	/**
   * Change item's name.
	 * @param name This item's name.
	 */
	public void setName(String name) {
		this.name = name;
		System.out.println("Name has been changed succesfully!");

	}

	/**
   * Change item's description.
	 * @param description This item's description.
	 */
	public void setDescription(String description) {
		this.description = description;
		System.out.println("Description has been changed succesfully!");

	}

	/**
   * Change item's price.
	 * @param price This item's price.
	 */
	public void setPrice(double price) {
		this.price = price;
		System.out.println("Price has been changed succesfully!");

	}

	/**
   * Change item's type.
	 * @param type This item's type.
	 */
	public void setType(String type) {
		this.type = type;
		System.out.println("Type has been changed succesfully!");

	}
}
