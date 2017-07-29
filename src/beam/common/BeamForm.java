/*
 * This class will have to handle implementing the array algorithim with several different objects
 */

package beam.common;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import info.monitorenter.gui.chart.Chart2D;

public class BeamForm {
	
	public static String filePath = "/Users/barthinator/Desktop/Fc100KHz_2MSPS_1KHzModulation_June12_2017.wav";
	
	public static void main(String args[]){
		BeamObject b = new BeamObject(filePath, 103030, 103000, new Complex(0,0));
		b.callSDR();
	}
}
