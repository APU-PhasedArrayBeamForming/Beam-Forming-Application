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
import java.awt.Font;

public class FinalInterface extends JFrame {

	private JPanel contentPane;
	private JButton btnSelectFile1;
	private JTextField tfFilePath1;
	private JLabel lblFrequency;
	private JTextField tfFreq;
	private JLabel lblNumberOfIterations;
	private JTextField tfN;
	private JTextField tfFilePath2;
	private JTextField tfFilePath3;
	private JTextField tfFilePath4;
	private JTextField tfFilePath5;
	private JTextField tfFilePath6;
	private JTextField tfFilePath7;
	private JTextField tfFilePath8;
	private JTextField tfDistance;
	private JLabel lblAngle;
	private JLabel lblInches;
	private JTextField tfAngle;
	private JLabel lblDegrees;
	private JLabel lblMHz;
	
	public BeamObject b1 = new BeamObject();


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FinalInterface frame = new FinalInterface();
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
	public FinalInterface() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		//		setBounds(100, 100, screenSize.width, screenSize.height);

		setBounds(100, 100, 586, 484);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		btnSelectFile1 = new JButton("Select File 1");

		btnSelectFile1.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
				int result = fileChooser.showOpenDialog(tfFilePath1);
				if (result == JFileChooser.APPROVE_OPTION) {
				    File selectedFile = fileChooser.getSelectedFile();
				    //System.out.println("Selected file: " + selectedFile.getAbsolutePath());
				    tfFilePath1.setText(selectedFile.getAbsolutePath());
				}
			}

		});
		
		tfFilePath1 = new JTextField();
		tfFilePath1.setColumns(10);
		
		lblFrequency = new JLabel("Frequency:");
		
		tfFreq = new JTextField();
		tfFreq.setColumns(10);
		
		lblNumberOfIterations = new JLabel("Number of Iterations:");
		
		tfN = new JTextField();
		tfN.setColumns(10);
		
		
		
		JButton btnRun = new JButton("Run");
		
		JButton btnSelectFile2 = new JButton("Select File 2");
		
		btnSelectFile2.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
				int result = fileChooser.showOpenDialog(tfFilePath1);
				if (result == JFileChooser.APPROVE_OPTION) {
				    File selectedFile = fileChooser.getSelectedFile();
				    //System.out.println("Selected file: " + selectedFile.getAbsolutePath());
				    tfFilePath2.setText(selectedFile.getAbsolutePath());
				}
			}
		});
		
		tfFilePath2 = new JTextField();
		tfFilePath2.setColumns(10);
		
		JButton btnSelectFile3 = new JButton("Select File 3");
		
		btnSelectFile3.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
				int result = fileChooser.showOpenDialog(tfFilePath1);
				if (result == JFileChooser.APPROVE_OPTION) {
				    File selectedFile = fileChooser.getSelectedFile();
				    //System.out.println("Selected file: " + selectedFile.getAbsolutePath());
				    tfFilePath3.setText(selectedFile.getAbsolutePath());
				}
			}
		});
		
		tfFilePath3 = new JTextField();
		tfFilePath3.setColumns(10);
		
		tfFilePath4 = new JTextField();
		tfFilePath4.setColumns(10);
		
		JButton btnSelectFile4 = new JButton("Select File 4");
		
		btnSelectFile4.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
				int result = fileChooser.showOpenDialog(tfFilePath1);
				if (result == JFileChooser.APPROVE_OPTION) {
				    File selectedFile = fileChooser.getSelectedFile();
				    //System.out.println("Selected file: " + selectedFile.getAbsolutePath());
				    tfFilePath4.setText(selectedFile.getAbsolutePath());
				}
			}
		});
		
		JButton btnSelectFile5 = new JButton("Select File 5");
		
		btnSelectFile5.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
				int result = fileChooser.showOpenDialog(tfFilePath1);
				if (result == JFileChooser.APPROVE_OPTION) {
				    File selectedFile = fileChooser.getSelectedFile();
				    //System.out.println("Selected file: " + selectedFile.getAbsolutePath());
				    tfFilePath5.setText(selectedFile.getAbsolutePath());
				}
			}
		});
		
		tfFilePath5 = new JTextField();
		tfFilePath5.setColumns(10);
		
		JButton btnSelectFile6 = new JButton("Select File 6");
		
		btnSelectFile6.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
				int result = fileChooser.showOpenDialog(tfFilePath1);
				if (result == JFileChooser.APPROVE_OPTION) {
				    File selectedFile = fileChooser.getSelectedFile();
				    //System.out.println("Selected file: " + selectedFile.getAbsolutePath());
				    tfFilePath6.setText(selectedFile.getAbsolutePath());
				}
			}
		});
		
		JButton btnSelectFile7 = new JButton("Select File 7");
		
		btnSelectFile7.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
				int result = fileChooser.showOpenDialog(tfFilePath1);
				if (result == JFileChooser.APPROVE_OPTION) {
				    File selectedFile = fileChooser.getSelectedFile();
				    //System.out.println("Selected file: " + selectedFile.getAbsolutePath());
				    tfFilePath7.setText(selectedFile.getAbsolutePath());
				}
			}
		});
		
		JButton btnSelectFile8 = new JButton("Select File 8");
		
		btnSelectFile8.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
				int result = fileChooser.showOpenDialog(tfFilePath1);
				if (result == JFileChooser.APPROVE_OPTION) {
				    File selectedFile = fileChooser.getSelectedFile();
				    //System.out.println("Selected file: " + selectedFile.getAbsolutePath());
				    tfFilePath8.setText(selectedFile.getAbsolutePath());
				}
			}
		});
		
		tfFilePath6 = new JTextField();
		tfFilePath6.setColumns(10);
		
		tfFilePath7 = new JTextField();
		tfFilePath7.setColumns(10);
		
		tfFilePath8 = new JTextField();
		tfFilePath8.setColumns(10);
		
		JLabel lblDistance = new JLabel("Distance:");
		
		tfDistance = new JTextField();
		tfDistance.setColumns(10);
		
		lblAngle = new JLabel("Angle:");
		
		lblInches = new JLabel("inches");
		lblInches.setFont(new Font("Hiragino Sans GB", Font.PLAIN, 10));
		
		tfAngle = new JTextField();
		tfAngle.setColumns(10);
		
		lblDegrees = new JLabel("degrees");
		lblDegrees.setFont(new Font("Hiragino Sans GB", Font.PLAIN, 10));
		
		lblMHz = new JLabel("MHz");
		lblMHz.setFont(new Font("Hiragino Sans GB", Font.PLAIN, 10));
		
		JButton btnGraph = new JButton("Graph");
		btnGraph.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				b1.finalGraph();
			}
		});
		

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(btnSelectFile1)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(tfFilePath1, GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(btnSelectFile2)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(tfFilePath2, 406, 406, 406))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(lblFrequency)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(tfFreq, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(lblNumberOfIterations)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(tfN, 0, 0, Short.MAX_VALUE)))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblMHz)
									.addPreferredGap(ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGap(60)
											.addComponent(lblDistance)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(tfDistance, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(lblInches))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGap(59)
											.addComponent(lblAngle)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(tfAngle, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(lblDegrees)))
									.addPreferredGap(ComponentPlacement.RELATED))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(btnSelectFile3)
										.addComponent(btnSelectFile4))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(tfFilePath4, GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE)
										.addComponent(tfFilePath3, GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE)))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(btnSelectFile5)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(tfFilePath5, GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(btnSelectFile6)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(tfFilePath6, GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(btnSelectFile7)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(tfFilePath7, GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(btnSelectFile8)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(tfFilePath8, GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE)))
							.addGap(39))
						.addGroup(Alignment.TRAILING, gl_contentPane.createParallelGroup(Alignment.LEADING, false)
							.addComponent(btnGraph, Alignment.TRAILING, 0, 0, Short.MAX_VALUE)
							.addComponent(btnRun, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(tfFilePath1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSelectFile1))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSelectFile2)
						.addComponent(tfFilePath2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSelectFile3)
						.addComponent(tfFilePath3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(tfFilePath4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSelectFile4))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSelectFile5)
						.addComponent(tfFilePath5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSelectFile6)
						.addComponent(tfFilePath6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSelectFile7)
						.addComponent(tfFilePath7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSelectFile8)
						.addComponent(tfFilePath8, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(32)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFrequency)
						.addComponent(tfFreq, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(tfDistance, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblInches)
						.addComponent(lblDistance)
						.addComponent(lblMHz))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblAngle)
							.addComponent(lblNumberOfIterations)
							.addComponent(tfN, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(tfAngle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblDegrees)))
					.addGap(18)
					.addComponent(btnRun)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnGraph)
					.addGap(119))
		);

		contentPane.setLayout(gl_contentPane);

		btnRun.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
