package beam.common;

import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import javax.swing.JFrame;

import info.monitorenter.gui.chart.Chart2D;
import info.monitorenter.gui.chart.IAxis;
import info.monitorenter.gui.chart.ITrace2D;
import info.monitorenter.gui.chart.io.ADataCollector;
import info.monitorenter.gui.chart.io.RandomDataCollectorTimeStamped;
import info.monitorenter.gui.chart.traces.Trace2DLtd;
import info.monitorenter.gui.chart.traces.Trace2DSimple;

public class BeamObject {

	public String filePath;

	public double frequency;
	public double n;
	public double sample;
	public double omega;
	public double p;
	public double dt;

	private double I[];
	private double Q[];
	public Complex E[];
	public Complex fE[];
	public double fr[];
	public double absfE[];

	double[] h  = new double[] {-0.0007433, 0.0008555, 0.0029073, 0.005288, 0.0061011,
			0.0031605, -0.0046278, -0.0157893, -0.0256264, -0.0271283, -0.013497,
			0.0185766, 0.0663613, 0.1206791, 0.1682412, 0.1960321, 0.1960321,
			0.1682412, 0.1206791, 0.0663613, 0.0185766, -0.013497, -0.0271283,
			-0.0256264, -0.0157893, -0.0046278, 0.0031605, 0.0061011, 0.005288,
			0.0029073, 0.0008555, -0.0007433};

	//Filtered variables
	public Complex[] hComplex;
	public Complex[] Z;
	public Complex[] Zf;
	double[] absZf;

	//Filtered with Weightings
	public Complex[] zTimesWeightings;
	private Complex weighting;
	double[] absAddedWeightings;
	public double angle;
	//ArrayList<Double> addedWeightingsAmp = new ArrayList<>();
	private ArrayList<Double> signalAngle;
	private ArrayList<Double> signalPower;
	public ArrayList<AngleVsPower> plotPoints;



	/**
	 * Constructor
	 * @param filePath
	 * @param frequency
	 * @param n
	 * @param sample
	 */

	public BeamObject()
	{
		filePath = null;
		frequency = 0;
		n = 0;
		weighting = new Complex(0,0);
	}

	public BeamObject(String filePath, double frequency, double n, Complex weighting) 
	{

		//can implement buffered version
		//double [][] IQdata = readFileWithBufferSize((n), filePath);

		double [][] IQdata = readFileWithBufferSize(filePath);

		I = Arrays.copyOf(IQdata[0], (int) n);
		Q = Arrays.copyOf(IQdata[1], (int) n);

		this.n = I.length;
		if (this.n < n)
		{
			p = Math.floor(Math.log(this.n) / Math.log(2));
			this.n = Math.pow(2, p);
			System.out.println("Used 'n' from data.");
		}
		else
		{
			this.n = n;
		}

		this.setWeighting(weighting);
		this.filePath = filePath;
		this.frequency = frequency*1000000; 		// Convert Frequency into kHz
		this.n = n;
		omega = 2*Math.PI*frequency;
		dt = 1/sample;

		makeE();
		fourierTransform();
		filterAndWeightings(weighting);
		//filter();
		
	
	}




	//Methods

	public Chart2D graphUnfiltered()
	{
		// Create a chart:  
		Chart2D chart = new Chart2D();

		// Create an ITrace: 
		ITrace2D trace = new Trace2DSimple(); 
		trace.setName("");

		// Add the trace to the chart. This has to be done before adding points (deadlock prevention): 
		chart.addTrace(trace);    

		// Add all points, as it is static: 


		for (int i = 0; i < n; i++)					// Unfiltered version
			trace.addPoint(fr[i], absfE[i]);

		// Make it visible: Create a frame.
		JFrame frame = new JFrame("MinimalStaticChart");

		// add the chart to the frame: 
		frame.getContentPane().add(chart);
		frame.setSize(600,400);
		frame.setTitle("Unfiltered Data");
		frame.setLocation(850, 0);

		frame.setVisible(true);


		return chart;
	}

