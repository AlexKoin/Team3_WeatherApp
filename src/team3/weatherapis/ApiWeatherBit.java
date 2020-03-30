package team3.weatherapis;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class ApiWeatherBit extends WeatherApi {
	
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
            Map<String, Object> map = WeatherApi.jsonToMap(json);
            ArrayList<Map<String, Object>> data = (ArrayList<Map<String, Object>>) map.get("data");
            Map<String, Object> details = data.get(0);
            
	        temperature = details.get("temp").toString();
	        humidity = details.get("rh").toString();
	        windSpeed = details.get("wind_spd").toString();
	        float windDirectionInDegrees = Float.parseFloat(details.get("wind_dir").toString());
	        windDirection = CardinalDirection.fromDegree(windDirectionInDegrees).toString();
	        timestamp = "to be implemented";
		}
		
		return new Weather(this.getSourceName(), location, temperature, humidity, windSpeed, windDirection, timestamp);
	}
}
