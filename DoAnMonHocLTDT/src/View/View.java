package View;

import java.awt.Color;
import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.BorderFactory;
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
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

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
	public JFileChooser fileChooser;
	public String pathFile;
	
	public JRadioButton rdbtnDFS;
	public JRadioButton rdbtnBFS;
	public JTextField textFieldDinhChon;
	public JLabel lblDuyetTheo;
	public JLabel lblDinhBatDauDuyet;
	public JButton btnDuyetDothi;
	public JButton btnSave;
	public JButton btnRemoveVertex;
	public JButton btnAddVertex;
	public GraphPanel graphPanel;
	public GraphPanelDFSAndBFS graphPanelDFSAndBFS;
	public JTextField textFieldDinhXoa;
	public JPanel panelDinhXoa;
	public JLabel lblDinhXoa;
	public JTextField textFieldLast;
	public JTextField textFieldFirst;
	public JLabel lblDinhDau;
	public JLabel lblDinhCuoi;
	public JLabel lblValue;
	public JTextField textFieldValue;

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

		btnCheckConnect = new JButton("Kiểm tra liên thông");
		btnCheckConnect.setBounds(459, 13, 157, 42);
		jPanelFunc.add(btnCheckConnect);

		textFieldDinhChon = new JTextField();
		textFieldDinhChon.setBounds(511, 96, 44, 19);
		jPanelFunc.add(textFieldDinhChon);
		textFieldDinhChon.setColumns(10);

		rdbtnDFS = new JRadioButton("DFS");
		rdbtnDFS.setBounds(448, 121, 53, 21);
		jPanelFunc.add(rdbtnDFS);

		rdbtnBFS = new JRadioButton("BFS");
		rdbtnBFS.setBounds(511, 121, 53, 21);
		jPanelFunc.add(rdbtnBFS);

		ButtonGroup btnGroup = new ButtonGroup();
		btnGroup.add(rdbtnDFS);
		btnGroup.add(rdbtnBFS);

		lblDuyetTheo = new JLabel("Duy\u1EC7t theo: ");
		lblDuyetTheo.setBounds(368, 121, 85, 21);
		jPanelFunc.add(lblDuyetTheo);

		lblDinhBatDauDuyet = new JLabel("\u0110\u1EC9nh b\u1EAFt \u0111\u1EA7u duy\u1EC7t: ");
		lblDinhBatDauDuyet.setBounds(368, 92, 114, 23);
		jPanelFunc.add(lblDinhBatDauDuyet);

		btnRemove = new JButton("Xóa cạnh");
		btnRemove.setBounds(134, 84, 114, 42);
		jPanelFunc.add(btnRemove);
		btnRemove.addActionListener(controller);

		loadGraph = new JPanel();
		contentPane.add(loadGraph);
		loadGraph.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 303, 225);
		loadGraph.add(scrollPane);

		textAreaMatrix = new JTextArea();
		scrollPane.setViewportView(textAreaMatrix);
		textAreaMatrix.setEditable(false);

		btnDuyetDothi = new JButton("Duyệt đồ thị");
		btnDuyetDothi.setBounds(441, 163, 114, 42);
		jPanelFunc.add(btnDuyetDothi);

		btnSelectFile = new JButton("Open File");
		btnSelectFile.setBounds(10, 13, 114, 42);
		jPanelFunc.add(btnSelectFile);

		btnLoadGraph = new JButton("Hiển thị");
		btnLoadGraph.setBounds(317, 14, 114, 42);
		jPanelFunc.add(btnLoadGraph);

		btnAdd = new JButton("Thêm cạnh");
		btnAdd.setBounds(10, 84, 114, 42);
		jPanelFunc.add(btnAdd);

		btnSave = new JButton("Save File");
		btnSave.setBounds(168, 13, 114, 42);
		jPanelFunc.add(btnSave);

		btnAddVertex = new JButton("Thêm đỉnh");
	
		btnAddVertex.setBounds(10, 162, 114, 44);
		jPanelFunc.add(btnAddVertex);

		btnRemoveVertex = new JButton("Xóa đỉnh");
		btnRemoveVertex.setBounds(134, 163, 114, 42);
		jPanelFunc.add(btnRemoveVertex);

		btnAdd.addActionListener(controller);
		controller = new Controller(this);
		textFieldDinhXoa = new JTextField();
		lblDinhXoa = new JLabel("Nhập Đỉnh cần xóa: ");
		
		lblDinhDau = new JLabel("Nhập đỉnh đầu: ");
		lblDinhCuoi = new JLabel("Nhập đỉnh cuối: ");
		lblValue = new JLabel("Nhập trọng số: ");
		textFieldFirst = new JTextField();
		textFieldLast = new JTextField();
		textFieldValue = new JTextField();
		
		
		
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
	public void hienThi(GraphPanel graphPanel) {
		  this.graphPanel = graphPanel;	
			loadGraph.add(this.graphPanel);	
			this.graphPanel.setBounds(323, 10, 393, 325);
			
	}
	public void hienThiDFSOrBFS(GraphPanelDFSAndBFS graphPanel) {
		  this.graphPanelDFSAndBFS = graphPanel;	
			loadGraph.add(this.graphPanelDFSAndBFS);
			this.graphPanelDFSAndBFS.setBounds(323, 10, 393, 325);
	}
}