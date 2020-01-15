/**
 * 
 */
package sandbox;

import net.sf.openrocket.simulation.SimulationStatus;
import net.sf.openrocket.simulation.exception.SimulationCancelledException;
import net.sf.openrocket.simulation.exception.SimulationException;
import net.sf.openrocket.simulation.listeners.SimulationListener;

/**
 * @author Beta
 *
 */
public class PrintingListener implements SimulationListener {
	static double totalApogee = 0.0;
	static int numSimulations = 0;
	
	/**
	 * 
	 */
	public PrintingListener() {
		// Do nothing for now
	}

	@Override
	public SimulationListener clone() {
		// TODO Auto-generated method stub
		return new PrintingListener();
	}

	@Override
	public void endSimulation(SimulationStatus arg0, SimulationException arg1) {
		if(arg1 != null) {
			System.out.println(arg1.getMessage());
		}
	}

	@Override
	public boolean isSystemListener() {
		//I don't think I'm a system listener
		return false;
	}

	@Override
	public void postStep(SimulationStatus arg0) throws SimulationException {
		// Stop the simulation if the apogee has been reached
		if(arg0.isApogeeReached()) {
			//Add to the number of simulations
			numSimulations++;
			//Add to the total apogee
			totalApogee += arg0.getMaxAlt();
			//Get the average apogee
			double averageApogee = totalApogee/((double) numSimulations);
			//Print it to the console
			StringBuilder sb = new StringBuilder();
			sb.append("After simulation ");
			sb.append(numSimulations);
			sb.append(": ");
			sb.append("average apogee altitude is ");
			sb.append(averageApogee);
			System.out.println(sb.toString());
			
			//Halt the simulation
			throw new SimulationCancelledException();
		}
	}

	@Override
	public boolean preStep(SimulationStatus arg0) throws SimulationException {
		//Do nothing. Assume we're good!
		return true;
		
	}

	@Override
	public void startSimulation(SimulationStatus arg0) throws SimulationException {
		System.out.println("Starting simulation");
	}

}
