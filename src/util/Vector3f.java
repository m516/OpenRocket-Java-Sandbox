/**
 * 
 */
package util;

import net.sf.openrocket.util.Coordinate;

/**
 * A three-dimentional floating point vector.
 * @author Micah
 */
public class Vector3f{

	
	public float x;
	public float y;
	public float z;
	
	/**
	 * Creates a new vector with x, y, z = 0
	 */
	public Vector3f() {
		x = 0;
		y = 0;
		z = 0;
	}
	
	/**
	 * Creates a new vector with the same coordinates as another vector
	 * @param vecToCopy the vector to clone
	 */
	public Vector3f(Vector3f vecToCopy) {
		x = vecToCopy.x;
		y = vecToCopy.y;
		z = vecToCopy.z;
	}
	
	/**
	 * Creates a new vector with certain values
	 * @param x the x-value of the new vector
	 * @param y the y-value of the new vector
	 * @param z the z-value of the new vector
	 */
	public Vector3f(double x, double y, double z) {
		this.x = (float)x;
		this.y = (float)y;
		this.z = (float)z;
	}

	/**
	 * Creates a new vector with certain values
	 * @param x the x-value of the new vector
	 * @param y the y-value of the new vector
	 * @param z the z-value of the new vector
	 */
	public Vector3f(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/**
	 * Creates a new vector with certain values
	 * @param x the x-value of the new vector
	 * @param y the y-value of the new vector
	 * @param z the z-value of the new vector
	 */
	public Vector3f(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/**
	 * Creates a new vector with certain values
	 * @param x the x-value of the new vector
	 * @param y the y-value of the new vector
	 * @param z the z-value of the new vector
	 */
	public Vector3f(byte x, byte y, byte z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/**
	 * Creates a new vector in the XY plane with certain values
	 * @param x the x-value of the new vector
	 * @param y the y-value of the new vector
	 */
	public Vector3f(double x, double y) {
		this.x = (float)x;
		this.y = (float)y;
	}

	/**
	 * Creates a new vector in the XY plane with certain values
	 * @param x the x-value of the new vector
	 * @param y the y-value of the new vector
	 */
	public Vector3f(float x, float y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Creates a new vector in the XY plane with certain values
	 * @param x the x-value of the new vector
	 * @param y the y-value of the new vector
	 */
	public Vector3f(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Creates a new vector in the XY plane with certain values
	 * @param x the x-value of the new vector
	 * @param y the y-value of the new vector
	 */
	public Vector3f(byte x, byte y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Multiply this vector by a scalar. <br>
	 * 
	 * If it is necessary to create a new vector that
	 * is a scalar multiple of this vector, call the
	 * static method instead. <br>
	 * 
	 * <code>
	 * @param value
	 */
	public void mult(float value) {
		x*=value;
		y*=value;
		z*=value;
	}
	
	/**
	 * Creates a new vector that is a scalar multiple 
	 * of another vector.
	 * @param source the source vector
	 * @param value the scalar value to multiply to the source vector
	 * @return a new vector that is the result of the multiplication
	 */
	public static Vector3f mult(Vector3f source, float value) {
		Vector3f ret = new Vector3f(source);
		ret.mult(value);
		return ret;
	}
	
	/**
	 * Translates this vector by an offset of another vector
	 * @param other the vector to offset this one by
	 */
	public void translate(Vector3f other) {
		x+=other.x;
		y+=other.y;
		z+=other.z;
	}
	
	/**
	 * Creates a new vector that is translated by another vector
	 * @param source a vector
	 * @param other a vector
	 * @return the source translated by the other vector
	 */
	public static Vector3f translate(Vector3f source, Vector3f other) {
		Vector3f ret = new Vector3f(source);
		ret.translate(other);
		return ret;
	}
	
	/**
	 * Returns a human-readable String of this vector
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		sb.append(x);
		sb.append(", ");
		sb.append(y);
		sb.append(", ");
		sb.append(z);
		sb.append("}");
		return sb.toString();
	}
	
	/*-----Conversion to/from OpenRocket's net.sf.openrocket.util.Coordinate class----*/
	
	/**
	 * Converts an OpenRocket Coordinate into a home-style Vector3f
	 * @param c the Coordinate to transorm
	 * @return a Vector3f representation of the Coordinate
	 */
	public static Vector3f fromOpenRocketCoordinate(Coordinate c) {
		Vector3f ret = new Vector3f();
		
		ret.x=(float) c.x;
		ret.y=(float) c.y;
		ret.z=(float) c.z;
		
		return ret;
	}
	
	/**
	 * A constructor that copies the values of an OpenRocket Coordinate <p>
	 * Note that some data is lost in the conversion because Coordinate retains
	 * its values as doubles, while Vector3f stores them as floats.
	 * @param c the Coordinate to transform into a Vector3f
	 * 
	 */
	public Vector3f(Coordinate c) {
		x=(float) c.x;
		y=(float) c.y;
		z=(float) c.z;
	}
	
	/**
	 * Converts this Vector3f into an OpenRocket Coordinate <p>
	 * Used to perform operations supported by Openrocket such as
	 * quaternion rotations, etc.
	 * @return Coordinate representation of this Vector3f
	 */
	public Coordinate toCoordinate() {
		Coordinate c = new Coordinate(x, y, z);
		return c;
	}
}
