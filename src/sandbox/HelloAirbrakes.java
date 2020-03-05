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
public class HelloAirbrakes implements AirbrakesSocketConnection.OutputListener {
	private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
	/**
	 *	Creates a new HelloAirbrakes() instance
	 */
	public HelloAirbrakes() {
		System.out.println("Hello there!");
		LOGGER.log(Level.INFO, "Log away!");
		AirbrakesSocketConnection as = new AirbrakesSocketConnection();
		as.addOutputListener(this);
		as.start();
	}

	/**
	 * Main method for testing airbrakes
	 * @param args
	 */
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		HelloAirbrakes helloAirbrakes = new HelloAirbrakes();
	}

	@Override
	public void outputReceived(byte[] bytes) {
		for (int i = 0; i < bytes.length; i++) {
			System.out.print(bytes[i]);
			System.out.print(" ");
		}
		System.out.println();

		AirbrakesParser.parseCommand(bytes);
	}
}
