package team3.weatherapis;

import java.io.IOException;
import java.util.Map;

public class ApiWeatherStack extends WeatherApi {
    
    public ApiWeatherStack()
    {
    	super("weatherstack.com");
    }

    @Override
    public Weather getWeather(String location) {
        Weather weather = null;
        
        String apiKey = "321c152f35c73d014a4b237d574a84e7";
        String apiUrl = "http://api.weatherstack.com/current?access_key=" + apiKey + "&query=" + location;

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
            
	        temperature = currentMap.get("temperature").toString();
	        humidity = currentMap.get("humidity").toString();
	        windSpeed = currentMap.get("wind_speed").toString();
	        float windDirectionInDegrees = Float.parseFloat(currentMap.get("wind_degree").toString());
	        windDirection = CardinalDirection.fromDegree(windDirectionInDegrees).toString();
	        timestamp = "to be implemented";
		}
		
		return new Weather(this.getSourceName(), location, temperature, humidity, windSpeed, windDirection, timestamp);
	}
}
