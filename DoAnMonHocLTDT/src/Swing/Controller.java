package Swing;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.io.IOException;

import Function.Graph;

public class Controller implements ActionListener {
	GiaoDien giaoDien;
	Graph graph;

	public Controller(GiaoDien giaoDien) {
		this.giaoDien = giaoDien;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String ac = e.getActionCommand();

		if (ac.equals("Update graph")) {
			giaoDien.addSetTextAreaMatrix();
		}
		if (ac.equals("AddEdge")) {
			giaoDien.addEdge();

		}
		if (ac.equals("RemoveEdge")) {
			giaoDien.removeEdge();

		}
	}

}
