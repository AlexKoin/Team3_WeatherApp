package team3.dbmanagement;

import java.sql.*;
import java.util.ArrayList;


public class DatabaseManager {
	/* In theory, this is a big vulnerability, any security information must not be stored in the repo. */
	/* In real-world scenario, the values should be moved to a separate config file and added to .gitignore. */
	private static final String user = "b4fd5f310df062";
	private static final String password = "4139d084";
	private static final String url = "jdbc:mysql://b4fd5f310df062:4139d084@us-cdbr-iron-east-01.cleardb.net/heroku_03582f067142cb5?reconnect=true";

//	public Weather apiAris = null;
//	public Weather apiCc = null;
//	public Weather apiDs = null;
//	public Weather apiOwm = null;
//	public Weather apiWapi = null;
//	public Weather apiWb = null;
//	public Weather apiWs = null;
	
	private static Connection connection = null;
	private static Statement sqlStatement = null;

	private DatabaseManager() {}
	
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

	
	
//	public void setCityToApis(String city)
//	{
//		ResultSet sqlResultSet = null;
//
//		try {
//			sqlStatement = connection.createStatement();
//		} catch (Exception e) {
//
//		}
//
//		try {
//			sqlResultSet = sqlStatement.executeQuery("SELECT * FROM cities WHERE CityName = '" + city + "'");
//			
//			while (sqlResultSet.next()) {
//				this.apiAris = new ApiAeris().getWeather(sqlResultSet.getString("WeatherAPIARIS")) ;
//				this.apiCc = new ApiClimaCell().getWeather(sqlResultSet.getString("WeatherAPICC"));
//				this.apiDs = new ApiDarkSky().getWeather(sqlResultSet.getString("WeatherAPIDarkSky"));
//				this.apiOwm = new ApiOpenWeatherMap().getWeather(sqlResultSet.getString("WeatherAPIOWM"));
//				this.apiWapi = new ApiWeatherApi().getWeather(sqlResultSet.getString("WeatherAPIWAPI"));
//				this.apiWb = new ApiWeatherBit().getWeather(sqlResultSet.getString("WeatherAPIWB"));
//				this.apiWs = new ApiWeatherStack().getWeather(sqlResultSet.getString("WeatherAPIWS"));
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
	
	
	public static ArrayList<String> getCityNameList()
	{
		ArrayList<String> cityNameList = new ArrayList<String>();
		
		lazyInit();
		
		try {
			ResultSet sqlResultSet = sqlStatement.executeQuery("SELECT * FROM cities ORDER BY CityName");
			
			while (sqlResultSet.next()) {
				cityNameList.add(sqlResultSet.getString("CityName"));
			}
			
			// TODO: debug
			System.out.println(cityNameList.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cityNameList;
	}
	
	private static void lazyInit()
	{
		// Lazy class initialisation:
		if (connection == null)
		{
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				connection = DriverManager.getConnection(url, user, password);
				
				if (sqlStatement == null)
				{
					sqlStatement = connection.createStatement();
				}
				
				// TODO: debug
				System.out.println("Initialised connection to database.");
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}

//	public Weather getApiAris() {
//		return this.apiAris;
//	}
//
//	public Weather getApiCc() {
//		return this.apiCc;
//	}
//
//	public Weather getApiDs() {
//		return this.apiDs;
//	}
//
//	public Weather getApiWapi() {
//		return this.apiWapi;
//	}
//
//	public Weather getApiOwm() {
//		return this.apiOwm;
//	}
//
//	public Weather getApiWb() {
//		return this.apiWb;
//	}
//
//	public Weather getApiWs() {
//		return this.apiWs;
//	}
}


