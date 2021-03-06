package beam.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import javax.swing.JFrame;

import info.monitorenter.gui.chart.Chart2D;
import info.monitorenter.gui.chart.ITrace2D;
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
	public double ICut[]; 
	public double QCut[]; 
	public Complex E[];
	public Complex fE[];
	public double fr[];
	public double absfE[];

	//Filter. If we need the filter, n size is set to this size (32) if n is less than
	//32. This makes it so we have no errors when applying a filter
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
	 * Default Constructor: Should only be used when pre-initializing data
	 * before adding other needed values. Will not read in file.
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

	/**
	 * Overloaded constructor: Calculates the n value based on what was input,
	 * it will round to the closest power of 2 based off the input. Will also
	 * read in the .wav file from the filePath and uses the rest of the data
	 * to initialize and calculate the beginning array values.
	 * @param filePath
	 * @param frequency
	 * @param n
	 * @param weighting
	 */
	public BeamObject(String filePath, double frequency, double n, Complex weighting) 
	{

		//can implement buffered version
		//double [][] IQdata = readFileWithBufferSize((n), filePath);

		double [][] IQdata = readFileWithBufferSize(filePath);
		I = Arrays.copyOf(IQdata[0], (int) n);
		Q = Arrays.copyOf(IQdata[1], (int) n);

		//Rounds to the nearest power of 2
		p = Math.floor(Math.log(n) / Math.log(2));
		this.n = Math.pow(2, p);
		System.out.println("Used 'n' from data.");

		this.filePath = filePath;
		this.frequency = frequency*1000000; 		// Convert Frequency into kHz

		omega = 2*Math.PI*frequency;
		dt = 1/sample;

		makeE();
		System.out.println("Changed n to: " + n);
		fourierTransform();
		filterAndWeightings(weighting);
		//filter();
		this.setWeighting(weighting);
	}




	/**
	 * alignRecordings: Used to chop the ends of the recordings so that they match each other.
	 * Currently not used yet. Need to know the pattern, i checks that there is 2 pts in a row
	 * above the threshold, and cut from first above threshold.
	 */
	public void alignRecordings() 
	{  
		int j=0; 
		int locationEnd=0; 
		int locationStart=0;
		int counter=0;
		for (int i=0;i<I.length;i++) 
		{ 
			if (I[i]>99) 								//if above threshold...
			{ 
				j++;
				if (j>2&&locationStart==0)			//if second in a row above threshold, 
				{
					locationStart=i-1;				//then this is where we cut first end.
				}
			}
			else if (I[i]<=99)							//to make sure at least two are in a row.
			{
				j=0;
			}
			else if (I[i]<=99&&locationStart!=0&&locationEnd==0)		//if below/at threshold and first end has been set already,
			{
				locationEnd=i;										//set ending of our snippet.
				for (int i2=locationStart; i2<=locationEnd; i2++)			//make snippet.
				{
					double[] ICut= new double[I.length-locationStart-locationEnd];
					double[] QCut= new double[I.length-locationStart-locationEnd];
					ICut[counter]=I[i2];
					QCut[counter]=Q[i2];
					counter++;
				}
			}
		}
	}

	/**
	 * calcWeighting: Returns a complex number that calculates the weighting. Its
	 * the time delay that aligns all the recordings towards a specific direction and
	 * angle.
	 * @param freq
	 * @param distance
	 * @param angle
	 * @param numReceiver
	 * @return
	 */

	public Complex calcWeighting(double freq, double distance, double angle, double numReceiver)
	{
		double e = Math.E;			// Euler's number
		double c = 299792458;				// Speed of light (m/s)
		double pi = 3.14159265358979;		// Pi
		this.angle = angle;
		distance = distance*0.0254;			// Convert distance to meters
		distance = distance*numReceiver;


		double k = (2*pi*freq)/c;

		//mine
		double power = k*distance*Math.sin(Math.toRadians(angle)); //k* d(n) *sin(theta) 

		//theirs
		//double power = k*distance*Math.sin(angle);
		//need toRadians?

		//theirs (sarah can you explain to me?)
		Complex weighting = new Complex(Math.cos(power), (Math.sin(power)*-1));

		//mine
		//Complex complexPower = new Complex(0, (power)*-1);

		//Complex weighting =complexPower.exponential();

		//		System.out.println();
		//		System.out.println(weighting);
		//		System.out.println();

		return weighting;
	}

	/**
	 * Applies the filter and the weighting.
	 * @param weighting
	 */
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

	/**
	 * Takes in 5 beam objects and helps graph and plot angles. It also writes to a file
	 * to store the previous data.
	 * @param b1
	 * @param b2
	 * @param b3
	 * @param b4
	 * @param b5
	 */
	public void addWeightings(Complex[] b1, Complex[] b2, Complex[] b3, Complex[] b4, Complex[] b5)
	{
		Complex[] addedWeightings = new Complex[zTimesWeightings.length];
		for (int i = 0; i < zTimesWeightings.length; i++)
		{
			addedWeightings[i] = b1[i].plus(b2[i]).plus(b3[i]).plus(b4[i]).plus(b5[i]);
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

	/**
	 * Uses print writer and fileOutputStream to write angle data to file
	 * @param plotPoints
	 * @param fileName
	 */
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
				
				System.out.println();
				System.out.println("Created point: (" + pp.getSignalAngle() + ", " + pp.getSignalPower() + ")");
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


	/**
	 * Reads in the plot data from the file where the angle data is stored.
	 * @param fileName
	 * @return
	 */
	private ArrayList<AngleVsPower> readPlotPointsFromFile(String fileName)
	{
		// Create connection to fileName for reading
		FileInputStream fis = null;
		Scanner fScan = null;
		signalAngle = new ArrayList<>();
		signalPower = new ArrayList<>();
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
				//				System.out.println(pp.getSignalAngle());
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


	/**
	 * Used to graph the results when the graph button is pressed
	 * @return
	 */
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

	/**
	 * Applies the h filter
	 */
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


	/**
	 * Performs an FFT on data, requires n to be a power of 2
	 */
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

	/**
	 * Makes the complex array representation of the IQ data
	 */
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
			//This should only happen if we are loading things within the project
			//Ignore this I am still working on getting it to work
			//URL resourceUrl = getClass().getResource("/");
			//File file = new File((resourceUrl.toURI().getSchemeSpecificPart() + filePath));
			//System.out.println(file.toString());
			
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
	
	//Need to make sure n is less than h.length and is a power of 2.
	public void setN(double n) 
	{
		if(n < 32){
			n = 32;
		}
		p = Math.floor(Math.log(n) / Math.log(2));
		this.n = Math.pow(2, p);
	}
	
	public void setWeighting(Complex weighting2) {this.weighting = weighting2;}
	public void setAngle(double angle) {this.angle = angle;}

}
