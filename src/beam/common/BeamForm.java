/*
 * This class will have to handle implementing the array algorithim with several different objects
 */

package beam.common;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import info.monitorenter.gui.chart.Chart2D;

public class BeamForm {
	
	public static String filePath = "/Users/Justin/Desktop/Array-Based-Beam-Forming/Scripts/Fc100KHz_2MSPS_1KHzModulation_June12_2017.wav";
	
	public static void main(String args[]){
		BeamObject b = new BeamObject(filePath, 1030000000, 262144, 2*Math.pow(10, 6));
		BeamObject[] array = new BeamObject[8];
		array[0] = b;
	}
}
