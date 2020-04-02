package team3.tests;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import team3.weatherapis.Weather;
import team3.weatherapis.WeatherApi;

class unitConversionTest {



		@Nested
		class CelsiusToKelvinTest {

			@Test
			public void testIsKelvinToCelsiusValidTrue() {
				assertTrue((WeatherApi.isKelvinToCelsiusValid("50") == true));
			}

			@Test
			public void testIsKelvinToCelsiusValidFalse() {
				assertTrue((WeatherApi.isKelvinToCelsiusValid("-300") == false));
			}

			@Test
			public void testIsValidTemperatureException() {
				assertTrue((Weather.isValidTemperature("test case") == false));
			}
}
		@Nested
		class kphToMpsTest {

			@Test
			public void testIskphToMpsValidTrue() {
				assertTrue((WeatherApi.iskphToMpsValid("50") == true));
			}

			@Test
			public void testIskphToMpsValidTrueFalse() {
				assertTrue((WeatherApi.iskphToMpsValid("600") == false));
			}

			@Test
			public void testIskphToMpsValidTrueException() {
				assertTrue((WeatherApi.iskphToMpsValid("test case") == false));
			}
	
}
}
		
		
		
		
		

