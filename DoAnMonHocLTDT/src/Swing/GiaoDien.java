package Swing;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Function.Graph;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JScrollBar;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import java.awt.FlowLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;

public class GiaoDien extends JFrame  {

	public JButton btnLoadGraph, btnDuyetDothi, btnCheckConnect, prinImage,btnRemove,btnAdd;
	public JPanel contentPane, loadGraph, jPanelFunc;
	public JTextArea textAreaMatrix;
	public ActionListener controller;
	public JTextField textFieldFirst;
	public JTextField textFieldLast;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;

	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GiaoDien frame = new GiaoDien();

					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 * @throws NumberFormatException 
	 */
	public GiaoDien() throws NumberFormatException, IOException {
<<<<<<< HEAD
		graph = new Graph("C:\\Users\\ASUS\\OneDrive - st.hcmuaf.edu.vn\\MyCode\\Java\\DoAnLTDT\\DoAnLTDT\\DoAnMonHocLTDT\\graph.txt");
=======

>>>>>>> 376ff3a9dfe1d781c4fe57b4422703fce6753c42
		GUI();
		addAction();
	}

	public void GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 507, 379);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

		jPanelFunc = new JPanel();
		contentPane.add(jPanelFunc);
		jPanelFunc.setLayout(null);

		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "DFS", "BFS" }));
		comboBox.setSelectedIndex(0);
		comboBox.setBounds(151, 72, 141, 21);
		jPanelFunc.add(comboBox);

		prinImage = new JButton("Show graph");
		prinImage.setBounds(10, 10, 119, 21);
		jPanelFunc.add(prinImage);

		btnCheckConnect = new JButton("Check connect");
		btnCheckConnect.setBounds(10, 41, 141, 21);
		jPanelFunc.add(btnCheckConnect);

		btnDuyetDothi = new JButton("Graph browsing");
		btnDuyetDothi.setBounds(10, 72, 119, 21);
		jPanelFunc.add(btnDuyetDothi);
		
		textFieldFirst = new JTextField();
		textFieldFirst.setBounds(161, 103, 22, 22);
		jPanelFunc.add(textFieldFirst);
		textFieldFirst.setColumns(10);
		
		textFieldLast = new JTextField();
		textFieldLast.setBounds(161, 135, 22, 21);
		jPanelFunc.add(textFieldLast);
		textFieldLast.setColumns(10);
		
		btnRemove = new JButton("RemoveEdge");
		btnRemove.setBounds(193, 135, 91, 21);
		jPanelFunc.add(btnRemove);
		
		btnAdd = new JButton("AddEdge");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAdd.setBounds(195, 103, 85, 21);
		jPanelFunc.add(btnAdd);
		
		lblNewLabel = new JLabel("\u0110\u1EC9nh \u0111\u1EA7u:");
		lblNewLabel.setBounds(85, 107, 65, 18);
		jPanelFunc.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("\u0110\u1EC9nh cu\u1ED1i");
		lblNewLabel_1.setBounds(86, 137, 65, 17);
		jPanelFunc.add(lblNewLabel_1);

		loadGraph = new JPanel();
		contentPane.add(loadGraph);
		loadGraph.setLayout(null);

		textAreaMatrix = new JTextArea();
		textAreaMatrix.setEditable(false);
		textAreaMatrix.setBounds(10, 10, 304, 146);
		loadGraph.add(textAreaMatrix);

		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(297, 10, 17, 146);
		loadGraph.add(scrollBar);

		btnLoadGraph = new JButton("Update graph");
		btnLoadGraph.setBounds(344, 12, 118, 21);
		loadGraph.add(btnLoadGraph);
		
	    controller = new Controller(this);
	}
	public void addAction() {
		this.btnLoadGraph.addActionListener(controller);
		this.btnAdd.addActionListener(controller);
		this.btnRemove.addActionListener(controller);
	}
	
	
}