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
	
	/* Each child class must implement these methods: */
	public abstract Weather getWeather(String location);
	protected abstract Weather parseWeather(String json);
	
	/* Use this for database lookup: */
	public final String getApiId()
	{
		return this.getClass().getSimpleName();
	}
	
	/* Parses JSON string into an object */
	public static Map<String, Object> jsonToMap(String str) {
		Map<String, Object> map = new Gson().fromJson(str, new TypeToken<HashMap<String, Object>>() {
		}.getType());

		return map;
	}
	
	public static boolean isKelvinToCelsiusValid(float kelvin) {
		boolean result = false;
		try {
			if (kelvin>= -273 && kelvin <= 100) {
				result = true;
			}
		} catch (Exception e) {
			result = false;
		}
		return result;
	}
	
	public static boolean isKelvinToCelsiusValid(String kelvin) {
		boolean result = false;
		try {
			if (Float.parseFloat(kelvin) >= -273 && Float.parseFloat(kelvin) <= 100) {
				result = true;
			}
		} catch (Exception e) {
			result = false;
		}
		return result;
	}
	
	public static boolean iskphToMpsValid(String kph) {
		boolean result = false;
		try {
			if (Float.parseFloat(kph) >= 0 && Float.parseFloat(kph) <= 500) {
				result = true;
			}
		} catch (Exception e) {
			result = false;
		}
		return result;
	}
	
	
	
	/* Utility method */
	public static float kelvinToCelsius(float kelvin) {
		
		if (isKelvinToCelsiusValid(kelvin) == true) {
			return kelvin - 273.15f;
		} else {
			return kelvin;
		}
		
	}

	/* Utility method */
	public static float kelvinToCelsius(String kelvin) {
		if (isKelvinToCelsiusValid(kelvin) == true) {
			return Float.parseFloat(kelvin) - 273.15f;
		} else {
			return Float.parseFloat(kelvin);
		}
	}

	/* Utility method */
	public static String kphToMps(String kph) {
		if (iskphToMpsValid(kph) == true) {
			return String.format("%.1f", Float.parseFloat(kph)/3.6f);
		} else {
			return "Invalid data";
		}
	}
	
	/* Sends a request to API site, gets a response string */
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
}
