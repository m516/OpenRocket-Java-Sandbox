/**
 * 
 */
package sandbox;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Create a TCP connection to the airbrake code
 * @author Micah Mundy
 *
 */
public class HelloAirbrakes {
	private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
	/**
	 *	Creates a new HelloAirbrakes() instance
	 */
	public HelloAirbrakes() {
		System.out.println("Hello there!");
		LOGGER.log(Level.INFO, "Log away!");
		PropertyManager.initialize();
		AirbrakesSocketConnection as = new AirbrakesSocketConnection();
		as.initialize();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		HelloAirbrakes helloAirbrakes = new HelloAirbrakes();
	}
	
	

}
