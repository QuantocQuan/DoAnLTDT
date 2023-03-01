package Swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener{
	protected GiaoDien giaoDien ;
	
	public Controller(GiaoDien giaoDien) {
		super();
		this.giaoDien = giaoDien;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == giaoDien.btnLoadGraph) {
			// Code To popup an ERROR_MESSAGE Dialog.
			giaoDien.textAreaMatrix.setText("oke");
		}
	}

}
