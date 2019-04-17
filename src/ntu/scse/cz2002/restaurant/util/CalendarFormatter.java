package ntu.scse.cz2002.restaurant.util;

import java.util.Calendar;

public class CalendarFormatter { // might not be needed, can use the built in functions in calendar
	// enum{"Full"; "Date"; "Time"};
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

	public static String toString(Calendar c) { // this should be in somesort of CalenderHelperclass
		return toString(c, 1);
	}
}
