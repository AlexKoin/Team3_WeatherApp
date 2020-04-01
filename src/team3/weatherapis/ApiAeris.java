package team3.weatherapis;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class ApiAeris extends WeatherApi {

	public static void main(String[] args)
	{
		WeatherApi api = new ApiAeris();
		
		System.out.println(api.getWeather("Riga,lv").toString());
	}
	
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
		String location="", weather="", temperature = "", humidity = "", precipitation="", windSpeed = "", windDirection = "", timestamp = "";
		
		if (json != null) {
			// TODO : remove debug
			//System.out.println(json);
			
            Map<String, Object> map = WeatherApi.jsonToMap(json);
            
            if (map.get("success").toString().equals("true")) {
	            ArrayList<Map<String, Object>> responsesMap = (ArrayList<Map<String, Object>>) map.get("response");
	            Map<String, Object> responseMap = responsesMap.get(0);
	            ArrayList<Map<String, Object>> periodsMap = (ArrayList<Map<String, Object>>) responseMap.get("periods");
	            Map<String, Object> periodMap = periodsMap.get(0);
	            Map<String, Object> locMap = (Map<String, Object>)responseMap.get("loc");
	            
	            location = "lat:" + locMap.get("lat").toString() + ", lon:" + locMap.get("long").toString();
	            weather=periodMap.get("weather").toString().toLowerCase();
		        temperature = periodMap.get("avgTempC").toString();
		        humidity = periodMap.get("humidity").toString();
		        precipitation = periodMap.get("precipMM").toString();
		        windSpeed = WeatherApi.kphToMps(periodMap.get("windSpeedKPH").toString());
		        float windDirectionInDegrees = Float.parseFloat(periodMap.get("windDirDEG").toString());
		        windDirection = CardinalDirection.fromDegree(windDirectionInDegrees).toString().toLowerCase();
		        
		        timestamp = "to be implemented";
            }
		}
		
		return new Weather(this.getSourceName(), location, weather, temperature, humidity, precipitation, windSpeed, windDirection, timestamp);
	}
}
