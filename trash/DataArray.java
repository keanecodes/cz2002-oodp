package ntu.scse.cz2002.restaurant.dataAccess;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import ntu.scse.cz2002.restaurant.model.Invoice;
import ntu.scse.cz2002.restaurant.model.Staff;

public class DataArray {
	// public DataArray(){};

	/*
	 * public ArrayList<DataAccessible> read(String filename, DataAccessible obj)
	 * throws IOException { ArrayList<String> stringArray = (ArrayList)
	 * DataAccessHelper.read(filename); Class<? extends DataAccessible> type =
	 * obj.getClass(); ArrayList<DataAccessible> objArray= new
	 * ArrayList<DataAccessible>(); for (String entry: stringArray) {
	 * objArray(i).DataDecoder(entry)); }
	 * 
	 * return objArray; }
	 */ // can't figure this shit out

	public ArrayList<String> read(String filename) throws IOException {
		ArrayList<String> stringArray = (ArrayList) DataAccessHelper.read(filename);
		return stringArray;
	}

	public static void save(String filename, ArrayList<DataAccessible> listofitem) throws IOException {
		ArrayList<String> alw = new ArrayList<String>();

		for (DataAccessible item : listofitem) {
			alw.add(item.DataFormatter());
		}
		System.out.println(alw);

		DataAccessHelper.write(filename, alw);
	}

}
