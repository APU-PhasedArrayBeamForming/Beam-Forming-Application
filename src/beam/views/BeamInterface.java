package beam.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import beam.common.BeamObject;
import info.monitorenter.gui.chart.Chart2D;

public class BeamInterface extends JFrame {

	private JPanel contentPane;
	private JPanel pnlFilteredGraph;
	private JPanel pnlUnfilteredGraph;
	private JButton btnSelectFile;
	private JTextField tfFilePath;
	private JLabel lblFrequency;
	private JTextField tfFreq;
	private JTextField tfSampleRate;
	private JLabel lblSampleRate;
	private JLabel lblNumberOfIterations;
	private JTextField tfN;
	private JComboBox comboBox;


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

	/**
	 * Create the frame.
	 */
	public BeamInterface() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		//		setBounds(100, 100, screenSize.width, screenSize.height);

		setBounds(100, 100, 712, 500);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		pnlFilteredGraph = new JPanel();
		pnlFilteredGraph.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Filtered Graph", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

		pnlUnfilteredGraph = new JPanel();
		pnlUnfilteredGraph.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Unfiltered Graph", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		//Create a file chooser
//		final JFileChooser fileChooser = new JFileChooser();
		

		btnSelectFile = new JButton("Select File");

		btnSelectFile.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
				int result = fileChooser.showOpenDialog(tfFilePath);
				if (result == JFileChooser.APPROVE_OPTION) {
				    File selectedFile = fileChooser.getSelectedFile();
				    //System.out.println("Selected file: " + selectedFile.getAbsolutePath());
				    tfFilePath.setText(selectedFile.getAbsolutePath());
				}
			}

		});
		
		tfFilePath = new JTextField();
		tfFilePath.setColumns(10);
		
		lblFrequency = new JLabel("Frequency:");
		
		tfFreq = new JTextField();
		tfFreq.setColumns(10);
		
		lblSampleRate = new JLabel("Sample Rate:");
		
		tfSampleRate = new JTextField();
		tfSampleRate.setColumns(10);
		
		lblNumberOfIterations = new JLabel("Number of Iterations:");
		
		tfN = new JTextField();
		tfN.setColumns(10);
		
		JLabel lblFilterType = new JLabel("Filter Type:");
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Filter1"}));
		
		
		
		JButton btnRun = new JButton("Run");
		

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnSelectFile)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(tfFilePath, GroupLayout.PREFERRED_SIZE, 202, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblFrequency)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(tfFreq, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblSampleRate)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(tfSampleRate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNumberOfIterations)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(tfN, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblFilterType)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnRun))
					.addPreferredGap(ComponentPlacement.RELATED, 75, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(pnlUnfilteredGraph, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(pnlFilteredGraph, GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE)))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnSelectFile)
								.addComponent(tfFilePath, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblFrequency)
								.addComponent(tfFreq, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblSampleRate)
								.addComponent(tfSampleRate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNumberOfIterations)
								.addComponent(tfN, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblFilterType)
								.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addComponent(pnlUnfilteredGraph, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(97)
							.addComponent(pnlFilteredGraph, GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
							.addGap(21))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnRun)
							.addContainerGap())))
		);

		pnlUnfilteredGraph.setLayout(new BorderLayout(0, 0));

		pnlFilteredGraph.setLayout(new BorderLayout(0, 0));

		contentPane.setLayout(gl_contentPane);

		btnRun.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				BeamObject b = new BeamObject();
				
				b.setFilePath(tfFilePath.getText());
				b.setFrequency(Double.parseDouble(tfFreq.getText()));
				b.setSample(Double.parseDouble(tfSampleRate.getText()));
				b.setN(Integer.parseInt(tfN.getText()));
				
				
				b = new BeamObject(b.getFilePath(), 
						b.getFrequency(), b.getN(), b.getSample());

				Chart2D graph = b.graphFiltered();
				Chart2D graph2 = b.graphUnfiltered();

				pnlFilteredGraph.add(graph, BorderLayout.CENTER);
				pnlUnfilteredGraph.add(graph2, BorderLayout.CENTER);
			}
		});
//		BeamObject b = new BeamObject("/Users/Justin/Desktop/Array-Based-Beam-Forming/Scripts/Fc100KHz_2MSPS_1KHzModulation_June12_2017.wav", 
//				1030000000, 262144, 2*Math.pow(10, 6));
		
		BeamObject b = new BeamObject("/Users/Justin/Documents/Visual Studio 2015/Projects/ConsoleApplication1/ConsoleApplication1/bin/Debug/outputfile.wav", 
				1030000000, 262144, 2*Math.pow(10, 6));

		Chart2D graph = b.graphFiltered();
		Chart2D graph2 = b.graphUnfiltered();

				pnlFilteredGraph.add(graph, BorderLayout.CENTER);
				pnlUnfilteredGraph.add(graph2, BorderLayout.CENTER);



//		In response to a button click:
//		int returnVal = fc.showOpenDialog(aComponent);
	}
}
