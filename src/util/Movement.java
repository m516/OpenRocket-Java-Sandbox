
/**
 * 
 */
package util;

/**
 * @author Micah
 *
 */
public class Movement extends Vector3f {

	//Units must be numbered from 0 to 8 so that the conversion table works as expected

	/** A flag that indicates that a certain Movement instance 
	 * contains a distance measured in meters 
	 * 
	 * @see UNIT_DISTANCE_METER
	 * @see UNIT_DISTANCE_KILOMETER
	 * @see UNIT_DISTANCE_CENTIMETER
	 * @see UNIT_DISTANCE_MILLIMETER
	 * @see UNIT_DISTANCE_FOOT
	 * @see UNIT_DISTANCE_INCH
	 * @see UNIT_DISTANCE_MILE
	 * @see UNIT_DISTANCE_YARD
	 * @see UNIT_DISTANCE_NAUTICAL_MILES
	 * **/
	public static final int UNIT_DISTANCE_METER = 0;
	/** A flag that indicates that a certain Movement instance 
	 * contains a distance measured in kilometers 
	 * 
	 * @see UNIT_DISTANCE_METER
	 * @see UNIT_DISTANCE_KILOMETER
	 * @see UNIT_DISTANCE_CENTIMETER
	 * @see UNIT_DISTANCE_MILLIMETER
	 * @see UNIT_DISTANCE_FOOT
	 * @see UNIT_DISTANCE_INCH
	 * @see UNIT_DISTANCE_MILE
	 * @see UNIT_DISTANCE_YARD
	 * @see UNIT_DISTANCE_NAUTICAL_MILES**/
	public static final int UNIT_DISTANCE_KILOMETER = 1;
	/** A flag that indicates that a certain Movement instance 
	 * contains a distance measured in centimeters 
	 * 
	 * @see UNIT_DISTANCE_METER
	 * @see UNIT_DISTANCE_KILOMETER
	 * @see UNIT_DISTANCE_CENTIMETER
	 * @see UNIT_DISTANCE_MILLIMETER
	 * @see UNIT_DISTANCE_FOOT
	 * @see UNIT_DISTANCE_INCH
	 * @see UNIT_DISTANCE_MILE
	 * @see UNIT_DISTANCE_YARD
	 * @see UNIT_DISTANCE_NAUTICAL_MILES**/
	public static final int UNIT_DISTANCE_CENTIMETER = 2;
	/** A flag that indicates that a certain Movement instance 
	 * contains a distance measured in millimeters 
	 * 
	 * @see UNIT_DISTANCE_METER
	 * @see UNIT_DISTANCE_KILOMETER
	 * @see UNIT_DISTANCE_CENTIMETER
	 * @see UNIT_DISTANCE_MILLIMETER
	 * @see UNIT_DISTANCE_FOOT
	 * @see UNIT_DISTANCE_INCH
	 * @see UNIT_DISTANCE_MILE
	 * @see UNIT_DISTANCE_YARD
	 * @see UNIT_DISTANCE_NAUTICAL_MILES
	 * **/
	public static final int UNIT_DISTANCE_MILLIMETER = 3;
	/** A flag that indicates that a certain Movement instance 
	 * contains a distance measured in feet 
	 * 
	 * @see UNIT_DISTANCE_METER
	 * @see UNIT_DISTANCE_KILOMETER
	 * @see UNIT_DISTANCE_CENTIMETER
	 * @see UNIT_DISTANCE_MILLIMETER
	 * @see UNIT_DISTANCE_FOOT
	 * @see UNIT_DISTANCE_INCH
	 * @see UNIT_DISTANCE_MILE
	 * @see UNIT_DISTANCE_YARD
	 * @see UNIT_DISTANCE_NAUTICAL_MILES
	 * **/
	public static final int UNIT_DISTANCE_FOOT = 4;
	/** A flag that indicates that a certain Movement instance 
	 * contains a distance measured in inch 
	 * 
	 * @see UNIT_DISTANCE_METER
	 * @see UNIT_DISTANCE_KILOMETER
	 * @see UNIT_DISTANCE_CENTIMETER
	 * @see UNIT_DISTANCE_MILLIMETER
	 * @see UNIT_DISTANCE_FOOT
	 * @see UNIT_DISTANCE_INCH
	 * @see UNIT_DISTANCE_MILE
	 * @see UNIT_DISTANCE_YARD
	 * @see UNIT_DISTANCE_NAUTICAL_MILES
	 * **/
	public static final int UNIT_DISTANCE_INCH = 5;
	/** A flag that indicates that a certain Movement instance 
	 * contains a distance measured in miles 
	 * 
	 * @see UNIT_DISTANCE_METER
	 * @see UNIT_DISTANCE_KILOMETER
	 * @see UNIT_DISTANCE_CENTIMETER
	 * @see UNIT_DISTANCE_MILLIMETER
	 * @see UNIT_DISTANCE_FOOT
	 * @see UNIT_DISTANCE_INCH
	 * @see UNIT_DISTANCE_MILE
	 * @see UNIT_DISTANCE_YARD
	 * @see UNIT_DISTANCE_NAUTICAL_MILES
	 * **/
	public static final int UNIT_DISTANCE_MILE = 6;
	/** A flag that indicates that a certain Movement instance 
	 * contains a distance measured in yards 
	 * 
	 * @see UNIT_DISTANCE_METER
	 * @see UNIT_DISTANCE_KILOMETER
	 * @see UNIT_DISTANCE_CENTIMETER
	 * @see UNIT_DISTANCE_MILLIMETER
	 * @see UNIT_DISTANCE_FOOT
	 * @see UNIT_DISTANCE_INCH
	 * @see UNIT_DISTANCE_MILE
	 * @see UNIT_DISTANCE_YARD
	 * @see UNIT_DISTANCE_NAUTICAL_MILES
	 * **/
	public static final int UNIT_DISTANCE_YARD = 7;
	/** A flag that indicates that a certain Movement instance 
	 * contains a distance measured in nautical miles 
	 * 
	 * @see UNIT_DISTANCE_METER
	 * @see UNIT_DISTANCE_KILOMETER
	 * @see UNIT_DISTANCE_CENTIMETER
	 * @see UNIT_DISTANCE_MILLIMETER
	 * @see UNIT_DISTANCE_FOOT
	 * @see UNIT_DISTANCE_INCH
	 * @see UNIT_DISTANCE_MILE
	 * @see UNIT_DISTANCE_YARD
	 * @see UNIT_DISTANCE_NAUTICAL_MILES
	 * **/
	public static final int UNIT_DISTANCE_NAUTICAL_MILES = 8;


