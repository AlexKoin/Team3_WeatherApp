package team3.weatherapis;

public enum CardinalDirection {
	NONE, NORTH, NORTHEAST, EAST, SOUTHEAST, SOUTH, SOUTHWEST, WEST, NORTHWEST;
	
	/**
	 * @param input decimal degree
	 * @return cardinal direction
	 */
	public static CardinalDirection fromDegree(float inputDegree)
	{
		CardinalDirection direction = NONE;
		
		float clippedDegree = inputDegree%360.0f;
		
		if ((clippedDegree >= 0f) && (clippedDegree < 22.5f))
		{
			direction = NORTH;
		}
		else if ((clippedDegree >= 22.5f) && (clippedDegree < 67.5f))
		{
			direction = NORTHEAST;
		}
		else if ((clippedDegree >= 67.5f) && (clippedDegree < 112.5f))
		{
			direction = EAST;
		}
		else if ((clippedDegree >= 112.5f) && (clippedDegree < 157.5f))
		{
			direction = SOUTHEAST;
		}
		else if ((clippedDegree >= 157.5f) && (clippedDegree < 202.5f))
		{
			direction = SOUTH;
		}
		else if ((clippedDegree >= 202.5f) && (clippedDegree < 247.5f))
		{
			direction = SOUTHWEST;
		}
		else if ((clippedDegree >= 247.5f) && (clippedDegree < 292.5f))
		{
			direction = WEST;
		}
		else if ((clippedDegree >= 292.5f) && (clippedDegree < 337.5f))
		{
			direction = NORTHWEST;
		}
		else if ((clippedDegree >= 337.5f) && (clippedDegree <= 360.0f))
		{
			direction = NORTH;
		}
		else
		{
			direction = NONE;
		}
		
		return direction;
	}
}
