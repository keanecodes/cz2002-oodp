package ntu.scse.cz2002.restaurant.util;

import java.util.Calendar;
/**
 * Calendar Formatter
 * @author He Zeqing
 *@version 1.0
 *@since 2019-4-17
 */


public class CalendarFormatter { // might not be needed, can use the built in functions in calendar
	// enum{"Full"; "Date"; "Time"};
	/**
	 *Format Calendar to String
	 *@param c passes a Calendar for formatting, 
	 *@param formattype type of formatting
	 @return formatted String
	 */	
	public static String toString(Calendar c, int formattype) { // this should be in somesort of CalenderHelperclass
		int dd = c.get(Calendar.DAY_OF_MONTH);
		int MM = c.get(Calendar.MONTH) + 1;
		int YYYY = c.get(Calendar.YEAR);
		int HH = c.get(Calendar.HOUR_OF_DAY);
		int mm = c.get(Calendar.MINUTE);
		int sec = c.get(Calendar.SECOND);

		switch (formattype) {
		case 1:
			return "" + YYYY + "." + MM + "." + dd + " " + HH + ":" + mm + ":" + sec;
		case 2:
			return "" + YYYY + "." + MM + "." + dd;
		case 3:
			return " " + HH + ":" + mm + ":" + sec;
		default:
			return "" + YYYY + "." + MM + "." + dd + " " + HH + ":" + mm + ":" + sec;
		}

	}

	/**
	 * override default toString function
	 * @param c the Calendar to format
	 * @return the output string
	 */

	public static String toString(Calendar c) { // this should be in somesort of CalenderHelperclass
		return toString(c, 1);
	}
}
