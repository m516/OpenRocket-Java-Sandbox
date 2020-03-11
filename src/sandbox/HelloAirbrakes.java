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
public class HelloAirbrakes implements AirbrakesSocketConnection.MessageListener {
	private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
	/**
	 *	Creates a new HelloAirbrakes() instance
	 */
	public HelloAirbrakes() {
		System.out.println("Hello there!");
		LOGGER.log(Level.INFO, "Log away!");
		AirbrakesSocketConnection as = new AirbrakesSocketConnection();
		as.addMessageListener(this);
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
	
	/**
	 * What happens when the airbrakes code sends a message
	 * to this program
	 */
	@Override
	public void messageReceived(byte[] bytes) {
		for (int i = 0; i < bytes.length; i++) {
			System.out.print(bytes[i]);
			System.out.print(" ");
		}
		System.out.println();

		AirbrakesParser.parseCommand(bytes);
	}
}
