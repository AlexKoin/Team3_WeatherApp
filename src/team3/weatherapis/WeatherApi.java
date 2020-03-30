package team3.weatherapis;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public abstract class WeatherApi {
	private String sourceName = null;
		
	protected WeatherApi(String sourceName)
	{
		this.sourceName = sourceName;
	}
	
	public final String getSourceName()
	{
		return this.sourceName;
	}
	
	public abstract Weather getWeather(String location);
	protected abstract Weather parseWeather(String json);
	
	/* Use this for the database lookup: */
	public final String getApiId()
	{
		return this.getClass().getSimpleName();
	}
	
	
	
	
	
	public static Map<String, Object> jsonToMap(String str) {
		Map<String, Object> map = new Gson().fromJson(str, new TypeToken<HashMap<String, Object>>() {
		}.getType());

		return map;
	}

	public static float kelvinToCelsius(float kelvin) {
		return kelvin - 273.15f;
	}

	public static float kelvinToCelsius(String kelvin) {
		return Float.parseFloat(kelvin) - 273.15f;
	}

	public static String contactApi(String apiUrl) throws IOException {
		String apiResponse = null;

		URL connectionUrl = new URL(apiUrl);
		HttpURLConnection connection = (HttpURLConnection)connectionUrl.openConnection();
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		StringBuilder stringBuilder = new StringBuilder();

		String line;
		while ((line = reader.readLine()) != null) {
			stringBuilder.append(line);
		}

		reader.close();

		apiResponse = stringBuilder.toString();

		return apiResponse;
	}
	
//	public static String cityToCoord(String city)
//	{
//		// World Cities by andruxnet
//		String apiKey = "bd476e1f1emsh795b16203d643acp110a1ejsn3cca316d5214";
//		
//		String urlString = "https://andruxnet-world-cities-v1.p.rapidapi.com/?query=" + city + "&searchby=city";
//
//		StringBuilder stringBuilder = new StringBuilder();
//		
//		try {			
//			URL url = new URL(urlString);
//			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
//
//			conn.setRequestMethod("GET");
//			conn.setRequestProperty("RapidAPI Project", "default-application_4288913");
//			conn.setRequestProperty("x-rapidapi-host", "andruxnet-world-cities-v1.p.rapidapi.com");
//			conn.setRequestProperty("x-rapidapi-key", apiKey);
//			conn.setDoInput(true);
//			conn.setDoOutput(true);
//
//			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//
//			String line;
//			while ((line = reader.readLine()) != null) {
//				stringBuilder.append(line);
//			}
//
//			reader.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//		
//		return stringBuilder.toString();
//	}
}
