package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import collage.Picture;
import collage.User.UserClass;
//import Objects.File;

public class DataContainer {
	private String driverString;
	
	public DataContainer() {
		driverString = "jdbc:mysql://localhost/csci_310_project2?user=root&password=orange212&useSSL=true";
		//populateDataContainer();
	}
		
	public DataContainer(String username, String password, String ipaddress, String ssl) {
		driverString = "jdbc:mysql://localhost/csci_310_project2?user=root&password=orange212&useSSL=true";
		
		if(password.equals("")) { //no password was entered
			driverString = "jdbc:mysql://" + ipaddress + "/csci_310_project2?user="+ username + "&useSSL=" + ssl;
		}
		else { //some password was entered
			driverString = "jdbc:mysql://" + ipaddress + "/csci_310_project2?user="+ username +"&password=" + password + "&useSSL=" + ssl;
		}
		//populateDataContainer();
	}
	
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
				
				//TODO make somewhere to store their collages
				return true;
			}
			
			System.out.println("THIS SHOULD NEVER BE PRINTED (DC.JAVA)");
			return false;
			
		} catch(SQLException sqle) {
			System.out.println("sqle1 in DC.java: " + sqle.getMessage());
			return false;
		}	
	}
	
	public void populateUserClass(String username) {		
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
			
			ps = conn.prepareStatement("SELECT * FROM collages WHERE userID=?");
			ps.setInt(1, userID);
			resultSet = ps.executeQuery();
			
			//maybe clear user class first?
			UserClass.resetUserClass();
			
			while(resultSet.next()) {
				//each loop build another Picture
				int width = resultSet.getInt("width");
				int height = resultSet.getInt("height");
				String filepath = resultSet.getString("filepath");
				
				Picture p = new Picture(width, height, filepath);
				p.setTopic(resultSet.getString("topic"));

				UserClass.numPreviousSearches++;
				UserClass.addCollage(p);
				UserClass.username = username;
			}			
		} catch(SQLException sqle) {
			System.out.println("sqle2 in DC.java: " + sqle.getMessage());
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
}











