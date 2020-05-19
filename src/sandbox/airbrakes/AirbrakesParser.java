package sandbox.airbrakes;

import java.util.logging.Level;
import java.util.logging.Logger;

import sandbox.PropertyManager;

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
	 * Parses a command from the airbrakes
	 * @param cmd a byte array of the command data to parse
	 */
	private void parseCommand(byte[] cmd) {
		byte opCode = cmd[0];
		boolean registered = false;
		if(opCode == code_request_data_MPL115A2) {
			/*
			This means the airbrakes code is requesting a 
			value that the simulation contains.
			
			At the time this comment was written (5/18/2020),
			it will request the air pressure, a value that
			it would otherwise get from an MPL115A2 barometer.
			
			The structure of this request is bound by two bytes:
				* The first byte is code_request_data_MPL115A2
				* The second byte is the request op code described
				  	above. It will likely only request the air 
				  	pressure with the byte 
				  	MPL115A2_REGISTER_PRESSURE_MSB
			*/
			byte requestedDataOpCode = cmd[1];
			sendMPL115A2Response(requestedDataOpCode);
			registered = true;
		}
		if(opCode == code_request_data_BNO055_addr_A) {
			/*
			This means the airbrakes code is requesting a 
			value that the simulation contains.
			
			At the time this comment was written (5/18/2020),
			it will request either the chip address, the gravity 
			vector, or the linear acceleration vector that it 
			would otherwise get from a BNO055 six-axis IMU.
			
			The structure of this request is bound by two bytes:
				* The first byte is code_request_data_MPL115A2
				* The second byte is the request op code described
				     above.
			*/
			
			// Get the op code for the requested element of the IMU
			byte requestedDataOpCode = cmd[1];
			sendBNO055ResponseA(requestedDataOpCode);
			registered = true;
		}
		if(opCode == code_request_data_BNO055_addr_B) {	
			/*
			This means the airbrakes code is requesting a 
			value that the simulation contains.
			
			At the time this comment was written (5/18/2020),
			it will request either the chip address, the gravity 
			vector, or the linear acceleration vector that it 
			would otherwise get from a BNO055 six-axis IMU.
			
			The structure of this request is bound by two bytes:
				* The first byte is code_request_data_MPL115A2
				* The second byte is the request op code described
				     above.
			*/
			
			// Get the op code for the requested element of the IMU
			byte requestedDataOpCode = cmd[1];
			sendBNO055ResponseB(requestedDataOpCode);
			registered = true;
		}
		if(opCode == code_request_data_time) {
			/*
			This means that the airbrakes code is requesting the 
			current time of the simulation in milliseconds.
			
			There are no additional parameters.
			 */
			sendTimeResponse();
			registered = true;
		}         
		if(opCode == code_command_actuate) {
			/*
			 With this opcode, the airbrakes code informs 
			 the simulation that the airbrakes have been actuated.
			 
			 There are no additional parameters.
			 */
			actuateAirbrakes();
			registered = true;
		}           
		if(opCode == code_command_deactuate) {
			/*
			 With this opcode, the airbrakes code informs 
			 the simulation that the airbrakes have been deactuated.
			 
			 There are no additional parameters.
			 */
			deactuateAirbrakes();
			registered = true;
		}         
		if(opCode == code_command_done) {
			/*
			 With this opcode, the airbrakes code informs 
			 the simulation it has finished execution.
			 
			 There are no additional parameters.
			 */
			airbrakesFinished();
			registered = true;
		}              
		if(opCode == code_command_finished) {
			/*
			 With this opcode, the airbrakes code informs 
			 the simulation it has finished execution.
			 
			 There are no additional parameters.
			 */
			airbrakesFinished();
			registered = true;
		} 
		
		if(!registered) {
			LOGGER.log(Level.WARNING, "Recieved unregistered message type: " + String.valueOf((int)opCode));
		}
	}
	
	/**
	 * This method is executed when the airbrakes code has decided to
	 * deploy the airbrakes.
	 */
	protected abstract void actuateAirbrakes();
	
	/**
	 * This method is executed when the airbrakes code has decided to
	 * deactuate the airbrakes.
	 */
	protected abstract void deactuateAirbrakes();
	
	/**
	 * This method is executed when the airbrakes code has stopped
	 * executing.
	 */
	protected abstract void airbrakesFinished();
	
	/**
	 * Sends the current time of the simulation in milliseconds to the 
	 * airbrakes code. <p>
	 * 
	 * This value must be sent as a <b>little-endian signed short 
	 * (two bytes)</b> with the following method: <p>
	 * 
	 * <code>currentAirbrakesSocketConnection.sendMessage(data);</code> <p>
	 * 
	 * where <code>data</code> is the byte array of the response.
	 */
	protected abstract void sendTimeResponse();
	
	/**
	 * Sends data that would be requested from a physical MPL115A2 
	 * barometer. <p>
	 * 
	 * At the time this comment was written (5/18/2020), the airbrakes
	 * code will request the current air pressure and temperature, values that
	 * it would otherwise get from a real MPL115A2 barometer. In this
	 * case, the value of <code>requestedDataOpCode</code> will be 
	 * equal to the byte obtained by 
	 * <code>PropertyManager.getProperty("MPL115A2_REGISTER_PRESSURE_MSB")</code>. <p>
	 * 
	 * The airbrakes code is first expecting the pressure. It must be in Pascals, 
	 * rounded and divided by 5. <p>
	 * 
	 * Then it is expecting the temperature. The temperature value must be in 
	 * Celsius, multiplied by 100. <p>
	 * 
	 * Each value must be sent as a <b>little-endian signed short 
	 * (two bytes)</b> with the following method: <br>
	 * <code>currentAirbrakesSocketConnection.sendMessage(data);</code> <br>
	 * where <code>data</code> is the byte array of the response. <br>
	 */
	protected abstract void sendMPL115A2Response(byte requestedDataOpCode);
	
	/**
	 * Sends data that would be requested from a physical BNO055 six-axis IMU to the
	 * airbrakes code. <p>
	 * 
	 * At the time this comment was written (5/18/2020), the airbrakes
	 * code will request one of the following values from the BNO055:
	 *  <ul>
	 *    <li>a Cartesian vector of the gravity acting on the IMU,
	 *    which is mounted to the rocket (Units: meter per second squared)</li>
	 *    <li>a Cartesian vector of the IMU's acceleration,
	 *    which is mounted to the rocket (Units: meter per second squared)</li>
	 *    <li>the chip ID (equal to the byte value of BNO055_CHIP_ID_ADDR)</li>
	 *  </ul> 
	 *  
	 * The specific data requested by the airbrakes code from this simulated IMU
	 * is indicated by the first parameter (<code>requestedDataOpCode</code>): 
	 * <ul>
	 *    <li>If <code>requestedDataOpCode</code> is equal to 
	 *    <code>PropertyManager.getProperty("BNO055_CHIP_ID_ADDR")</code>,
	 *    then the airbrakes code is requesting the IMU's chip ID. </li>
	 *    <li>If <code>requestedDataOpCode</code> is equal to 
	 *    <code>PropertyManager.getProperty("BNO055_VECTOR_GRAVITY")</code>,
	 *    then the airbrakes code is requesting the IMU's gravity value. </li>
	 *    <li>If <code>requestedDataOpCode</code> is equal to 
	 *    <code>PropertyManager.getProperty("BNO055_VECTOR_LINEARACCEL")</code>,
	 *    then the airbrakes code is requesting the IMU's acceleration value. </li>
	 *  </ul> 
	 *  
	 * The IMU is oriented in the rocket in such a way that the <b>z-axis</b> is
	 * pointing up. 
	 * 
	 * Each vector must be sent as the x, y, and z values in that order.
	 * Each of these values must be sent as a <b>little-endian signed short 
	 * (two bytes)</b>.<p>
	 * 
	 * The chip ID must be sent as a single byte.
	 * 
	 * The entire response must be sent in an array of bytes 
	 * with the following method: <br>
	 * <code>currentAirbrakesSocketConnection.sendMessage(data);</code> <br>
	 * where <code>data</code> is the byte array of the response.
	 * 
	 * @param requestedDataOpCode the requested data op code
	 * @see sendBNO055ResponseB
	 */
	protected abstract void sendBNO055ResponseA(byte requestedDataOpCode);
	
	/**
	 * Sends data that would be requested from a physical BNO055 six-axis IMU to the
	 * airbrakes code. <p>
	 * 
	 * At the time this comment was written (5/18/2020), the airbrakes
	 * code will request one of the following values from the BNO055:
	 *  <ul>
	 *    <li>a Cartesian vector of the gravity acting on the IMU,
	 *    which is mounted to the rocket (Units: meter per second squared)</li>
	 *    <li>a Cartesian vector of the IMU's acceleration,
	 *    which is mounted to the rocket (Units: meter per second squared)</li>
	 *    <li>the chip ID (equal to the byte value of BNO055_CHIP_ID_ADDR)</li>
	 *  </ul> 
	 *  
	 * The specific data requested by the airbrakes code from this simulated IMU
	 * is indicated by the first parameter (<code>requestedDataOpCode</code>): 
	 * <ul>
	 *    <li>If <code>requestedDataOpCode</code> is equal to 
	 *    <code>PropertyManager.getProperty("BNO055_CHIP_ID_ADDR")</code>,
	 *    then the airbrakes code is requesting the IMU's chip ID. </li>
	 *    <li>If <code>requestedDataOpCode</code> is equal to 
	 *    <code>PropertyManager.getProperty("BNO055_VECTOR_GRAVITY")</code>,
	 *    then the airbrakes code is requesting the IMU's gravity value. </li>
	 *    <li>If <code>requestedDataOpCode</code> is equal to 
	 *    <code>PropertyManager.getProperty("BNO055_VECTOR_LINEARACCEL")</code>,
	 *    then the airbrakes code is requesting the IMU's acceleration value. </li>
	 *  </ul> 
	 *  
	 * The IMU is oriented in the rocket in such a way that the <b>z-axis</b> is
	 * pointing up. 
	 * 
	 * Each vector must be sent as the x, y, and z values in that order.
	 * Each of these values must be sent as a <b>little-endian signed short 
	 * (two bytes)</b>.<p>
	 * 
	 * The chip ID must be sent as a single byte.
	 * 
	 * The entire response must be sent in an array of bytes 
	 * with the following method: <br>
	 * <code>currentAirbrakesSocketConnection.sendMessage(data);</code> <br>
	 * where <code>data</code> is the byte array of the response.
	 * 
	 * @param requestedDataOpCode the requested data op code
	 * @see sendBNO055ResponseA
	 */
	protected abstract void sendBNO055ResponseB(byte requestedDataOpCode);

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
