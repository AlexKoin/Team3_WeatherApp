package team3.weatherapis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class ApiClimaCell extends WeatherApi {

    public ApiClimaCell()
    {
    	super("climacell.co");
    }

    @Override
    public Weather getWeather(String location) {
        Weather weather = null;
        
        String apiKey = "dCj790S0DL1qEx8DmUdV9JDyY0aT0E4x";
        String apiUrl = "https://api.climacell.co/v3/weather/realtime?" + location + "&fields=temp,humidity,wind_speed,wind_direction";

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
		String location = "", temperature = "", humidity = "", windSpeed = "", windDirection = "", timestamp = "";
		
		if (json != null) {
			Map<String, Object> map = WeatherApi.jsonToMap(json);
            
	        temperature = ((Map<String, Object>)map.get("temp")).get("value").toString();
	        humidity = ((Map<String, Object>)map.get("humidity")).get("value").toString();
	        windSpeed = ((Map<String, Object>)map.get("wind_speed")).get("value").toString();
	        float windDirectionInDegrees = Float.parseFloat(((Map<String, Object>)map.get("wind_direction")).get("value").toString());
	        windDirection = CardinalDirection.fromDegree(windDirectionInDegrees).toString();
	        timestamp = "to be implemented";
		}
		
		return new Weather(this.getSourceName(), location, temperature, humidity, windSpeed, windDirection, timestamp);
	}
}
