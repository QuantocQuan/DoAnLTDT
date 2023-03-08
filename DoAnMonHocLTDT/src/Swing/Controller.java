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

		if (ac.equals("Update graph")) {
			this.giaoDien.textAreaMatrix.setText(giaoDien.graph.printMatrix());
		}
		if (ac.equals("AddEdge")) {
			int first = Integer.valueOf(giaoDien.textFieldFirst.getText());
			int last = Integer.valueOf(giaoDien.textFieldLast.getText());
			if (first < 0 || first >= giaoDien.graph.soDinh || last < 0 || last >= giaoDien.graph.soDinh) {
				JOptionPane.showMessageDialog(giaoDien, "Bạn chỉ có thể nhập  0 <= đỉnh < " + giaoDien.graph.soDinh,	"Error", JOptionPane.ERROR_MESSAGE);
			}
			giaoDien.graph.addEdges(first, last);
			this.giaoDien.textAreaMatrix.setText(giaoDien.graph.printMatrix());
		}
		if (ac.equals("RemoveEdge")) {
			int first = Integer.valueOf(giaoDien.textFieldFirst.getText());
			int last = Integer.valueOf(giaoDien.textFieldLast.getText());
			if (first < 0 || first >= giaoDien.graph.soDinh || last < 0 || last >= giaoDien.graph.soDinh) {
				JOptionPane.showMessageDialog(giaoDien, "Bạn chỉ có thể nhập  0 <= đỉnh < " + giaoDien.graph.soDinh,	"Error", JOptionPane.ERROR_MESSAGE);
			} else if (giaoDien.graph.mtk[first][last] == 0) {
				JOptionPane.showMessageDialog(giaoDien, "Cạnh cần xóa không tồn tại", "Error", JOptionPane.ERROR_MESSAGE);
			}

			giaoDien.graph.removeEdges(first, last);
			this.giaoDien.textAreaMatrix.setText(giaoDien.graph.printMatrix());
		}
		if(ac.equals("Choose file")) {
			giaoDien.fileChooser = new JFileChooser("E:\\DoAnLTDT\\DoAnMonHocLTDT");
			int returnVal = giaoDien.fileChooser.showOpenDialog(giaoDien);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
	            File file =giaoDien.fileChooser.getSelectedFile();
	            //This is where a real application would open the file.
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
			System.out.println("Ban vua nhan nut");
			if (giaoDien.rdbtnDFS.isSelected()) {
				int start = Integer.valueOf(giaoDien.textFieldDinhChon.getText());
				JOptionPane.showMessageDialog(giaoDien, "Duyệt đồ thị theo DFS: " + giaoDien.graph.DFSLinkkeList(start));

			} else if (giaoDien.rdbtnBFS.isSelected()) {
				int start = Integer.valueOf(giaoDien.textFieldDinhChon.getText());
				JOptionPane.showMessageDialog(giaoDien, "Duyệt đồ thị theo BFS: " + giaoDien.graph.BFSLinkedlist(start));
			}
		}
	}

}
