package sandbox;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * A class that manages all the properties of this project in a cute little package
 */
public class PropertyManager {
	private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
	public static final String PROPERTIES_FILE_LOCATION = "data/properties.txt";
	
	private static PropertyManager thisInstance;
	
	//Fields specific to thisInstance
	private boolean initialized = false;
	private File propertiesFile;
	private Properties properties;

	private PropertyManager() {
		singletonInitialize();
	}
	
	private void singletonInitialize() {
		if(initialized) return;
		readProperties();
		initialized = true;
	}
	
	/**
	 * Reads user-configured properties
	 */
	private void readProperties() {
		LOGGER.log(Level.FINE, "Reading properties");
		propertiesFile = new File(PROPERTIES_FILE_LOCATION);
		properties = new Properties();
		try {
			InputStream propertiesInputStream = new FileInputStream(propertiesFile);
			properties.load(propertiesInputStream);
		} catch (FileNotFoundException e) {
			LOGGER.log(Level.WARNING,"Cannot find properties file. Creating a new one");
			try {
				PrintWriter pw = new PrintWriter(new FileWriter(propertiesFile.getCanonicalPath()));
				properties.put("airbrakes_exe","data/airbrakes_code/airbrakes_sim.exe");
				properties.store(pw, "Generated properties file for sandbox ");
			} catch (IOException e1) {
				LOGGER.log(Level.SEVERE,"Couldn't write the properties file! Aborting.\n\nStack trace:");
				e1.printStackTrace();
				return;
			}
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE,"Couldn't write the properties file! Aborting.\n\nStack trace:");
			e.printStackTrace();
			return;
		}
		LOGGER.log(Level.CONFIG,"Airbrakes executable: " + properties.getProperty("airbrakes_exe"));
	}
	
	/**
	 * Gets a property with a certain key. All initialization is done automagically
	 * @param key the key to search for
	 * @return the value associated with that key
	 */
	public static String getProperty(String key) {
		checkInitialization();
		
		String property = thisInstance.properties.getProperty(key);
		if(property == null) {
			LOGGER.log(Level.WARNING, "couldn't find any value for the key: "+key);
		}
		return property;
	}
	
	/**
	 * Checks if the PropertyManager has been initialized correctly
	 */
	private static void checkInitialization() {
		if(thisInstance==null) thisInstance = new PropertyManager();
		if(thisInstance.initialized == false) 
			throw new IllegalStateException("PropertyManager hasn't been properly configured. Is the value for PROPERTIES_FILE_LOCATION set correctly?");
	}
}
