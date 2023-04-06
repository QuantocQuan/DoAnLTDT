package Swing;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import Function.Graph;

public class Controller implements ActionListener {
	View giaoDien;

	public Controller(View giaoDien) {
		this.giaoDien = giaoDien;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String ac = e.getActionCommand();

		if (ac.equals("Show graph")) {
			this.giaoDien.textAreaMatrix.setText(giaoDien.graph.printMatrix());
		}
		if (ac.equals("AddEdge")) {

			try {
				int first = Integer.valueOf(giaoDien.textFieldFirst.getText());
				int last = Integer.valueOf(giaoDien.textFieldLast.getText());
				String value = giaoDien.textFieldValue.getText();

				if (first < 0 || first >= giaoDien.graph.soDinh || last < 0 || last >= giaoDien.graph.soDinh) {
					JOptionPane.showMessageDialog(giaoDien, "You can only enter 0 <= vertex < " + giaoDien.graph.soDinh,
							"Error", JOptionPane.ERROR_MESSAGE);
				}else
				if(first == last) {
					JOptionPane.showMessageDialog(giaoDien, "Khong duoc add khuyen",
							"Error", JOptionPane.ERROR_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(giaoDien, "Khong duoc add canh song song",
							"Error", JOptionPane.ERROR_MESSAGE);
				}
				giaoDien.graph.addEdges(first, last, value);
				this.giaoDien.textAreaMatrix.setText(giaoDien.graph.printMatrix());
			} catch (NumberFormatException c) {
				JOptionPane.showMessageDialog(giaoDien, "Invalid value", "Error", JOptionPane.ERROR_MESSAGE);
			}

		}
		if (ac.equals("RemoveEdge")) {
			System.out.println("ban vua nhan ");
			try {
				int first = Integer.valueOf(giaoDien.textFieldFirst.getText());
				int last = Integer.valueOf(giaoDien.textFieldLast.getText());
				if (first < 0 || first >= giaoDien.graph.soDinh || last < 0 || last >= giaoDien.graph.soDinh) {
					JOptionPane.showMessageDialog(giaoDien, "You can only enter 0 <= vertex <" + giaoDien.graph.soDinh,
							"Error", JOptionPane.ERROR_MESSAGE);
				} else if (giaoDien.graph.condition(giaoDien.graph.mtk, first, last)) {
					JOptionPane.showMessageDialog(giaoDien, "Edge to be deleted does not exist", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
				giaoDien.graph.removeEdges(first, last);
				this.giaoDien.textAreaMatrix.setText(giaoDien.graph.printMatrix());
			} catch (NumberFormatException c) {
				JOptionPane.showMessageDialog(giaoDien,
						"The first and last vertices to be deleted cannot be left blank", "Error",
						JOptionPane.ERROR_MESSAGE);
			}

		}
		if (ac.equals("Choose file")) {
			giaoDien.fileChooser = new JFileChooser("C:\\Users\\ASUS\\OneDrive - st.hcmuaf.edu.vn\\MyCode\\Java\\DoAnLTDT\\DoAnLTDT\\DoAnLTDT\\DoAnMonHocLTDT");
			int returnVal = giaoDien.fileChooser.showOpenDialog(giaoDien);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = giaoDien.fileChooser.getSelectedFile();
				// This is where a real application would open the file.
				System.out.println(file.toString());
				giaoDien.pathFile = file.toString();
				try {
					giaoDien.graph = new Graph(giaoDien.pathFile);
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		}
		if (ac.equals("Graph browsing")) {
			int start;
			String strStart = giaoDien.textFieldDinhChon.getText();
			if (!strStart.equals(""))
				start = Integer.valueOf(strStart);

			else
				start = 0;
			if (giaoDien.rdbtnDFS.isSelected()) {

				JOptionPane.showMessageDialog(giaoDien,
						"Browse the graph by DFS: " + giaoDien.graph.DFSLinkkeList(start));

			} else if (giaoDien.rdbtnBFS.isSelected()) {

				JOptionPane.showMessageDialog(giaoDien,
						"Browse the graph by BFS: " + giaoDien.graph.BFSLinkedlist(start));
			}
		}if(ac.equals("Check connect")) { 
			if(giaoDien.graph.checkConnectStrongly()) {
				JOptionPane.showMessageDialog(giaoDien,"Do thi lien thong manh");
			}else if(giaoDien.graph.checkConnectWeekly()) {
				JOptionPane.showMessageDialog(giaoDien,"Do thi lien thong yeu");
			}else {
				JOptionPane.showMessageDialog(giaoDien,"Do thi khong lien thong");
			}
		}
	}

}
