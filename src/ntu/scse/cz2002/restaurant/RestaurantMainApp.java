package ntu.scse.cz2002.restaurant;

import java.io.*;
import java.text.*;
import java.util.InputMismatchException;
import java.util.Scanner;

import ntu.scse.cz2002.restaurant.data.*;
import ntu.scse.cz2002.restaurant.model.Restaurant;
//import ntu.scse.cz2002.restaurant.model.Staff;
import ntu.scse.cz2002.restaurant.view.*;

public class RestaurantMainApp {

	public static void main(String[] args) {
		(new MainRestaurantView()).show();
		
	}
}
