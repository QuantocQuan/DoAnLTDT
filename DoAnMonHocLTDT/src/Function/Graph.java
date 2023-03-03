package Function;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Graph {
	public int soDinh;
	public int[][] mtk;
	public String path;
	// use path file to load matrix and vertex from file .txt  
	public Graph(String pathFile) throws NumberFormatException, IOException {
		loadGraph(pathFile);
	}
	
	public Graph(int[][] matrix) {
		
	}
	
	public Graph(int soDinh) {
		
	}
	
	public Graph(int soDinh , int[][] matrix) {
		
	}
	
	public void loadGraph(String pathFile) throws NumberFormatException, IOException {
		File file = new File(pathFile);
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		this.soDinh = Integer.valueOf(br.readLine());
		this.mtk = new int[soDinh][soDinh];
		int dong = 0;
		String line = "";
		while ((line = br.readLine()) != null) {
			String[] temp = line.split(" ");
			for (int i = 0; i < temp.length; i++) {
				mtk[dong][i] = Integer.valueOf(temp[i]);
			}
			dong++;
		}
	}
	// show matrix in JtextArea
	public String printMatrix() {
			StringBuilder str = new StringBuilder();
			for (int i = 0; i < soDinh; i++) {
				for (int j = 0; j < soDinh; j++) {
					str.append(mtk[i][j]).append(" ");
				}
				str.append("\n");
			}
				return str.toString();
			
	}
	/**
	 * i la dinh dau, j la dinh cuoi
	 * @param i vertex i
	 * @param j vertex j
	 * add edges connect i and j
	 */
	public void addEdges(int i , int j) {
		try {
			if (i <= soDinh && j <= soDinh) {
				// truong hop them khuyen
				if (i == j) {
					mtk[i][j] += 1;
				} else {
					mtk[i][j] += 1;
				}
			} 
		} catch (Exception e) {
			System.out.println("Vui long nhap lai");
		}
	}
	/**
	 * i la dinh dau, j la dinh cuoi
	 * @param i vertex i
	 * @param j vertex j
	 * remove edges connect i and j
	 */
	public void removeEdges(int i , int j) {
		try {
			if (i <= soDinh && j <= soDinh && mtk[i][j] > 0) {
				// truong hop xoa khuyen
				if (i == j) {
					mtk[i][j] -= 1;
				} else {
					mtk[i][j] -= 1;
				}
			} 
		} catch (Exception e) {
			System.out.println("Vui long nhap lai");
		}
	
	}
	/**
	 *  determine simple graph
	 * @return true if this is simple graph
	 * @return false if this is not simple graph
	 */
	
	public boolean checkValid() {
		return false;
	}
	/**
	 * determine undirected graph
	 * @return true if this is directed graph
	 * @return false if not
	 */
	public boolean checkUnGraph() {
		return false;
	}
	/**
	 * use DFS to scanning the graph
	 * @param startVertex
	 * @return
	 */
	public String DFSLinkkeList(int startVertex) {
		return null ;
	}
	/**
	 * use BFS to scanning the graph
	 * @param startVertex
	 * @return
	 */
	public String BFSLinkedlist(int startVertex) {
		return null ;
	}
	/**
	 * 
	 * @param startVex
	 * @return true if this is connect graph
	 */
	public boolean checkConnectGraph(int startVex) {
		return false ;
	}
	/**
	 * 
	 * @param startVex
	 * @return show how many connect element and all vertex in a connect element
	 */
	public String findElementConnect(int startVex) {
		return null ;
	}
	
}
