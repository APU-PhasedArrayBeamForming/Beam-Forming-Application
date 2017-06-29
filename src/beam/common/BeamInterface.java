package beam.common;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;

import info.monitorenter.gui.chart.Chart2D;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.FlowLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class BeamInterface extends JFrame {

	private JPanel contentPane;
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

		setBounds(100, 100, 586, 257);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

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
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnSelectFile)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(tfFilePath, GroupLayout.PREFERRED_SIZE, 396, GroupLayout.PREFERRED_SIZE))
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
					.addContainerGap(67, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
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
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnRun)
					.addContainerGap())
		);

		contentPane.setLayout(gl_contentPane);

		btnRun.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{

				
				BeamObject b = new BeamObject();
				
				b.setFilePath(tfFilePath.getText());
				b.setFrequency(Double.parseDouble(tfFreq.getText()));
				b.setSample(Double.parseDouble(tfSampleRate.getText()));
				b.setN(Integer.parseInt(tfN.getText()));;
				
				
				b = new BeamObject(b.getFilePath(), b.getFrequency(), b.getN(), b.getSample());
				

				Chart2D graph = b.graphFiltered();
				Chart2D graph2 = b.graphUnfiltered();

			}
		});
		
		
//		BeamObject b = new BeamObject("/Users/sarahdepillis/Documents/Array-Based-Beam-Forming/Recordings/Fc100KHz_2MSPS_1KHzModulation_June12_2017.wav", 
//				1030000000, 262144, 2*Math.pow(10, 6));
//
//
//		Chart2D graph = b.graphFiltered();
//		Chart2D graph2 = b.graphUnfiltered();
//
//
//		pnlFilteredGraph.add(graph, BorderLayout.CENTER);
//		pnlUnfilteredGraph.add(graph2, BorderLayout.CENTER);
		
		
		

		//In response to a button click:
		//int returnVal = fc.showOpenDialog(aComponent);
	}
}
