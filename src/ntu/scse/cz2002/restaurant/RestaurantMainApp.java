package ntu.scse.cz2002.restaurant;

import java.io.*;
import java.text.*;
import java.util.InputMismatchException;
import java.util.Scanner;

import ntu.scse.cz2002.restaurant.data.*;
import ntu.scse.cz2002.restaurant.model.Restaurant;
//import ntu.scse.cz2002.restaurant.model.Staff;
import ntu.scse.cz2002.restaurant.view.*;
/**
 * @author Zeqing
 *@version 1.0
 *@since 2019-4-17
 */

/**
 *  Run Restaurant through this MainApp
 */
public class RestaurantMainApp {

	/**
	 *main app
	 *<br> calls BoundaryClass MainRestaurantView to start the menu 
	 */
	public static void main(String[] args) {
		(new MainRestaurantView()).show();
		
	}
}
