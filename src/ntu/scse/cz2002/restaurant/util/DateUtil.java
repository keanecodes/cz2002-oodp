package ntu.scse.cz2002.restaurant.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * @author Nguyen Kim Xuyen
 *@version 1.0
 *@since 2019-04-17
 * Date Formatter
 */

public class DateUtil {

	private static SimpleDateFormat dateFormatter;

	/**
	 * Date to String
	 * @param formattingDate the date that you wanna format
	 * @param format the format to format in
	 * @return formatted String
	 */
	public static String format(Date formattingDate, String format) {
		switch (format) {
			case "datetime":
				dateFormatter = new SimpleDateFormat("E, dd/MM/yyyy, HH:mm");
				return dateFormatter.format(formattingDate);
			case "date":
				dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
				return dateFormatter.format(formattingDate);
			// Add on if there need be another date/time format
			default:
				return "Date error";
		}

	}

	/**
	 * String to Date
	 * @param DateString the formatted string
	 * @param format String to format with
	 * @return Date the date
	 * @throws ParseException it ignores parse
	 */
	public static Date format(String DateString, String format) throws ParseException {
		switch (format) {
		case "datetime":
			dateFormatter = new SimpleDateFormat("E, MMM dd yyyy HH:mm:ss");
			return dateFormatter.parse(DateString);
		default:
			return null;
		}
	}
	
	public static String now() {
		return format(new Date(), "date");
	}
}
