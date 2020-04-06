package sensors;

import util.Movement;

/**
 * 
 * @author Micah
 *
 */

public interface AccelerationSensor extends Sensor {
	
	/**
	 * @return the Cartesian acceleration force of this sensor
	 */
	public Movement getAcceleration();
}
