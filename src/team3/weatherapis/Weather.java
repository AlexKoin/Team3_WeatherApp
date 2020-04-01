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

	public Weather(String sourceName, String location, String weather, String temperature, String humidity, String precipitation,
			String windSpeed, String windDirection, String timeStamp) {
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
	
	public String getSourceName() {
		return sourceName;
	}
	
	public String getLocation() {
		return location;
	}
	
	public String getWeather() {
		return weather;
	}

	public String getTemperature() {
		return temperature;
	}

	public String getHumidity() {
		return humidity;
	}
	
	public String getPrecipitation() {
		return precipitation;
	}

	public String getWindSpeed() {
		return windSpeed;
	}

	public String getWindDirection() {
		return windDirection;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public String toString() {
		return "source.........." + this.sourceName +
				"\nlocation........" + this.location +
				"\nweather........." + this.weather +
				"\ntemperature....." + this.temperature + suffixCelsius +
				"\nhumidity........" + this.humidity + "%" +
				"\nprecipitation..." + this.precipitation + "mm/h" +
				"\nwind speed......" + this.windSpeed + "m/s" +
				"\nwind direction.." + this.windDirection +
				"\ntime stamp......" + this.timestamp;
	}
}
