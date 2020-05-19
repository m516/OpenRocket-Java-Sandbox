/**
 * 
 */
package sensors;

/**
 * A template for a timer
 * @author Micah Mundy
 *
 */
public interface TimeSensor {
	/**
	 * @return the effective time between the start of the 
	 * simulation and the most recent measurement of another 
	 * sensor
	 */
	public float getTime();
}
