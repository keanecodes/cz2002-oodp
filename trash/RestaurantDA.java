package ntu.scse.cz2002.restaurant.dataAccess;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

import ntu.scse.cz2002.restaurant.model.Restaurant;
import ntu.scse.cz2002.restaurant.model.Staff;

public class RestaurantDA {
	
	private static final String FILE_PATH = "src/ntu/scse/cz2002/restaurant/dataAccess/";
	
	public static Restaurant loadData() throws ParseException {
		Restaurant r = new Restaurant();
		r.setCurrentStaff(initStaff());
		
		return r;
	}
	
	public static Staff initStaff() throws ParseException {
		StaffDA txtDB = new StaffDA();
		String filename = FILE_PATH + "staff.txt";
		
		try { return StaffDA.getLastUsedStaff(filename); } 
		catch (IOException e) { System.out.println("IOException > " + e.getMessage()); } 
		
		return null;
	}
}