	/** A flag that indicates that a certain Movement instance 
	 * contains a time measured in seconds 
	 * 
	 * @see UNIT_TIME_SECOND
	 * @see UNIT_TIME_MILLISECOND
	 * @see UNIT_TIME_MINUTE
	 * @see UNIT_TIME_HOUR
	 * @see UNIT_TIME_DAY
	 * @see UNIT_TIME_MICROSECOND
	 * **/
	public static final int UNIT_TIME_SECOND = 0;
	/** A flag that indicates that a certain Movement instance 
	 * contains a time measured in milliseconds 
	 * 
	 * @see UNIT_TIME_SECOND
	 * @see UNIT_TIME_MILLISECOND
	 * @see UNIT_TIME_MINUTE
	 * @see UNIT_TIME_HOUR
	 * @see UNIT_TIME_DAY
	 * @see UNIT_TIME_MICROSECOND
	 * **/
	public static final int UNIT_TIME_MILLISECOND = 1;
	/** A flag that indicates that a certain Movement instance 
	 * contains a time measured in minutes 
	 * 
	 * @see UNIT_TIME_SECOND
	 * @see UNIT_TIME_MILLISECOND
	 * @see UNIT_TIME_MINUTE
	 * @see UNIT_TIME_HOUR
	 * @see UNIT_TIME_DAY
	 * @see UNIT_TIME_MICROSECOND
	 * **/
	public static final int UNIT_TIME_MINUTE = 2;
	/** A flag that indicates that a certain Movement instance 
	 * contains a time measured in hours 
	 * 
	 * @see UNIT_TIME_SECOND
	 * @see UNIT_TIME_MILLISECOND
	 * @see UNIT_TIME_MINUTE
	 * @see UNIT_TIME_HOUR
	 * @see UNIT_TIME_DAY
	 * @see UNIT_TIME_MICROSECOND
	 * **/
	public static final int UNIT_TIME_HOUR = 3;
	/** A flag that indicates that a certain Movement instance 
	 * contains a time measured in days 
	 * 
	 * @see UNIT_TIME_SECOND
	 * @see UNIT_TIME_MILLISECOND
	 * @see UNIT_TIME_MINUTE
	 * @see UNIT_TIME_HOUR
	 * @see UNIT_TIME_DAY
	 * @see UNIT_TIME_MICROSECOND
	 * **/
	public static final int UNIT_TIME_DAY = 4;
	/** A flag that indicates that a certain Movement instance 
	 * contains a time measured in microseconds 
	 * 
	 * @see UNIT_TIME_SECOND
	 * @see UNIT_TIME_MILLISECOND
	 * @see UNIT_TIME_MINUTE
	 * @see UNIT_TIME_HOUR
	 * @see UNIT_TIME_DAY
	 * @see UNIT_TIME_MICROSECOND
	 * **/
	public static final int UNIT_TIME_MICROSECOND = 5;


