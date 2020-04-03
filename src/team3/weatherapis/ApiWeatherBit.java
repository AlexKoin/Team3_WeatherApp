package team3.weatherapis;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import team3.dbmanagement.DatabaseManager;

public class ApiWeatherBit extends WeatherApi {
	
	public static void main(String[] args)
	{
		WeatherApi api = new ApiWeatherBit();
		
		System.out.println(api.getWeather(DatabaseManager.getApiLocation("London", api.getApiId()).toString()));
	}
	
    public ApiWeatherBit()
    {
    	super("weatherbit.io");
    }

    @Override
    public Weather getWeather(String location) {
        Weather weather = null;
        
        String apiKey = "54e6200b9c554b03b0c77a61c48bb576";

        String apiUrl = "https://api.weatherbit.io/v2.0/current?city=" + location + "&key=" + apiKey;

        try {
        	String response = WeatherApi.contactApi(apiUrl);
        	
        	// TODO : remove debug
        	//System.out.println(response);
        	
        	// Assign values from API response to instance of Weather
            if (response != null) {
                weather = parseWeather(response);
            }
        } catch (IOException e)
        {
        	e.printStackTrace();
        } catch (Exception e)
        {
        	e.printStackTrace();
        }
        
        return weather;
    }
    
	@SuppressWarnings("unchecked")
	@Override
	protected Weather parseWeather(String json) {
		String location="", weather="", temperature = "", humidity = "", precipitation="", windSpeed = "", windDirection = "", timestamp = "";
		
		if (json != null) {
			// TODO : remove debug
			//System.out.println(json);
            
			Map<String, Object> map = WeatherApi.jsonToMap(json);
            ArrayList<Map<String, Object>> dataMaps = (ArrayList<Map<String, Object>>) map.get("data");
            Map<String, Object> dataMap = dataMaps.get(0);
            Map<String, Object> weatherMap = (Map<String, Object>) dataMap.get("weather");
            
            location = "lat:" + String.format("%.3f", Float.parseFloat(dataMap.get("lat").toString())) + ", lon:" + String.format("%.3f", Float.parseFloat(dataMap.get("lon").toString()));
			weather = weatherMap.get("description").toString().toLowerCase();
	        temperature = dataMap.get("temp").toString();
	        humidity = dataMap.get("rh").toString();
	        precipitation = dataMap.get("precip").toString();
	        windSpeed = String.format("%.1f", Float.parseFloat(dataMap.get("wind_spd").toString()));
	        float windDirectionInDegrees = Float.parseFloat(dataMap.get("wind_dir").toString());
	        windDirection = CardinalDirection.fromDegree(windDirectionInDegrees).toString().toLowerCase();
	        
	        timestamp = "to be implemented";
		}
		
		return new Weather(this.getSourceName(), location, weather, temperature, humidity, precipitation, windSpeed, windDirection, timestamp);
	}
}
