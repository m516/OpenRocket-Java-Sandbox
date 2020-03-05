package sandbox;

public class AirbrakesParser {
	
	@SuppressWarnings("unused")
	private static AirbrakesParser thisInstance = new AirbrakesParser();
	
	private static byte code_request_data_MPL115A2,
			code_request_data_BNO055_addr_A,
			code_request_data_BNO055_addr_B,
			code_request_data_time,
			code_command_actuate,
			code_command_deactuate,
			code_command_done,
			code_command_finished;

	private AirbrakesParser() {
		code_request_data_MPL115A2      = Byte.parseByte(PropertyManager.GetProperty("airbrakes_request_data_MPL115A2"));
		code_request_data_BNO055_addr_A = Byte.parseByte(PropertyManager.GetProperty("airbrakes_request_data_BNO055_addr_A"));
		code_request_data_BNO055_addr_B = Byte.parseByte(PropertyManager.GetProperty("airbrakes_request_data_BNO055_addr_B"));
		code_request_data_time          = Byte.parseByte(PropertyManager.GetProperty("airbrakes_request_data_time"));
		code_command_actuate            = Byte.parseByte(PropertyManager.GetProperty("airbrakes_command_actuate"));
		code_command_deactuate          = Byte.parseByte(PropertyManager.GetProperty("airbrakes_command_deactuate"));
		code_command_done               = Byte.parseByte(PropertyManager.GetProperty("airbrakes_command_done"));
		code_command_finished           = Byte.parseByte(PropertyManager.GetProperty("airbrakes_command_finished"));
	}

	
	public static void parseCommand(byte[] cmd) {
		byte opCode = cmd[0];
		if(opCode == code_request_data_MPL115A2) {
			System.out.println("code_request_data_MPL115A2");
		}
		if(opCode == code_request_data_BNO055_addr_A) {
			System.out.println("code_request_data_BNO055_addr_A");
		}
		if(opCode == code_request_data_BNO055_addr_B) {
			System.out.println("code_request_data_BNO055_addr_B");
		}
		if(opCode == code_request_data_time) {
			System.out.println("code_request_data_time");
		}         
		if(opCode == code_command_actuate) {
			System.out.println("code_command_actuate");
		}           
		if(opCode == code_command_deactuate) {
			System.out.println("code_command_deactuate");
		}         
		if(opCode == code_command_done) {
			System.out.println("code_command_done");
		}              
		if(opCode == code_command_finished) {
			System.out.println("code_command_finished");
		} 
	}
}
