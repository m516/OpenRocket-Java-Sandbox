package sandbox;

import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class AirbrakesParser implements AirbrakesSocketConnection.MessageListener{
	

	/**
	 * The logging instance
	 */
	protected static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
		
	private byte code_request_data_MPL115A2,
			code_request_data_BNO055_addr_A,
			code_request_data_BNO055_addr_B,
			code_request_data_time,
			code_command_actuate,
			code_command_deactuate,
			code_command_done,
			code_command_finished;
	
	protected AirbrakesSocketConnection currentAirbrakesSocketConnection;

	/**
	 * Creates an AirbrakesParser that binds to an existing socket connection
	 * @param asc
	 */
	public AirbrakesParser(AirbrakesSocketConnection asc) {
		loadCommands();
		currentAirbrakesSocketConnection = asc;
		currentAirbrakesSocketConnection.addMessageListener(this);
	}
	
	/**
	 * Creates an AirbrakesParser that binds to a new socket connection.
	 * That connection is automatically started
	 */
	public AirbrakesParser() {
		loadCommands();
		currentAirbrakesSocketConnection = new AirbrakesSocketConnection();
		currentAirbrakesSocketConnection.addMessageListener(this);
		currentAirbrakesSocketConnection.start();
	}
	
	/**
	 * Loads the commands
	 */
	private void loadCommands() {
		code_request_data_MPL115A2      = Byte.parseByte(PropertyManager.getProperty("airbrakes_request_data_MPL115A2"));
		code_request_data_BNO055_addr_A = Byte.parseByte(PropertyManager.getProperty("airbrakes_request_data_BNO055_addr_A"));
		code_request_data_BNO055_addr_B = Byte.parseByte(PropertyManager.getProperty("airbrakes_request_data_BNO055_addr_B"));
		code_request_data_time          = Byte.parseByte(PropertyManager.getProperty("airbrakes_request_data_time"));
		code_command_actuate            = Byte.parseByte(PropertyManager.getProperty("airbrakes_command_actuate"));
		code_command_deactuate          = Byte.parseByte(PropertyManager.getProperty("airbrakes_command_deactuate"));
		code_command_done               = Byte.parseByte(PropertyManager.getProperty("airbrakes_command_done"));
		code_command_finished           = Byte.parseByte(PropertyManager.getProperty("airbrakes_command_finished"));
	}

	/**
	 * Parses the command
	 * @param cmd
	 */
	private void parseCommand(byte[] cmd) {
		byte opCode = cmd[0];
		boolean registered = false;
		if(opCode == code_request_data_MPL115A2) {
			sendMPL115A2Response();
			registered = true;
		}
		if(opCode == code_request_data_BNO055_addr_A) {
			sendBNO055ResponseA();
			registered = true;
		}
		if(opCode == code_request_data_BNO055_addr_B) {
			sendBNO055ResponseB();
			registered = true;
		}
		if(opCode == code_request_data_time) {
			sendTimeResponse();
			registered = true;
		}         
		if(opCode == code_command_actuate) {
			actuateAirbrakes();
			registered = true;
		}           
		if(opCode == code_command_deactuate) {
			deactuateAirbrakes();
			registered = true;
		}         
		if(opCode == code_command_done) {
			airbrakesFinished();
			registered = true;
		}              
		if(opCode == code_command_finished) {
			airbrakesFinished();
			registered = true;
		} 
		
		if(!registered) {
			LOGGER.log(Level.WARNING, "Recieved unregistered message type!");
			LOGGER.log(Level.WARNING, String.valueOf(opCode));
			
		}
	}
	
	protected abstract void actuateAirbrakes();
	
	protected abstract void deactuateAirbrakes();
	
	protected abstract void airbrakesFinished();
	
	protected abstract void sendTimeResponse();
	
	protected abstract void sendMPL115A2Response();
	
	protected abstract void sendBNO055ResponseA();
	
	protected abstract void sendBNO055ResponseB();

	@Override
	public void messageReceived(byte[] bytes) {
		for (int i = 0; i < bytes.length; i++) {
			System.out.print(bytes[i]);
			System.out.print(" ");
		}
		System.out.println();

		parseCommand(bytes);
	}
	
	public final AirbrakesSocketConnection getAirbrakesSocketConnection() {
		return currentAirbrakesSocketConnection;
	}
}
