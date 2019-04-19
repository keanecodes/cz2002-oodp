package ntu.scse.cz2002.restaurant.data;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Wrapper for writing Arrays<Objects> to Txt files
 * @author Gee Cheng Mun
 * @version 1.0
 * @since 2019-04-17
 *
 */
public class DataAccessHelper {
	/** Write fixed content to the given file. */
	public static void write(String fileName, List data) throws IOException {
		PrintWriter out = new PrintWriter(new FileWriter(fileName));

		try {
			for (int i = 0; i < data.size(); i++)
				out.println((String) data.get(i));
		} finally {
			out.close();
		}
	}

	/** Read the contents of the given file. */
	public static List read(String fileName) throws IOException {

		List data = new ArrayList();
		Scanner scanner = new Scanner(new FileInputStream(fileName));

		try {
			while (scanner.hasNextLine()) {
				data.add(scanner.nextLine());
			}
		} finally {
			scanner.close();
		}

		return data;
	}
}
