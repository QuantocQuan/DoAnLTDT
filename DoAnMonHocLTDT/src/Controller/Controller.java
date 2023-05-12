package Controller;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;


import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.channels.UnsupportedAddressTypeException;
import java.nio.file.FileSystemNotFoundException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Model.Graph;
import View.GraphPanel;
import View.GraphPanelDFSAndBFS;
import View.View;

public class Controller implements ActionListener {
	View giaoDien;
    Graph graph = new Graph() ;

	public Controller(View giaoDien) {
		this.giaoDien = giaoDien;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String ac = e.getActionCommand();

		if (ac.equals("Hiển thị")) {
			this.giaoDien.textAreaMatrix.setText(graph.printMatrix());
			String[] vertexLabels = new String[graph.soDinh];
			for (int i = 0; i < vertexLabels.length; i++) {
				vertexLabels[i] = ""+i;
			}
     		GraphPanel graphPanel = new GraphPanel(graph.mtk, vertexLabels);
			giaoDien.hienThi(graphPanel);
		}
		if (ac.equals("Thêm cạnh")) {
				 try {
					 GridLayout gridLayout = new GridLayout(3,3);
						gridLayout.setVgap(10);
						JPanel jPanel = new JPanel(gridLayout);
						jPanel.add(giaoDien.lblDinhDau);
						jPanel.add(giaoDien.textFieldFirst);
						jPanel.add(giaoDien.lblDinhCuoi);
						jPanel.add(giaoDien.textFieldLast);
						jPanel.add(giaoDien.lblValue);
						jPanel.add(giaoDien.textFieldValue);
				
					int option = JOptionPane.showConfirmDialog(null, jPanel, "Thêm cạnh",
							JOptionPane.OK_CANCEL_OPTION);
					int first = Integer.valueOf(giaoDien.textFieldFirst.getText());
					int last = Integer.valueOf(giaoDien.textFieldLast.getText());
					int value = Integer.valueOf(giaoDien.textFieldValue.getText());
					if (option == JOptionPane.OK_OPTION) {
						if (first < 0 || first >= graph.soDinh || last < 0 || last >= graph.soDinh) {
							JOptionPane.showMessageDialog(giaoDien,
									"Đỉnh phải nằm trong phạm vi từ 0 đến " + graph.soDinh, "Error",
									JOptionPane.ERROR_MESSAGE);
						} else if (first == last) {
							JOptionPane.showMessageDialog(giaoDien, "Không được thêm khuyên", "Error",
									JOptionPane.ERROR_MESSAGE);
						} else if (graph.mtk[first][last] != 0) {
							JOptionPane.showMessageDialog(giaoDien, "Không được thêm cạnh song song", "Error",
									JOptionPane.ERROR_MESSAGE);
						} else {
							graph.addEdges(first, last, value);
							this.giaoDien.textAreaMatrix.setText(graph.printMatrix());
							String[] vertexLabels = new String[graph.soDinh];
							for (int i = 0; i < vertexLabels.length; i++) {
								vertexLabels[i] = ""+i;
							}
				     		GraphPanel graphPanel = new GraphPanel(graph.mtk, vertexLabels);
							giaoDien.hienThi(graphPanel);
						}
						

					} 
				} catch (Exception e2) {
					System.out.println("");
				}
			

		}
		if (ac.equals("Xóa cạnh")) {
			
				try {
					GridLayout gridLayout = new GridLayout(2,2);
					gridLayout.setVgap(10);
					JPanel jPanel = new JPanel(gridLayout);
					jPanel.add(giaoDien.lblDinhDau);
					jPanel.add(giaoDien.textFieldFirst);
					jPanel.add(giaoDien.lblDinhCuoi);
					jPanel.add(giaoDien.textFieldLast);

					int option = JOptionPane.showConfirmDialog(null, jPanel, "Xóa cạnh",
							JOptionPane.OK_CANCEL_OPTION);
					int first = Integer.valueOf(giaoDien.textFieldFirst.getText());
					int last = Integer.valueOf(giaoDien.textFieldLast.getText());
					if (option == JOptionPane.OK_OPTION) {
						if (first < 0 || first >= graph.soDinh || last < 0 || last >= graph.soDinh) {
							JOptionPane.showMessageDialog(giaoDien,
									"Đỉnh phải nằm trong phạm vi từ 0 đến " + graph.soDinh, "Error",
									JOptionPane.ERROR_MESSAGE);
						} else if (graph.mtk[first][last] == 0) {
							JOptionPane.showMessageDialog(giaoDien, "Cạnh không tồn tại", "Error",
									JOptionPane.ERROR_MESSAGE);
						} else {
							graph.removeEdges(first, last);
							this.giaoDien.textAreaMatrix.setText(graph.printMatrix());
							String[] vertexLabels = new String[graph.soDinh];
							for (int i = 0; i < vertexLabels.length; i++) {
								vertexLabels[i] = ""+i;
							}
				     		GraphPanel graphPanel = new GraphPanel(graph.mtk, vertexLabels);
							giaoDien.hienThi(graphPanel);
						}
					} 
				} catch (Exception e2) {
					System.out.println("");
				}
		}

		
		if (ac.equals("Open File")) {
			giaoDien.fileChooser = new JFileChooser("C:\\Users\\DATA\\OneDrive\\Desktop\\DoThi");
			int returnVal = giaoDien.fileChooser.showOpenDialog(giaoDien);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = giaoDien.fileChooser.getSelectedFile();
				// This is where a real application would open the file.
				System.out.println(file.getName());
				giaoDien.pathFile = file.toString();
				try {
					graph = new Graph(giaoDien.pathFile);
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		}
		if(ac.equals("Save File")) {
				int returnVal = giaoDien.fileChooser.showSaveDialog(this.giaoDien);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = giaoDien.fileChooser.getSelectedFile();
					save(file.getAbsolutePath());
				} 
			
		}
		
		if (ac.equals("Duyệt đồ thị")) {
			int start;
			String strStart = giaoDien.textFieldDinhChon.getText();
			if (!strStart.equals(""))
				start = Integer.valueOf(strStart);

			else
				start = -1;
			if (giaoDien.rdbtnDFS.isSelected()) {

				if (start >= 0) {
					JOptionPane.showMessageDialog(giaoDien,
							"Browse the graph by DFS: " +graph.DFSLinkkeList(start));
					String[] vertexLabels = new String[graph.soDinh];
					for (int i = 0; i < vertexLabels.length; i++) {
						vertexLabels[i] = ""+i;
					}
		     		GraphPanelDFSAndBFS graphPanel = new GraphPanelDFSAndBFS(graph.listReDFSOrBFS,graph.mtk, vertexLabels);
					giaoDien.hienThiDFSOrBFS(graphPanel);
				}else {
					JOptionPane.showMessageDialog(giaoDien,
							"Browse the graph by DFS: " + graph.DFSLinkkeList());
					String[] vertexLabels = new String[graph.soDinh];
					for (int i = 0; i < vertexLabels.length; i++) {
						vertexLabels[i] = ""+i;
					}
		     		GraphPanelDFSAndBFS graphPanel2 = new GraphPanelDFSAndBFS(graph.listReDFSOrBFS,graph.mtk, vertexLabels);
					giaoDien.hienThiDFSOrBFS(graphPanel2);
				}
			} else if (giaoDien.rdbtnBFS.isSelected()) {
				if (start >= 0) {
					JOptionPane.showMessageDialog(giaoDien,
							"Browse the graph by BFS: " + graph.BFSLinkedlist(start));
				String[] vertexLabels = new String[graph.soDinh];
				for (int i = 0; i < vertexLabels.length; i++) {
					vertexLabels[i] = ""+i;
				}
	     		GraphPanelDFSAndBFS graphPanel3 = new GraphPanelDFSAndBFS(graph.listReDFSOrBFS,graph.mtk, vertexLabels);
				giaoDien.hienThiDFSOrBFS(graphPanel3);
			}else {
					JOptionPane.showMessageDialog(giaoDien,
							"Browse the graph by BFS: " +graph.BFSLinkedlist());
					String[] vertexLabels = new String[graph.soDinh];
					for (int i = 0; i < vertexLabels.length; i++) {
						vertexLabels[i] = ""+i;
					}
		     		GraphPanelDFSAndBFS graphPanel4 = new GraphPanelDFSAndBFS(graph.listReDFSOrBFS,graph.mtk, vertexLabels);
					giaoDien.hienThiDFSOrBFS(graphPanel4);
			}
		}
		}
		if (ac.equals("Kiểm tra liên thông")) {
			JOptionPane.showMessageDialog(giaoDien, graph.checkConnect());
		}
		if(ac.equals("Thêm đỉnh")) {
			graph.addVertex();
			this.giaoDien.textAreaMatrix.setText(graph.printMatrix());
			String[] vertexLabels = new String[graph.soDinh];
			for (int i = 0; i < vertexLabels.length; i++) {
				vertexLabels[i] = ""+i;
			}
     		GraphPanel graphPanel = new GraphPanel(graph.mtk, vertexLabels);
			giaoDien.hienThi(graphPanel);
		}
		if(ac.equals("Xóa đỉnh")) {
			 try {
					 GridLayout gridLayout = new GridLayout(1,1);	
						JPanel jPanel = new JPanel(gridLayout);
						jPanel.add(giaoDien.lblDinhXoa);
						jPanel.add(giaoDien.textFieldDinhXoa);
			
				int option = JOptionPane.showConfirmDialog(null, jPanel, "Xóa Đỉnh", JOptionPane.OK_CANCEL_OPTION);
				int vex = Integer.parseInt(giaoDien.textFieldDinhXoa.getText());
				if (option == JOptionPane.OK_OPTION) {
					if (vex <graph.soDinh) {
						graph.removeVertex(vex);
						JOptionPane.showMessageDialog(giaoDien, "Xóa Đỉnh thành công ");
						this.giaoDien.textAreaMatrix.setText(graph.printMatrix());
						String[] vertexLabels = new String[graph.soDinh];
						for (int i = 0; i < vertexLabels.length; i++) {
							vertexLabels[i] = ""+i;
						}
			     		GraphPanel graphPanel = new GraphPanel(graph.mtk, vertexLabels);
						giaoDien.hienThi(graphPanel);
					} else {
						JOptionPane.showMessageDialog(giaoDien, "Đỉnh không tồn tại", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				} 
			} catch (Exception e2) {
				System.out.println("");
			}
		}
		
		
	}
	public void save(String fileName) {
		try {
			PrintWriter pw = new PrintWriter(fileName, "UTF-8");
			String data = graph.graphMaxtrix();
			pw.print(data);
			pw.flush();
			pw.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
	}

}
