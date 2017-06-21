/*Class: BeamObject
 * 
 * This class is a class that creates an object which is made up of the mathematical
 * functions and processes to retrieve the appropriate data from a frequency.
 * The class allows for the ability to populate the various array data used for
 * beam forming. One object is meant to represent the data created from a single RSP
 * that is connected. The class retrieves the raw .wav I/Q data from a filePath String
 * input. This class uses functions from an outside Complex number class, and 
 * also uses an outside .wav file reading class.
 * 
 */
package beam.common;

import java.io.File;
import java.util.Arrays;

import info.monitorenter.gui.chart.Chart2D;
import info.monitorenter.gui.chart.ITrace2D;
import info.monitorenter.gui.chart.traces.Trace2DSimple;

public class BeamObject{
	
	//Used for the .wav file's location
	public String filePath;
	
	/* Base Variables
	 * frequency = double value representing the frequency used
	 * n = double value representing the amount of iterations (how many I/Q values read)
	 * sample = double value representing the sample rate
	 * omega = 2pi*frequency (calculated in the constructor)
	 * p = ***Not sure what this does honeslty, not used anymore...***
	 * dt = 1 / sampleRate
	 */
	public double frequency;
	public double n;
	public double sample;
	public double omega;
	public double p;
	public double dt;
	
	
	/* I/Q Data Variables
	 * I = Left channel data
	 * Q = Right channel data
	 * E = Complex 
	 * fE = 
	 * fr = 
	 * absfE = Real version of E
	 */
	private double I[];
	private double Q[];
	public Complex E[];
	public Complex fE[];
	public double fr[];
	public double absfE[];
	
	//Currently the default filter, plan on implementing a selection of filters and
	//filter loading from files.
	double[] h  = new double[] {-0.0007433, 0.0008555, 0.0029073, 0.005288, 0.0061011,
			0.0031605, -0.0046278, -0.0157893, -0.0256264, -0.0271283, -0.013497,
			0.0185766, 0.0663613, 0.1206791, 0.1682412, 0.1960321, 0.1960321,
			0.1682412, 0.1206791, 0.0663613, 0.0185766, -0.013497, -0.0271283,
			-0.0256264, -0.0157893, -0.0046278, 0.0031605, 0.0061011, 0.005288,
			0.0029073, 0.0008555, -0.0007433};
	
	/* Filtered Variables
	 * hComplex = The complex implementation of the filter h
	 * Z = 
	 * Zf = 
	 * absZf = The real part of Zf
	 */
	public Complex[] hComplex;
	public Complex[] Z;
	public Complex[] Zf;
	double[] absZf;
	
	/**
	 * The Default constructor. Used when need to previously initialize data before running
	 * calculations. Make sure to reinstantiate the object using the overloaded constructor
	 * or the variables will not be populated!
	 */
	public BeamObject(){
		filePath = null;
		frequency = 0;
		n = 0;
		sample = 0;
	}
	
	
	/**
	 * Overloaded constructor. Takes in the filePath, frequency, n and sample. Then it
	 * populates the arrays with the correct data and calculations.
	 * @param filePath
	 * @param frequency
	 * @param n
	 * @param sample
	 */
	public BeamObject(String filePath, double frequency, double n, double sample) {
	
		//Can implement buffered version
		//double [][] IQdata = readFileWithBufferSize((n), filePath);
		
		double [][] IQdata = readFileWithBufferSize(filePath);
		
		//Copies the data from each channel into their own arrays with n size
		I = Arrays.copyOf(IQdata[0], (int) n);
		Q = Arrays.copyOf(IQdata[1], (int) n);
	
		//Sets the data and also creates data from the data given
		this.filePath = filePath;
		this.frequency = frequency;
		this.n = n;
		this.sample = sample;
		omega = 2*Math.PI*frequency;
		p = Math.floor(Math.log(n) / Math.log(2));
		dt = 1/sample;
		
		//Called to populate the remaining arrays
		makeE();
		fourierTransform();
		filter();
	}


