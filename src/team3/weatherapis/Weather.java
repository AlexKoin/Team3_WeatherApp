package team3.weatherapis;

public class Weather {
	private String sourceName;
	private String location;
	private String weather;
	private String temperature;
	private String humidity;
	private String precipitation;
	private String windSpeed;
	private String windDirection;
	private String timestamp; // FIXME: not implemented

	private static final String suffixCelsius = "°C";

	@SuppressWarnings("unused")
	private Weather() {
	}

	public Weather(String sourceName, String location, String weather, String temperature, String humidity,
			String precipitation, String windSpeed, String windDirection, String timeStamp) {
		this.sourceName = sourceName;
		this.location = location;
		this.weather = weather;
		this.temperature = temperature;
		this.humidity = humidity;
		this.windSpeed = windSpeed;
		this.precipitation = precipitation;
		this.windDirection = windDirection;
		this.timestamp = timeStamp;
	}

	public static boolean isValidTemperature(String temperature) {
		boolean result = false;
		try {
			if (Float.parseFloat(temperature) >= -30 && Float.parseFloat(temperature) <= 45) {
				result = true;
			}
		}

		catch (Exception e) {
			System.out.println("Exception: " + e);
		}
		return result;
	}

	public static boolean isValidHumidity(String humidity) {

		boolean result = false;

		try {
			if (Float.parseFloat(humidity) >= 0 && Float.parseFloat(humidity) <= 100) {
				result = true;
			}
		} catch (Exception e) {
			result = false;
		}
		return result;
	}

	public static boolean isValidPrecipitation(String precipitation) {
		boolean result = false;
		try {
			if (Float.parseFloat(precipitation) >= 0 && Float.parseFloat(precipitation) <= 100) {
				result = true;
			}
		} catch (Exception e) {
			result = false;
		}
		return result;
	}

	public static boolean isValidWindSpeed(String windSpeed) {
		boolean result = false;
		try {
			if (Float.parseFloat(windSpeed) >= 0 && Float.parseFloat(windSpeed) <= 40) {
				result = true;
			}
		} catch (Exception e) {
			result = false;
		}
		return result;
	}

	public static boolean isValidSourceName(String sourceName) {

		boolean result = false;

		try {
			if (!sourceName.isEmpty() && sourceName.length() <= 40) {
				result = true;
			}
		} catch (Exception e) {
			result = false;
		}
		return result;
	}

	public static boolean isValidLocation(String location) {

		boolean result = false;

		try {
			if (!location.isEmpty() && location.length() <= 40) {
				result = true;
			}
		} catch (Exception e) {
			result = false;
		}
		return result;
	}

	public static boolean isValidWeather(String weather) {
		boolean result = false;

		try {
			if (!weather.isEmpty() && weather.length() <= 40) {
				result = true;
			}
		} catch (Exception e) {
			result = false;
		}
		return result;
	}

	public static boolean isValidWindDirection(String windDirection) {
		boolean result = false;

		try {
			if (!windDirection.isEmpty() && windDirection.length() <= 40) {
				result = true;
			}
		} catch (Exception e) {
			result = false;
		}
		return result;
	}

	public String getSourceName() {

		if (isValidSourceName(sourceName) == true) {
			return sourceName;
		} else {
			return "invalid data";
		}
	}

	public String getLocation() {

		if (isValidLocation(location) == true) {
			return location;
		} else {
			return "invalid data";
		}
	}

	public String getWeather() {
		return weather;
	}

	public String getTemperature() {

		if (isValidTemperature(temperature) == true) {
			return temperature;
		} else {
			return "invalid data";
		}
	}

	public String getHumidity() {

		if (isValidHumidity(humidity) == true) {
			return humidity;
		} else {
			return "invalid data";
		}
	}

	public String getPrecipitation() {

		if (isValidPrecipitation(precipitation) == true) {
			return precipitation;
		} else {
			return "invalid data";
		}
	}

	public String getWindSpeed() {

		if (isValidWindSpeed(windSpeed) == true) {
			return windSpeed;
		} else {
			return "invalid data";
		}
	}

	public String getWindDirection() {
		if (isValidWindDirection(windDirection) == true) {
			return windDirection;
		} else {
			return "invalid data";
		}
	}

	public String getTimestamp() {
		return timestamp;
	}

	public String toString() {
		return "source.........." + this.sourceName + "\nlocation........" + this.location + "\nweather........."
				+ this.weather + "\ntemperature....." + this.temperature + suffixCelsius + "\nhumidity........"
				+ this.humidity + "%" + "\nprecipitation..." + this.precipitation + "mm/h" + "\nwind speed......"
				+ this.windSpeed + "m/s" + "\nwind direction.." + this.windDirection + "\ntime stamp......"
				+ this.timestamp;
	}
}