	public Chart2D graphFiltered()
	{
		// Create a chart:  
		Chart2D chart = new Chart2D();

		// Create an ITrace: 
		ITrace2D trace = new Trace2DSimple(); 
		trace.setName("");

		// Add the trace to the chart. This has to be done before adding points (deadlock prevention): 
		chart.addTrace(trace);    

		// Add all points, as it is static: 
		for (int i = 0; i < n; i++)					// Filtered version
			trace.addPoint(fr[i], absZf[i]);

		// Make it visible: Create a frame.
		JFrame frame = new JFrame("MinimalStaticChart");

		// add the chart to the frame: 
		frame.getContentPane().add(chart);
		frame.setSize(600,400);
		frame.setTitle("Filtered Data");
		frame.setLocation(850, 450);

		frame.setVisible(true);


		return chart;

	}

	public Complex calcWeighting(double freq, double distance, double angle, double numReceiver)
	{
		double e = 2.718281828459;			// Euler's number
		double c = 299792458;				// Speed of light (m/s)
		double pi = 3.14159265358979;		// Pi
		this.angle = angle;
		distance = distance*0.0254;			// Convert distance to meters
		distance = distance*numReceiver;


		double k = (2*pi*freq)/c;

		double power = k*distance*Math.sin(angle);

		Complex weighting = new Complex(Math.cos(power), (Math.sin(power)*-1));

		return weighting;
	}

	public void filterAndWeightings(Complex weighting)
	{	
		// Create complex version of h
		hComplex = new Complex[E.length];

		//Populates everything with zeroes
		for (int i = 0; i < E.length; i++)
		{
			hComplex[i] = new Complex(0,0);
		}

		//Adding the values from H into hComplex
		for (int i = 0; i < h.length; i++)
		{
			hComplex[i] = new Complex(h[i], 0);
		}

		// Convolution
		Z = FFT.cconvolve(hComplex, E);

		// Multiply by weighting
		zTimesWeightings = new Complex[Z.length];
		for (int i = 0; i < Z.length; i++) 
		{
			zTimesWeightings[i] = Z[i].times(weighting);
		}


		// For plotting
		//				absZf = absComplex(Zf);
	}

	public void addWeightings(Complex[] b1, Complex[] b2, Complex[] b3, Complex[] b4, Complex[] b5, Complex[] b6, Complex[] b7, Complex[] b8)
	{
		Complex[] addedWeightings = new Complex[zTimesWeightings.length];
		for (int i = 0; i < zTimesWeightings.length; i++)
		{
			addedWeightings[i] = b1[i].plus(b2[i]).plus(b3[i]).plus(b4[i]).plus(b5[i]).plus(b6[i]).plus(b7[i]).plus(b8[i]);
		}

		absAddedWeightings = absComplex(addedWeightings);

		double max = -100000;
		for(int i = 0; i < addedWeightings.length; i++)
		{
			if (absAddedWeightings[i] > max)
				max = absAddedWeightings[i];
		}

		//this.addedWeightingsAmp.add(max);


		// Add plot points to an ArrayList
		ArrayList<AngleVsPower> plotPoints = new ArrayList<AngleVsPower>();

		// Make a NEW instance of a plot point
		plotPoints.add(new AngleVsPower(angle, max));


		// Write plot points to file
		String fileName = "PlotPoints.txt";
		writePlotPointsToFile(plotPoints, fileName);


		//		return absAddedWeightings;

	}

