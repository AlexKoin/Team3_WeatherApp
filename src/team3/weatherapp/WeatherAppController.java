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

	private WeatherAppController() {
	}

	private static ArrayList<WeatherApi> getWeatherApiList() {
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

	@SuppressWarnings("unused")
	public static String formatWeatherResults(HttpServletRequest request, HttpServletResponse response) {
		StringBuilder htmlStringBuilder = new StringBuilder();

		String locationParameter = request.getParameter("location");
		String displayParameter = request.getParameter("display");

		if ((locationParameter != null) && (!locationParameter.isBlank())) {
			ArrayList<WeatherApi> weatherApis = getWeatherApiList();
			ArrayList<Weather> weatherResults = new ArrayList<Weather>();

			/* Use location parameter here to retrieve weather data: */
			for (WeatherApi api : weatherApis) {
				weatherResults.add(api.getWeather(DatabaseManager.getApiLocation(locationParameter, api.getApiId())));
			}

			// TODO : delete debug
			// System.out.println("Got weather results: " + weatherResults.size());

			/* Use display parameter to format data for display: */
			if ((displayParameter != null) && (!displayParameter.isBlank())) {

				switch (displayParameter) {
				// Rendering of table display
				case "table": {
					htmlStringBuilder.append("<h1>Weather forecast for " + locationParameter + "</h1>");

					int columnCount = 3; // non-index column count per row
					int lastColumn = columnCount - 1; // index of last column in row
					int totalColumnCount = (int) (Math.ceil((double) weatherResults.size() / (double) columnCount)
							* columnCount);

					// TODO : delete debug
					// System.out.println(weatherResults.size() + "/" + columnCount + "/" +
					// totalColumnCount);

					int currentColumn = 0;
					String source = "", location = "", weather = "", temperature = "", humidity = "",
							precipitation = "", windSpeed = "", windDirection = "", timestamp = "";

					for (int i = 0; i < totalColumnCount; i++) {
						/* open row */
						if (0 == currentColumn) {
							htmlStringBuilder.append("<div class=\"row\">");
						}

						/* open column */
						htmlStringBuilder.append("<div class=\"col\">");

						if (i < weatherResults.size()) {
							/* Build weather result card */
							htmlStringBuilder.append(buildWeatherCard(weatherResults.get(i)));
						}

						/* close column */
						htmlStringBuilder.append("</div>");

						/* close row */
						if (lastColumn == currentColumn) {
							htmlStringBuilder.append("</div>");
						}

						currentColumn++;
						currentColumn %= lastColumn + 1;
					}

					response.setStatus(HttpServletResponse.SC_OK);

					break;
				}

				case "average": {
					String location = locationParameter;
					String weatherDescription = "";
					String temperature = "";
					String humidity = "";
					String precipitation = "";
					String windSpeed = "";
					String windDirection = "";

					int apiCount = 0;
					float temperatureTotal = 0.0f;
					float humidityTotal = 0.0f;
					float precipitationTotal = 0.0f;
					float windSpeedTotal = 0.0f;

					for (Weather weather : weatherResults) {
						temperatureTotal += Float.parseFloat(weather.getTemperature());
						humidityTotal += Float.parseFloat(weather.getHumidity());
						precipitationTotal += Float.parseFloat(weather.getPrecipitation());
						windSpeedTotal += Float.parseFloat(weather.getWindSpeed());
						apiCount++;
					}

					if (apiCount > 0) {
						temperature = String.format("%.1f", temperatureTotal / apiCount);
						humidity = String.format("%.1f", humidityTotal / apiCount);
						precipitation = String.format("%.1f", precipitationTotal / apiCount);
						windSpeed = String.format("%.1f", windSpeedTotal / apiCount);
					}

//						/* open row */
//						htmlStringBuilder.append("<div class=\"row\">");

//						/* open column */
//						htmlStringBuilder.append("<div class=\"col\">");
//						/* close column */
//						htmlStringBuilder.append("</div>");

					/* open column */
					htmlStringBuilder.append("<div class=\"col\">");
					/* Build weather result card */
					htmlStringBuilder.append(buildWeatherCard(new Weather("average weather", location,
							weatherDescription, temperature, humidity, precipitation, windSpeed, windDirection, "")));
					/* close column */
					htmlStringBuilder.append("</div>");

//						/* open column */
//						htmlStringBuilder.append("<div class=\"col\">");
//						/* close column */
//						htmlStringBuilder.append("</div>");

//						/* close row */
//						htmlStringBuilder.append("</div>");

					response.setStatus(HttpServletResponse.SC_OK);
					break;
				}

				case "minmax": {
					String location = locationParameter;
					String weatherDescription = "";

					/* 1000.0 is a crutch to get the values to update */
					String temperatureMin = "1000.0";
					String humidityMin = "1000.0";
					String precipitationMin = "1000.0";
					String windSpeedMin = "1000.0";

					String windDirection = "";

					String temperatureMax = "0.0";
					String humidityMax = "0.0";
					String precipitationMax = "0.0";
					String windSpeedMax = "0.0";

					for (Weather weather : weatherResults) {
						System.out.println(weather.getSourceName());
						
						temperatureMin = (Float.parseFloat(weather.getTemperature()) < Float.parseFloat(temperatureMin))
								? weather.getTemperature()
								: temperatureMin;
						temperatureMax = (Float.parseFloat(weather.getTemperature()) > Float.parseFloat(temperatureMax))
								? weather.getTemperature()
								: temperatureMax;
								
						humidityMin = (Float.parseFloat(weather.getHumidity()) < Float.parseFloat(humidityMin))
								? weather.getHumidity()
								: humidityMin;
						humidityMax = (Float.parseFloat(weather.getHumidity()) > Float.parseFloat(humidityMax))
								? weather.getHumidity()
								: humidityMax;
								
						precipitationMin = (Float.parseFloat(weather.getPrecipitation()) < Float.parseFloat(precipitationMin))
								? weather.getPrecipitation()
								: precipitationMin;
						precipitationMax = (Float.parseFloat(weather.getPrecipitation()) > Float.parseFloat(precipitationMax))
								? weather.getPrecipitation()
								: precipitationMax;
								
						windSpeedMin = (Float.parseFloat(weather.getWindSpeed()) < Float.parseFloat(windSpeedMin))
								? weather.getWindSpeed()
								: windSpeedMin;
						windSpeedMax = (Float.parseFloat(weather.getWindSpeed()) > Float.parseFloat(windSpeedMax))
								? weather.getWindSpeed()
								: windSpeedMax;
					}
					
					Weather weatherMin = new Weather("minimum weather", location,
							weatherDescription, temperatureMin, humidityMin, precipitationMin, windSpeedMin, windDirection, "");
					Weather weatherMax = new Weather("maximum weather", location,
							weatherDescription, temperatureMax, humidityMax, precipitationMax, windSpeedMax, windDirection, "");

					/* open row */
					htmlStringBuilder.append("<div class=\"row\">");

					/* open column */
					htmlStringBuilder.append("<div class=\"col\">");
					/* Build weather result card */
					htmlStringBuilder.append(buildWeatherCard(weatherMin));
					/* close column */
					htmlStringBuilder.append("</div>");

					/* open column */
					htmlStringBuilder.append("<div class=\"col\">");
					/* Build weather result card */
					htmlStringBuilder.append(buildWeatherCard(weatherMax));
					/* close column */
					htmlStringBuilder.append("</div>");

					/* close row */
					htmlStringBuilder.append("</div>");

					response.setStatus(HttpServletResponse.SC_OK);
					break;
				}

				default: {
					// Invalid display parameter
					response.setStatus(HttpServletResponse.SC_ACCEPTED);
				}
				}
			}
		} else if ((locationParameter == null) || (locationParameter.isBlank())) {
			if ((displayParameter != null) && (!displayParameter.isBlank())) {
				htmlStringBuilder.append("<span class='badge badge-pill badge-warning p-2'>Please select a city!</span>");
			}
		}

		return htmlStringBuilder.toString();
	}

	private static String buildWeatherCard(Weather weather) {
		StringBuilder stringBuilder = new StringBuilder();

		String source = weather.getSourceName();
		String location = weather.getLocation();
		String weatherDescription = weather.getWeather();
		String temperature = weather.getTemperature();
		String humidity = weather.getHumidity();
		String precipitation = weather.getPrecipitation();
		String windSpeed = weather.getWindSpeed();
		String windDirection = weather.getWindDirection();
		// String timestamp = weather.getTimestamp();

		/* Build weather result card */
		stringBuilder.append("<div class=\"weather-card rounded shadow\">");
		stringBuilder.append("<div class=\"weather-card-header rounded-top bg-accent-light\">");
		stringBuilder.append("<strong>" + source + "</strong>");
		stringBuilder.append("</div>");

		stringBuilder.append("<div class=\"weather-card-content rounded-bottom bg-white\">");
		stringBuilder.append("location: " + location + "<br>");
		stringBuilder.append("weather: " + weatherDescription + "<br>");
		stringBuilder.append("temperature: " + temperature + "°C<br>");
		stringBuilder.append("humidity: " + humidity + "%<br>");
		stringBuilder.append("precipitation: " + precipitation + "mm/h<br>");
		stringBuilder.append("wind speed: " + windSpeed + "m/s<br>");
		stringBuilder.append("wind direction: " + windDirection);
		stringBuilder.append("</div>");
		stringBuilder.append("</div>");

		return stringBuilder.toString();
	}
}
