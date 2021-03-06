package sandbox.airbrakes;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.ProcessBuilder.Redirect;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import sandbox.PropertyManager;


public class AirbrakesSocketConnection {

	//Fields
	/**
	 * The logging instance
	 */
	private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	/**
	 * The list of listeners for the output of the airbrakes
	 */
	private ArrayList<MessageListener> listeners = new ArrayList<AirbrakesSocketConnection.MessageListener>();

	/** The current execution thread **/
	private ExecutionThread currentExecutionThread;
	/** The current execution thread **/
	private AirbrakesServerThread currentAirbrakesServerThread;


	/**
	 * A constructor for AirbrakesSocketConnection
	 */
	public AirbrakesSocketConnection() {
		//Do nothing so far
	}

	/**
	 * A thread that executes a given file
	 * @author Micah Mundy
	 *
	 */
	private static class ExecutionThread extends Thread{

		private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

		/**
		 * The name of the file to run, stored locally in this class
		 */
		private String executableFilename;

		/**
		 * A constructor for a new ExecutionThread. Note that after an
		 * ExecutionThread has been initialized, a program must call the 
		 * thread's start() method for the program to start running
		 * 
		 * @param executableFilename the filename of the executable to run
		 */
		@SuppressWarnings("unused")
		public ExecutionThread(String executableFilename) {
			super();
			this.executableFilename = executableFilename;
		}

		/**
		 * A constructor for a new ExecutionThread. Note that after an
		 * ExecutionThread has been initialized, a program must call the 
		 * thread's start() method for the program to start running <br>
		 * 
		 * <b>Warning:</b> Starting a thread immediately after using this
		 * contructor will cause an IllegalStateException because the 
		 * ExecutionThread doesn't know what program to execute. Make sure
		 * to explicitly configure the thread with 
		 * <code>setExecutableFilename(String newFileName)</code> or by 
		 * adding the filename as a parameter to the constructor.
		 * 
		 * @param executableFilename the filename of the executable to run
		 */
		public ExecutionThread() {
			super();
		}


		/**
		 * Sets the file for this ExecutionThread to run.
		 * @param newFileName the executable's filename
		 */
		public void setExecutableFilename(String newFileName) {
			executableFilename = newFileName;
		}


		/**
		 * The main body of the thread
		 */
		@Override
		public void run() {

			//Check for a filename
			if(executableFilename == null) {
				throw new IllegalStateException("Airbrakes execution thread is not properly configured. It looks like you forgot to give it a reference to an executable file.");
			}


			//Create a ProcessBuilder for the executable
			ProcessBuilder pb = new ProcessBuilder();
			LOGGER.log(Level.INFO, "Starting EXE");
			LOGGER.log(Level.FINER, "Executable path: "+executableFilename);
			pb.command(executableFilename);   
			pb.redirectOutput(Redirect.INHERIT);

			//Run it and grab its output
			try {
				//Start the process
				Process p = pb.start();
				LOGGER.log(Level.FINER, "EXE has been started");
				//Grab its exit code
				int exitCode = p.waitFor();
				//If it's 0, it exited successfully
				if(exitCode==0) {
					LOGGER.log(Level.FINE, "Airbrakes ended without problems");
				}
				//If it's non-zero, show the exit code and a warning message
				else {
					LOGGER.log(Level.WARNING, "Airbrakes encountered a problem and exited");
					LOGGER.log(Level.WARNING, "Exit code: "+exitCode);
				}
			} 
			//In case anything failed
			catch (IOException e) {
				LOGGER.log(Level.SEVERE, "Airbrakes Executable failed to start!");
				e.printStackTrace();
			} catch (InterruptedException e) {
				LOGGER.log(Level.SEVERE, "Airbrakes Executable has been interrupted!");
				e.printStackTrace();
			}
			//Finish logging stuff
			LOGGER.log(Level.INFO, "End of Airbrakes execution");
		}
	}

	/**
	 * An interface that enables reads in real time from the Airbrakes code.
	 * Output will come from a separate thread, so be sure that the classes and
	 * code implementing this interface are thread-safe.
	 * 
	 * @author Micah Mundy
	 */
	public interface MessageListener{
		public abstract void messageReceived(byte[] bytes);
	}

	/**
	 * A thread that reads from and writes data to the Airbrakes code
	 * @author Micah Mundy
	 */
	private class AirbrakesServerThread extends Thread{

		/**
		 * The server socket
		 */
		ServerSocket ss;

		int port = -1;

		//Used for sanity checks
		volatile boolean isRunning = false;

		//Output stream for writing bytes to the airbrakes code
		private OutputStream os;

