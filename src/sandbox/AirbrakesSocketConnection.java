package sandbox;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.ProcessBuilder.Redirect;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AirbrakesSocketConnection {
	private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
	Socket s;

	public AirbrakesSocketConnection() {
		//Do nothing so far
	}
	
	private static class ExecutionThread extends Thread{

		private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
		private String executableFilename;
		
		public ExecutionThread(String executableFilename) {
			super();
			this.executableFilename = executableFilename;
		}
		
		public ExecutionThread() {
			super();
		}
		
		public void setExecutableFilename(String newFileName) {
			executableFilename = newFileName;
		}
		
		@Override
		public void run() {
			
			//Check for a filename
			if(executableFilename == null) {
				throw new IllegalStateException("Airbrakes execution thread is not properly configured. It looks like you missed to give it a reference to an executable file.");
			}
			
			
	        ProcessBuilder pb = new ProcessBuilder();
	        LOGGER.log(Level.INFO, "Starting EXE");
	        LOGGER.log(Level.FINER, "Executable path: "+executableFilename);
	        pb.command(executableFilename);   
	        pb.redirectOutput(Redirect.INHERIT);
	        
	        try {
				Process p = pb.start();
		        LOGGER.log(Level.FINER, "EXE has been started");
		        int exitCode = p.waitFor();
		        if(exitCode==0) {
		        	LOGGER.log(Level.FINE, "Airbrakes ended without problems");
		        }
		        else {
		        	LOGGER.log(Level.WARNING, "Airbrakes encountered a problem and exited");
		        	LOGGER.log(Level.WARNING, "Exit code: "+exitCode);
		        }
			} catch (IOException e) {
				LOGGER.log(Level.SEVERE, "Airbrakes Executable failed to start!");
				e.printStackTrace();
			} catch (InterruptedException e) {
				LOGGER.log(Level.SEVERE, "Airbrakes Executable has been interrupted!");
				e.printStackTrace();
			}
	        
	        LOGGER.log(Level.INFO, "End of Airbrakes execution");
		}
	}
	
	public void runExecutable() {
		LOGGER.log(Level.FINER, "Beginning execution procedures");
		ExecutionThread et = new ExecutionThread();
		String command = PropertyManager.GetProperty("airbrakes_exe");
		LOGGER.log(Level.FINEST, "Running the following command: "+command);
		et.setExecutableFilename(command);
		LOGGER.log(Level.FINEST, "Beginning execution thread");
		et.start();
		LOGGER.log(Level.FINER, "Beginning execution thread");
	}
	
	private void initializeSocket() {
		LOGGER.log(Level.INFO, "Initializing socket");
		try {
			LOGGER.log(Level.FINE, "Connecting to localhost, port 8090");
			s = new Socket("127.0.0.1", 8090);
			InputStream dis = s.getInputStream();
			while(dis.available()!=0) {
				System.out.println(dis.read());
			}

			LOGGER.log(Level.INFO, "No more data from InputStream. Closing socket and exiting");
			s.close();
			
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "Connection experienced an IOException");
			e.printStackTrace();
		}

		LOGGER.log(Level.INFO, "Socket done");
	}
	
	public void initialize() {
		runExecutable();
		initializeSocket();
	}

}
