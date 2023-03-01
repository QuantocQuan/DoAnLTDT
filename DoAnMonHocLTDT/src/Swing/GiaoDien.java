package Swing;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JScrollBar;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import java.awt.FlowLayout;
import javax.swing.DefaultComboBoxModel;

public class GiaoDien extends JFrame  {

	protected JButton btnLoadGraph, btnDuyetDothi, btnCheckConnect, prinImage;
	protected JPanel contentPane, loadGraph, function;
	JTextArea textAreaMatrix;
	Controller controller = new Controller(this);

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
	 */
	public GiaoDien() {
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

		function = new JPanel();
		contentPane.add(function);
		function.setLayout(null);

		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "DFS", "BFS" }));
		comboBox.setSelectedIndex(0);
		comboBox.setBounds(157, 72, 141, 21);
		function.add(comboBox);

		prinImage = new JButton("Show graph");
		prinImage.setBounds(10, 10, 119, 21);
		function.add(prinImage);

		btnCheckConnect = new JButton("Check connect");
		btnCheckConnect.setBounds(10, 41, 141, 21);
		function.add(btnCheckConnect);

		btnDuyetDothi = new JButton("Graph browsing");
		btnDuyetDothi.setBounds(10, 72, 119, 21);
		function.add(btnDuyetDothi);

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
	}
	public void addAction() {
		this.btnLoadGraph.addActionListener(controller);
	}
	

	
}
