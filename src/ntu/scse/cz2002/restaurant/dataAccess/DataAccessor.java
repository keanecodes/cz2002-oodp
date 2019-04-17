package ntu.scse.cz2002.restaurant.dataAccess;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class DataAccessor {
	public static void write(String filename, ArrayList data) {
		try {
			FileOutputStream f = new FileOutputStream(new File(filename));
			ObjectOutputStream o = new ObjectOutputStream(f);

			o.writeObject(data);

			o.close();
			f.close();
		} catch (IOException e) {
			System.out.println("Failed to save data: IOException");
		}
	}

	public static ArrayList read(String filename) {
		ArrayList data = null;

		try {
			FileInputStream f = new FileInputStream(new File(filename));
			ObjectInputStream o = new ObjectInputStream(f);

			data = (ArrayList) o.readObject();

			o.close();
			f.close();
		} catch (IOException e) {
			System.out.println("Failed to load data: IOException");
		} catch (ClassNotFoundException e) {
			System.out.println("Failed to load data: ClassNotFoundException");
		}

		return data;
	}
}
