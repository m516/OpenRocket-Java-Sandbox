/**
 * 
 */
package sandbox.processing;

import processing.core.PApplet;

/**
 * @author Micah Mundy
 *
 */
public class HelloProcessing extends PApplet {
	
    // The argument passed to main must match the class name
    public static void main(String[] args) {
        PApplet.main("sandbox.processing.HelloProcessing");
    }
	
	@Override
	public void settings() {
		size(640,480);
	}
	
	@Override
	public void draw() {
		background(0);
		fill(255,255,255);
		rect(100,100,200,200);
	}
}
