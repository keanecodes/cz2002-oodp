package ntu.scse.cz2002.restaurant;

import java.io.*;
import java.text.*;
import java.util.ArrayList;

import ntu.scse.cz2002.restaurant.dataAccess.RestaurantDA;
import ntu.scse.cz2002.restaurant.model.Restaurant;
import ntu.scse.cz2002.restaurant.view.RestaurantView;

public class RestaurantMainApp {
	
	public static void main(String[] args) throws ParseException, IOException {
	
		//TODO Load data from previous session or initialise data
		Restaurant r = RestaurantDA.loadData();
		
		RestaurantView.showMainOptions(r.getCurrentStaff());
	
		//TODO Save state before closing
		//RestaurantData.saveToFile();
	}
}