package team3.weatherapis;

import java.io.IOException;
import java.util.Map;

public class ApiDarkSky extends WeatherApi {

	public ApiDarkSky()
    {
    	super("darksky.net");
    }

    @Override
    public Weather getWeather(String location) {
        Weather weather = null;
        
        String apiKey = "a6c84f43a34c0ad01c730dba9c5e1f27";
        String apiUrl = "https://api.darksky.net/forecast/" + apiKey + "/" + location;

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
            Map<String, Object> currentlyMap = (Map<String, Object>) WeatherApi.jsonToMap(json).get("currently");
            
	        temperature = currentlyMap.get("temperature").toString();
	        humidity = currentlyMap.get("humidity").toString();
	        windSpeed = currentlyMap.get("windSpeed").toString();
	        float windDirectionInDegrees = Float.parseFloat(currentlyMap.get("windBearing").toString());
	        windDirection = CardinalDirection.fromDegree(windDirectionInDegrees).toString();
	        timestamp = "to be implemented";
		}
		
		return new Weather(this.getSourceName(), location, temperature, humidity, windSpeed, windDirection, timestamp);
	}
}