//				b.setAngle(Double.parseDouble(tfAngle.getText()));
//				b.setFrequency(Double.parseDouble(tfFreq.getText()));
//				b.setN(Integer.parseInt(tfN.getText()));
//				b.setWeighting(b.calcWeighting(Double.parseDouble(tfFreq.getText()), Double.parseDouble(tfDistance.getText()), Double.parseDouble(tfAngle.getText()), 0));
				
				
				//BeamObject b1 = new BeamObject();
				b1.setFilePath(tfFilePath1.getText());
				b1.setFrequency(Double.parseDouble(tfFreq.getText()));
				b1.setN(Integer.parseInt(tfN.getText()));
				b1.setWeighting(b1.calcWeighting(Double.parseDouble(tfFreq.getText()), Double.parseDouble(tfDistance.getText()), Double.parseDouble(tfAngle.getText()), 0));
				b1 = new BeamObject(b1.getFilePath(), b1.getFrequency(), b1.getN(), b1.getWeighting());
				
				
				BeamObject b2 = new BeamObject();
				b2.setFilePath(tfFilePath2.getText());
				b2.setFrequency(Double.parseDouble(tfFreq.getText()));
				b2.setN(Integer.parseInt(tfN.getText()));
				b2.setWeighting(b1.calcWeighting(Double.parseDouble(tfFreq.getText()), Double.parseDouble(tfDistance.getText()), Double.parseDouble(tfAngle.getText()), 1));
				b2 = new BeamObject(b2.getFilePath(), b2.getFrequency(), b2.getN(), b2.getWeighting());	
				
				
				BeamObject b3 = new BeamObject();
				b3.setFilePath(tfFilePath3.getText());
				b3.setFrequency(Double.parseDouble(tfFreq.getText()));
				b3.setN(Integer.parseInt(tfN.getText()));
				b3.setWeighting(b1.calcWeighting(Double.parseDouble(tfFreq.getText()), Double.parseDouble(tfDistance.getText()), Double.parseDouble(tfAngle.getText()), 2));
				b3 = new BeamObject(b3.getFilePath(), b3.getFrequency(), b3.getN(), b3.getWeighting());	
				
				
				BeamObject b4 = new BeamObject();
				b4.setFilePath(tfFilePath4.getText());
				b4.setFrequency(Double.parseDouble(tfFreq.getText()));
				b4.setN(Integer.parseInt(tfN.getText()));
				b4.setWeighting(b1.calcWeighting(Double.parseDouble(tfFreq.getText()), Double.parseDouble(tfDistance.getText()), Double.parseDouble(tfAngle.getText()), 3));
				b4 = new BeamObject(b4.getFilePath(), b4.getFrequency(), b4.getN(), b4.getWeighting());	
				
				
				BeamObject b5 = new BeamObject();
				b5.setFilePath(tfFilePath5.getText());
				b5.setFrequency(Double.parseDouble(tfFreq.getText()));
				b5.setN(Integer.parseInt(tfN.getText()));
				b5.setWeighting(b1.calcWeighting(Double.parseDouble(tfFreq.getText()), Double.parseDouble(tfDistance.getText()), Double.parseDouble(tfAngle.getText()), 4));
				b5 = new BeamObject(b5.getFilePath(), b5.getFrequency(), b5.getN(), b5.getWeighting());	
				
				
				BeamObject b6 = new BeamObject();
				b6.setFilePath(tfFilePath6.getText());
				b6.setFrequency(Double.parseDouble(tfFreq.getText()));
				b6.setN(Integer.parseInt(tfN.getText()));
				b6.setWeighting(b1.calcWeighting(Double.parseDouble(tfFreq.getText()), Double.parseDouble(tfDistance.getText()), Double.parseDouble(tfAngle.getText()), 5));
				b6 = new BeamObject(b6.getFilePath(), b6.getFrequency(), b6.getN(), b6.getWeighting());	
				
				
				BeamObject b7 = new BeamObject();
				b7.setFilePath(tfFilePath7.getText());
				b7.setFrequency(Double.parseDouble(tfFreq.getText()));
				b7.setN(Integer.parseInt(tfN.getText()));
				b7.setWeighting(b1.calcWeighting(Double.parseDouble(tfFreq.getText()), Double.parseDouble(tfDistance.getText()), Double.parseDouble(tfAngle.getText()), 6));
				b7 = new BeamObject(b7.getFilePath(), b7.getFrequency(), b7.getN(), b7.getWeighting());	
				
				
				BeamObject b8 = new BeamObject();
				b8.setFilePath(tfFilePath8.getText());
				b8.setFrequency(Double.parseDouble(tfFreq.getText()));
				b8.setN(Integer.parseInt(tfN.getText()));
				b8.setWeighting(b1.calcWeighting(Double.parseDouble(tfFreq.getText()), Double.parseDouble(tfDistance.getText()), Double.parseDouble(tfAngle.getText()), 7));
				b8 = new BeamObject(b8.getFilePath(), b8.getFrequency(), b8.getN(), b8.getWeighting());	
				
				
				b1.addWeightings(b1.zTimesWeightings, b2.zTimesWeightings, b3.zTimesWeightings, b4.zTimesWeightings, 
						b5.zTimesWeightings, b6.zTimesWeightings, b7.zTimesWeightings, b8.zTimesWeightings);
				
			}

		});
	}
}
