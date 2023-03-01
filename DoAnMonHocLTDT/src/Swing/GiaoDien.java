package Swing;

import java.awt.EventQueue;

import javax.swing.JFrame;
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

public class GiaoDien extends JFrame {

	private JPanel contentPane;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 428, 311);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Duyệt theo chiều sâu", "Duyệt theo chiều rộng"}));
		comboBox.setSelectedIndex(0);
		comboBox.setBounds(118, 72, 141, 21);
		panel.add(comboBox);
		
		JButton prinImage = new JButton("Biểu diễn đồ thị");
		prinImage.setBounds(10, 10, 119, 21);
		panel.add(prinImage);
		
		JButton btnCheckConnect = new JButton("KIểm tra tính liên thông");
		btnCheckConnect.setBounds(10, 41, 141, 21);
		panel.add(btnCheckConnect);
		
		JButton btnDuyetDothi = new JButton("Duyệt đồ thị");
		btnDuyetDothi.setBounds(10, 72, 98, 21);
		panel.add(btnDuyetDothi);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(10, 10, 249, 112);
		panel_1.add(textArea);
		
		JButton btnLoadGraph = new JButton("Cập nhật đồ thị");
		btnLoadGraph.setBounds(276, 12, 118, 21);
		panel_1.add(btnLoadGraph);
	}
}
