package ntu.scse.cz2002.restaurant.util;

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
