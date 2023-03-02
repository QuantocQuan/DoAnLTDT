package Swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

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
			try {
				this.graphFunction = new Graph("C:\\Users\\ASUS\\Downloads\\New folder\\DoAnLTDT\\DoAnMonHocLTDT\\graph.txt");
			} catch (NumberFormatException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			giaoDien.textAreaMatrix.setText(graphFunction.printMatrix());
		}
	}

}
