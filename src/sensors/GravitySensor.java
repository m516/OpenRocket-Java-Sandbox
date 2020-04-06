package sensors;

import util.Movement;

/**
 * 
 * @author Micah
 *
 */

public interface GravitySensor extends Sensor {
	
	/**
	 * @return the Cartesian acceleration value of
	 * gravity acting on this sensor
	 */
	public Movement getGravity();
}
