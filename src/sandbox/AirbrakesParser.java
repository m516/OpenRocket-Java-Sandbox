package sandbox;

public abstract class AirbrakesParser implements AirbrakesSocketConnection.MessageListener{
		
	private byte code_request_data_MPL115A2,
			code_request_data_BNO055_addr_A,
			code_request_data_BNO055_addr_B,
			code_request_data_time,
			code_command_actuate,
			code_command_deactuate,
			code_command_done,
			code_command_finished;
	
	private AirbrakesSocketConnection currentAirbrakesSocketConnection;

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
		if(opCode == code_request_data_MPL115A2) {
			sendMPL115A2Response();
		}
		if(opCode == code_request_data_BNO055_addr_A) {
			sendBNO055ResponseA();
		}
		if(opCode == code_request_data_BNO055_addr_B) {
			sendBNO055ResponseB();
		}
		if(opCode == code_request_data_time) {
			sendTimeResponse();
		}         
		if(opCode == code_command_actuate) {
			actuateAirbrakes();
		}           
		if(opCode == code_command_deactuate) {
			deactuateAirbrakes();
		}         
		if(opCode == code_command_done) {
			airbrakesFinished();
		}              
		if(opCode == code_command_finished) {
			airbrakesFinished();
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
