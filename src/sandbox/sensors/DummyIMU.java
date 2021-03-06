/**
 * 
 */
package sandbox.sensors;

import sensors.AccelerationSensor;
import sensors.GravitySensor;
import util.Movement;

/**
 * @author Micah Mundy
 */
public class DummyIMU implements GravitySensor, AccelerationSensor {
	Movement acceleration;
	Movement gravity;
	
	public DummyIMU() {
		acceleration = new Movement(0, 0, 1, Movement.UNIT_DISTANCE_METER, Movement.UNIT_TIME_SECOND, 2);
		gravity = new Movement(0, 0, -9.81, Movement.UNIT_DISTANCE_METER, Movement.UNIT_TIME_SECOND, 2);
	}
	
	@Override
	public Movement getAcceleration() {
		return acceleration;
	}

	@Override
	public Movement getGravity() {
		return gravity;
	}

}
