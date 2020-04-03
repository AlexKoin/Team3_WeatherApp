package team3.tests;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import team3.weatherapis.CardinalDirection;

public class CardinalDirectionsTest {

	@Test
	@DisplayName("CardinalDirectionsTest")
	public void testCardinalDirectionsTest()

	{
		assertAll(
				() -> assertEquals(CardinalDirection.NORTHEAST, CardinalDirection.fromDegree(67.0f),
						"Wrong Cardinal Direction "),
				() -> assertEquals(CardinalDirection.EAST, CardinalDirection.fromDegree(112.0f),
						"Wrong Cardinal Direction "),
				() -> assertEquals(CardinalDirection.SOUTHEAST, CardinalDirection.fromDegree(157.0f),
						"Wrong Cardinal Direction "),
				() -> assertEquals(CardinalDirection.SOUTH, CardinalDirection.fromDegree(202.0f),
						"Wrong Cardinal Direction "),
				() -> assertEquals(CardinalDirection.SOUTHWEST, CardinalDirection.fromDegree(247.0f),
						"Wrong Cardinal Direction "),
				() -> assertEquals(CardinalDirection.WEST, CardinalDirection.fromDegree(292.0f),
						"Wrong Cardinal Direction "),
				() -> assertEquals(CardinalDirection.NORTHWEST, CardinalDirection.fromDegree(337.0f),
						"Wrong Cardinal Direction "),
				() -> assertEquals(CardinalDirection.NORTH, CardinalDirection.fromDegree(357f),
						"Wrong Cardinal Direction "),
				() -> assertEquals(CardinalDirection.NONE, CardinalDirection.fromDegree(-1100),
						"Wrong Cardinal Direction "));
	}
}
