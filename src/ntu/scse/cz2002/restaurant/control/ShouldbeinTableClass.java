//not done class

package ntu.scse.cz2002.restaurant.control;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.InputMismatchException;

import ntu.scse.cz2002.restaurant.data.DataAccessor;
import ntu.scse.cz2002.restaurant.model.Reservation;

public class ShouldbeinTableClass {

	/**
		 * Gets an available table for walk-in customers (No reservations made)
		 * 
		 * @param customerID The customerID of the customer
		 * @param numOfPeople The number of people that the table has to accomodate
		 * 
		 * @return The available table, if any and null if there are no
		 * 		   tables available at the moment
		 */
		public Table getAvailableTable(int customerID, int numOfPeople)
		{
			checkReservations();
			
			for(Table table : _tables)
			{
				if(!table.isOccupied())
				{
					if(!table.isReserved())
					{
						if(table.getNumOfSeats() >= numOfPeople)
						{
							table.assignTable(customerID);
							return table;
						}
					}
				}
			}
			
			System.out.println("\nNo available tables!");
			return null;
		}
}
