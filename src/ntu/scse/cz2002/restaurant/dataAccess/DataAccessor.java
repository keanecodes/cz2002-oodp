package ntu.scse.cz2002.restaurant.dataAccess;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DataAccessor {
	
	public static final Path DATAPATH = Paths.get(System.getProperty("user.dir"), 
                                        "src/ntu/scse/cz2002/restaurant/dataAccess");

	
	public static int write(String filename, Object data) {
		
		Path fileDataPath = Paths.get(DATAPATH.toString(), filename);

		try {
			FileOutputStream f = new FileOutputStream(fileDataPath.toString());
			ObjectOutputStream o = new ObjectOutputStream(f);

			o.writeObject(data);

			o.close();
			f.close();
			
			return 0;
		} catch (IOException e) {
			System.out.println("Failed to save data: IOException");
			return 1;
	    }
	}

	public static Object read(String filename) {
		Path fileDataPath = Paths.get(DATAPATH.toString(), filename);
		
		Object data = null;

		try {
			FileInputStream f = new FileInputStream(fileDataPath.toString());
			ObjectInputStream o = new ObjectInputStream(f);

			data = o.readObject();

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
