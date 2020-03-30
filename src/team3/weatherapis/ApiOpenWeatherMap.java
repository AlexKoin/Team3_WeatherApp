package team3.weatherapis;

import java.io.IOException;
import java.util.Map;

public class ApiOpenWeatherMap extends WeatherApi {
	
    public ApiOpenWeatherMap()
    {
    	super("openweathermap.org");
    }

    @Override
    public Weather getWeather(String location) {
        Weather weather = null;
        
        String apiKey = "542d30a2a9b0fee31f38da18ad05ba41";
        String apiUrl = "https://api.openweathermap.org/data/2.5/weather?q=" + location + "&appid=" + apiKey;

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
			Map<String, Object> mainMap = (Map<String, Object>) WeatherApi.jsonToMap(json).get("main");
			Map<String, Object> windMap = (Map<String, Object>) WeatherApi.jsonToMap(json).get("wind");
            
	        temperature = mainMap.get("temp").toString();
	        humidity = mainMap.get("humidity").toString();
	        windSpeed = windMap.get("speed").toString();
	        float windDirectionInDegrees = Float.parseFloat(windMap.get("deg").toString());
	        windDirection = CardinalDirection.fromDegree(windDirectionInDegrees).toString();
	        timestamp = "to be implemented";
		}
		
		return new Weather(this.getSourceName(), location, temperature, humidity, windSpeed, windDirection, timestamp);
	}
}
