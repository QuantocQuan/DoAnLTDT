package Swing;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;

import Function.Graph;

public class Controller implements ActionListener {
	GiaoDien giaoDien;
	Graph graph;

	public Controller(GiaoDien giaoDien) {
		try {
			graph = new Graph("E:\\CodeLTDT\\src\\graph.txt");
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.giaoDien = giaoDien;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String ac = e.getActionCommand();

		if (ac.equals("Update graph")) {
			this.giaoDien.textAreaMatrix.setText(graph.printMatrix());
		}
		if (ac.equals("AddEdge")) {
			int first = Integer.valueOf(giaoDien.textFieldFirst.getText());
			int last = Integer.valueOf(giaoDien.textFieldLast.getText());
			if(first < 0 || first >= graph.soDinh || last < 0 || last >= graph.soDinh)  {
				JOptionPane.showMessageDialog(giaoDien.btnAdd, "Ban chi co the nhap index dinh dau va dinh cuoi trong khoang quy dinh");
			}
			graph.addEdges(first, last);
			this.giaoDien.textAreaMatrix.setText(graph.printMatrix());
		}
		if (ac.equals("RemoveEdge")) {
			int first = Integer.valueOf(giaoDien.textFieldFirst.getText());
			int last = Integer.valueOf(giaoDien.textFieldLast.getText());
			if(first < 0 || first >= graph.soDinh || last < 0 || last >= graph.soDinh)  {
				JOptionPane.showMessageDialog(giaoDien.btnRemove, "Ban chi co the nhap index dinh dau va dinh cuoi trong khoang quy dinh");
			}else if(graph.mtk[first][last] == 0 ) {
				JOptionPane.showMessageDialog(giaoDien.btnRemove, "Canh can xoa khong ton tai");
			}
				
			graph.removeEdges(first, last);
			this.giaoDien.textAreaMatrix.setText(graph.printMatrix());

		}
	}

}
