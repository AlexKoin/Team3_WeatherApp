package team3.weatherapis;

import java.io.IOException;
import java.util.Map;

public class ApiDarkSky extends WeatherApi {

	public static void main(String[] args)
	{
		WeatherApi api = new ApiDarkSky();
		
		System.out.println(api.getWeather("56.946,24.105").toString());
	}
	
	public ApiDarkSky()
    {
    	super("darksky.net");
    }

    @Override
    public Weather getWeather(String location) {
        Weather weather = null;
        
        String apiKey = "a6c84f43a34c0ad01c730dba9c5e1f27";
        String apiUrl = "https://api.darksky.net/forecast/" + apiKey + "/" + location + "?units=si";

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
		String location="", weather="", temperature = "", humidity = "", precipitation="", windSpeed = "", windDirection = "", timestamp = "";
		
		if (json != null) {
			// TODO : remove debug
			//System.out.println(json);
			
			Map<String, Object> map = WeatherApi.jsonToMap(json);
            Map<String, Object> currentlyMap = (Map<String, Object>) map.get("currently");
            
			location = "lat:" + String.format("%.3f", Float.parseFloat(map.get("latitude").toString())) + ", lon:" + String.format("%.3f", Float.parseFloat(map.get("longitude").toString()));
			weather = currentlyMap.get("summary").toString().toLowerCase();
	        temperature = String.format("%.1f", Float.parseFloat(currentlyMap.get("temperature").toString()));
	        humidity = String.format("%.1f", 100.0f * Float.parseFloat(currentlyMap.get("humidity").toString()));
	        precipitation = String.format("%.1f", Float.parseFloat(currentlyMap.get("precipIntensity").toString()));
	        windSpeed = String.format("%.1f", Float.parseFloat(currentlyMap.get("windSpeed").toString()));
	        float windDirectionInDegrees = Float.parseFloat(currentlyMap.get("windBearing").toString());
	        windDirection = CardinalDirection.fromDegree(windDirectionInDegrees).toString().toLowerCase();
	        
	        timestamp = "to be implemented";
		}
		
		return new Weather(this.getSourceName(), location, weather, temperature, humidity, precipitation, windSpeed, windDirection, timestamp);
	}
}
