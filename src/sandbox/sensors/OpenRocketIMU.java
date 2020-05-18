/**
 * 
 */
package sandbox.sensors;

import java.util.ArrayList;
import java.util.List;

import net.sf.openrocket.simulation.SimulationStatus;
import net.sf.openrocket.simulation.exception.SimulationException;
import net.sf.openrocket.simulation.listeners.SimulationListener;
import net.sf.openrocket.util.Coordinate;
import sensors.AccelerationSensor;
import sensors.GravitySensor;
import util.Movement;
import util.Vector3f;

/**
 * @author Micah Mundy
 *
 */
public class OpenRocketIMU implements SimulationListener, GravitySensor, AccelerationSensor {
	
	Movement acceleration, gravity;
	Movement velocity;
	boolean initialized = false;

	/**
	 * 
	 */
	public OpenRocketIMU() {
	}

	@Override
	public Movement getAcceleration() {
		return acceleration;
	}

	@Override
	public Movement getGravity() {
		return gravity;
	}

	@Override
	public SimulationListener clone() {
		return new OpenRocketIMU();
	}

	@Override
	public void endSimulation(SimulationStatus arg0, SimulationException arg1) {
		update(arg0);

	}

	/**
	 * Return whether this is a system listener.  System listeners are used internally for various
	 * purposes by OpenRocket.  User-written listeners should always return <code>false</code>.
	 * <p>
	 * System listeners do not cause warnings to be added to the simulation results when they affect
	 * the simulation.
	 * 
	 * @return		whether this is a system listener
	 */
	@Override
	public boolean isSystemListener() {
		return false;
	}

	@Override
	public void postStep(SimulationStatus arg0) throws SimulationException {
		update(arg0);
	}

	@Override
	public boolean preStep(SimulationStatus arg0) throws SimulationException {
		return true;
	}

	@Override
	public void startSimulation(SimulationStatus arg0) throws SimulationException {
		//Update velocity, acceleration, and gravity.
		update(arg0);
	}
	
	/**
	 * Update the IMU's velocity and acceleartion from a SimulationStatus.
	 * @param arg0 the SimulationStatus to update with
	 */
	private void updateVelocityAndAcceleration(SimulationStatus arg0) {
		Coordinate c = arg0.getRocketVelocity();
		Movement newVelocity = new Movement(c.x, c.y, c.z, Movement.UNIT_DISTANCE_METER, Movement.UNIT_TIME_SECOND);
		
		if(velocity!=null) {
			acceleration = Movement.averageChangeOf(newVelocity, velocity, 1.0, Movement.UNIT_TIME_SECOND);
		}
	}
	
	private void updateGravity(SimulationStatus arg0){
		//Get the value of the gravity at the current position
		float gravityAmount = (float) arg0.getSimulationConditions().getGravityModel().getGravity(arg0.getRocketWorldPosition());
		//Create a Coordinate with the gravity
		Coordinate c = new Coordinate(0,0,-gravityAmount);
		//Rotate the Coordinate with the gravity
		Coordinate r = arg0.getRocketOrientationQuaternion().rotate(c);
		//Set the gravity to the value of that coordinate
		Vector3f rv = new Vector3f(r);
		gravity = new Movement(rv, Movement.UNIT_DISTANCE_METER, Movement.UNIT_TIME_SECOND, 2);
	}
	
	/**
	 * Updates the simulated gravity, velocity, acceleration, and initialized values.
	 * @param arg0
	 */
	private void update(SimulationStatus arg0) {
		updateVelocityAndAcceleration(arg0);
		updateGravity(arg0);
		fireAllStateChangedListeners();
		initialized=true;
	}
	
	
	/**--------------Listeners-----------------**/
	List<StateChangedListener> listeners = new ArrayList<StateChangedListener>();
	/**
	 * A listener interface that contains a method <code>OpenRocketIMUStateChanged()</code> that 
	 * will be called each time the state of the rocket has been changed.
	 * @author Micah Mundy
	 */
	public interface StateChangedListener{
		/**
		 * Override this method with any code that must be run when the state of an IMU
		 * has changed.
		 * @param imu the IMU whose state has changed
		 */
		public void OpenRocketIMUStateChanged(OpenRocketIMU imu);
	}
	/**
	 * Adds a listener to this OpenRocketIMU
	 * @param listener
	 */
	public void addStateChangedListener(StateChangedListener listener) {
		listeners.add(listener);
	}
	/**
	 * Calls the method <code>OpenRocketIMUStateChanged()</code> in all registered listeners
	 */
	private void fireAllStateChangedListeners() {
		for(StateChangedListener listener: listeners) {
			if(listener!=null) listener.OpenRocketIMUStateChanged(this);
		}
	}
}
