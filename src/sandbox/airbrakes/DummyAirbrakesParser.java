/**
 * 
 */
package sandbox.airbrakes;

/**
 * @author Beta
 *
 */
public class DummyAirbrakesParser extends AirbrakesParser {

	/**
	 * @param asc
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
		byte[] response = {66,-13,2,84};
		currentAirbrakesSocketConnection.sendMessage(response);
	}

	@Override
	protected void sendMPL115A2Response() {
		System.out.println("code_request_data_MPL115A2");
		byte[] response = {0,0,0,-57,3};
		currentAirbrakesSocketConnection.sendMessage(response);
	}

	@Override
	protected void sendBNO055ResponseA() {
		System.out.println("code_request_data_BNO055_addr_A");
		byte[] response = {-96,99,0,0,0};
		currentAirbrakesSocketConnection.sendMessage(response);
	}

	@Override
	protected void sendBNO055ResponseB() {
		System.out.println("code_request_data_BNO055_addr_B");
	}

}
