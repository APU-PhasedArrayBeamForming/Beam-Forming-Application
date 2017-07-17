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

		setBounds(100, 100, 586, 370);

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
		tfFreq.setText("104.3");
		tfFreq.setColumns(10);
		
		lblNumberOfIterations = new JLabel("Number of Iterations:");
		
		tfN = new JTextField();
		tfN.setText("128");
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
		
		JLabel lblDistance = new JLabel("Distance:");
		
		tfDistance = new JTextField();
		tfDistance.setText("5.4");
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
		
		JButton btnFolder = new JButton("Select Folder");
		//populates 1-5 of text areas with outputfile1.wav outputfile2.wav and so on...
		
		btnFolder.addActionListener(new ActionListener() 
		{
		  public void actionPerformed(ActionEvent e) 
			{
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				fileChooser.setAcceptAllFileFilterUsed(false);
				int result = fileChooser.showOpenDialog(tfFilePath1);
				if (result == JFileChooser.APPROVE_OPTION) {
				    File selectedFile = fileChooser.getSelectedFile();
				    //System.out.println("Selected file: " + selectedFile.getAbsolutePath());
				    tfFilePath1.setText(selectedFile.getAbsolutePath()+"\\outputfile1.wav");
				    tfFilePath2.setText(selectedFile.getAbsolutePath()+"\\outputfile2.wav");
				    tfFilePath3.setText(selectedFile.getAbsolutePath()+"\\outputfile2.wav");
				    tfFilePath4.setText(selectedFile.getAbsolutePath()+"\\outputfile2.wav");
				    tfFilePath5.setText(selectedFile.getAbsolutePath()+"\\outputfile2.wav");
				}
			}

		});
		

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(btnSelectFile1)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(tfFilePath1, GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE))
								.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
									.addComponent(btnSelectFile2)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(tfFilePath2, 406, 406, 406))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(btnSelectFile3)
										.addComponent(btnSelectFile4))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(tfFilePath4, GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE)
										.addComponent(tfFilePath3, GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE)))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(btnSelectFile5)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(tfFilePath5, GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED, 350, Short.MAX_VALUE)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblAngle)
										.addComponent(lblDistance))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
										.addComponent(tfAngle, 0, 0, Short.MAX_VALUE)
										.addComponent(tfDistance, GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblDegrees)
										.addComponent(lblInches))))
							.addGap(39))
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblNumberOfIterations)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(tfN, 0, 0, Short.MAX_VALUE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblFrequency)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(tfFreq, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblMHz)
							.addContainerGap(335, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnFolder, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(397)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(btnGraph, Alignment.TRAILING, 0, 0, Short.MAX_VALUE)
								.addComponent(btnRun, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
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
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFrequency)
						.addComponent(tfFreq, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblMHz)
						.addComponent(lblDistance)
						.addComponent(tfDistance, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblInches))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(tfN, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblNumberOfIterations))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblAngle)
								.addComponent(tfAngle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblDegrees))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnRun)
								.addComponent(btnFolder))))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnGraph)
					.addGap(321))
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
				
				
				
				b1.addWeightings(b1.zTimesWeightings, b2.zTimesWeightings, b3.zTimesWeightings, b4.zTimesWeightings, 
						b5.zTimesWeightings);
				
			}

		});
	}
}
