package team3.tests;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import team3.weatherapis.Weather;

class ApiParameterTestCase {

	// Temperature check

	@Nested
	class TemperetureTest {

		@Test
		public void testIsValidTemperatureValid() {
			assertTrue((Weather.isValidTemperature("20") == true));
		}

		@Test
		public void testIsValidTemperatureInvalid() {
			assertTrue((Weather.isValidTemperature("-40") == false));
		}

		@Test
		public void testIsValidTemperatureException() {
			assertTrue((Weather.isValidTemperature("test case") == false));
		}
	}

	// Humidity check
	@Nested
	class HumidityTest {

		@Test
		public void testIsValidHumidityValid() {
			assertTrue((Weather.isValidHumidity("50") == true));
		}

		@Test
		public void testIsValidHumidityInvalid() {
			assertTrue((Weather.isValidHumidity("-40") == false));
		}

		@Test
		public void testIsValidHumidityException() {
			assertTrue((Weather.isValidTemperature("test case") == false));
		}
	}

	@Nested
	class PrecipitationTest {
		// Precipitation check
		@Test
		public void testIsValidPrecipitationValid() {
			assertTrue((Weather.isValidHumidity("50") == true));
		}

		@Test
		public void testIsValidPrecipitationInvalid() {
			assertTrue((Weather.isValidHumidity("-40") == false));
		}

		@Test
		public void testIsValidPrecipitationException() {
			assertTrue((Weather.isValidHumidity("test case") == false));
		}
	}

	// WindSpeed check
	@Nested
	class WindSpeedTest {

		@Test
		public void testIsValidWindSpeedValid() {
			assertTrue((Weather.isValidWindSpeed("20") == true));
		}

		@Test
		public void testIsValidWindSpeedinValid() {
			assertTrue((Weather.isValidWindSpeed("80") == false));
		}

		@Test
		public void testIsValidWindSpeedVException() {
			assertTrue((Weather.isValidWindSpeed("test case") == false));
		}
	}

	// ValidSourceName check
	@Nested
	class SourceNameTest {

		@Test
		public void testIsValidSourceNameValid() {
			assertTrue((Weather.isValidSourceName("20") == true));
		}

		@Test
		public void testIsValidSourceNameInvalid() {
			assertTrue((Weather.isValidSourceName("") == false));
		}

		@Test
		public void testIsValidSourceNameException() {
			assertTrue((Weather.isValidSourceName(null) == false));
		}
	}

	// ValidLocation check
	@Nested
	class LocationTest {
		@Test
		public void testIsValidLocationValid() {
			assertTrue((Weather.isValidLocation("Riga") == true));
		}

		@Test
		public void testIsValidLocationInvalid() {
			assertTrue((Weather.isValidLocation("") == false));
		}

		@Test
		public void testIsValidLocationException() {
			assertTrue((Weather.isValidLocation(null) == false));
		}
	}

	// ValidWeather check
	@Nested
	class WeatherTest {
		@Test
		public void testIsValidWeatherValid() {
			assertTrue((Weather.isValidWeather("Riga") == true));
		}

		@Test
		public void testIsValidWeathernvalid() {
			assertTrue((Weather.isValidWeather("") == false));
		}

		@Test
		public void testIsWeatherException() {
			assertTrue((Weather.isValidWeather(null) == false));
		}
	}

	// ValidWindDirection check
	@Nested
	class WindDirectionTest {
		@Test
		public void testIsValidWindDirectionValid() {
			assertTrue((Weather.isValidWindDirection("Riga") == true));
		}

		@Test
		public void testIsValidWindDirectionInvalid() {
			assertTrue((Weather.isValidWindDirection("") == false));
		}

		@Test
		public void testIsWindDirectionException() {
			assertTrue((Weather.isValidWindDirection(null) == false));
		}
	}

}
