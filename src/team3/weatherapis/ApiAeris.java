package team3.weatherapis;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class ApiAeris extends WeatherApi {

    public ApiAeris()
    {
    	super("aerisapi.com");
    }

    @Override
    public Weather getWeather(String location) {
        Weather weather = null;
        
        String client_id = "6Fq0AwC3h41DSlMbyOjBG";
        String client_secret = "Ili6IaCULOoz1llsE4iNIcODvG2TGbnYdKMAuaWq";
        String apiUrl = "https://api.aerisapi.com/forecasts/" + location + "?&format=json&filter=day&limit=7&client_id=" + client_id + "&client_secret=" + client_secret;

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
            Map<String, Object> currentMap = WeatherApi.jsonToMap(json);
            ArrayList<Map<String, Object>> temp1 = (ArrayList<Map<String, Object>>) currentMap.get("response");
            Map<String, Object> details = temp1.get(0);
            ArrayList<Map<String, Object>> details2 = (ArrayList<Map<String, Object>>) details.get("periods");
            Map<String, Object> details3 = details2.get(0);
            
	        temperature = details3.get("avgTempC").toString();
	        humidity = details3.get("humidity").toString();
	        windSpeed = details3.get("windSpeedKPH").toString();
	        float windDirectionInDegrees = Float.parseFloat(details3.get("windDir80mDEG").toString());
	        windDirection = CardinalDirection.fromDegree(windDirectionInDegrees).toString();
	        timestamp = "to be implemented";
		}
		
		return new Weather(this.getSourceName(), location, temperature, humidity, windSpeed, windDirection, timestamp);
	}
}
