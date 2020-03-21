/**
 * 
 */
package util;

/**
 * @author Micah
 *
 */
public class Force extends Vector3f {
	
	//Units must be numbered from 0 to 8 so that the conversion table works as expected
	
	/** A flag that indicates that a certain Force instance is in Newtons **/
	public static final int UNIT_NEWTON = 0;
	/** A flag that indicates that a certain Force instance is in kilograms 
	 * as perceived from sea-level **/
	public static final int UNIT_KILOGRAM = 1;
	/** A flag that indicates that a certain Force instance is in pound-feet **/
	public static final int UNIT_POUND = 2;
	/** A flag that indicates that a certain Force instance is in ounce-inches **/
	public static final int UNIT_OUNCE = 3;
	/** A flag that indicates that a certain Force instance is in Newtons **/
	public static final int UNIT_DYNE = 4;
	/** A flag that indicates that a certain Force instance is in Newtons **/
	public static final int UNIT_GRAM = 5;
	/** A flag that indicates that a certain Force instance is in Newtons **/
	public static final int UNIT_KILONEWTON = 6;
	/** A flag that indicates that a certain Force instance is in Newtons **/
	public static final int UNIT_KIP = 7;
	/** A flag that indicates that a certain Force instance is in Newtons **/
	public static final int UNIT_TON = 8;
	

	/**
	 * A conversion table of all units relative to Newtons
	 */
	private static final float[] FORCE_CONVERSION_TABLE = 
		{
				1.f,
				0.1019716f,
				0.2248089f,
				3.596943f,
				100000f,
				101.9716f,
				0.001f,
				0.0002248089f,
				0.0001019716f
		};
	
	private int unit = 0;

	/**
	 * Creates a new force
	 */
	public Force() {
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
	public Force(Vector3f vector) {
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
	public Force(double x, double y, double z) {
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
	public Force(float x, float y, float z) {
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
	public Force(int x, int y, int z) {
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
	public Force(byte x, byte y, byte z) {
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
	public Force(Vector3f vector, int units) {
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
	public Force(double x, double y, double z, int units) {
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
	public Force(float x, float y, float z, int units) {
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
	public Force(int x, int y, int z, int units) {
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
	public Force(byte x, byte y, byte z, int units) {
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
		mult(FORCE_CONVERSION_TABLE[newUnit]/FORCE_CONVERSION_TABLE[unit]);
		unit = newUnit;
	}
	
	
	@Override public Object clone() {
		Force f = new Force();
		f.x = x;
		f.y = y;
		f.z = z;
		f.unit = unit;
		return f;
	}
	

	/**
	 * @return a copy of this Force
	 */
	public Force copy() {
		Force f = new Force();
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
	public void add(Force other) {
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
	public static Force add(Force force1, Force force2) {
		Force newForce = force1.copy();
		newForce.add(force2);
		return newForce;
	}
}
