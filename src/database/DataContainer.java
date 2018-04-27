package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.Graphics2D;
import collage.Picture;
import collage.User.UserClass;
import java.io.File;
import java.util.Vector;
import java.awt.image.BufferedImage;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

//import Objects.File;

import javax.imageio.ImageIO;

public class DataContainer {
	private String driverString;
	
	public DataContainer() {
		driverString = "jdbc:mysql://localhost/csci_310_project2?user=root&password=orange212&useSSL=true";
		//populateDataContainer();
	}
		
//	public DataContainer(String username, String password, String ipaddress, String ssl) {
//		driverString = "jdbc:mysql://localhost/csci_310_project2?user=root&password=orange212&useSSL=true";
//		
//		if(password.equals("")) { //no password was entered
//			driverString = "jdbc:mysql://" + ipaddress + "/csci_310_project2?user="+ username + "&useSSL=" + ssl;
//		}
//		else { //some password was entered
//			driverString = "jdbc:mysql://" + ipaddress + "/csci_310_project2?user="+ username +"&password=" + password + "&useSSL=" + ssl;
//		}
//		//populateDataContainer();
//	}
	
	public boolean searchForUser(String username, String password) {
		Connection conn = null;
		PreparedStatement ps = null;	
		ResultSet resultSet = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(driverString);
		} catch(ClassNotFoundException cnfe) {
			System.out.println("EXCEPTION - cnfe: " + cnfe.getMessage() + ", trying to connect JDBC ");
			cnfe.printStackTrace();
		} catch(SQLException sqle) {
			System.out.println("EXCEPTION - sqle: " + sqle.getMessage() + ", trying to make conn");
			sqle.printStackTrace();
		}
		
		try {
			ps = conn.prepareStatement("SELECT * FROM users WHERE username=?");
			ps.setString(1, username);
			resultSet = ps.executeQuery();
			
			boolean userFound = false;
			
			while(resultSet.next()) {
				//if here, then there is a user with that username
				userFound = true;
				if(resultSet.getString("password").equals(password))
					return true; //password is correct
				else
					return false; //incorrect password for already registered user
			}
			
			if(!userFound) {

				String insertNewUser = "insert into users (username, password) values (?,?)";
				ps = conn.prepareStatement(insertNewUser);
				ps.setString(1, username);
				ps.setString(2, password);
				ps.execute();
				return true;
			}		
		} catch(SQLException sqle) {
		}	
		return false;
	}
	
	public void populateUserClass(String username) {		
		System.out.println("populating user class");
		
		UserClass.resetUserClass();
		UserClass.username = username;
		//pull all files from collages directory
		Vector<File> userFiles = getUserFiles(username);
		System.out.println("User already has " + userFiles.size() + " files");
		for(int i=0; i<userFiles.size(); i++) {
			try {
				BufferedImage in = ImageIO.read(userFiles.get(i));
				BufferedImage newImage = new BufferedImage(in.getWidth(), in.getHeight(), BufferedImage.TYPE_INT_ARGB);
				Graphics2D g = newImage.createGraphics();
				g.drawImage(in, 0, 0, null);
				g.dispose();
				
				System.out.println("abs path: " + userFiles.get(i).getAbsolutePath());
				String filename = userFiles.get(i).getName();
				int firstDel = filename.indexOf("_");
				String fileNoName = filename.substring(firstDel+1);
				int secondDel = fileNoName.indexOf("_");
				String topic = fileNoName.substring(0, secondDel);
				
				Picture p = new Picture(newImage);
				p.setTopic(topic);
				UserClass.addCollage(p);
				UserClass.numPreviousSearches++;				
			} catch(IOException ioe) {	
				
			}
		}		
		List<Picture> pictures = UserClass.getCollages();
		System.out.println("number of prev pictures: " + pictures.size());
		for(Picture p : pictures) {
			System.out.println(p.getTopic());
		}						
	}
	
	public void addCollageToDB(Picture pic, String username) {
		Connection conn = null;
		PreparedStatement ps = null;	
		ResultSet resultSet = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(driverString);
		} catch(ClassNotFoundException cnfe) {
			System.out.println("EXCEPTION - cnfe: " + cnfe.getMessage() + ", trying to connect JDBC ");
			cnfe.printStackTrace();
		} catch(SQLException sqle) {
			System.out.println("EXCEPTION - sqle: " + sqle.getMessage() + ", trying to make conn");
			sqle.printStackTrace();
		}
		
		
		
		try {
			int userID = -1;
			
			ps = conn.prepareStatement("SELECT * FROM users WHERE username=?");
			ps.setString(1, username);
			resultSet = ps.executeQuery();
			while(resultSet.next())
				userID = resultSet.getInt("userID");		
			
			String insertNewUser = "insert into collages (width, height, filepath, userID, topic) values (?,?,?,?,?)";
			ps = conn.prepareStatement(insertNewUser);
			ps.setInt(1, pic.getWidth());
			ps.setInt(2, pic.getHeight());
			ps.setString(3, "FILEPATH");
			ps.setInt(4, userID);
			ps.setString(5, pic.getTopic());
			ps.execute();
			
		} catch(SQLException sqle) {
			System.out.println("sqle3 in DC.java: " + sqle.getMessage());
		}
	}


	public static Vector<File> getUserFiles(String username) {
		Vector<File> userFiles = new Vector<File>();
		
		File dir = new File("/Users/markashworth/git/CSCI310_Project2/collages/");
		System.out.println(dir.getAbsolutePath());
		File[] directoryListing = dir.listFiles();
		if (directoryListing != null) {
		  for (File child : directoryListing) {
			  System.out.println(child.getName());
			  String file = child.getName();
			  int firstDel = file.indexOf("_");

			  if(firstDel == -1 || file.equals(".DS_Store")) continue;
			  
			  String user = file.substring(0, firstDel);
			  System.out.println("|" + username + "|");
			  System.out.println("|" + user + "|");
			  if(user.equals(username)) {
				  	System.out.println("adding a file for this user");
				  	userFiles.add(child);	
			  }
		  }
		} 
			
		return userFiles;
	}

}











