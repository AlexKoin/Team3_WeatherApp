package team3.weatherapis;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import team3.dbmanagement.DatabaseManager;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

public class WeatherAPITest {

	public static void main(String[] args) {
		//ArrayList<WeatherAPI> weatherApis = new ArrayList<WeatherAPI>();

		//DatabaseManager city = new DatabaseManager();
		//city.setCityToApis("Paris");

		DatabaseManager.getCityNameList();

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

		String locationParameter = "Riga";
		ArrayList<Weather> weatherResults = new ArrayList<Weather>();

		/* Use location parameter here to retrieve weather data: */
		for (WeatherApi api : weatherApis) {
			weatherResults.add(api.getWeather(DatabaseManager.getApiLocation(locationParameter, api.getApiId())));
		}

//		for (Weather weatherResult : weatherResults) {
//			System.out.println(weatherResult.toString());
//			System.out.println("***");
//		}
	}


	@Before
	public void setUp () throws Exception {
		System.out.println("Starting test..");
	}

	@After
	public void tearDown () throws Exception {
		System.out.println("Finishing test..");
	}

	@Test
	public void test01CardinalDirections () {
		CardinalDirection actual, expected;

		actual = CardinalDirection.fromDegree(180.0f + 22.4f);
		expected = CardinalDirection.SOUTH;
		assertEquals("Cardinal Direction incorrect", expected, actual);
	}

}
