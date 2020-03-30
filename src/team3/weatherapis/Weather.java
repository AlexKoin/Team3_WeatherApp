package team3.weatherapis;


public class Weather {
	// TODO: add implementation for source, location and timestamp
	// TODO: fix field types as agreed
	private String sourceName;
	private String location;
	private String temperature;
	private String humidity;
	private String windSpeed;
	//private CardinalDirection windDirection; // TODO: should it be stored as String?
	private String windDirection;
	private String timestamp; // TODO: should it be 'date'?

	private static final String suffixCelsius = "°C";

	@SuppressWarnings("unused")
	private Weather() {
		//this("", "", "", "", "", CardinalDirection.NONE.toString(), "");
	}

	public Weather(String sourceName, String location, String temperature, String humidity, String windSpeed,
			String windDirection, String timeStamp) {
		this.sourceName = sourceName;
		this.location = location;
		this.temperature = temperature;
		this.humidity = humidity;
		this.windSpeed = windSpeed;
		this.windDirection = windDirection;
		this.timestamp = timeStamp;
	}
	
	public String getSourceName() {
		return sourceName;
	}
	
	public String getLocation() {
		return location;
	}

	public String getTemperature() {
		return temperature;
	}

	public String getHumidity() {
		return humidity;
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
		return "source: " + this.sourceName + "<br>" + "location: " + this.location + "<br>" + "temperature: " + this.temperature
				+ suffixCelsius + "<br>" + "humidity: " + this.humidity + "<br>" +"wind speed: " + this.windSpeed
				+ "<br>" + "wind direction: " + this.windDirection + "<br>" + "time stamp: " + this.timestamp;
	}
}
