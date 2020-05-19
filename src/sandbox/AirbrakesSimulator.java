/**
 * 
 */
package sandbox;

import java.util.logging.Logger;

import sandbox.airbrakes.AirbrakesParser;

/**
 * Create a TCP connection to the airbrake code
 * @author Micah Mundy
 *
 */
public class AirbrakesSimulator{
	@SuppressWarnings("unused")
	private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	AirbrakesParser airbrakesParser;
	
	/**
	 *	Creates a new HelloAirbrakes() instance
	 */
	public AirbrakesSimulator() {
		System.out.println("Initializing the simulation!");
		airbrakesParser = new OpenRocketAirbrakesController();
	}

	/**
	 * Main method for testing airbrakes
	 * @param args
	 */
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		AirbrakesSimulator helloAirbrakes = new AirbrakesSimulator();
	}
}
