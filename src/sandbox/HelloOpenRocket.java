/**
 * Opens up a new OpenRocket simulation window
 */
package sandbox;
import java.io.File;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;

import net.sf.openrocket.document.OpenRocketDocument;
import net.sf.openrocket.file.GeneralRocketLoader;
import net.sf.openrocket.plugin.PluginModule;
import net.sf.openrocket.rocketcomponent.Rocket;
import net.sf.openrocket.startup.Application;
import net.sf.openrocket.startup.GuiModule;


public class HelloOpenRocket {
	
	
	public static void main(String args[]) throws Exception {
		
		//Get the Open Rocket file
		File f = new File("p.ork");
		
		//Check if it exists
		if(f.exists()) {
			System.out.println("File exists!");
		}
		else {
			System.err.println("File doesn't exist!");
			return;
		}
		
		//Print its path for debugging purposes
		System.out.println(f.getAbsolutePath());
		
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
		System.out.println("File loaded");
		
		//Load the rocket
		Rocket rocket = ord.getRocket();
		System.out.println(rocket.toDebugString());
		
		//Check for simulations
		if(ord.getSimulationCount()==0) {
			System.out.println("Rocket has no simulations. Nothing to do.");
			return;
		}
		
		//Run the simulation
		PrintingListener pl = new PrintingListener();
		for(int i = 0; i < 25; i ++) {
			ord.getSimulation(0).simulate(pl);
		}
	}
}
