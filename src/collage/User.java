package collage;

import java.util.ArrayList;
import java.util.List;

public class User {
	public static class UserClass {
		private static List<Picture> previousCollages = new ArrayList<Picture>();
		public static Picture currentCollage = null;
		public static int numPreviousSearches = -1;
		public static String username;
		
			
		public static List<Picture> getCollages(){
			return previousCollages;
		}

		/*
		 * SETTERS
		 */
		public static void setCurrentCollage(Picture currCollage) {
			currentCollage = currCollage;
		}
		
		public static void addPreviousCollage(){
			previousCollages.add(currentCollage);
		}
		
		public static void addCollage(Picture p) {
			previousCollages.add(p);
		}
		
		public static int getNumPreviousSearches() {
			return numPreviousSearches;
		}
		
		/*
		 * RESET CLASS FOR EACH NEW USER
		 */
		public static void resetUserClass() {
			for(int i=0; i<previousCollages.size(); i++) {
				previousCollages.remove(0);
			}
			currentCollage = null;
			numPreviousSearches = -1;
		}
	}
	
}
