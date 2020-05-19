/**
 * 
 */
package sandbox.airbrakes;

/**
 * An AirbrakesParser that sends bogus data and prints all messages to System.out
 * @author Micah Mundy
 */
public class DummyAirbrakesParser extends AirbrakesParser {

	/**
	 * Creates a new DummyAirbrakesParser instance from an AirbrakesSocketConnection
	 * @param asc the AirbrakesSocketConnection to listen to
	 */
	public DummyAirbrakesParser(AirbrakesSocketConnection asc) {
		super(asc);
	}

	/**
	 * 
	 */
	public DummyAirbrakesParser() {
		super();
	}
	


	@Override
	protected void actuateAirbrakes() {
		System.out.println("code_command_actuate");
	}

	@Override
	protected void deactuateAirbrakes() {
		System.out.println("code_command_deactuate");
	}

	@Override
	protected void airbrakesFinished() {
		System.out.println("code_command_finished");
	}

	@Override
	protected void sendTimeResponse() {
		System.out.println("code_request_data_time");
		byte[] data = {66,-13,2,84};
		currentAirbrakesSocketConnection.sendMessage(data);
	}

	@Override
	protected void sendMPL115A2Response(byte requestedOpCodeData) {
		System.out.println("code_request_data_MPL115A2");
		byte[] data = {0,0,0,-57,3};
		currentAirbrakesSocketConnection.sendMessage(data);
	}

	@Override
	protected void sendBNO055ResponseA(byte requestedOpCodeData) {
		System.out.println("code_request_data_BNO055_addr_A");
		byte[] data = {-96,99,0,0,0};
		currentAirbrakesSocketConnection.sendMessage(data);
	}

	@Override
	protected void sendBNO055ResponseB(byte requestedOpCodeData) {
		System.out.println("code_request_data_BNO055_addr_B");
	}
}
