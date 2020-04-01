package team3.weatherapis;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class ApiOpenWeatherMap extends WeatherApi {
	
	public static void main(String[] args)
	{
		WeatherApi api = new ApiOpenWeatherMap();
		
		System.out.println(api.getWeather("Riga,lv").toString());
	}
	
    public ApiOpenWeatherMap()
    {
    	super("openweathermap.org");
    }

    @Override
    public Weather getWeather(String location) {
        Weather weather = null;
        
        String apiKey = "542d30a2a9b0fee31f38da18ad05ba41";
        String apiUrl = "https://api.openweathermap.org/data/2.5/weather?q=" + location + "&appid=" + apiKey + "&units=metric";

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
			Map<String, Object> weatherMap = ((ArrayList<Map<String, Object>>)map.get("weather")).get(0);
			Map<String, Object> coordMap = (Map<String, Object>) map.get("coord");
			Map<String, Object> mainMap = (Map<String, Object>) map.get("main");
			Map<String, Object> windMap = (Map<String, Object>) map.get("wind");
            
			location = "lat:" + String.format("%.3f", Float.parseFloat(coordMap.get("lat").toString())) + ", lon:" + String.format("%.3f", Float.parseFloat(coordMap.get("lon").toString()));
			weather = weatherMap.get("main").toString().toLowerCase();
	        temperature = String.format("%.1f", Float.parseFloat(mainMap.get("temp").toString()));
	        humidity = mainMap.get("humidity").toString();
	        // FIXME: precipitation is unavailable
	        precipitation = "0.0";
	        windSpeed = windMap.get("speed").toString();
	        float windDirectionInDegrees = Float.parseFloat(windMap.get("deg").toString());
	        windDirection = CardinalDirection.fromDegree(windDirectionInDegrees).toString().toLowerCase();
	        timestamp = "to be implemented";
		}
		
		return new Weather(this.getSourceName(), location, weather, temperature, humidity, precipitation, windSpeed, windDirection, timestamp);
	}
}
