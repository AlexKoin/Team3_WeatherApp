package team3.weatherapis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class ApiClimaCell extends WeatherApi {

	public static void main(String[] args)
	{
		WeatherApi api = new ApiClimaCell();
		
		System.out.println(api.getWeather("lat=56.946&lon=24.105").toString());
	}
	
    public ApiClimaCell()
    {
    	super("climacell.co");
    }

    @Override
    public Weather getWeather(String location) {
        Weather weather = null;
        
        String apiKey = "dCj790S0DL1qEx8DmUdV9JDyY0aT0E4x";
        String apiUrl = "https://api.climacell.co/v3/weather/realtime?" + location + "&fields=temp,humidity,precipitation,weather_code,wind_speed,wind_direction";

		StringBuilder stringBuilder = new StringBuilder();

		try {
			URL url = new URL(apiUrl);
			HttpURLConnection connection = (HttpURLConnection)url.openConnection();

			connection.setRequestProperty("apikey", apiKey);
			connection.setRequestProperty("Content-Type", "application/JSON");
			connection.setDoInput(true);
			connection.setDoOutput(true);

			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			String line;
			while ((line = reader.readLine()) != null) {
				stringBuilder.append(line);
			}

			reader.close();
			
			String response = stringBuilder.toString();
	        	
        	// Assign values from API response to instance of Weather
            if (response != null) {
                weather = parseWeather(response);
            }			
		} catch (IOException e) {
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
            
			location = "lat:" + String.format("%.3f", Float.parseFloat(map.get("lat").toString())) + ", lon:" + String.format("%.3f", Float.parseFloat(map.get("lon").toString()));
			weather = ((Map<String, Object>)map.get("weather_code")).get("value").toString();
	        temperature = ((Map<String, Object>)map.get("temp")).get("value").toString();
	        humidity = ((Map<String, Object>)map.get("humidity")).get("value").toString();
	        precipitation = ((Map<String, Object>)map.get("precipitation")).get("value").toString();
	        windSpeed = ((Map<String, Object>)map.get("wind_speed")).get("value").toString();
	        float windDirectionInDegrees = Float.parseFloat(((Map<String, Object>)map.get("wind_direction")).get("value").toString());
	        windDirection = CardinalDirection.fromDegree(windDirectionInDegrees).toString().toLowerCase();
	        
	        timestamp = "to be implemented";
		}
		
		return new Weather(this.getSourceName(), location, weather, temperature, humidity, precipitation, windSpeed, windDirection, timestamp);
	}
}
