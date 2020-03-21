//TODO implement movement methods. Conversions done. Nothing else done.

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
	 * contains a distance measured in meters **/
	public static final int UNIT_DISTANCE_METER = 0;
	/** A flag that indicates that a certain Movement instance 
	 * contains a distance measured in kilometers **/
	public static final int UNIT_DISTANCE_KILOMETER = 1;
	/** A flag that indicates that a certain Movement instance 
	 * contains a distance measured in centimeters **/
	public static final int UNIT_DISTANCE_CENTIMETER = 2;
	/** A flag that indicates that a certain Movement instance 
	 * contains a distance measured in millimeters **/
	public static final int UNIT_DISTANCE_MILLIMETER = 3;
	/** A flag that indicates that a certain Movement instance 
	 * contains a distance measured in feet **/
	public static final int UNIT_DISTANCE_FOOT = 4;
	/** A flag that indicates that a certain Movement instance 
	 * contains a distance measured in inch **/
	public static final int UNIT_DISTANCE_INCH = 5;
	/** A flag that indicates that a certain Movement instance 
	 * contains a distance measured in miles **/
	public static final int UNIT_DISTANCE_MILE = 6;
	/** A flag that indicates that a certain Movement instance 
	 * contains a distance measured in yards **/
	public static final int UNIT_DISTANCE_YARD = 7;
	/** A flag that indicates that a certain Movement instance 
	 * contains a distance measured in nautical miles **/
	public static final int UNIT_DISTANCE_NAUTICAL_MILES = 8;
	

	/** A flag that indicates that a certain Movement instance 
	 * contains a time measured in seconds **/
	public static final int UNIT_TIME_SECOND = 0;
	/** A flag that indicates that a certain Movement instance 
	 * contains a time measured in milliseconds **/
	public static final int UNIT_TIME_MILLISECOND = 1;
	/** A flag that indicates that a certain Movement instance 
	 * contains a time measured in minutes **/
	public static final int UNIT_TIME_MINUTE = 2;
	/** A flag that indicates that a certain Movement instance 
	 * contains a time measured in hours **/
	public static final int UNIT_TIME_HOUR = 3;
	/** A flag that indicates that a certain Movement instance 
	 * contains a time measured in days **/
	public static final int UNIT_TIME_DAY = 4;
	/** A flag that indicates that a certain Movement instance 
	 * contains a time measured in microseconds **/
	public static final int UNIT_TIME_MICROSECOND = 5;

	/**
	 * A conversion table of all units relative to Newtons
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
	
	private static final float[] TIME_CONVERSION_TABLE = 
		{
			1.f,
			1e-3f,
			60f,
			3600f,
			86400f,
			1e-6f
		};
	
	private int unit = 0;

	/**
	 * Creates a new force
	 */
	public Movement() {
		super();
	}

	/**
	 * Creates a Force whose x, y, and z 
	 * components are given by the vector.
	 * Units are assumed to be Newtons.<br>
	 * 
	 * To not use kilograms, add an additional parameter
	 * at the end of this function.
	 * @param vector the value of the force
	 */
	public Movement(Vector3f vector) {
		super(vector);
	}

	/**
	 * Creates a Force whose x, y, and z 
	 * components are given.
	 * Units are assumed to be Newtons.<br>
	 * 
	 * To not use kilograms, add an additional parameter
	 * at the end of this function.
	 * @param x
	 * @param y
	 * @param z
	 */
	public Movement(double x, double y, double z) {
		super(x, y, z);
	}

	/**
	 * Creates a Force whose x, y, and z 
	 * components are given.
	 * Units are assumed to be Newtons.<br>
	 * 
	 * To not use kilograms, add an additional parameter
	 * at the end of this function.
	 * @param x
	 * @param y
	 * @param z
	 */
	public Movement(float x, float y, float z) {
		super(x, y, z);
	}

	/**
	 * Creates a Force whose x, y, and z 
	 * components are given.
	 * Units are assumed to be Newtons.<br>
	 * 
	 * To not use kilograms, add an additional parameter
	 * at the end of this function.
	 * @param x the x-component of this force
	 * @param y the y-component of this force
	 * @param z the z-component of this force
	 */
	public Movement(int x, int y, int z) {
		super(x, y, z);
	}

	/**
	 * Creates a Force whose x, y, and z 
	 * components are given.
	 * Units are assumed to be Newtons.<br>
	 * 
	 * To not use kilograms, add an additional parameter
	 * at the end of this function.
	 * @param x the x-component of this force
	 * @param y the y-component of this force
	 * @param z the z-component of this force
	 */
	public Movement(byte x, byte y, byte z) {
		super(x, y, z);
	}
	
	/**
	 * Creates a Force whose x, y, and z 
	 * components are given by the vector.
	 * Units are specified manually using 
	 * the <b>units</b> constants contained
	 * by this class.<br>
	 * 
	 * @param vector the value of the force
	 * 
	 * @see 	UNIT_KILOGRAM
	 * @see 	UNIT_POUND
	 * @see 	UNIT_OUNCE
	 * @see 	UNIT_DYNE
	 * @see 	UNIT_GRAM
	 * @see 	UNIT_KILONEWTON
	 * @see 	UNIT_KIP
	 * @see 	UNIT_TON
	 */
	public Movement(Vector3f vector, int units) {
		super(vector);
	}

	/**
	 * Creates a Force whose x, y, and z 
	 * components are given.
	 * Units are specified manually using 
	 * the <b>units</b> constants contained
	 * by this class.<br>
	 * 
	 * @param x the x-component of this force
	 * @param y the y-component of this force
	 * @param z the z-component of this force
	 * 
	 * @see 	UNIT_KILOGRAM
	 * @see 	UNIT_POUND
	 * @see 	UNIT_OUNCE
	 * @see 	UNIT_DYNE
	 * @see 	UNIT_GRAM
	 * @see 	UNIT_KILONEWTON
	 * @see 	UNIT_KIP
	 * @see 	UNIT_TON
	 */
	public Movement(double x, double y, double z, int units) {
		super(x, y, z);
	}

	/**
	 * Creates a Force whose x, y, and z 
	 * components are given.
	 * Units are specified manually using 
	 * the <b>units</b> constants contained
	 * by this class.<br>
	 * 
	 * @param x the x-component of this force
	 * @param y the y-component of this force
	 * @param z the z-component of this force
	 * 
	 * @see 	UNIT_KILOGRAM
	 * @see 	UNIT_POUND
	 * @see 	UNIT_OUNCE
	 * @see 	UNIT_DYNE
	 * @see 	UNIT_GRAM
	 * @see 	UNIT_KILONEWTON
	 * @see 	UNIT_KIP
	 * @see 	UNIT_TON
	 */
	public Movement(float x, float y, float z, int units) {
		super(x, y, z);
	}

	/**
	 * Creates a Force whose x, y, and z 
	 * components are given.
	 * Units are specified manually using 
	 * the <b>units</b> constants contained
	 * by this class.<br>
	 * 
	 * @param x the x-component of this force
	 * @param y the y-component of this force
	 * @param z the z-component of this force
	 * 
	 * @see 	UNIT_KILOGRAM
	 * @see 	UNIT_POUND
	 * @see 	UNIT_OUNCE
	 * @see 	UNIT_DYNE
	 * @see 	UNIT_GRAM
	 * @see 	UNIT_KILONEWTON
	 * @see 	UNIT_KIP
	 * @see 	UNIT_TON
	 */
	public Movement(int x, int y, int z, int units) {
		super(x, y, z);
	}

	/**
	 * Creates a Force whose x, y, and z 
	 * components are given.
	 * Units are specified manually using 
	 * the <b>units</b> constants contained
	 * by this class.<br>
	 * 
	 * @param x the x-component of this force
	 * @param y the y-component of this force
	 * @param z the z-component of this force
	 * 
	 * @see 	UNIT_KILOGRAM
	 * @see 	UNIT_POUND
	 * @see 	UNIT_OUNCE
	 * @see 	UNIT_DYNE
	 * @see 	UNIT_GRAM
	 * @see 	UNIT_KILONEWTON
	 * @see 	UNIT_KIP
	 * @see 	UNIT_TON
	 */
	public Movement(byte x, byte y, byte z, int units) {
		super(x, y, z);
	}

	/**
	 * @return the units of this Force
	 */
	public int getUnit() {
		return unit;
	}

	/**
	 * @param newUnit the unit to set to. Values are adjusted 
	 * accordingly so that minimal data is lost.
	 */
	public void setUnit(int newUnit) {
		mult(CONVERSION_TABLE[newUnit]/CONVERSION_TABLE[unit]);
		unit = newUnit;
	}
	
	
	@Override public Object clone() {
		Movement f = new Movement();
		f.x = x;
		f.y = y;
		f.z = z;
		f.unit = unit;
		return f;
	}
	

	/**
	 * @return a copy of this Force
	 */
	public Movement copy() {
		Movement f = new Movement();
		f.x = x;
		f.y = y;
		f.z = z;
		f.unit = unit;
		return f;
	}
	
	/**
	 * Adds a force to this one, taking units into account
	 * @param other the force to add to this one
	 * 
	 * @see add
	 */
	public void add(Movement other) {
		setUnit(other.unit);
		translate(other);
	}

	
	/**
	 * Adds a force to this one, taking units into account
	 * @param other the force to add to this one
	 * 
	 * @return the result of the addition of force1 and force2
	 * 
	 * @see add
	 */
	public static Movement add(Movement force1, Movement force2) {
		Movement newForce = force1.copy();
		newForce.add(force2);
		return newForce;
	}
}
