package beam.common;
import java.io.File;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Arrays;
import java.util.Locale;

public class BeamForm {
	
	public String filePath = "/Users/barthinator/Desktop/Fc100KHz_2MSPS_1KHzModulation_June12_2017.wav";
	
	public static void main(String args[]){
		BeamForm b = new BeamForm();
		double [][] IQdata = b.readFileWithBufferSize(10, b.filePath);
		
		// I/Q Data Broken up
		double [] I = Arrays.copyOf(IQdata[0], IQdata[0].length);
		double [] Q = Arrays.copyOf(IQdata[1], IQdata[1].length);
		
		//Variables
		double frequency = 1030000000;
		double omega = 2*Math.PI*frequency;
		int n = I.length;
		double p = Math.floor(Math.log(n) / Math.log(2));
		//n = Math.pow(2, p);
		n = 262144;
		
		//Only take the first n values
		I = Arrays.copyOf(I, n);
		Q = Arrays.copyOf(Q, n);
		
		//Sample rate
		double sample = 2*Math.pow(10, 6);
		double dt = 1/sample;
		
		//Demodulation
		
//		//Use this decimal format when printing if you want to see really small values
//		DecimalFormat decf = new DecimalFormat("0", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
//		decf.setMaximumFractionDigits(340); //340 = DecimalFormat.DOUBLE_FRACTION_DIGITS
		
		//Linspace array created
		double[] t = b.linspace(0, (dt*(n-1)), n);
		
		Complex[] E = new Complex[Q.length];
		
		//E = I + %i*Q
		for(int i = 0; i < E.length; i++){
			E[i] = new Complex(I[i], Q[i]);
		}
		
		//Fourier Transform
		
		double df = 1 / (n*dt);
		double m = n;
		
		Complex[] fE = FFT.fft(E);
		
		double[] fr = new double[(int) m];
		
		for(int i = 1; i < m; i++){
			if(i<(m/2)+1){
				fr[i] = (i-1)*df;
			}
			else{
				fr[i] = (i-m-1)*df;
			}
		}
		
		//If we need to plot fE
		double[] absfE = b.absComplex(fE);
		
		
	}
	
	/**
	 * Function to take the absolute value of an entire complex array
	 * @param A
	 * @return
	 */
	
	public double[] absComplex(Complex[] A){
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
	public double[] linspace(double min, double max, int points) {  
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
	public double[][] readFileWithBufferSize(int bufferSize, String filePath){
		
		try
	      {
	         // Open the wav file specified as the first argument
	         WavFile wavFile = WavFile.openWavFile(new File(filePath));

	         // Display information about the wav file
	         wavFile.display();

	         // Get the number of audio channels in the wav file
	         int numChannels = wavFile.getNumChannels();

	         double[][] buffer = new double[numChannels][3379200];

	         int framesRead;

	         do
	         {
	            // Read frames into buffer
	            framesRead = wavFile.readFrames(buffer, 3379200);
	         }
	         while (framesRead != 0);
	         
	         /*
	         for(int i = 0; i < buffer.length; i++){
	        	 System.out.println("Channel " + i);
	        	 for(int j = 0; j < buffer[i].length; j++){
	        		 System.out.println(buffer[i][j]);
	        	 }
	         }
	         */
	         
	         //System.out.println(Arrays.toString(buffer[0]));
	         //System.out.println(Arrays.toString(buffer[1]));
	         
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
