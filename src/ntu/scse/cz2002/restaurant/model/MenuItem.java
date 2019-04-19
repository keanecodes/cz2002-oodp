package ntu.scse.cz2002.restaurant.model;

import java.io.Serializable;

public class MenuItem implements Serializable{
	/**
	 * 
	 */
	protected String name;
	/**
	 * 
	 */
	protected String description;
	/**
	 * 
	 */
	protected double price;
	/**
	 * 
	 */
	private String type;

	/**
	 * @param name
	 * @param description
	 * @param price
	 * @param type
	 */
	public MenuItem(String name, String description, double price, String type) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.type = type;

	}

	/**
	 * @return
	 */
	public String getName() {
		return this.name;

	}

	/**
	 * @return
	 */
	public String getDescription() {
		return this.description;

	}

	/**
	 * @return
	 */
	public double getPrice() {
		return this.price;

	}

	/**
	 * @return
	 */
	public String getType() {
		return this.type;

	}

	/**
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
		System.out.println("Name has been changed succesfully!");

	}

	/**
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
		System.out.println("Description has been changed succesfully!");

	}

	/**
	 * @param price
	 */
	public void setPrice(double price) {
		this.price = price;
		System.out.println("Price has been changed succesfully!");

	}

	/**
	 * @param type
	 */
	public void setType(String type) {
		this.type = type;
		System.out.println("Type has been changed succesfully!");

	}
}
