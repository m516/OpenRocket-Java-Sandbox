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

		OpenRocketDocument ord;
		
		File f = new File("p.ork");
		
		if(f.exists()) {
			System.out.println("File exists!");
		}
		else {
			System.err.println("File doesn't exist!");
		}
		
		System.out.println(f.getAbsolutePath());
		
		//Load modules
		GuiModule guiModule = new GuiModule();
		Module pluginModule = new PluginModule();
		Injector injector = Guice.createInjector(guiModule, pluginModule);
		Application.setInjector(injector);
		guiModule.startLoader();
		
		
		final GeneralRocketLoader rl = new GeneralRocketLoader(f);
		
		ord = rl.load();
		
		System.out.println("File loaded");
		
		System.out.println(ord.getRocket().toDebugString());
		
	}
	
	
	
	Rocket robert;
}
