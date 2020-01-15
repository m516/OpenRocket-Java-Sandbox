/**
 * 
 */
package sandbox;

import net.sf.openrocket.simulation.SimulationStatus;
import net.sf.openrocket.simulation.exception.SimulationException;
import net.sf.openrocket.simulation.listeners.SimulationListener;

/**
 * @author Beta
 *
 */
public class PrintingListener implements SimulationListener {

	/**
	 * 
	 */
	public PrintingListener() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public SimulationListener clone() {
		// TODO Auto-generated method stub
		return new PrintingListener();
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
		// TODO Auto-generated method stub

	}

	@Override
	public boolean preStep(SimulationStatus arg0) throws SimulationException {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void startSimulation(SimulationStatus arg0) throws SimulationException {
		// TODO Auto-generated method stub

	}

}