	private static void writePlotPointsToFile(ArrayList<AngleVsPower> plotPoints, String fileName)
	{
		// Create connection to fileName for printing
		File f = null;
		FileOutputStream fos = null;
		PrintWriter pw = null;
		try
		{
			f = new File(fileName);
			
			fos = new FileOutputStream(f, true);
			pw = new PrintWriter(fos);
			

			// Write points contents out to file
			for (AngleVsPower pp : plotPoints)
			{
				pw.write(pp.getSignalAngle() + ", ");
				pw.write(pp.getSignalPower() + "");
				pw.println("");
				
				System.out.println(pp.getSignalAngle() + ", " + pp.getSignalPower());
			}
			
			System.out.println("Successfully Written");

		}
		catch(FileNotFoundException e)
		{
			System.out.println("ERROR (File not found): " + e.getMessage());
		}
		catch(IOException e)
		{
			System.out.println("ERROR (File not found): " + e.getMessage());
		}
		finally
		{

			try 
			{
				//f.close();
				pw.close();
			}
			catch(Exception e) { System.out.println("ERROR: " + e.getMessage());}
		}
	}
	
	
	private ArrayList<AngleVsPower> readPlotPointsFromFile(String fileName)
	{
		// Create connection to fileName for reading
		FileInputStream fis = null;
		Scanner fScan = null;
		ArrayList<AngleVsPower> plotPoints = new ArrayList<AngleVsPower>();

		try
		{
			fis = new FileInputStream(fileName);
			fScan = new Scanner(fis);

			// Read in each plot point line
			while(fScan.hasNextLine())
			{
				// Create a scanner to scan the line
				String line = fScan.nextLine();	
				AngleVsPower pp = new AngleVsPower();
				Scanner lineScan = new Scanner(line);	
				lineScan.useDelimiter(",");

				// Create new plot point and populate
				//AngleVsPower pp = new AngleVsPower();
				pp.setSignalAngle(Double.parseDouble(lineScan.next().trim()));
				signalAngle.add(pp.getSignalAngle());
				pp.setSignalPower(Double.parseDouble(lineScan.next().trim()));
				signalPower.add(pp.getSignalPower());
				plotPoints.add(pp);
				// Debugging
				System.out.println(pp.toString());

			}
		}
		catch(FileNotFoundException e)
		{
			System.out.println("ERROR (File not found): " + e.getMessage());
		}
		catch(IOException e)
		{
			System.out.println("ERROR (File not found): " + e.getMessage());
		}
		finally
		{
			try 
			{
				fis.close();
				fScan.close();
			}
			catch(Exception e) { System.out.println("ERROR: " + e.getMessage());}
		}

		return plotPoints;
	}


	public Chart2D finalGraph()
	{
		ArrayList<AngleVsPower> plotPointsFromFile = readPlotPointsFromFile("PlotPoints.txt");
		
		// Create a chart:  
		Chart2D chart = new Chart2D();

		// Create an ITrace: 
		ITrace2D trace = new Trace2DSimple(); 
		trace.setName("");

		// Add the trace to the chart. This has to be done before adding points (deadlock prevention): 
		chart.addTrace(trace);    

		// Add all points, as it is static:


		for (int i = 0; i < signalAngle.size(); i++)	
			trace.addPoint(signalAngle.get(i), signalPower.get(i));

		// Make it visible: Create a frame.
		JFrame frame = new JFrame("MinimalStaticChart");

		// add the chart to the frame: 
		frame.getContentPane().add(chart);
		frame.setSize(600,400);
		frame.setTitle("");
		frame.setLocation(850, 0);

		frame.setVisible(true);


		return chart;
	}

	public void filter()
	{
		// Create complex version of h
		hComplex = new Complex[E.length];

		//Populates everything with zeroes
		for (int i = 0; i < E.length; i++)
		{
			hComplex[i] = new Complex(0,0);
		}

		//Adding the values from H into hComplex
		for (int i = 0; i < h.length; i++)
		{
			hComplex[i] = new Complex(h[i], 0);
		}

		// Convolution
		Z = FFT.cconvolve(hComplex, E);

		//Fast fourier transform
		Zf = FFT.fft(Z);

		// For plotting
		absZf = absComplex(Zf);
	}


	public void fourierTransform()
	{
		//Fourier Transform

		double df = 1 / (getN()*getDt());
		double m = getN();

		fE = FFT.fft(E);

		fr = new double[(int) m];

		for(int i = 1; i < m; i++){
			if(i<(m/2)+1){
				fr[i] = (i-1)*df;
			}
			else{
				fr[i] = (i-m-1)*df;
			}
		}

		//If we need to plot fE
		absfE = absComplex(fE);
	}

