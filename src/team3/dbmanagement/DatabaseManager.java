package team3.dbmanagement;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseManager {
	/* In theory, this is a big vulnerability, any security information must not be stored in the repo. */
	/* In real-world scenario, the values should be moved to a separate config file and added to .gitignore. */
	
//	private static final String user = "b9456f267bd65f";
//	private static final String password = "369d252b";
//	private static final String url = "jdbc:mysql://eu-cdbr-west-02.cleardb.net:3306/heroku_e7b48380c052931?reconnect=true";
	
	private static final String user = "uygxfraznldxvp";
	private static final String password = "dae795b0e34014421e5f2ecdaddd1fa4d3cc370871b399fc8e3e1a84da28487a";
	private static final String url = "jdbc:postgresql://ec2-79-125-26-232.eu-west-1.compute.amazonaws.com:5432/debfhgr9kile4r?ssl=true&sslmode=require";
	
	private static Connection connection = null;
	private static Statement sqlStatement = null;

	/* Hide constructor to prevent class instantiation */
	private DatabaseManager() {}
	
	/* Returns a location formatted to each of the known API location input requirements. */
	public static String getApiLocation(String cityName, String apiId)
	{
		String apiLocation = null;
		
		lazyInit();
		
		try {
			String sql = "SELECT * FROM cities WHERE CityName='" + cityName + "'";
			ResultSet sqlResultSet = sqlStatement.executeQuery(sql);
			
			if (sqlResultSet.next()) {
				apiLocation = sqlResultSet.getString(apiId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return apiLocation;
	}
	
	/* Returns a list of all city names stored in the database. */
	public static ArrayList<String> getCityNameList()
	{
		ArrayList<String> cityNameList = new ArrayList<String>();
		
		lazyInit();
		
		try {
			ResultSet sqlResultSet = sqlStatement.executeQuery("SELECT * FROM cities ORDER BY CityName");
			
			while (sqlResultSet.next()) {
				cityNameList.add(sqlResultSet.getString("CityName"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cityNameList;
	}
	
	private static void lazyInit()
	{
		// Lazy class initialisation:
		try {
			if ((connection == null) || (connection.isClosed())) {

//				Class.forName("com.mysql.cj.jdbc.Driver");
				Class.forName("org.postgresql.Driver");
				connection = DriverManager.getConnection(url, user, password);
				
				// TODO: remove debug
				System.out.println("Initialised connection to database.");

				if ((sqlStatement == null) || (sqlStatement.isClosed())) {
					sqlStatement = connection.createStatement();
					
					// TODO: remove debug
					System.out.println("Initialised database statement.");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
