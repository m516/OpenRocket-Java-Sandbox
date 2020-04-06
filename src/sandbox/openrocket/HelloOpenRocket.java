/**
 * Opens up a new OpenRocket simulation window
 */
package sandbox.openrocket;
import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;

import net.sf.openrocket.document.OpenRocketDocument;
import net.sf.openrocket.document.Simulation;
import net.sf.openrocket.file.GeneralRocketLoader;
import net.sf.openrocket.logging.LoggingSystemSetup;
import net.sf.openrocket.plugin.PluginModule;
import net.sf.openrocket.rocketcomponent.Rocket;
import net.sf.openrocket.startup.Application;
import net.sf.openrocket.startup.GuiModule;


public class HelloOpenRocket {
	
	private static final Logger logger = LoggerFactory.getLogger(HelloOpenRocket.class);
	
	public static void main(String args[]) throws Exception {
		
		//Set up the logging
		LoggingSystemSetup.setupLoggingAppender();
		LoggingSystemSetup.addConsoleAppender();
		
		//Get the Open Rocket file
		File f = new File("data/rockets/Invictus.ork");
		
		//Check if it exists
		if(f.exists()) {
			logger.info("File exists!");
		}
		else {
			logger.error("File doesn't exist!");
			return;
		}
		
		//Print its path for debugging purposes
		logger.info(f.getAbsolutePath());
		
		//Load OpenRocket modules
		GuiModule guiModule = new GuiModule();
		Module pluginModule = new PluginModule();
		Injector injector = Guice.createInjector(guiModule, pluginModule);
		Application.setInjector(injector);
		guiModule.startLoader();
		
		//Create an instance of the GeneralRocketLoader, which loads a 
		//variety of rocket types from OpenRocket and RocketSim
		final GeneralRocketLoader rl = new GeneralRocketLoader(f);
		
		//Load the rocket into a new OpenRocketDocument instance
		OpenRocketDocument ord;
		ord = rl.load();
		logger.info("File loaded");
		
		//Load the rocket
		Rocket rocket = ord.getRocket();
		logger.info(rocket.toDebugString());
		
		//Check for simulations
		if(ord.getSimulationCount()==0) {
			logger.info("Rocket has no simulations. Nothing to do.");
			return;
		}
		
		//Run the simulation
		//Initialize the simulation
		Simulation sim = ord.getSimulation(0);
		//Initialize the SimulationListener
		PrintingListener pl = new PrintingListener();
		//Simulate 25 times
		sim.simulate(pl);
		
		
	}
}
