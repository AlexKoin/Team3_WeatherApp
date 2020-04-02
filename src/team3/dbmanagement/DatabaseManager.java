package team3.dbmanagement;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseManager {
	/* In theory, this is a big vulnerability, any security information must not be stored in the repo. */
	/* In real-world scenario, the values should be moved to a separate config file and added to .gitignore. */
	private static final String user = System.getenv("JDBC_DATABASE_USERNAME");
	private static final String password = System.getenv("JDBC_DATABASE_PASSWORD");
	private static final String url = System.getenv("JDBC_DATABASE_URL");
	
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

				Class.forName("org.postgresql.Driver");
				connection = DriverManager.getConnection(url, user, password);

				if ((sqlStatement == null) || (sqlStatement.isClosed())) {
					sqlStatement = connection.createStatement();
				}

				// TODO: remove debug
				//System.out.println("Initialised connection to database.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
