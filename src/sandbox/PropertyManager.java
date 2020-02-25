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
	private static final Logger LOGGER = Logger.getLogger( PropertyManager.class.getName() );

	/** 
	 * 
	*/
	private File propertiesFile;
	private Properties properties;

	private PropertyManager() {
		readProperties();
	}
	
	/**
	 * Reads user-configured properties
	 */
	private void readProperties() {
		LOGGER.log(Level.INFO, "Reading properties");
		propertiesFile = new File("data/properties.txt");
		properties = new Properties();
		try {
			InputStream propertiesInputStream = new FileInputStream(propertiesFile);
			properties.load(propertiesInputStream);
		} catch (FileNotFoundException e) {
			System.err.println("Cannot find properties file. Creating a new one");
			try {
				PrintWriter pw = new PrintWriter(new FileWriter(propertiesFile.getCanonicalPath()));
				properties.put("airbrakes_exe","data/airbrakes_code/airbrakes_sim.exe");
				properties.store(pw, "Generated properties file for sandbox ");
			} catch (IOException e1) {
				System.err.println("Couldn't write the properties file! Aborting.\n\nStack trace:");
				e1.printStackTrace();
				return;
			}
		} catch (IOException e) {
			System.err.println("Couldn't write the properties file! Aborting.\n\nStack trace:");
			e.printStackTrace();
			return;
		}
		System.out.print("Airbrakes executable: ");
		System.out.println(properties.getProperty("airbrakes_exe"));
	}
	
	

}
