package ntu.scse.cz2002.restaurant.dataAccess;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

public abstract class DataAccess {
	
	private static final String FILE_PATH_GENERAL = "src/ntu/scse/cz2002/restaurant/dataAccess/dataFiles/";
	private abstract static String filename;
	
	private abstract String DataFormatter();
	private abstract DataDecoder();
	
	public static loadData() {
		
	};
	
	public static writeData();
	
}
