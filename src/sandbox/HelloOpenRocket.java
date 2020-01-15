/**
 * Opens up a new OpenRocket simulation window
 */
package sandbox;


public class HelloOpenRocket {


	public static void main(String args[]) throws Exception {
		HelloOpenRocket d = new HelloOpenRocket();
		d.startup(args);
	}

	private void startup(String args[]) throws Exception {
		net.sf.openrocket.startup.OpenRocket.main(args);
	}
}
