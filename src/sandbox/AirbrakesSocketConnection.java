package sandbox;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.ProcessBuilder.Redirect;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AirbrakesSocketConnection {
	private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);


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

	private static class AirbrakesServerThread extends Thread{
		private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

		/**
		 * The server socket
		 */
		ServerSocket ss;
		
		int port = 8080;
		
		volatile boolean isRunning = false;

		@Override public void run() {
			
			isRunning = true;

			LOGGER.log(Level.INFO, "Initializing socket");
			try {
				LOGGER.log(Level.FINE, "Connecting to localhost, port 8090");
				ss = new ServerSocket(port);
				Socket s = ss.accept();
				InputStream dis = s.getInputStream();
				Scanner bis = new Scanner(dis);
				while(s.isConnected()) {
					System.out.println(dis.read());
				}

				LOGGER.log(Level.INFO, "No more data from InputStream. Closing socket and exiting");
				ss.close();

			} catch (IOException e) {
				LOGGER.log(Level.SEVERE, "Connection experienced an IOException");
				e.printStackTrace();
			}

			LOGGER.log(Level.INFO, "Socket done");
			
			isRunning = false;
		}
		
		void setPort(int newPort) {
			if(isRunning) {
				throw new IllegalStateException("Server is already running");
			}
			
			port = newPort;
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
		LOGGER.log(Level.FINER, "Beginning connection procedures");
		AirbrakesServerThread ast = new AirbrakesServerThread();
		String port = PropertyManager.GetProperty("airbrakes_server_port");
		LOGGER.log(Level.FINEST, "Running server on the following port: "+port);
		ast.setPort(Integer.parseUnsignedInt(port));
		LOGGER.log(Level.FINEST, "Beginning execution thread");
		ast.start();
		LOGGER.log(Level.FINER, "Beginning execution thread");
	}

	public void initialize() {
		runExecutable();
		initializeSocket();
	}

}