	/**
	 * A conversion table of all distance units relative to the meter
	 */
	private static final float[] DISTANCE_CONVERSION_TABLE = 
		{
				1.f,
				1e3f,
				1e-2f,
				1e-3f,
				3.28084f,
				39.37008f,
				0.000621371f,
				1.093613f,
				0.000539957f
		};


	/**
	 * A conversion table of all time units relative to the second
	 */
	private static final float[] TIME_CONVERSION_TABLE = 
		{
				1.f,
				1e-3f,
				60f,
				3600f,
				86400f,
				1e-6f
		};

	/**
	 * Names of degrees of movement, used for debugging messages
	 */
	private static final String[] LEVEL_NAMES = 
		{
				"distance",
				"velocity",
				"acceleration",
				"jerk",
				"jounce"
		};
	
	/**
	 * Names of the actual distance units, all lowercase and singular
	 */
	private static final String[] DISTANCE_UNIT_NAMES_PLURAL = 
		{
				 "meters",
				 "kilometers",
				 "centimeters",
				 "millimeters",
				 "feet",
				 "inches",
				 "miles",
				 "yards",
				 "nautical miles"
		};
	
	/**
	 * Names of the actual time units, all lowercase and singular
	 */
	private static final String[] TIME_UNIT_NAMES_SINGULAR = 
		{
				 "second",
				 "millisecond",
				 "minute",
				 "hour",
				 "day",
				 "microsecond"
		};

	private int unitDistance;
	private int[] unitTime;

	/**
	 * Creates a new movement with a given distance unit and time unit (if given)
	 * @param unitDistance the distance unit of this Movement
	 * @param unitTime the time units of this Movement
	 * 
	 * @see UNIT_DISTANCE_METER
	 * @see UNIT_TIME_SECOND
	 */
	public Movement(int unitDistance, int... unitTime) {
		this.unitDistance = unitDistance;
		this.unitTime = unitTime;
	}

