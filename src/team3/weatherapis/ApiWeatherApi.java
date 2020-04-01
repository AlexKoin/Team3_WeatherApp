package team3.weatherapis;

import java.io.IOException;
import java.util.Map;

public class ApiWeatherApi extends WeatherApi {
	
	public static void main(String[] args)
	{
		WeatherApi api = new ApiWeatherApi();
		
		System.out.println(api.getWeather("Riga,lv").toString());
	}
	
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
		String location="", weather="", temperature = "", humidity = "", precipitation="", windSpeed = "", windDirection = "", timestamp = "";

		if (json != null) {
			// TODO : remove debug
			//System.out.println(json);
            
			Map<String, Object> map = WeatherApi.jsonToMap(json);
			Map<String, Object> locationMap = (Map<String, Object>) map.get("location");
 			Map<String, Object> currentMap = (Map<String, Object>) map.get("current");
 			Map<String, Object> conditionMap = (Map<String, Object>) currentMap.get("condition");
            
 			location = "lat:" + String.format("%.3f", Float.parseFloat(locationMap.get("lat").toString())) + ", lon:" + String.format("%.3f", Float.parseFloat(locationMap.get("lon").toString()));
			weather = conditionMap.get("text").toString().toLowerCase();
	        temperature = currentMap.get("temp_c").toString();
	        humidity = currentMap.get("humidity").toString();
	        precipitation = currentMap.get("precip_mm").toString();
	        windSpeed = currentMap.get("wind_kph").toString();
	        float windDirectionInDegrees = Float.parseFloat(currentMap.get("wind_degree").toString());
	        windDirection = CardinalDirection.fromDegree(windDirectionInDegrees).toString().toLowerCase();
	        timestamp = "to be implemented";
		}
		
		return new Weather(this.getSourceName(), location, weather, temperature, humidity, precipitation, windSpeed, windDirection, timestamp);
	}
}
