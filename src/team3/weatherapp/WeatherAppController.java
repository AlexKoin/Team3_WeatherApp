package team3.weatherapp;

import java.io.Serializable;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team3.dbmanagement.DatabaseManager;
import team3.weatherapis.Weather;
import team3.weatherapis.WeatherApi;
import team3.weatherapis.ApiAeris;
import team3.weatherapis.ApiClimaCell;
import team3.weatherapis.ApiDarkSky;
import team3.weatherapis.ApiOpenWeatherMap;
import team3.weatherapis.ApiWeatherApi;
import team3.weatherapis.ApiWeatherBit;
import team3.weatherapis.ApiWeatherStack;


public class WeatherAppController implements Serializable {

	private static final long serialVersionUID = -8408135492483896421L;
	
	public WeatherAppController() {

	}
	
	private static ArrayList<WeatherApi> getWeatherApiList()
	{
		ArrayList<WeatherApi> weatherApis = new ArrayList<WeatherApi>();
		
		// openweathermap.org
		weatherApis.add(new ApiAeris());
		// climacell.co
		weatherApis.add(new ApiClimaCell());
		// darksky.net
		weatherApis.add(new ApiDarkSky());
		// openweathermap.org
		weatherApis.add(new ApiOpenWeatherMap());
		// weatherbit.io
		weatherApis.add(new ApiWeatherBit());
		// weatherapi.com
		weatherApis.add(new ApiWeatherApi());
		// weatherstack.com
		weatherApis.add(new ApiWeatherStack());
		
		return weatherApis;
	}

//	public static String formatInputGroup(HttpServletRequest request)
//	{
//		StringBuilder htmlStringBuilder= new StringBuilder();
//		
//		String location = request.getParameter("location");
//		//String display = request.getParameter("display");
//		
//		// if ((location != null) && (!location.isBlank())) {
//		// }
//		
//		return htmlStringBuilder.toString();
//	}
	
	public static String formatWeatherResults(HttpServletRequest request, HttpServletResponse response) {
		StringBuilder htmlStringBuilder = new StringBuilder();
		
		String locationParameter = request.getParameter("location");
		String displayParameter = request.getParameter("display");
		
		if ((locationParameter != null) && (!locationParameter.isBlank())) {
			
			//ArrayList<String> cityNameList = DatabaseManager.getCityNameList();
			
			ArrayList<WeatherApi> weatherApis = getWeatherApiList();
			ArrayList<Weather> weatherResults = new ArrayList<Weather>();

			/* Use location parameter here to retrieve weather data: */
			for (WeatherApi api : weatherApis) {
				weatherResults.add(api.getWeather(DatabaseManager.getApiLocation(locationParameter, api.getApiId())));
			}
			
			System.out.println("Got weather results: " + weatherResults.size());
			
			/* Use display parameter to format data for display: */
			if ((displayParameter != null) && (!displayParameter.isBlank()))
			{
				htmlStringBuilder.append("<div class='container py-3'>");
				
				switch (displayParameter)
				{
					case "table" :
					{
						htmlStringBuilder.append("<h1>Weather forecast for " + locationParameter +"</h1>");
						htmlStringBuilder.append("<div class='container'>");
						
//						htmlStringBuilder.append("<div class='row'>");
//						htmlStringBuilder.append("<div class='col-sm'>");
//						htmlStringBuilder.append(city.getApiAris().toString());
//						htmlStringBuilder.append("</div>");
//						htmlStringBuilder.append("<div class='col-sm'>");
//						htmlStringBuilder.append(city.getApiCc().toString());
//						htmlStringBuilder.append("</div>");
//						htmlStringBuilder.append("</div>");
//						htmlStringBuilder.append("<div class='row'>");
//						htmlStringBuilder.append("<div class='col-sm'>");
//						htmlStringBuilder.append(city.getApiDs().toString());
//						htmlStringBuilder.append("</div>");
//						htmlStringBuilder.append("<div class='col-sm'>");
//						htmlStringBuilder.append(city.getApiOwm().toString());
//						htmlStringBuilder.append("</div>");
//						htmlStringBuilder.append("</div>");
//						htmlStringBuilder.append("<div class='row'>");
//						htmlStringBuilder.append("<div class='col-sm'>");
//						htmlStringBuilder.append(city.getApiWapi().toString());
//						htmlStringBuilder.append("</div>");
//						htmlStringBuilder.append("<div class='col-sm'>");
//						htmlStringBuilder.append(city.getApiWb().toString());
//						htmlStringBuilder.append("</div>");
//						htmlStringBuilder.append("</div>");
//						htmlStringBuilder.append("<div class='row'>");
//						htmlStringBuilder.append("<div class='col-sm'>");
//						htmlStringBuilder.append(city.getApiWs().toString());
//						htmlStringBuilder.append("</div>");
//						htmlStringBuilder.append("</div>");
//						htmlStringBuilder.append("</div>");		
						
						for (Weather weather : weatherResults) {
							htmlStringBuilder.append("<li class='list-group-item'>");
							htmlStringBuilder.append(weather.toString());
							htmlStringBuilder.append("</li>");
						}
						
						htmlStringBuilder.append("</ul>");
						htmlStringBuilder.append("</div>");	
						
						response.setStatus(HttpServletResponse.SC_OK);
						
						break;
					}
				
					case "average" :
					{
						int apiCount = 0;
						float temperatureTotal = 0.0f;
						for (Weather weather : weatherResults) {
							temperatureTotal += Float.parseFloat(weather.getTemperature());
							apiCount ++;
						}
						
						float averageTemperature = 0.0f;
						
						if (apiCount > 0)
						{
							averageTemperature = temperatureTotal / apiCount;
						}

						htmlStringBuilder.append("<p> Average Temperature for "+ locationParameter +" is " + averageTemperature + " °C</p>");
						// TODO: need to implement
						// htmlBuilder.append("<div class='badge badge-warning'>Not implemented</div>");
						// response.setStatus(HttpServletResponse.SC_NOT_IMPLEMENTED);
						
						response.setStatus(HttpServletResponse.SC_OK);
						break;
					}
					
					default :
					{
						// Invalid display parameter
						response.setStatus(HttpServletResponse.SC_ACCEPTED);
					}
				}
				
				htmlStringBuilder.append("</div>");
			}	
		}
		else if((locationParameter == null))
		{
			if((displayParameter != null) && (!displayParameter.isBlank())) 
			{
				htmlStringBuilder.append("<div class='container py-3'>");
				htmlStringBuilder.append("<div class='badge badge-warning'>Please choose city!</div>");
				htmlStringBuilder.append("</div>");
			}
		}

		return htmlStringBuilder.toString();
	}
}