	/**
	 * 
	 * Creates a new movement with a given distance unit and time unit (if given)
	 * @param x the x-component of this Movement
	 * @param y the y-component of this Movement
	 * @param z the z-component of this Movement
	 * @param unitDistance the distance unit of this Movement
	 * @param unitTime the time units of this Movement
	 * 
	 * @see UNIT_DISTANCE_METER
	 * @see UNIT_TIME_SECOND
	 */
	public Movement(float x, float y, float z, int unitDistance, int... unitTime) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.unitDistance = unitDistance;
		this.unitTime = unitTime;
	}
	
	/**
	 * 
	 * Creates a new movement with a given distance unit and time unit (if given)
	 * @param x the x-component of this Movement
	 * @param y the y-component of this Movement
	 * @param z the z-component of this Movement
	 * @param unitDistance the distance unit of this Movement
	 * @param unitTime the time units of this Movement
	 * 
	 * @see UNIT_DISTANCE_METER
	 * @see UNIT_TIME_SECOND
	 */
	public Movement(double x, double y, double z, int unitDistance, int... unitTime) {
		this.x = (float)x;
		this.y = (float)y;
		this.z = (float)z;
		this.unitDistance = unitDistance;
		this.unitTime = unitTime;
	}
	
	/**
	 * 
	 * Creates a new movement with a given distance unit and time unit (if given)
	 * @param x the x-component of this Movement
	 * @param y the y-component of this Movement
	 * @param z the z-component of this Movement
	 * @param unitDistance the distance unit of this Movement
	 * @param unitTime the time units of this Movement
	 * @param degree the degree of this Movement. For example, a Movement of
	 * degree 0 is a distance, degree 1 is a velocity, degree 2 is an 
	 * acceleration, etc.
	 * 
	 * @see UNIT_DISTANCE_METER
	 * @see UNIT_TIME_SECOND
	 */
	public Movement(double x, double y, double z, int unitDistance, int unitTime, int degree) {
		this.x = (float)x;
		this.y = (float)y;
		this.z = (float)z;
		this.unitDistance = unitDistance;
		this.unitTime = new int[degree];
		for (int i = 0; i < degree; i++) {
			this.unitTime[i]=unitTime;
		}
	}

	/**
	 * 
	 * Creates a new movement with a given distance unit and time unit (if given)
	 * @param value the value of the movement vector. Example: (1,0,0), 
	 * @param unitDistance the distance unit of this Movement
	 * @param unitTime the time units of this Movement
	 * 
	 * @see UNIT_DISTANCE_METER
	 * @see UNIT_TIME_SECOND
	 */
	public Movement(Vector3f value, int unitDistance, int... unitTime) {
		this.x = value.x;
		this.y = value.y;
		this.z = value.z;
		this.unitDistance = unitDistance;
		this.unitTime = unitTime;
	}
	
	
	/**
	 * 
	 * Creates a new movement with a given distance unit and time unit (if given)
	 * @param value the value of the movement vector. Example: (1,0,0), 
	 * @param unitDistance the distance unit of this Movement
	 * @param unitTime the time units of this Movement
	 * @param degree the degree of this Movement. For example, a Movement of
	 * degree 0 is a distance, degree 1 is a velocity, degree 2 is an 
	 * acceleration, etc.
	 * 
	 * @see UNIT_DISTANCE_METER
	 * @see UNIT_TIME_SECOND
	 */
	public Movement(Vector3f value, int unitDistance, int unitTime, int degree) {
		this.x = value.x;
		this.y = value.y;
		this.z = value.z;
		this.unitDistance = unitDistance;
		this.unitTime = new int[degree];
		for (int i = 0; i < degree; i++) {
			this.unitTime[i]=unitTime;
		}
	}

	/**
	 * Creates a new distance with a given distance unit
	 * @param unitDistance the distance unit of this Movement
	 * 
	 * @see UNIT_DISTANCE_METER
	 */
	public Movement(int unitDistance) {
		this.unitDistance = unitDistance;
	}

	/**
	 * 
	 * Creates a new distance with a given distance unit
	 * @param x the x-component of this Movement
	 * @param y the y-component of this Movement
	 * @param z the z-component of this Movement
	 * @param unitDistance the distance unit of this Movement
	 * 
	 * @see UNIT_DISTANCE_METER
	 */
	public Movement(float x, float y, float z, int unitDistance) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.unitDistance = unitDistance;
	}
	
	/**
	 * 
	 * Creates a new distance with a given distance unit
	 * @param x the x-component of this Movement
	 * @param y the y-component of this Movement
	 * @param z the z-component of this Movement
	 * @param unitDistance the distance unit of this Movement
	 * 
	 * @see UNIT_DISTANCE_METER
	 */
	public Movement(double x, double y, double z, int unitDistance) {
		this.x = (float)x;
		this.y = (float)y;
		this.z = (float)z;
		this.unitDistance = unitDistance;
	}

	/**
	 * 
	 * Creates a new distance with a given distance unit
	 * @param value the value of the movement vector. Example: (1,0,0), 
	 * @param unitDistance the distance unit of this Movement
	 * 
	 * @see UNIT_DISTANCE_METER
	 */
	public Movement(Vector3f value, int unitDistance) {
		this.x = value.x;
		this.y = value.y;
		this.z = value.z;
		this.unitDistance = unitDistance;
	}

	/**
	 * Convert this movement to another set of units.
	 * @param unitDistance the distance unit to convert to
	 * @param unitTime the time units to convert to
	 */
	public void convertTo(int unitDistance, int... unitTime) {

		//Don't allow conversion between different degrees of time movement
		if(this.unitTime.length != unitTime.length) {
			System.err.println("Time units mismatch!");

			System.err.print("You are trying to convert a Movement of degree ");
			System.err.print(this.unitTime.length);
			System.err.print(" to degree ");
			System.err.print(unitTime.length);
			System.err.println(".");

			System.err.print("This is similar to converting a ");
			System.err.print(getType());
			System.err.print(" to a ");
			System.err.print(getType(unitTime.length));
			System.err.println(".");

			throw new IllegalArgumentException("Time units mismatch");
		}

		//Convert distance first
		mult(DISTANCE_CONVERSION_TABLE[unitDistance]/DISTANCE_CONVERSION_TABLE[this.unitDistance]);
		this.unitDistance = unitDistance;

		//Now convert time
		for(int i = 0; i < unitTime.length; i++) {
			mult(TIME_CONVERSION_TABLE[unitTime[i]]/TIME_CONVERSION_TABLE[this.unitTime[i]]);
			this.unitTime[i] = unitTime[i];
		}
	}


	/**
	 * Convert a <b>distance</b> to another set of units.<br>
	 * Use convertTo(int unitDistance, int... unitTime) for anything that 
	 * is NOT a distance
	 * 
	 * @param unitDistance the distance unit to convert to
	 */
	public void convertTo(int unitDistance) {

		//Don't allow conversion between different degrees of time movement
		if(unitTime.length != 0) {
			System.err.println("Time units mismatch. Read the docs boi!");

			System.err.print("You are trying to convert a Movement of degree ");
			System.err.print(unitTime.length);
			System.err.print(" to degree ");
			System.err.print(0);
			System.err.println(".");

			System.err.print("This is similar to converting a ");
			System.err.print(getType());
			System.err.print(" to a ");
			System.err.print(getType(0));
			System.err.println(".");

			throw new IllegalStateException("Attempting to treat a movement as a distance during conversion of units");
		}

		//Convert distance first
		mult(DISTANCE_CONVERSION_TABLE[unitDistance]/DISTANCE_CONVERSION_TABLE[this.unitDistance]);
		this.unitDistance = unitDistance;

		//Now convert time
		for(int i = 0; i < unitTime.length; i++) {
			mult(TIME_CONVERSION_TABLE[unitTime[i]]/TIME_CONVERSION_TABLE[this.unitTime[i]]);
			this.unitTime[i] = unitTime[i];
		}
	}

	/**
	 * @return a human-readable String type of this Movement, 
	 * which will never change. For example, a Movement instance
	 * with 0 time units will return "distance"
	 */
	public String getType() {
		return getType(unitTime.length);
	}

	/**
	 * Gives a human-readable String that is the name of a 
	 * Movement with a certain degree.
	 * @param numDegrees the degree of the Movement
	 * @return the type it corresponds to
	 */
	public static String getType(int numDegrees) {
		if(numDegrees < LEVEL_NAMES.length) {
			return LEVEL_NAMES[numDegrees];
		}
		StringBuilder sb = new StringBuilder();
		sb.append(numDegrees);
		sb.append("-degree Movement");
		return sb.toString();
	}

	@Override public Object clone() {
		return copy();
	}

	/**
	 * @return a deep copy of this Movement
	 */
	public Movement copy() {
		Movement m = new Movement(this, unitDistance, unitTime);
		return m;
	}

	/**
	 * Adds this movement to another movement
	 * @param other the movement to add to this one
	 * @throws IllegalArgumentException the degrees of this
	 * movement and the other movement don't match.
	 */
	public void add(Movement other) {
		convertTo(other.unitDistance, other.unitTime);
		x+=other.x;
		y+=other.y;
		z+=other.z;
	}

	/**
	 * Updates the Movement<br> by causing a change in motion
	 * 
	 * For example, a distance can be updated by
	 * <code>distanceInstance.update(velocityInstance, 1, UNIT_TIME_SECOND)</code><br>
	 * 
	 * For example, a velocity can be updated by
	 * <code>velocityInstance.update(accelerationInstance, 1, UNIT_TIME_SECOND)</code><br>
	 * 
	 * 
	 * @param other a Motion with one lower degree
	 * @param timestep
	 * @param timeUnits
	 */
	public void update(Movement other, float timestep, int timeUnits) {
		
		//Don't allow conversion between different degrees of time
		if(unitTime.length != other.unitTime.length-1) {
			System.err.println("Time units mismatch!");

			System.err.print("You are trying to update a Movement of degree ");
			System.err.print(unitTime.length);
			System.err.print(" with a Movement of degree ");
			System.err.print(other.unitTime.length);
			System.err.println(".");

			System.err.print("This is similar to updating a ");
			System.err.print(getType());
			System.err.print(" to a ");
			System.err.print(getType(other.unitTime.length));
			System.err.println(".");

			throw new IllegalArgumentException("Time units are invalid");
		}
		

		Movement otherConverted = other.copy();
		int[] newUnits = new int[other.unitTime.length];
		for(int i = 0; i < unitTime.length; i++) {
			newUnits[i]=unitTime[i];
		}
		newUnits[unitTime.length] = timeUnits;
		otherConverted.convertTo(unitDistance, newUnits);
		

		//Convert distance first
		other.mult(DISTANCE_CONVERSION_TABLE[unitDistance]/DISTANCE_CONVERSION_TABLE[this.unitDistance]);

		//Now convert time
		for(int i = 0; i < unitTime.length; i++) {
			other.mult(TIME_CONVERSION_TABLE[unitTime[i]]/TIME_CONVERSION_TABLE[this.unitTime[i]]);
		}


		x+=other.x;
		y+=other.y;
		z+=other.z;
	}
	
	/**
	 * Gets the average change of two Movements. <p>
	 * For example, the average change of two velocity vectors is an acceleration. <br>
	 * And the average change of two position vectors is a velocity. <br>
	 * @param m1 the first Movement
	 * @param m2 the second Movement. Must be the same degree as the first Movement.
	 * @return the change between the Movements. The degree of this new Movement is one
	 * higher than the degree of the other movement.
	 * 
	 */
	public static Movement averageChangeOf(Movement m1, Movement m2, double time, int timeUnits) {
		if(timeUnits<0 || timeUnits>5) 
			throw new IllegalArgumentException("Illegal time unit: "+timeUnits);
		if(m1.unitTime.length != m2.unitTime.length) {
			System.err.println("Time units mismatch!");

			System.err.print("You are trying to update a Movement of degree ");
			System.err.print(m1.unitTime.length);
			System.err.print(" with a Movement of degree ");
			System.err.print(m2.unitTime.length);
			System.err.println(".");

			System.err.print("This is similar to updating a ");
			System.err.print(m1.getType());
			System.err.print(" to a ");
			System.err.print(getType(m2.unitTime.length));
			System.err.println(".");

			throw new IllegalArgumentException("Time units are invalid");
		}
		
		Movement m2c = m2.copy();
		m2c.convertTo(m1.unitDistance, m1.unitTime);
		
		int[] retTimeUnits = new int[m1.unitTime.length+1];
		for (int i = 0; i < m1.unitTime.length; i++) {
			retTimeUnits[i] = m1.unitTime[i];
		}
		
		retTimeUnits[retTimeUnits.length-1] = timeUnits;
		Movement ret = new Movement((m1.x-m2c.x)/time, (m1.y-m2c.y)/time, (m1.z-m2c.z)/time, m1.unitDistance, retTimeUnits);
		return ret;
	}
	
	/**
	 * Returns a human-readable String of this Movement
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		

		sb.append(" [A ");
		sb.append(getType());
		sb.append(" with units: ");
		sb.append(DISTANCE_UNIT_NAMES_PLURAL[unitDistance]);
		for (int i = 0; i < unitTime.length; i++) {
			sb.append(" per ");
			sb.append(TIME_UNIT_NAMES_SINGULAR[unitTime[i]]);
		}
		sb.append("]");
		
		return sb.toString();
	}



}