	//////////////////////////////////////
	//			Getters				   //
	////////////////////////////////////
	
	/**
	 * Returns a copy of the array I
	 * @return
	 */
    public double[] getI() {
        return Arrays.copyOf(I, I.length);
    }
    
	/**
	 * Returns a copy of the array Q
	 * @return
	 */
    public double[] getQ() {
        return Arrays.copyOf(Q, Q.length);
    }
    
	/**
	 * Returns a copy of the array hComplex
	 * @return
	 */
    public Complex[] gethComplex() {
		return Arrays.copyOf(hComplex, hComplex.length);
	}

	/**
	 * Returns a copy of the array Z
	 * @return
	 */
	public Complex[] getZ() {
		return Arrays.copyOf(Z, Z.length);
	}

	/**
	 * Returns a copy of the array Zf
	 * @return
	 */
	public Complex[] getZf() {
		return Arrays.copyOf(Zf, Zf.length);
	}
	
	/**
	 * Returns a copy of the array AbsZf
	 * @return
	 */
	public double[] getAbsZf() {
		return Arrays.copyOf(absZf, absZf.length);
	}
	
	/**
	 * Returns a copy of the array E
	 * @return
	 */
	public Complex[] getE() {
		return Arrays.copyOf(E, E.length);
	}

	/**
	 * Returns a copy of the array fE
	 * @return
	 */
	public Complex[] getfE() {
		return Arrays.copyOf(fE, fE.length);
	}
	
	/**
	 * Returns a copy of the array AbsfE
	 * @return
	 */
	public double[] getAbsfE() {
		return absfE;
	}
	
	/**
	 * Returns a copy of the array fr
	 * @return
	 */
	public double[] getFr() {
		return Arrays.copyOf(fr, fr.length);
	}

	/**
	 * Returns omega's double value
	 * @return
	 */
    public double getOmega() {
		return omega;
	}

    /**
     * Returns p's double value
     * @return
     */
	public double getP() {
		return p;
	}

	/**
	 * Return dt's double value
	 * @return
	 */
	public double getDt() {
		return dt;
	}
	
	/**
	 * Returns the file path String
	 * @return
	 */
	public String getFilePath() {
		return filePath;
	}
	
	/**
	 * Returns n's double value
	 * @return
	 */
	public double getN() {
		return n;
	}
	
	/**
	 * Returns the frequency's double value
	 * @return
	 */
	public double getFrequency() {
		return frequency;
	}
	
	/**
	 * Returns the sample rates double value
	 * @return
	 */
	public double getSample() {
		return sample;
	}
	
	//////////////////////////////////////
	//			Setters				   //
	////////////////////////////////////
	
	/**
	 * Pass in a double array and I will be set to a copy of it
	 * @param array
	 */
	public void setI(double[] array) {
        I = Arrays.copyOf(array, array.length);
    }
    
	/**
	 * Pass in a double array and Q will be set to a copy of it
	 * @param array
	 */
    public void setQ(double[] array) {
    	Q = Arrays.copyOf(array, array.length);
    }

    /**
     * Pass in a double array and H will be set to a copy of it
     * @param array
     */
	public void setH(double[] array) {
		h = Arrays.copyOf(array, array.length);
	}

	/**
	 * Pass in a String and the filePath will be set to it. Please make sure the path is
	 * to a .wav file!
	 * @param filePath
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	/**
	 * Used to set the frequency, it is a double representation
	 * @param frequency
	 */
	public void setFrequency(double frequency) {
		this.frequency = frequency;
	}

	/**
	 * Used to set n (the number of iterations aka amount of data)
	 * @param n
	 */
	public void setN(double n) {
		this.n = n;
	}

	/**
	 * Used to set the sample rate, which is a double
	 * @param sample
	 */
	public void setSample(double sample) {
		this.sample = sample;
	}
	
	
	//////////////////////////////////////
	//			Methods				   //
	////////////////////////////////////
	
