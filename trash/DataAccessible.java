package ntu.scse.cz2002.restaurant.dataAccess;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import ntu.scse.cz2002.restaurant.model.Staff;
import ntu.scse.cz2002.restaurant.util.DateUtil;

public interface DataAccessible {
	
	String FILE_PATH_GENERAL = "src/ntu/scse/cz2002/restaurant/dataAccess/dataFiles/";
	//String filename;

	public static final String SEPARATOR = "|";
	
	public String DataFormatter();
	
	public void DataDecoder(String item);

	/*public static ArrayList<Staff> readAllStaff(String filename) throws IOException, ParseException {
			
	ArrayList stringArray = (ArrayList) DataAccessHelper.read(filename);
	ArrayList<Staff> alr = new ArrayList<Staff>();

	        for (int i = 0 ; i < stringArray.size() ; i++) {
					String st = (String)stringArray.get(i);
					StringTokenizer star = new StringTokenizer(st , SEPARATOR);	
					
					String  name = star.nextToken().trim();	
					char  gender = star.nextToken().trim().charAt(0);	
					int  staffID = Integer.parseInt(star.nextToken().trim()); 
					String  jobTitle = star.nextToken().trim();	
					Date lastUsed = DateUtil.format(star.nextToken().trim(), "datetime");
					
					Staff staff = new Staff(name, gender, staffID, jobTitle, lastUsed);
					
					alr.add(staff) ;
				}
			return alr;
		}
		
		public static Staff getLastUsedStaff(String filename) throws IOException, ParseException {
			
			ArrayList<Staff> staffAl = readAllStaff(filename);
			
			Comparator c = Collections.reverseOrder();
			Collections.sort(staffAl, c);
			
			return (Staff) staffAl.get(0);
		}

		public static void saveStaffs(String filename, List al) throws IOException {
			
			List alw = new ArrayList();

	        for (int i = 0 ; i < al.size() ; i++) {
					Staff staff = (Staff)al.get(i);
					StringBuilder st =  new StringBuilder() ;
					st.append(staff.getName().trim());
					st.append(SEPARATOR);
					st.append(staff.getGender());
					st.append(SEPARATOR);
					st.append(staff.getStaffID());
					st.append(SEPARATOR);
					st.append(staff.getJobTitle().trim());
					alw.add(st.toString()) ;
				}
			DataAccessHelper.write(filename,alw);
		}
	}
*/
	
}




