package Swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Function.Graph;

public class Controller implements ActionListener{
	protected GiaoDien giaoDien ;
	protected Graph graphFunction ;
	
	public Controller(GiaoDien giaoDien) {
		super();
		this.giaoDien = giaoDien;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == giaoDien.btnLoadGraph) {
			// Code To popup an ERROR_MESSAGE Dialog.
			this.graphFunction = new Graph("C:\\Users\\ASUS\\Downloads\\New folder\\DoAnLTDT\\DoAnMonHocLTDT\\graph.txt");
			giaoDien.textAreaMatrix.setText("Load ma tran thanh cong");
		}
	}

}
