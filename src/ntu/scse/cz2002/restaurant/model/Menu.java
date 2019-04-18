package ntu.scse.cz2002.restaurant.model;

import java.util.ArrayList;

public class Menu {
	private ArrayList<MenuItem> items = new ArrayList<MenuItem>();
	private int itemCount, promotionCount;

	public Menu() {
		this.items = new ArrayList<MenuItem>();
		this.itemCount = 0;
		this.promotionCount = 0;
	}

	public Menu(ArrayList<MenuItem> items) {
		this.items = items;
		this.itemCount = items.size();
	}

	public void addItem(MenuItem item) {
		this.items.add(item);
		this.itemCount += 1;
	}

	public MenuItem getItem(String name) {
		for (int i = 0; i < this.items.size(); i++) {
			if (this.items.get(i).getName().equals(name)) {
				return this.items.get(i);
			}
		}

		return null;
	}

	public int getItemCount() {
		return this.itemCount;
	}

	public ArrayList<MenuItem> getItemList() {
		return this.items;
	}

	public int removeItem(String name) {
		for (int i = 0; i < this.items.size(); i++) {
			if (this.items.get(i).getName().equals(name)) {
				this.items.remove(i);
				this.itemCount -= 1;
				return 0;
			}
		}

		System.out.println("Item not found!");
		return 1;
	}
}

