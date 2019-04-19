package ntu.scse.cz2002.restaurant.util;

/**
 * Header for our Restaurant
 * @author keane
 *@version 1.0
 *@since 2019-4-17
 */
public class Utilities {
	
	public static void clearScreen() {
		for (int i = 0; i < 50; ++i) System.out.println();
	}
	
	public static void newScreenHeader() {  
		clearScreen();
		System.out.println("**************************************************\n" + 
                           "************** Happy Kopitiam RRPPS **************\n" +
                           "**************************************************\n\n");
	}  
}