	public void makeE()
	{

		//Demodulation

		//		//Use this decimal format when printing if you want to see really small values
		//		DecimalFormat decf = new DecimalFormat("0", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
		//		decf.setMaximumFractionDigits(340); //340 = DecimalFormat.DOUBLE_FRACTION_DIGITS

		//Linspace array created

		double[] t = linspace(0, (getDt()*(getN()-1)), (int)getN());

		E = new Complex[Q.length];

		//E = I + %i*Q
		for(int i = 0; i < E.length; i++){
			E[i] = new Complex(I[i], Q[i]);
		}
	}

	/**
	 * Function to take the absolute value of an entire complex array
	 * @param A
	 * @return
	 */

	public static double[] absComplex(Complex[] A)
	{
		double [] B = new double[A.length];
		for(int i = 0; i < A.length; i++)
		{
			B[i] = A[i].abs();
		}
		return B;
	}

	/**
	 * Linspace function
	 * @param min
	 * @param max
	 * @param points
	 * @return
	 */
	public static double[] linspace(double min, double max, int points) 
	{  
		double batasBawah = min;
		double batasAtas = max;
		double jumlahData = points;

		double a = (max - min)/ points,
				A[] = new double[(int) points];
		double temp = 0;
		for(int i=0; i< A.length ;i++)
		{
			A[i] = temp;
			temp+= a;
		}
		return A;
	}  

	/**
	 * Can be used top print a single double array.
	 * @param array
	 */
	public void printArray(double[] array)
	{
		for(int i = 0; i < 10; i++)
		{
			System.out.println(array[i]);
		}
	}

	/**
	 * Reads in the I/Q data
	 * @param bufferSize
	 * @param filePath
	 * @return
	 */
	public double[][] readFileWithBufferSize(String filePath)
	{

		try
		{
			// Open the wav file specified as the first argument
			WavFile wavFile = WavFile.openWavFile(new File(filePath));

			// Display information about the wav file
			wavFile.display();

			// Get the number of audio channels in the wav file
			int numChannels = wavFile.getNumChannels();

			double[][] buffer = new double[numChannels][(int) wavFile.getNumFrames()];

			int framesRead;

			do
			{
				// Read frames into buffer
				framesRead = wavFile.readFrames(buffer, (int) wavFile.getNumFrames());
			}
			while (framesRead != 0);

			sample = wavFile.getSampleRate();
			wavFile.close();

			return buffer;
		}
		catch (Exception e)
		{
			System.err.println(e);
		}

		return null;
	}

	/**
	 * Getters/Setters
	 */
	/** @return a copy of the array */
	public double[] getI() {return Arrays.copyOf(I, I.length);}
	public double[] getQ() {return Arrays.copyOf(Q, Q.length);}
	public Complex[] gethComplex() {return Arrays.copyOf(hComplex, hComplex.length);}
	public Complex[] getZ() {return Arrays.copyOf(Z, Z.length);}
	public Complex[] getZf() {return Arrays.copyOf(Zf, Zf.length);}
	public double[] getAbsZf() {return Arrays.copyOf(absZf, absZf.length);}
	public Complex[] getE() {return E;}
	public Complex[] getfE() {return fE;}
	public double[] getFr() {return fr;}
	public double[] getAbsfE() {return absfE;}
	public double getOmega() {return omega;}
	public double getP() {return p;}
	public double getDt() {return dt;}
	public String getFilePath() {return filePath;}
	public double getFrequency() {return frequency;}
	public double getN() {return n;}
	public Complex getWeighting() {return weighting;}
	public double getAngle() {return angle;}

	public void setI(double[] array) {I = Arrays.copyOf(array, array.length);}
	public void setQ(double[] array) {Q = Arrays.copyOf(array, array.length);}
	public void setH(double[] array) {h = Arrays.copyOf(array, array.length);}
	public void setFilePath(String filePath) {this.filePath = filePath;}
	public void setFrequency(double frequency) {this.frequency = frequency;}
	public void setN(double n) {this.n = n;}
	public void setWeighting(Complex weighting2) {this.weighting = weighting2;}
	public void setAngle(double angle) {this.angle = angle;}

}
