package beam.common;

import java.io.File;
import java.util.Arrays;

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
	
	
	/**
	 * Constructor
	 * @param filePath
	 * @param frequency
	 * @param n
	 * @param sample
	 */
	
	public BeamObject(String filePath, double frequency, double n, double sample) {
	
		//can implement buffered version
		//double [][] IQdata = readFileWithBufferSize((n), filePath);
		
		double [][] IQdata = readFileWithBufferSize(filePath);
		
		I = Arrays.copyOf(IQdata[0], (int) n);
		Q = Arrays.copyOf(IQdata[1], (int) n);
	
		this.filePath = filePath;
		this.frequency = frequency;
		this.n = n;
		this.sample = sample;
		omega = 2*Math.PI*frequency;
		p = Math.floor(Math.log(n) / Math.log(2));
		dt = 1/sample;
		
		makeE();
		fourierTransform();
		filter();
	}


	/**
	 * Getters/Setters
	 */
    	/** @return a copy of the array */
    public double[] getI() {
        return Arrays.copyOf(I, I.length);
    }
    
    public double[] getQ() {
        return Arrays.copyOf(Q, Q.length);
    }
    
    public void setI(double[] array) {
        I = Arrays.copyOf(array, array.length);
    }
    
    public void setQ(double[] array) {
    	Q = Arrays.copyOf(array, array.length);
    }
    
    public Complex[] gethComplex() {
		return Arrays.copyOf(hComplex, hComplex.length);
	}

	public Complex[] getZ() {
		return Arrays.copyOf(Z, Z.length);
	}

	public Complex[] getZf() {
		return Arrays.copyOf(Zf, Zf.length);
	}

	public double[] getAbsZf() {
		return Arrays.copyOf(absZf, absZf.length);
	}

	public void setH(double[] array) {
		h = Arrays.copyOf(array, array.length);
	}
    
	public Complex[] getE() {
		return E;
	}

	public Complex[] getfE() {
		return fE;
	}

	public double[] getFr() {
		return fr;
	}

	public double[] getAbsfE() {
		return absfE;
	}
    
    public double getOmega() {
		return omega;
	}

	public double getP() {
		return p;
	}

	public double getDt() {
		return dt;
	}
	
	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public double getFrequency() {
		return frequency;
	}

	public void setFrequency(double frequency) {
		this.frequency = frequency;
	}

	public double getN() {
		return n;
	}

	public void setN(double n) {
		this.n = n;
	}

	public double getSample() {
		return sample;
	}

	public void setSample(double sample) {
		this.sample = sample;
	}
	
	//Methods
	
	public Chart2D graph(double[] x, double[] y, double m){
		// Create a chart:  
				Chart2D chart = new Chart2D();

				// Create an ITrace: 
				ITrace2D trace = new Trace2DSimple(); 

				// Add the trace to the chart. This has to be done before adding points (deadlock prevention): 
				chart.addTrace(trace);    

				// Add all points, as it is static: 

//				for (int i = 0; i < m; i++)					// Unfiltered version
//					trace.addPoint(fr[i], absfE[i]);
				
				for (int i = 0; i < m; i++)					// Filtered version
					trace.addPoint(x[i], y[i]);

//
//				// Make it visible: Create a frame.
//				JFrame frame = new JFrame("MinimalStaticChart");
//
//				// add the chart to the frame: 
//				frame.getContentPane().add(chart);
//				frame.setSize(600,400);
//				frame.setTitle("I&Q Data");
//
//				// Enable the termination button [cross on the upper right edge]: 
//				frame.addWindowListener(
//						new WindowAdapter()
//						{
//							public void windowClosing(WindowEvent e)
//							{
//								System.exit(0);
//							}
//						}
//						);
//				frame.setVisible(true);
				
				return chart;
				
	}
	
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
	
	
	public void fourierTransform(){
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

	public void makeE(){
		
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
	
	public static double[] absComplex(Complex[] A){
		double [] B = new double[A.length];
		for(int i = 0; i < A.length; i++){
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
	public static double[] linspace(double min, double max, int points) {  
		double batasBawah = min;
		double batasAtas = max;
		double jumlahData = points;

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
	
	/**
	 * Reads in the I/Q data
	 * @param bufferSize
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