		/**
		 * The main body of the thread
		 */
		@Override public void run() {
			//Disable configuration changes and stuff like that
			isRunning = true;

			//Check that the port has been set
			if(port<0) {
				LOGGER.log(Level.WARNING, "Port not set for server. Defaulting to 8080");
				port = 8080;
			}

			LOGGER.log(Level.INFO, "Initializing socket");


			try {
				//Create a new ServerSocket with a certain port
				LOGGER.log(Level.FINE, "Creating a server at port " + port);
				ss = new ServerSocket(port);

				//Accept the first request to connect to the port
				Socket s = ss.accept();

				//Create a new ServerSocket with a certain port
				LOGGER.log(Level.INFO, "Socket connected at port " + port);

				//Create a buffer for bytes
				ArrayList<Byte> bytes = new ArrayList<Byte>();
				InputStream dis = s.getInputStream();
				
				//Create the output stream
				os = s.getOutputStream();


				//While the socket is connected
				while(s.isConnected()) {
					//Read data from it
					int data = dis.read();

					//End of stream
					if(data==-1) {
						LOGGER.log(Level.WARNING, "Got a strange value for data");
						break;
					}
					//End of string
					if(data==0) {
						//Turn the bytes from the ArrayList into a byte array
						byte[] message = new byte[bytes.size()];
						for(int i = 0; i < bytes.size(); i++) {
							message[i]=bytes.get(i).byteValue();
						}
						//Clear the list of bytes
						bytes.clear();

						//Spam all the listeners with the message received
						for(MessageListener ol: listeners) {
							ol.messageReceived(message);
						}

						//Don't do anything else in this loop
						continue;
					}


					//If the new byte is not 0 or -1, add it to the list of bytes in the bytes ArrayList
					bytes.add(Byte.valueOf((byte)data));
				}

				//At this point, the socket is closed or there's no more data to read. Close the server.
				LOGGER.log(Level.INFO, "Socket disconnected. Closing server and exiting");
				ss.close();

			} catch (IOException e) {
				LOGGER.log(Level.SEVERE, "Connection experienced an IOException");
				System.out.println("Perhaps you are running two instances of the Airbrakes code?");
				e.printStackTrace();
			}

			//Finish things up
			LOGGER.log(Level.INFO, "Socket done");
			isRunning = false;
		}

		/**
		 * Sets the port on which the server listens. Note that this value must 
		 * be set before running the server or an <code>IllegalStateException</code>
		 * will be thrown.
		 * 
		 * @param newPort the port to listen to.
		 */
		void setPort(int newPort) {
			if(isRunning) {
				throw new IllegalStateException("Server is already running");
			}

			port = newPort;
		}
	}

	/**
	 * Runs the executable whose location is specified under the "airbrakes_exe" key
	 * of the Properties file
	 */
	private void runExecutable() {
		LOGGER.log(Level.FINER, "Beginning execution procedures");
		currentExecutionThread = new ExecutionThread();
		String command = PropertyManager.getProperty("airbrakes_exe");
		LOGGER.log(Level.FINEST, "Running the following command: "+command);
		currentExecutionThread.setExecutableFilename(command);
		LOGGER.log(Level.FINEST, "Beginning execution thread");
		currentExecutionThread.start();
		LOGGER.log(Level.FINER, "Beginning execution thread");
	}

	/**
	 * Initializes the socket connection to the executable that was run 
	 * by creating a SocketServer for the port specified under the 
	 * "airbrakes_server_port" key. Accepts a connection to that port.
	 */
	private void initializeSocket() {
		//Create an AirbrakesServerThread
		LOGGER.log(Level.FINER, "Beginning connection procedures");
		currentAirbrakesServerThread = new AirbrakesServerThread();

		//Set its port to airbrakes_server_port
		String port = PropertyManager.getProperty("airbrakes_server_port");
		LOGGER.log(Level.FINEST, "Running server on the following port: "+port);
		currentAirbrakesServerThread.setPort(Integer.parseUnsignedInt(port));

		//Start it
		LOGGER.log(Level.FINER, "Beginning execution thread");
		currentAirbrakesServerThread.start();
		LOGGER.log(Level.FINEST, "Execution thread has begun");
	}

	/**
	 * A nifty method that begins all procedures for starting and maintaining 
	 * the executable and its connection. These are done in separate threads so
	 * the program doesn't hang 
	 */
	public void start() {
		runExecutable();
		initializeSocket();
	}

	/*
	 * Causes an OutputListener to listen to the output from the Airbrakes code.
	 */
	public void addMessageListener(MessageListener newOutputListener) {
		listeners.add(newOutputListener);
	}
	
	
	/**
	 * Send a message to the airbrakes code
	 * @param bytes the list of bytes to send
	 */
	public void sendMessage(byte[] bytes) {
		
		if(currentAirbrakesServerThread == null) 
			throw new IllegalStateException("There is no AirbrakesServerThread");
		if(!currentAirbrakesServerThread.isRunning) 
			throw new IllegalStateException("AirbrakesServerThread exists, but is not running");
		
		LOGGER.log(Level.INFO, "Sending a message: " + String.valueOf(bytes));		
		
		try {
			currentAirbrakesServerThread.os.write(bytes);
			currentAirbrakesServerThread.os.flush();

			LOGGER.log(Level.INFO, "Message sent successfully: " + String.valueOf(bytes));	
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "Error writing a message: "+String.valueOf(bytes));
			e.printStackTrace();
		}
	}
}
