package ntu.scse.cz2002.restaurant.model;

import java.util.ArrayList;

/**
   Represents the restaurant's menu.
   It contains all the food items sold by the restaurant.
   @author  Johan Tjuatja
   @version 1.0
   @since   2019-03-15
 */
public class Menu {
	/**
	 * An array to store all the menu items.
	 */
	private ArrayList<MenuItem> items = new ArrayList<MenuItem>();

	/**
	 * The number of items existing in the menu.
	 */
	private int itemCount;

	/**
	 * Creates a new empty menu.
	 */
	public Menu() {
		this.items = new ArrayList<MenuItem>();
		this.itemCount = 0;
	}

	/**
   * Creates a new menu with items.
	 * @param items The list of items to be stored in the menu.
	 */
	public Menu(ArrayList<MenuItem> items) {
		this.items = items;
		this.itemCount = items.size();
	}

	/**
   * Adds an item into the menu.
	 * @param item The item to be added into the menu.
	 */
	public void addItem(MenuItem item) {
		this.items.add(item);
		this.itemCount += 1;
	}

	/**
   * Gets an item object from the menu.
	 * @param name The name of the item to be retrieved.
	 * @return the item object or null, if item does not exist
	 */
	public MenuItem getItem(String name) {
		for (int i = 0; i < this.items.size(); i++) {
			if (this.items.get(i).getName().equalsIgnoreCase(name)) {
				return this.items.get(i);
			}
		}

		return null;
	}

	/**
   * Gets the total number of items in the menu currently.
	 * @return the number of items in menu.
	 */
	public int getItemCount() {
		return this.itemCount;
	}

	/**
   * Gets the list of items in the menu.
	 * @return this menu's item list.
	 */
	public ArrayList<MenuItem> getItemList() {
		return this.items;
	}

	/**
   * Removes an item from the menu.
	 * @param name The name of item to be removed.
	 * @return 0 if successful, 1 if unsuccessful.
	 */
	public int removeItem(String name) {
		for (int i = 0; i < this.items.size(); i++) {
			if (this.items.get(i).getName().equalsIgnoreCase(name)) {
				this.items.remove(i);
				this.itemCount -= 1;
				return 0;
			}
		}

		System.out.println("Item not found!");
		return 1;
	}
}

