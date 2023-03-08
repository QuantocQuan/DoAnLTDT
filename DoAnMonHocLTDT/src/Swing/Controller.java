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
			if(first < 0 || first >= giaoDien.graph.soDinh || last < 0 || last >= giaoDien.graph.soDinh)  {
				JOptionPane.showMessageDialog(giaoDien.btnAdd, "Ban chi co the nhap index dinh dau va dinh cuoi trong khoang quy dinh");
			}
			giaoDien.graph.addEdges(first, last);
			this.giaoDien.textAreaMatrix.setText(giaoDien.graph.printMatrix());
		}
		if (ac.equals("RemoveEdge")) {
			int first = Integer.valueOf(giaoDien.textFieldFirst.getText());
			int last = Integer.valueOf(giaoDien.textFieldLast.getText());
			if(first < 0 || first >= giaoDien.graph.soDinh || last < 0 || last >= giaoDien.graph.soDinh)  {
				JOptionPane.showMessageDialog(giaoDien.btnRemove, "Ban chi co the nhap index dinh dau va dinh cuoi trong khoang quy dinh");
			}else if(giaoDien.graph.mtk[first][last] == 0 ) {
				JOptionPane.showMessageDialog(giaoDien.btnRemove, "Canh can xoa khong ton tai");
			}
				
			giaoDien.graph.removeEdges(first, last);
			this.giaoDien.textAreaMatrix.setText(giaoDien.graph.printMatrix());

		}
		if(ac.equals("Choose file")) {
			giaoDien.fileChooser = new JFileChooser("C:\\Users\\ASUS\\OneDrive - st.hcmuaf.edu.vn\\MyCode\\Java\\DoAnLTDT\\DoAnLTDT\\DoAnMonHocLTDT");
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
	}

}
