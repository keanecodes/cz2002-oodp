package ntu.scse.cz2002.restaurant.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;

public class DateUtil {

	private static SimpleDateFormat dateFormatter;

	public static String format(Date formattingDate, String format) {
		switch (format) {
		case "datetime":
			dateFormatter = new SimpleDateFormat("E, dd/MM/yyyy, HH:mm");
			return dateFormatter.format(formattingDate);
		// Add on if there need be another date/time format
		default:
			return "Date error";
		}

	}

	public static Date format(String DateString, String format) throws ParseException {
		switch (format) {
		case "datetime":
			dateFormatter = new SimpleDateFormat("E, MMM dd yyyy HH:mm:ss");
			return dateFormatter.parse(DateString);
		default:
			return null;
		}

	}
}
