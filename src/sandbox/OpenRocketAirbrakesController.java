/**
 * 
 */
package sandbox;

import sandbox.airbrakes.AirbrakesParser;
import sandbox.airbrakes.AirbrakesSocketConnection;

/**
 * An AirbrakesParser that sends bogus data and prints all messages to System.out
 * @author Micah Mundy
 */
public class OpenRocketAirbrakesController extends AirbrakesParser {

	/**
	 * Creates a new DummyAirbrakesParser instance from an AirbrakesSocketConnection
	 * @param asc the AirbrakesSocketConnection to listen to
	 */
	public OpenRocketAirbrakesController(AirbrakesSocketConnection asc) {
		super(asc);
	}

	
	public OpenRocketAirbrakesController() {
		super();
	}
	

	@Override
	protected void actuateAirbrakes() {
		System.out.println("code_command_actuate");
		//TODO stub
	}

	@Override
	protected void deactuateAirbrakes() {
		System.out.println("code_command_deactuate");
		//TODO stub
	}

	@Override
	protected void airbrakesFinished() {
		System.out.println("code_command_finished");
		//TODO stub
	}

	@Override
	protected void sendTimeResponse() {
		System.out.println("code_request_data_time");
		//TODO stub
	}

	@Override
	protected void sendMPL115A2Response(byte requestedDataOpCode) {
		System.out.println("code_request_data_MPL115A2");
		//TODO stub
	}

	@Override
	protected void sendBNO055ResponseA(byte requestedDataOpCode) {
		System.out.println("code_request_data_BNO055_addr_A");
		//TODO stub
	}

	@Override
	protected void sendBNO055ResponseB(byte requestedDataOpCode) {
		System.out.println("code_request_data_BNO055_addr_B");
		//TODO stub
	}
}
