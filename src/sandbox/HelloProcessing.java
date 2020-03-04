/**
 * 
 */
package sandbox;

import processing.core.PApplet;

/**
 * @author Beta
 *
 */
public class HelloProcessing extends PApplet {
	
    // The argument passed to main must match the class name
    public static void main(String[] args) {
        PApplet.main("HelloProcessing");
    }
	
	@Override
	public void setup() {
		size(640,480);
	}
	
	public void draw() {
		background(0);
		fill(255,255,255);
		rect(100,100,200,200);
	}
}
