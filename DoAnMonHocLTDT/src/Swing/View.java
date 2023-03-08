package Swing;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

import Function.Graph;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
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
import javax.swing.JFileChooser;

public class View extends JFrame {

	public JButton btnLoadGraph, btnCheckConnect, prinImage, btnRemove, btnAdd, btnSelectFile ;
	public JPanel contentPane, loadGraph, jPanelFunc;
	public JTextArea textAreaMatrix;
	public ActionListener controller;
	public JTextField textFieldFirst;
	public JTextField textFieldLast;
	public JLabel lblDinhDau;
	public JLabel lblNewLabel_1;
	public JLabel lblDinhCuoi;
	public JFileChooser fileChooser;
	public String pathFile;
	public Graph graph ;
	public JRadioButton rdbtnDFS;
	public JRadioButton rdbtnBFS;
	public JTextField textFieldDinhChon;
	public JLabel lblDuyetTheo;
	public  JLabel lblDinhBatDauDuyet;
	public JButton btnDuyetDothi;

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
		setBounds(100, 100, 565, 409);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

		jPanelFunc = new JPanel();
		contentPane.add(jPanelFunc);
		jPanelFunc.setLayout(null);

		prinImage = new JButton("Show graph");
		prinImage.setBounds(10, 10, 119, 21);
		jPanelFunc.add(prinImage);

		btnCheckConnect = new JButton("Check connect");
		btnCheckConnect.setBounds(10, 41, 141, 21);
		jPanelFunc.add(btnCheckConnect);
		lblDinhCuoi = new JLabel("\u0110\u1EC9nh cu\u1ED1i");
		lblDinhCuoi.setBounds(86, 137, 65, 17);
		jPanelFunc.add(lblDinhCuoi);
		
		textFieldDinhChon = new JTextField();
		textFieldDinhChon.setBounds(353, 26, 22, 19);
		jPanelFunc.add(textFieldDinhChon);
		textFieldDinhChon.setColumns(10);
		
		 rdbtnDFS = new JRadioButton("DFS");
		rdbtnDFS.setBounds(308, 51, 53, 21);
		jPanelFunc.add(rdbtnDFS);
		
		 rdbtnBFS = new JRadioButton("BFS");
		rdbtnBFS.setBounds(363, 51, 53, 21);
		jPanelFunc.add(rdbtnBFS);
		
		ButtonGroup btnGroup = new ButtonGroup();
		btnGroup.add(rdbtnDFS);
		btnGroup.add(rdbtnBFS);
		
		lblDuyetTheo = new JLabel("Duy\u1EC7t theo: ");
		lblDuyetTheo.setBounds(219, 53, 83, 21);
		jPanelFunc.add(lblDuyetTheo);
		
		 lblDinhBatDauDuyet = new JLabel("\u0110\u1EC9nh b\u1EAFt \u0111\u1EA7u duy\u1EC7t: ");
		lblDinhBatDauDuyet.setBounds(217, 27, 126, 18);
		jPanelFunc.add(lblDinhBatDauDuyet);
		

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

		lblDinhDau = new JLabel("\u0110\u1EC9nh \u0111\u1EA7u:");
		lblDinhDau.setBounds(85, 107, 65, 18);
		jPanelFunc.add(lblDinhDau);

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

		btnDuyetDothi = new JButton("Graph browsing");
		btnDuyetDothi.setBounds(342, 75, 119, 21);
		jPanelFunc.add(btnDuyetDothi);
		
		btnLoadGraph = new JButton("Update graph");
		btnLoadGraph.setBounds(344, 12, 118, 21);
		loadGraph.add(btnLoadGraph);
		
		btnSelectFile = new JButton("Choose file");
		btnSelectFile.setBounds(344, 52, 118, 21);
		loadGraph.add(btnSelectFile);
		 
		
		
		

		controller = new Controller(this);
	}

	public void addAction() {
		this.btnLoadGraph.addActionListener(controller);
		this.btnAdd.addActionListener(controller);
		this.btnRemove.addActionListener(controller);
		this.btnDuyetDothi.addActionListener(controller);
		this.btnCheckConnect.addActionListener(controller);
		this.btnSelectFile.addActionListener(controller);
	}
}