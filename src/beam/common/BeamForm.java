/*
 * This class will have to handle implementing the array algorithim with several different objects
 */

package beam.common;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import info.monitorenter.gui.chart.Chart2D;

public class BeamForm {
	
	public static String filePath1 = "/Users/Justin/Desktop/Array-Based-Beam-Forming/Scripts/Fc100KHz_2MSPS_1KHzModulation_June12_2017.wav";
	public static String filePath2 = "/Users/Justin/Documents/Visual Studio 2015/Projects/ConsoleApplication1/ConsoleApplication1/bin/Debug/outputfile1.wav";
	public static String filePath3 = "/Users/Justin/Documents/Visual Studio 2015/Projects/ConsoleApplication1/ConsoleApplication1/bin/Debug/outputfile2.wav";
	
	public static void main(String args[]){
//		BeamObject b1 = new BeamObject(filePath1, 1030000000, 262144, 2*Math.pow(10, 6));
		BeamObject b1 = new BeamObject(filePath1, 104300000, 262144, 2048000);
		BeamObject b2 = new BeamObject(filePath2, 104300000, 262144, 2048000);
		BeamObject[] array = new BeamObject[8];
		array[0] = b1;
		array[1] = b2;
	}
}
