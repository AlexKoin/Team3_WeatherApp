package team3.weatherapis;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class ApiWeatherStack extends WeatherApi {
    
	public static void main(String[] args)
	{
		WeatherApi api = new ApiWeatherStack();
		
		System.out.println(api.getWeather("Riga,lv").toString());
	}
	
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
		String location="", weather="", temperature = "", humidity = "", precipitation="", windSpeed = "", windDirection = "", timestamp = "";

		if (json != null) {
			// TODO : remove debug
			//System.out.println(json);
			
			Map<String, Object> map = WeatherApi.jsonToMap(json);
			Map<String, Object> locationMap = (Map<String, Object>) map.get("location");
			Map<String, Object> currentMap = (Map<String, Object>) map.get("current");
			ArrayList<String> weatherDescriptions = (ArrayList<String>)currentMap.get("weather_descriptions");
            
			location = "lat:" + locationMap.get("lat").toString() + ", lon:" + locationMap.get("lon").toString();
			for (String weatherDescription : weatherDescriptions)
			{
				weather += weatherDescription.toString().toLowerCase();
			}
	        temperature = currentMap.get("temperature").toString();
	        humidity = currentMap.get("humidity").toString();
	        precipitation = currentMap.get("precip").toString();
	        windSpeed = WeatherApi.kphToMps(currentMap.get("wind_speed").toString());
	        float windDirectionInDegrees = Float.parseFloat(currentMap.get("wind_degree").toString());
	        windDirection = CardinalDirection.fromDegree(windDirectionInDegrees).toString().toLowerCase();
	        
	        timestamp = "to be implemented";
		}
		
		return new Weather(this.getSourceName(), location, weather, temperature, humidity, precipitation, windSpeed, windDirection, timestamp);
	}
}
