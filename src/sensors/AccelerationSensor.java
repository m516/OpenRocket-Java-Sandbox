package sensors;

import util.Force;

/**
 * 
 * @author Micah
 *
 */

public interface AccelerationSensor extends Sensor {
	
	/**
	 * @return the Cartesian acceleration force of this sensor
	 */
	public Force getAcceleration();
}
