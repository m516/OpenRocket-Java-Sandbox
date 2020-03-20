package sandbox.sensors;

import util.Force;

/**
 * 
 * @author Micah
 *
 */

public interface GravitySensor extends Sensor {
	
	/**
	 * @return the Cartesian acceleration force of 
	 * gravity acting on this sensor
	 */
	public Force getGravity();
}
