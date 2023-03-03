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
	public JTextField textFieldaddFirst;
	public JTextField textFieldRemoveFirst;
	public JTextField textFieldaddLast;
	public JTextField textFieldRemoveLast;
	public Graph graph;
	
	

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
		graph = new Graph("E:\\CodeLTDT\\src\\graph.txt");
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
		
		textFieldaddFirst = new JTextField();
		textFieldaddFirst.setBounds(105, 103, 36, 21);
		jPanelFunc.add(textFieldaddFirst);
		textFieldaddFirst.setColumns(10);
		
		textFieldRemoveFirst = new JTextField();
		textFieldRemoveFirst.setBounds(105, 131, 36, 20);
		jPanelFunc.add(textFieldRemoveFirst);
		textFieldRemoveFirst.setColumns(10);
		
		textFieldaddLast = new JTextField();
		textFieldaddLast.setBounds(151, 103, 36, 21);
		jPanelFunc.add(textFieldaddLast);
		textFieldaddLast.setColumns(10);
		
		textFieldRemoveLast = new JTextField();
		textFieldRemoveLast.setBounds(151, 131, 36, 20);
		jPanelFunc.add(textFieldRemoveLast);
		textFieldRemoveLast.setColumns(10);
		
		btnRemove = new JButton("RemoveEdge");
		btnRemove.setBounds(10, 130, 91, 21);
		jPanelFunc.add(btnRemove);
		
		btnAdd = new JButton("AddEdge");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAdd.setBounds(10, 103, 85, 21);
		jPanelFunc.add(btnAdd);

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
	/**
	 * phuong thuc in ra ma tran tren textArea
	 */
	public void addSetTextAreaMatrix() {
		this.textAreaMatrix.setText(graph.printMatrix());
	}
	/**
	 * phuong thuc them canh 
	 */
	public void addEdge() {
		int first = Integer.valueOf(textFieldaddFirst.getText());
		int last = Integer.valueOf(textFieldaddLast.getText());
		if(first < 0 || first >= graph.soDinh || last < 0 || last >= graph.soDinh)  {
			JOptionPane.showMessageDialog(btnRemove, "Ban chi co the nhap index dinh dau va dinh cuoi trong khoang quy dinh");
		}
		graph.addEdges(first, last);
	}
	/**
	 * phuong thuc xoa canh
	 */
	public void removeEdge() {
		int first = Integer.valueOf(textFieldRemoveFirst.getText());
		int last = Integer.valueOf(textFieldRemoveLast.getText());
		if(first < 0 || first >= graph.soDinh || last < 0 || last >= graph.soDinh)  {
			JOptionPane.showMessageDialog(btnRemove, "Ban chi co the nhap index dinh dau va dinh cuoi trong khoang quy dinh");
		}else if(graph.mtk[first][last] == 0 ) {
			JOptionPane.showMessageDialog(btnRemove, "Canh can xoa khong ton tai");
		}
			
		graph.removeEdges(first, last);
	}
}