	/**
	 * Returns a filtered chart2D populated with fr and absZf data
	 * @return
	 */
	public Chart2D graphFiltered(){
		// Create a chart:  
				Chart2D chart = new Chart2D();

				// Create an ITrace: 
				ITrace2D trace = new Trace2DSimple(); 

				// Add the trace to the chart. This has to be done before adding points (deadlock prevention): 
				chart.addTrace(trace);    

				// Add all points, as it is static: 
				for (int i = 0; i < n; i++)					// Filtered version
					trace.addPoint(fr[i], absZf[i]);
				
				System.out.println("Chart created!");
				
				return chart;	
	}
	
	/**
	 * Returns an unfiltered chart2D populated with fr and absFe data
	 * @return
	 */
	public Chart2D graphUnfiltered(){
		// Create a chart:  
				Chart2D chart = new Chart2D();

				// Create an ITrace: 
				ITrace2D trace = new Trace2DSimple(); 

				// Add the trace to the chart. This has to be done before adding points (deadlock prevention): 
				chart.addTrace(trace);    

				// Add all points, as it is static: 
				for(int i = 0; i < n; i++){
					trace.addPoint(fr[i], absfE[i]);
				}
				
				System.out.println("Chart created!");
				
				return chart;	
	}
	
	
	/**
	 * Used to apply the default filter. Will implement the ability to select
	 * a custom filter, and apply different preset filters.
	 */
	public void filter(){
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
	 * Performs a fourier transform on E, which is store in fE. Then populates fr
	 * with the necesarry values.
	 */
	public void fourierTransform(){
		//Fourier Transform
		
		double df = 1 / (getN()*getDt());
		double m = getN();
		
		//Calls the fourier transform function on E
		fE = FFT.fft(E);
		
		fr = new double[(int) m];
		
		//fr population
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
	 * Populates the E array.
	 */
	public void makeE(){
		//Demodulation
		
//		//Use this decimal format when printing if you want to see really small values
//		DecimalFormat decf = new DecimalFormat("0", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
//		decf.setMaximumFractionDigits(340); //340 = DecimalFormat.DOUBLE_FRACTION_DIGITS
		
		//Linspace array created (Currently not used?)
		double[] t = linspace(0, (getDt()*(getN()-1)), (int)getN());
		
		//The complex version of Q
		E = new Complex[Q.length];
		
		//Loads E with an imaginary conversion: E = I + %i*Q
		for(int i = 0; i < E.length; i++){
			E[i] = new Complex(I[i], Q[i]);
		}
	}
	
	/**
	 * Function to take the absolute value of an entire complex array
	 * @param A
	 * @return
	 */
	public static double[] absComplex(Complex[] A){
		//Creates a temp array
		double [] B = new double[A.length];
		//Loops through and calls abs function on each element
		for(int i = 0; i < A.length; i++){
			B[i] = A[i].abs();
		}
		return B;
	}
	
	/**
	 * Calculates the linspace. Basically creates an array from the min to the max value
	 * with a stepsize that is equal to points.
	 * @param min
	 * @param max
	 * @param points
	 * @return
	 */
	public static double[] linspace(double min, double max, int points) {  
		double a = (max - min)/ points,
				A[] = new double[(int) points];
		double temp = 0;
		for(int i=0; i< A.length ;i++){
			A[i] = temp;
			temp+= a;
		}
		return A;
	}  
	
	/**
	 * Can be used top print a single double array.
	 * @param array
	 */
	public void printArray(double[] array){
		for(int i = 0; i < 10; i++){
			System.out.println(array[i]);
		}
	}
	
	//Should we keep the n implementation??? Here we just use the size of the
	//file and n is not taken into consideration.
	
	/**
	 * Reads the .wav file into a buffer array, which is the I/Q data. Currently
	 * does not implement n, will just read the entire file.
	 * @param filePath
	 * @return
	 */
	public double[][] readFileWithBufferSize(String filePath){
		
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
	         
	         //Always close!
	         wavFile.close();
	         
	         return buffer;
	      }
	      catch (Exception e)
	      {
	         System.err.println(e);
	      }
		
		return null;
	}

}
