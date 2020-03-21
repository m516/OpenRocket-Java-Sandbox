/**
 * 
 */
package sandbox.sensors;

import sensors.AccelerationSensor;
import sensors.GravitySensor;
import util.Force;

/**
 * @author Beta
 *
 */
public class DummyIMU implements GravitySensor, AccelerationSensor {
	Force acceleration;
	Force gravity;
	
	public DummyIMU() {
		acceleration = new Force(0, 0, 1);
	}
	
	/**
	 * 
	 */
	@Override
	public Force getAcceleration() {
		return null;
	}

	@Override
	public Force getGravity() {
		return null;
	}

}
