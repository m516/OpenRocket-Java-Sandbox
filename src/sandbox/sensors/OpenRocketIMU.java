/**
 * 
 */
package sandbox.sensors;

import net.sf.openrocket.simulation.SimulationStatus;
import net.sf.openrocket.simulation.exception.SimulationException;
import net.sf.openrocket.simulation.listeners.SimulationListener;
import sensors.AccelerationSensor;
import sensors.GravitySensor;
import util.Movement;

/**
 * @author Micah Mundy
 *
 */
public class OpenRocketIMU implements SimulationListener, GravitySensor, AccelerationSensor {
	
	Movement acceleration, gravity;
	Movement velocity;

	/**
	 * 
	 */
	public OpenRocketIMU() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Movement getAcceleration() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Movement getGravity() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SimulationListener clone() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void endSimulation(SimulationStatus arg0, SimulationException arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isSystemListener() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void postStep(SimulationStatus arg0) throws SimulationException {
		Movement newVelocity = new Movement(arg0.getRocketVelocity().x, 
				arg0.getRocketVelocity().y, 
				arg0.getRocketVelocity().z, 
				Movement.UNIT_DISTANCE_METER, 
				Movement.UNIT_TIME_SECOND);
		if(velocity!=null) {
			acceleration = Movement.averageChangeOf(newVelocity, velocity, 1.0, Movement.UNIT_TIME_SECOND); //TODO get delta time between two simulation steps
		}
		
	}

	@Override
	public boolean preStep(SimulationStatus arg0) throws SimulationException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void startSimulation(SimulationStatus arg0) throws SimulationException {
		// TODO Auto-generated method stub

	}

}
