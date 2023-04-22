package View;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import java.awt.FlowLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JFileChooser;
import java.awt.ScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Controller.Controller;
import Model.Graph;

public class View extends JFrame {

	public JButton btnLoadGraph, btnCheckConnect, btnRemove, btnAdd, btnSelectFile ;
	public JPanel contentPane, loadGraph, jPanelFunc;
	public JTextArea textAreaMatrix;
	public ActionListener controller;
	public JTextField textFieldFirst;
	public JTextField textFieldLast;
	public JLabel lblDinhDau;
	public JLabel lblDinhCuoi;
	public JFileChooser fileChooser;
	public String pathFile;
	public Graph graph = new Graph() ;
	public JRadioButton rdbtnDFS;
	public JRadioButton rdbtnBFS;
	public JTextField textFieldDinhChon;
	public JLabel lblDuyetTheo;
	public  JLabel lblDinhBatDauDuyet;
	public JButton btnDuyetDothi;
	public JTextField textFieldValue;
	private JButton btnSave;
	public JTextField textFieldThemDinh;
	private JButton btnRemoveVertex;
	private JButton btnAddVertex;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					View frame = new View();

					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	/**
	 * Create the frame.
	 * 
	 * @throws IOException
	 * @throws NumberFormatException
	 */
	public View() throws NumberFormatException, IOException {

//		Graph graph = new Graph(pathFile);
		GUI();
		addAction();
	}

	public void GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 538);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

		jPanelFunc = new JPanel();
		contentPane.add(jPanelFunc);
		jPanelFunc.setLayout(null);

		btnCheckConnect = new JButton("Check connect");
		btnCheckConnect.setBounds(459, 13, 134, 29);
		jPanelFunc.add(btnCheckConnect);
		lblDinhCuoi = new JLabel("Đỉnh cuối :");
		lblDinhCuoi.setBounds(10, 91, 67, 18);
		jPanelFunc.add(lblDinhCuoi);
		
		textFieldDinhChon = new JTextField();
		textFieldDinhChon.setBounds(514, 65, 44, 19);
		jPanelFunc.add(textFieldDinhChon);
		textFieldDinhChon.setColumns(10);
		
		 rdbtnDFS = new JRadioButton("DFS");
		rdbtnDFS.setBounds(474, 103, 53, 21);
		jPanelFunc.add(rdbtnDFS);
		
		 rdbtnBFS = new JRadioButton("BFS");
		rdbtnBFS.setBounds(540, 103, 53, 21);
		jPanelFunc.add(rdbtnBFS);
		
		ButtonGroup btnGroup = new ButtonGroup();
		btnGroup.add(rdbtnDFS);
		btnGroup.add(rdbtnBFS);
		
		lblDuyetTheo = new JLabel("Duy\u1EC7t theo: ");
		lblDuyetTheo.setBounds(390, 103, 85, 21);
		jPanelFunc.add(lblDuyetTheo);
		
		 lblDinhBatDauDuyet = new JLabel("\u0110\u1EC9nh b\u1EAFt \u0111\u1EA7u duy\u1EC7t: ");
		lblDinhBatDauDuyet.setBounds(390, 63, 114, 23);
		jPanelFunc.add(lblDinhBatDauDuyet);
		

		textFieldFirst = new JTextField();
		textFieldFirst.setBounds(74, 58, 37, 22);
		jPanelFunc.add(textFieldFirst);
		textFieldFirst.setColumns(10);

		textFieldLast = new JTextField();
		textFieldLast.setBounds(74, 90, 37, 21);
		jPanelFunc.add(textFieldLast);
		textFieldLast.setColumns(10);

		btnRemove = new JButton("RemoveEdge");
		btnRemove.setBounds(105, 149, 124, 21);
		jPanelFunc.add(btnRemove);
		btnRemove.addActionListener(controller);

		lblDinhDau = new JLabel("\u0110\u1EC9nh \u0111\u1EA7u:");
		lblDinhDau.setBounds(10, 59, 76, 18);
		jPanelFunc.add(lblDinhDau);

		loadGraph = new JPanel();
		contentPane.add(loadGraph);
		loadGraph.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 606, 225);
		loadGraph.add(scrollPane);
				
						textAreaMatrix = new JTextArea();
						scrollPane.setViewportView(textAreaMatrix);
						textAreaMatrix.setEditable(false);

		btnDuyetDothi = new JButton("Graph browsing");
		btnDuyetDothi.setBounds(455, 149, 138, 21);
		jPanelFunc.add(btnDuyetDothi);
		
		textFieldValue = new JTextField();
		textFieldValue.setBounds(64, 123, 96, 19);
		jPanelFunc.add(textFieldValue);
		textFieldValue.setColumns(10);
		
		JLabel lblValue = new JLabel("Value :");
		lblValue.setBounds(10, 120, 44, 21);
		jPanelFunc.add(lblValue);
		
		btnSelectFile = new JButton("Choose file");
		btnSelectFile.setBounds(25, 13, 124, 29);
		jPanelFunc.add(btnSelectFile);
		
		btnLoadGraph = new JButton("Show graph");
		btnLoadGraph.setBounds(324, 14, 112, 27);
		jPanelFunc.add(btnLoadGraph);
		
				btnAdd = new JButton("AddEdge");
				btnAdd.setBounds(10, 149, 85, 21);
				jPanelFunc.add(btnAdd);
				
				 btnSave = new JButton("Save");
				btnSave.setBounds(181, 13, 114, 29);
				jPanelFunc.add(btnSave);
				
				JLabel lblDinh = new JLabel("Đỉnh: ");
				lblDinh.setBounds(10, 208, 44, 18);
				jPanelFunc.add(lblDinh);
				
				textFieldThemDinh = new JTextField();
				textFieldThemDinh.setBounds(49, 208, 37, 19);
				jPanelFunc.add(textFieldThemDinh);
				textFieldThemDinh.setColumns(10);
				
				 btnAddVertex = new JButton("Thêm đỉnh");
				btnAddVertex.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					}
				});
				btnAddVertex.setBounds(230, 208, 124, 21);
				jPanelFunc.add(btnAddVertex);
				
				 btnRemoveVertex = new JButton("Xóa đỉnh");
				btnRemoveVertex.setBounds(105, 207, 106, 21);
				jPanelFunc.add(btnRemoveVertex);
				btnAdd.addActionListener(controller);
		controller = new Controller(this);
	}

	public void addAction() {
		this.btnLoadGraph.addActionListener(controller);
		this.btnAdd.addActionListener(controller);
		this.btnRemove.addActionListener(controller);
		this.btnDuyetDothi.addActionListener(controller);
		this.btnCheckConnect.addActionListener(controller);
		this.btnSelectFile.addActionListener(controller);
		this.btnSave.addActionListener(controller);
		this.btnRemoveVertex.addActionListener(controller);
		this.btnAddVertex.addActionListener(controller);
	}
}