package beam.views;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import beam.common.BeamObject;
import info.monitorenter.gui.chart.Chart2D;
import info.monitorenter.gui.chart.ITrace2D;
import info.monitorenter.gui.chart.traces.Trace2DSimple;
import java.awt.BorderLayout;

public class BeamInterface extends JFrame {

	private JPanel contentPane;
	private JPanel graphPane;
	private JButton btnRun;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BeamInterface frame = new BeamInterface();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public BeamInterface(){
		initComponents();
		createEvents();
	}

	/**
	 * Create the frame.
	 */
	public void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(100, 100, screenSize.width, screenSize.height);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		btnRun = new JButton("Run");
		contentPane.add(btnRun, BorderLayout.SOUTH);
	}
	
	public void createEvents(){
		btnRun.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				BeamObject b = new BeamObject("/Users/barthinator/Desktop/Fc100KHz_2MSPS_1KHzModulation_June12_2017.wav", 1030000000, 262144, 2*Math.pow(10, 6));
				// Graph

				// Create a chart:  
				Chart2D chart = new Chart2D();

				// Create an ITrace: 
				ITrace2D trace = new Trace2DSimple(); 

				// Add the trace to the chart. This has to be done before adding points (deadlock prevention): 
				chart.addTrace(trace);

				// Add all points, as it is static: 

				for (int i = 0; i < b.getN(); i++)
					trace.addPoint(b.fr[i], b.absfE[i]);
				
				JPanel p3 = new JPanel(new BorderLayout());
				p3.add(chart);
				
				contentPane.add(p3, BorderLayout.CENTER);
			}
		});
		
		
		
	}
}
