package team3.weatherapis;

import java.io.IOException;
import java.util.Map;

public class ApiWeatherApi extends WeatherApi {
	
    public ApiWeatherApi()
    {
    	super("weatherapi.com");
    }

    @Override
    public Weather getWeather(String location) {
        Weather weather = null;
        
        String apiKey = "719433f15a3249d2b79154934202403";
        String apiUrl = "http://api.weatherapi.com/v1/current.json?key=" + apiKey + "&q=" + location;

        try {
        	String response = WeatherApi.contactApi(apiUrl);
        	
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
		String location = "", temperature = "", humidity = "", windSpeed = "", windDirection = "", timestamp = "";

		if (json != null) {
            Map<String, Object> currentMap = (Map<String, Object>) WeatherApi.jsonToMap(json).get("current");
            
	        temperature = currentMap.get("temp_c").toString();
	        humidity = currentMap.get("humidity").toString();
	        windSpeed = currentMap.get("wind_kph").toString();
	        float windDirectionInDegrees = Float.parseFloat(currentMap.get("wind_degree").toString());
	        windDirection = CardinalDirection.fromDegree(windDirectionInDegrees).toString();
	        timestamp = "to be implemented";
		}
		
		return new Weather(this.getSourceName(), location, temperature, humidity, windSpeed, windDirection, timestamp);
	}
}
